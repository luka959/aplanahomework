package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class FiltersPage extends BasePage {
    @FindBy(xpath = "//*[@class='eFromToInput_InputField mFrom']")
    private WebElement minCostField;

    @FindBy(xpath = "//div[@id='price_filter']//div[@class='bFlatButton mMicro mWhite']")
    private WebElement applyBtn;

    @FindBy(xpath = "//div[contains(@class, 'bOneTile inline jsUpdateLink mRuble')]")
    private WebElement firstProduct;

    public void selectManufacturer(String manufacturer) {
        driver.findElement(
                By.xpath(
                        "//div[@id='facetControl_brand']" +
                                "//*[@class='eFilterList_OptionLink' and contains(text(),'" + manufacturer + "')]"
                )
        ).click();
    }

    public void minCost(int minCost) {
        minCostField.clear();
        minCostField.sendKeys(String.valueOf(minCost));
        new Actions(driver).sendKeys(minCostField, Keys.TAB).perform();
        applyBtn.click();
    }


    public void goToFirstProduct() {
        firstProduct.click();
    }
}
