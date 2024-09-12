package com.example.demo2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsPage {
    private WebDriver driver;

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    @FindBy(css = ":not(.b_adurl) > cite")
    private List <WebElement> results;

    public void clickElement(int id) {
        results.get(id).click();
        // First element in a list would have id equals 0
        System.out.println("Clicking link number " + (id + 1));
    }

    public String getTextFromSearchField() {
        String str = searchField.getAttribute("value");
        System.out.println("Text in search field: " + str);
        return str;
    }

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
