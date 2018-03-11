package com.company.utils;

import org.openqa.selenium.WebElement;

import java.util.List;

public class PriceUtils {
    public static String getFullPrice(WebElement mainPart, List<WebElement> subPart) {
        return subPart.isEmpty() ? mainPart.getText() : (mainPart.getText() + "," + subPart.get(0).getText());
    }
}
