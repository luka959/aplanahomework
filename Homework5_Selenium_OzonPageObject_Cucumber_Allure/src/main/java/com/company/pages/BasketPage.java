package com.company.pages;

import com.company.utils.PriceUtils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasketPage extends BasePage {
    @FindBy(xpath = "//div[@class='eCartItem_name']//span[@class='eCartItem_nameValue']")
    public WebElement productName;

    @FindBy(xpath = "//div[@class='bTotalPrices']//div[@class='bOzonPrice']/span[@class='eOzonPrice_main']")
    private WebElement priceRub;

    @FindBy(xpath = "//div[@class='bTotalPrices']//div[@class='bOzonPrice']/span[@class='eOzonPrice_submain']")
    private List<WebElement> priceCents;

    @FindBy(xpath = "//div[text()='Удалить всё']")
    public WebElement cleanBtn;

    @FindBy(xpath = "//span[contains(text(), 'Корзина') and contains(text(), 'пуста')]")
    private WebElement basketIsEmpty;

    public String getPrice() {
        return PriceUtils.getFullPrice(priceRub, priceCents);
    }

    public void waitUntilPageLoaded() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(priceRub));
    }

    public WebElement getBasketIsEmpty() {
        try {
            return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(basketIsEmpty));
        } catch (TimeoutException e) {
            return null;
        }
    }
}
