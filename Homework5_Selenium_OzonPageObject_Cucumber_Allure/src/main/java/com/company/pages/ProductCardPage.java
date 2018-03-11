package com.company.pages;

import com.company.utils.PriceUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductCardPage extends BasePage {
    @FindBy(xpath = "//div[text()='Добавить в корзину']")
    public WebElement inBasketBtn;
    @FindBy(xpath = "//div[@class='eHeader_MiddleRow']//a[@href='/context/cart']")
    public WebElement goToBasketBtn;
    @FindBy(xpath = "//h1[@class='bItemName']")
    public WebElement title;

    @FindBy(xpath = "//div[@class='bSaleBlock']//div[@class='bOzonPrice']/span[@class='eOzonPrice_main']")
    private WebElement priceRub;
    @FindBy(xpath = "//div[@class='bSaleBlock']//div[@class='bOzonPrice']/span[@class='eOzonPrice_submain']")
    private List<WebElement> priceCents;

    public String getPrice() {
        return PriceUtils.getFullPrice(priceRub, priceCents);
    }
}
