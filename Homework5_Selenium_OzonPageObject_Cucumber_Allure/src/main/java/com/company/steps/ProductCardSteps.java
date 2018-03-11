package com.company.steps;

import com.company.pages.BasketPage;
import com.company.pages.ProductCardPage;
import ru.yandex.qatools.allure.annotations.Step;

public class ProductCardSteps extends AbstractSteps {
    @Step("добавили продукт в корзину")
    public void addProductInBasket() {
        new ProductCardPage().inBasketBtn.click();
    }

    @Step("перешли в корзину")
    public void goToBasket() {
        new ProductCardPage().goToBasketBtn.click();
        waitUntilBasketLoaded();
    }

    @Step("корзина загружена")
    private void waitUntilBasketLoaded() {
        new BasketPage().waitUntilPageLoaded();
    }

    @Step("получено название первого продукта")
    public String getFirstProductName() {
        String value = new ProductCardPage().title.getText();
        log("название первого продукта", value);
        return value;
    }

    @Step("получена цена первого продукта")
    public String getFirstProductPrice() {
        String value = new ProductCardPage().getPrice();
        log("цена первого продукта", value);
        return value;
    }
}
