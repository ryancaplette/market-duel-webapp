package com.marketduel.config;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.marketduel.game.*;
import com.marketduel.util.stockdata.StockPriceData;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import com.marketduel.model.LoginResult;
import com.marketduel.model.Player;
import com.marketduel.service.impl.MarketDuelService;
import com.marketduel.util.stockdata.MarketDataFeed;
import com.marketduel.util.stockdata.Stock;

import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;
import spark.utils.StringUtils;

public class WebConfig {
	
	private static final String USER_SESSION_ID = "user";
	private MarketDuelService service;

	public WebConfig(MarketDuelService service) {
		this.service = service;
		staticFileLocation("/");
		setupRoutes();
	}
	
	private void setupRoutes() {
		get("/", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Welcome to Market Duel! Please login or register to continue.");
			map.put("player", player);
			return new ModelAndView(map, "homescreen.ftl");
        }, new FreeMarkerEngine());
		before("/", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			if(player != null) {
				res.redirect("/account");
				halt();
			}
		});


		get("/login", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			if(req.queryParams("r") != null) {
				map.put("message", "You were successfully registered and can login now");
			}
			return new ModelAndView(map, "login.ftl");
        }, new FreeMarkerEngine());


		post("/login", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			Player player = new Player();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8");
				BeanUtils.populate(player, params);
			} catch (Exception e) {
				halt(501);
				return null;
			}
			LoginResult result = service.checkPlayer(player);
			if(result.getPlayer() != null) {
				addAuthenticatedPlayer(req, result.getPlayer());
				res.redirect("/");
				halt();
			} else {
				map.put("error", result.getError());
			}
			map.put("username", player.getUsername());
			return new ModelAndView(map, "login.ftl");
        }, new FreeMarkerEngine());


		before("/login", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer != null) {
				res.redirect("/");
				halt();
			}
		});


		get("/register", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			return new ModelAndView(map, "register.ftl");
        }, new FreeMarkerEngine());


		post("/register", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			Player player = new Player();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8");
				BeanUtils.populate(player, params);
			} catch (Exception e) {
				halt(501);
				return null;
			}

			String error = player.validate();
			if(StringUtils.isEmpty(error)) {
				Player existingPlayer = service.getPlayerbyUsername(player.getUsername());
				if(existingPlayer == null) {
					System.out.println("Trying to add new player!");
					service.registerPlayer(player);
					res.redirect("/login?r=1");
					halt();
				} else {
					error = "The username is already taken";
				}
			}
			map.put("error", error);
			map.put("username", player.getUsername());
			map.put("email", player.getEmail());
			return new ModelAndView(map, "register.ftl");
        }, new FreeMarkerEngine());


		before("/register", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer != null) {
				res.redirect("/");
				halt();
			}
		});


		get("/logout", (req, res) -> {
			removeAuthenticatedPlayer(req);
			res.redirect("/");
			return null;
        });


		get("/account", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Account Dashboard");
			map.put("player", player);
			return new ModelAndView(map, "account.ftl");
		}, new FreeMarkerEngine());
		before("/account", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});

		get("/research", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Research");
			map.put("player", player);
			return new ModelAndView(map, "research.ftl");
		}, new FreeMarkerEngine());

		post("/research", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Research");
			map.put("player", player);

			if(req.queryParams("ticker") != null) {
				String tickerSymbol = req.queryParams("ticker").toUpperCase();
				map.put("pageTitle", "Research: " + tickerSymbol);
				MarketDataFeed dataFeed = new MarketDataFeed();
				Stock stock = dataFeed.requestStockByTickerSymbol(tickerSymbol);
				if (stock != null) {

					map.put("stockData", stock);
					map.put("message", "Woohoo! Stock data was found for \"" + tickerSymbol + "\"");

					StockPriceData stockPriceData = stock.getStockPriceToday();
					if (stockPriceData != null) {
						map.put("open", String.valueOf(stockPriceData.getOpen()));
						map.put("high", String.valueOf(stockPriceData.getHigh()));
						map.put("low", String.valueOf(stockPriceData.getLow()));
						map.put("close", String.valueOf(stockPriceData.getClose()));
						map.put("volume",new BigDecimal(stockPriceData.getVolume()).toPlainString());
					}

				} else {
					map.put("error", "Stock data could not be found for \"" + tickerSymbol + "\". Please try a different ticker symbol");
				}
			} else {
				map.put("error", "Please enter a stock ticker");
			}

			return new ModelAndView(map, "research.ftl");
		}, new FreeMarkerEngine());

		get("/match-detail", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Match Detail");
			map.put("player", player);

			int matchId = Integer.parseInt(req.queryParams("id"));
			Match match = service.getMatchById(matchId);
			if (match == null) {
				map.put("pageTitle", "Games");
				map.put("error", "Error: Match info not found");
				return new ModelAndView(map, "games.ftl");
			}

			//display match info and all [players in the match portfolios
			map.put("pageTitle", "Match Name: " + match.getMatchName());
			map.put("matchStart", "Match Start Date: " + match.getStartDate());
			map.put("matchEnd", "Match End Date: " + match.getEndDate());

			List<Portfolio> portfolios = service.getPortfoliosForMatchId(match.getMatchID());
			map.put("portfolios", portfolios);

			return new ModelAndView(map, "match-detail.ftl");
		}, new FreeMarkerEngine());
		before("/game-detail", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});

		get("/games", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Games");
			map.put("player", player);

			//get array list games player belongs too
			//get list of all games
			//subtract them
			//show drop down  list of games they can join by clicking button that form submits similar tot he register
			List<Game> playersGames = service.getGamesForPlayer(player);
			map.put("playerGamesList", playersGames);

			HashMap<Integer, Boolean> playerGameIdsDict = new HashMap<Integer, Boolean>();
			for(Game game : playersGames) {
				playerGameIdsDict.put(game.getGameId(), true);
			}

			List<Game> availableGames = service.getAvailableGames();
			ArrayList<Game> joinableGames = new ArrayList<>();
			for(Game game : availableGames) {
				if (!playerGameIdsDict.containsKey(game.getGameId())) {
					joinableGames.add(game);
					System.out.println("Joinable GameID: " + game.getGameId() );
				}
			}

			if (joinableGames.size() > 0) {
				map.put("joinableGamesList", joinableGames);
			}

			return new ModelAndView(map, "games.ftl");
		}, new FreeMarkerEngine());

		post("/games", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Games");
			map.put("player", player);

			if(req.queryParams("join-game") != null) {
				System.out.println(req.queryParams("join-game"));
				service.addPlayerToQuickGame(Integer.parseInt(req.queryParams("join-game")), player.getPlayerId());
			} else {
				System.out.println("fail");
			}

			//get array list games player belongs too
			//get list of all games
			//subtract them
			//show drop down  list of games they can join by clicking button that form submits similar tot he register
			List<Game> playersGames = service.getGamesForPlayer(player);
			map.put("playerGamesList", playersGames);

			HashMap<Integer, Boolean> playerGameIdsDict = new HashMap<Integer, Boolean>();
			for(Game game : playersGames) {
				playerGameIdsDict.put(game.getGameId(), true);
			}

			List<Game> availableGames = service.getAvailableGames();
			ArrayList<Game> joinableGames = new ArrayList<>();
			for(Game game : availableGames) {
				if (!playerGameIdsDict.containsKey(game.getGameId())) {
					joinableGames.add(game);
					System.out.println("Joinable GameID: " + game.getGameId() );
				}
			}

			if (joinableGames.size() > 0) {
				map.put("joinableGamesList", joinableGames);
			}

			return new ModelAndView(map, "games.ftl");
		}, new FreeMarkerEngine());
		
		before("/games", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});

		get("/game-detail", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Game Detail");
			map.put("player", player);

			int gameId = Integer.parseInt(req.queryParams("id"));
			Game game = service.getGameById(gameId);
			if (game == null) {
				map.put("pageTitle", "Games");
				map.put("error", "Error: Game not found");
				return new ModelAndView(map, "games.ftl");
			}

			if (game.getType() == Game.GameType.QUICK) { //if game is a quickmatch then we can just grab the first match id and redirect to the match view
				if (game.getMatchIds().size() <= 0) {
					map.put("pageTitle", "Games");
					map.put("error", "Error: Game match not found");
					return new ModelAndView(map, "games.ftl");
				}

				int matchId = game.getMatchIds().get(0);

				//redirect to match
				res.redirect("/match-detail?id=" + matchId);
				halt();
			}

			return new ModelAndView(map, "game-detail.ftl");
        }, new FreeMarkerEngine());
		before("/game-detail", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});

		get("/game-create", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Create Quick Match");
			map.put("player", player);
			return new ModelAndView(map, "game-create.ftl");
		}, new FreeMarkerEngine());
		before("/game-create", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});

		post("/game-create", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Create Quick Match");
			map.put("player", player);

			float budget = Float.parseFloat(req.queryParams("budget"));
			//String draft = req.queryParams("draft"); //need start and end of draft have to impliment this
			String start = req.queryParams("start");
			String matchName = req.queryParams("matchName");
			int duration = Integer.parseInt(req.queryParams("duration"));
			String draft = req.queryParams("draft");

			int gameType = Integer.parseInt(req.queryParams("gameType"));
			if (gameType == 0) { //single match quick game
				SimpleDateFormat dateFormat  =new SimpleDateFormat("yyyy-MM-dd");

				Date startDate = dateFormat.parse(start);
				
				SimpleDateFormat draftDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date draftStartDate = draftDateFormat.parse(draft);
				Date draftStartDateEST = new Date(draftStartDate.getTime() + TimeZone.getTimeZone("America/New_York").getRawOffset() + TimeZone.getTimeZone("America/New_York").getDSTSavings());
				TimeZone zone = TimeZone.getTimeZone("America/New_York");
				DateFormat format = DateFormat.getDateTimeInstance();
				format.setTimeZone(zone);

				//use duration of days to get end date
				Calendar c = Calendar.getInstance();
				c.setTime(dateFormat.parse(start));
				c.add(Calendar.DATE, duration);
				String end = dateFormat.format(c.getTime());  // dt is now the new date
				Date endDate = dateFormat.parse(end);

				Match match = new ClosedMatch();

				match.setMatchName(matchName);
				match.setStartDate(startDate);
				match.setEndDate(endDate);
				match.setDraftStartDate(draftStartDateEST);
				System.out.println("draftStartDate" + draftStartDate);
				System.out.println("draftStartDateEST" + draftStartDateEST);
				match.setDuration(duration);
				match.setInitialBalance(budget);
				match.setMatchType(Match.MatchType.Closed);

				match = service.createMatch(match);
				if (match == null) {
					map.put("error", "There was an error creating your game, please try again.");
					return new ModelAndView(map, "game-create.ftl");
				}

				Game game = new QuickGame();

				game.setContinuous(false);
				game.setFirstMatchStart(match.getStartDate());
				game.setMatchDurationInDays(duration);
				game.setType(Game.GameType.QUICK);
				game.addMatch(match.getMatchID());
				game.setGameName(matchName);

				game = service.createGame(game);
				if (game == null) {
					map.put("error", "There was an error creating your game, please try again.");
					return new ModelAndView(map, "game-create.ftl");
				}

				if(!service.updateMatchesForGame(game, game.getMatchIds())) {
					map.put("error", "There was an error creating your game, please try again.");
					return new ModelAndView(map, "game-create.ftl");
				}

				map.put("message", "Congrats! Your new game has been created. Go to the games tab to view it.");
			}

			return new ModelAndView(map, "game-create.ftl");
		}, new FreeMarkerEngine());
		before("/game-detail", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});

		get("/portfolios", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Portfolios");
			map.put("player", player);

			List<Portfolio> playerPortfolios = service.getPlayerPortfolios(player.getPlayerId());

			if (playerPortfolios != null) {
				map.put("portfolios", playerPortfolios);

				Map<String, String> matchNames = new HashMap<>();
				for (Portfolio p : playerPortfolios) {
					String matchName = service.getMatchById(p.getMatchId()).getMatchName();
					matchName = (matchName == null) ? "" : matchName;
					matchNames.put(Integer.toString(p.getMatchId()), matchName);
				}

				map.put("matchNames", matchNames);
			}

			return new ModelAndView(map, "portfolio.ftl");
        }, new FreeMarkerEngine());
		before("/portfolios", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});



		get("/portfolio-detail", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Portfolio Detail");
			map.put("player", player);

			//if id not given then this break need to handle..
			int pfId = Integer.parseInt(req.queryParams("id"));

			Portfolio portfolio = service.getPortfolioById(pfId);
			map.put("pfId", portfolio.getPortfolioId());
			map.put("stockHoldings", portfolio.getStockHoldings());
			map.put("balance", portfolio.getBalance());

			if (portfolio.getPlayerId() == player.getPlayerId()) {

				Match match = service.getMatchById(portfolio.getMatchId());

				if (match.isTradingActive()) {
					map.put("isTradingActive", true);
				}

				map.put("username", player.getUsername());

			} else {
				Player opponent = service.getPlayerById(portfolio.getPlayerId());
				if (opponent != null) {
					map.put("username", opponent.getUsername());
				}
			}

			return new ModelAndView(map, "portfolio-detail.ftl");
        }, new FreeMarkerEngine());
		before("/portfolio-detail", (req, res) -> {

			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});

		post("/stock-order", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Stock Order");
			map.put("player", player);
			

			int pfId = Integer.parseInt(req.queryParams("pfId"));
			float quantity = Float.valueOf(req.queryParams("quantity"));
			

			Portfolio portfolio = service.getPortfolioById(pfId);

			if (portfolio.getPlayerId() != player.getPlayerId()) {
				//only the owner of a portfolio should be allowed to make changes
				map.put("error", "You are not the owner of this portfolio.");
				return new ModelAndView(map, "portfolio-detail.ftl");
			}

			Match match = service.getMatchById(portfolio.getMatchId());
			map.put("pfId", portfolio.getPortfolioId());
			ArrayList<StockHolding> stockHoldings = portfolio.getStockHoldings();
			map.put("stockHoldings", stockHoldings);
			map.put("balance", portfolio.getBalance());

			String ticker = req.queryParams("ticker").toUpperCase();

			if (stockHoldings.size() >= Portfolio.MAX_NUM_HOLDINGS) {
				map.put("error", "Your portfolio is currently full. Please sell some stock and try again if you would like to add " + ticker + " to your portfolio.");
				return new ModelAndView(map, "portfolio-detail.ftl");
			}

			if (!match.isTradingActive())
			{
				map.put("error", "Trading is not currently active for the match that this portfolio belongs to.");
				return new ModelAndView(map, "portfolio-detail.ftl");
			} else {
				map.put("isTradingActive", true);
			}

			MarketDataFeed df = new MarketDataFeed(); //initiate a data feed (this object is a facade for the intrinio api
			StockPriceData stockPriceData = df.requestStockPriceDataLast(ticker);

			//add stock to portfolio if stock price can be found
			if (stockPriceData != null) {
				float lastPrice = (float) stockPriceData.getClose();
				if (lastPrice >= 0) {
					if (quantity*lastPrice > portfolio.getBalance()) {
						map.put("error", "Value of that many shares of " + ticker + " ($" + quantity*lastPrice + ") exceeds available portfolio balance ($" + portfolio.getBalance() + ").");
					}
					else {
						map.put("message", ticker + " has been successfully added to your portfolio.");
						StockHolding sh = new StockHolding(ticker, quantity, lastPrice);
						portfolio.addHoldingToPortfolio(sh);
						service.storeStockHoldingsInPortfolio(portfolio, portfolio.getStockHoldings());
						map.put("stockHoldings", portfolio.getStockHoldings()); //update since new holding was just added
						portfolio.updateBalance();
						map.put("balance", portfolio.getBalance());  //update since new holding was just added
					}
				}
			} else {
				map.put("error", "Stock price data could not be found for " + ticker + ". Please confirm your ticker symbol is correct and try again.");
			}

			map.put("name", player.getFirstName() + " " + player.getLastName());

			return new ModelAndView(map, "portfolio-detail.ftl");
		}, new FreeMarkerEngine());
		before("/portfolio-detail", (req, res) -> {

			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});

        get("/players", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Players");
			map.put("player", player);
			return new ModelAndView(map, "players.ftl");
        }, new FreeMarkerEngine());
		before("/players", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});



		get("/player-detail", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Player Detail");
			map.put("player", player);
			return new ModelAndView(map, "player-detail.ftl");
        }, new FreeMarkerEngine());
		before("/player-detail", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});
	




		get("/alerts", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Alerts");
			map.put("player", player);
			return new ModelAndView(map, "alerts.ftl");
        }, new FreeMarkerEngine());
		before("/alerts", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});




		get("/history", (req, res) -> {
			Player player = getAuthenticatedPlayer(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "History");
			map.put("player", player);
			return new ModelAndView(map, "history.ftl");
        }, new FreeMarkerEngine());
		before("/history", (req, res) -> {
			Player authPlayer = getAuthenticatedPlayer(req);
			if(authPlayer == null) {
				res.redirect("/");
				halt();
			}
		});
 




	}

	private void addAuthenticatedPlayer(Request request, Player p) {
		request.session().attribute(USER_SESSION_ID, p);
		
	}

	private void removeAuthenticatedPlayer(Request request) {
		request.session().removeAttribute(USER_SESSION_ID);
		
	}

	private Player getAuthenticatedPlayer(Request request) {
		return request.session().attribute(USER_SESSION_ID);
	}
}
