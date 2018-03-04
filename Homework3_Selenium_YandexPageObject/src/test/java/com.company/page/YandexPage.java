package com.company.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexPage extends BasePage {
    @FindBy(xpath = "//a[@data-id='market']")
    private WebElement marketButton;

    public YandexPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainMarketPage gotoMarket() {
        marketButton.click();
        return new MainMarketPage(driver);
    }
}
