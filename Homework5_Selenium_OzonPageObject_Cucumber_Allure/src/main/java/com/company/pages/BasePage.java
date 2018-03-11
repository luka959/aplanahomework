package com.company.pages;

import com.company.steps.BaseSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    WebDriver driver = BaseSteps.getDriver();

    public BasePage() {
        PageFactory.initElements(driver, this);
    }
}
