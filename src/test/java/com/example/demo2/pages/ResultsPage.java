package com.example.demo2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ResultsPage {
    private WebDriver driver;

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    @FindBy(css = ":not(.b_adurl) > cite")
    private WebElement nonAdv;

    @FindBy(css = "cite")
    private List<WebElement> results;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void switchTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1)
            driver.switchTo().window(tabs.get(1));
    }

    public void clickElement(int id) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.textToBePresentInElement(nonAdv, "selenium"),
                ExpectedConditions.elementToBeClickable(nonAdv)
        ));
        results.get(id).click();
        // First element in a list would have id equals 0
        System.out.println("Clicking link number " + (id + 1));
    }

    public String getTextFromSearchField() {
        String str = searchField.getAttribute("value");
        System.out.println("Text in search field: " + str);
        return str;
    }
}
