package com.outsera.mobile.hooks;

import com.outsera.mobile.driver.MobileDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class HooksMobile {

    @Before("@mobile")
    public void setUp() throws Exception {
        //MobileDriverFactory.initializeDriver();
    }

    @After("@mobile")
    public void tearDown() {
        //MobileDriverFactory.quitDriver();
    }
}
