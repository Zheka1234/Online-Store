package com.boss.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context Is UP");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context Is DOWN");
    }
}
