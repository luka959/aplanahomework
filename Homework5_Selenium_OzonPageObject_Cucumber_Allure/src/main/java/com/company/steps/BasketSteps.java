package com.company.steps;

import com.company.pages.BasketPage;
import com.company.pages.ProductCardPage;
import org.junit.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public class BasketSteps extends AbstractSteps {
    @Step("добавили продукт в корзину")
    public void addProductInBasket() {
        new ProductCardPage().inBasketBtn.click();
    }

    @Step("перешли в корзину")
    public void goToBasket() {
        new ProductCardPage().goToBasketBtn.click();
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

    @Step("название продукта в корзине равно {0}")
    public void checkProductName(String expectedValue) {
        String actualValue = new BasketPage().productName.getText();
        log("название продукта в корзине", actualValue);
        Assert.assertEquals("Название продукта отличается", expectedValue, actualValue);
    }

    @Step("цена продукта в корзине равна {0}")
    public void checkProductCost(String expectedValue) {
        String value = new BasketPage().getPrice();
        log("цена продукта в корзине", value);
        Assert.assertEquals("Цена продукта отличается", expectedValue, value);
    }

    @Step("нажато \"Удалить всё\"")
    public void clean() {
        new BasketPage().cleanBtn.click();
    }

    @Step("корзина пуста")
    public void checkBasketIsEmpty() {
        Assert.assertNotNull("Корзина не пуста", new BasketPage().getBasketIsEmpty());
    }
}
