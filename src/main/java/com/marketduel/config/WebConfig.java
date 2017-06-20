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

import com.marketduel.game.Game;
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
				map.put("isJoinableGamesList", true);
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
				service.addPlayerToGame(Integer.parseInt(req.queryParams("join-game")), player.getPlayerId());
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
				map.put("isJoinableGamesList", true);
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
