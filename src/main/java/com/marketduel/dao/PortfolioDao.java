package com.marketduel.dao;

import java.util.ArrayList;

import com.marketduel.game.Portfolio;
import com.marketduel.game.StockHolding;

public interface PortfolioDao {
	
	Boolean createPortfolio(Portfolio pf);
	
	Portfolio getNewestPortfolioId();

	Portfolio getPortfolioById(int pfId);

	Boolean storeStockHoldingsInPortfolio(Portfolio pf, ArrayList<StockHolding> shList); 
	
	Boolean deletePortfolioByID(int pfId);
}