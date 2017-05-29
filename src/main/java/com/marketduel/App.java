package com.marketduel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.marketduel.config.WebConfig;
import com.marketduel.service.impl.MarketDuelService;

@Configuration
@ComponentScan({ "com.marketduel" })
public class App {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
    	new WebConfig(ctx.getBean(MarketDuelService.class));
        ctx.registerShutdownHook();
    }
    
    
}
