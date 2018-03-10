package com.company.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainYandexPage extends BasePage {
    @FindBy(xpath = "//a[@data-id='market']")
    public WebElement marketButton;
}
