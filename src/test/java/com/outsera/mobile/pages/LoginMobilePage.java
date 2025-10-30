package com.outsera.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginMobilePage {

    private AndroidDriver driver;

    public LoginMobilePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void openSite(String url) {
        driver.get(url);
    }

    public void openSignInMenu() {
        driver.findElement(By.linkText("Sign in")).click();
    }

    public void login(String usuario, String senha) {
        driver.findElement(By.id("email")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(senha);
        driver.findElement(By.id("loginButton")).click();
    }

    public boolean isOnMyAccountPage() {
        return driver.findElement(By.xpath("//h1[contains(text(),'My account')]")).isDisplayed();
    }
}
