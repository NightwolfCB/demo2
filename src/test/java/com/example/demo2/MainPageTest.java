package com.example.demo2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        String input = "Selenium";
        // Searching for input field
        WebElement searchField = driver.findElement(By.cssSelector("#sb_form_q"));
        // Setting the text input defined earlier
        searchField.sendKeys(input);
        searchField.submit();
        // Setting explicit waiting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        // Waiting until our element could be clicked
        wait.until(ExpectedConditions.and(
                ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(":not(.b_adurl) > cite"), "selenium"),
                ExpectedConditions.elementToBeClickable(By.cssSelector(":not(.b_adurl) > cite"))
        ));
        // We need to find non-advertisement elements with <cite> children in them
        List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElements(
                driver.findElements(By.cssSelector(":not(.b_adurl) > cite"))
        ));
        // Clicking the first element of a list
        clickElement(results, 0);
        // After click we should get the right webpage
        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "Correct link follow failed!");
    }

    public void clickElement(List<WebElement> results, int id) {
        // Clicking the element with defined id
        results.get(id).click();
        // First element in a list would have id equals 0
        System.out.println("Clicking link number " + (id + 1) + " - " + driver.getCurrentUrl());
    }
}