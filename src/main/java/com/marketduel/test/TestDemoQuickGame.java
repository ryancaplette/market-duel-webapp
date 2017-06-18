package com.marketduel.test;

import com.marketduel.game.Match;
import com.marketduel.game.Portfolio;
import com.marketduel.game.QuickGame;
import com.marketduel.game.StockHolding;
import com.marketduel.util.stockdata.MarketDataFeed;
import com.marketduel.util.stockdata.StockPriceData;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDemoQuickGame {
    @Test
    public void testDemo() {
        /*
            I have created an example of how game logic might work as far as the backend logic is concerned

            I believe this will help get everyone onto the same page about how things will work

            By no means is my example the best solution to tie everything together so please modify as you see fit
         */

        System.out.println("Start Demo of Quick Game");

        try {

            //First setup a quickgame match (I set the date in the future so that players can be added to the game)
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-12");
            QuickGame game = new QuickGame(2, date, 2, 10000);
            game.setGameId(203);
            Match match = game.getMatch();

            //Each game consists of matches, but a quickgame only consists of 1 match.. lets add players to it
            match.setMatchID(1);
            match.addPlayer(1);
            match.addPlayer(2);

            //some minipulation to get things working for test purposes
            date = new SimpleDateFormat("yyyy-MM-dd").parse("2017-06-12");
            match.setStartDate(date); //nessesary for testing.. minipulating the start time of match so that the match can begin for testing purposes

            //now that a game and match have been setup and started each player will create a stock portfollio
            Portfolio player1Portfolio = new Portfolio();
            Portfolio player2Portfolio = new Portfolio();

            //mock players buying shares on monday 2017-06-12
            player1Portfolio.addHolding(new StockHolding("FB", 2, 146.77f));
            player2Portfolio.addHolding(new StockHolding("AAPL", 2, 143.72f));

            //mock players buying shares on tuesday 2017-06-13
            player1Portfolio.addHolding(new StockHolding("GOOG", 1, 954.80f));
            player2Portfolio.addHolding(new StockHolding("AMZN", 1, 974.72f));

            /*
                The quickgame was setup for a 2 day duration.
                So the match has ended at the end of trading day tuesday 2017-06-13

                Now the logic for determining a stock winner can take place which I have not worked out,
                but here is how the api could be called for helping get the stock prices to determine this
             */

            MarketDataFeed dataFeed = new MarketDataFeed(); //initiate a data feed (this object is a facade for the intrinio api

            //get a date object for the date that the match has ended for
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date requestedDate = dateFormat.parse("2017-06-13");

            //pull the closing price of each stock from the portfollios (this might be implimented by the portfollios class or match class
            StockPriceData stockPriceData = dataFeed.requestStockPriceDataForDate("AAPL", requestedDate);
            System.out.println("AAPL end of match value is: " + String.valueOf(stockPriceData.getClose()));
            stockPriceData = dataFeed.requestStockPriceDataForDate("FB", requestedDate);
            System.out.println(stockPriceData.getTickerSymbol() + "'s end of match value is: " + String.valueOf(stockPriceData.getClose()));
            stockPriceData = dataFeed.requestStockPriceDataForDate("AMZN", requestedDate);
            System.out.println(stockPriceData.getTickerSymbol() + "'s end of match value is: " + String.valueOf(stockPriceData.getClose()));
            stockPriceData = dataFeed.requestStockPriceDataForDate("GOOG", requestedDate);
            System.out.println(stockPriceData.getTickerSymbol() + "'s end of match value is: " + String.valueOf(stockPriceData.getClose()));

            //logic for doing the math and determining which portfollio is valued more
            //winner & player rank is determined

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e);
        }

        System.out.println("Quick Game Demo Complete");
    }
}
