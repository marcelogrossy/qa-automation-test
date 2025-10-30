package com.outsera.mobile.hooks;

import com.outsera.mobile.commons.AppiumServerManager;
import com.outsera.mobile.driver.MobileDriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class AppiumHooks {

    @Before("@mobile")
    public void startAppiumServer() {
//        AppiumServerManager.startServer();
    }

    @After("@mobile")
    public void stopAppiumServer() {
//        MobileDriverFactory.quitDriver();
//        AppiumServerManager.stopServer();
    }
}
