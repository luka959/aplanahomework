package com.company.steps;

import com.company.pages.ProductCard;
import org.junit.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public class ProductSteps {
    @Step("названию товара соответствует {0}")
    public void checkProductNameEqualsSaved(String expectedName) {
        String productTitle = new ProductCard().getProductTitle();
        Assert.assertEquals("Названия товара отличаются", expectedName, productTitle);
    }
}
