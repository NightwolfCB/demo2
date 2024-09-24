package com.example.demo2.tests;

import com.example.demo2.pages.MainPage;
import com.example.demo2.pages.ResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingSearchTest {
    MainPage mp;
    ResultsPage rp;
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
        mp = new MainPage(driver);
        rp = new ResultsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchResultsTest() {
        String input = "Selenium";
        mp.sendText(input);
        rp.clickElement(0);
        rp.switchTab();
        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "Correct link follow failed!");
    }

    @Test
    public void searchFieldTest() {
        String input = "Selenium";
        mp.sendText(input);
        assertEquals(input, rp.getTextFromSearchField(), "Text didn't match!");
    }
}