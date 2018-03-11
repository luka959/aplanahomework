package com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainOzonPage extends BasePage {
    @FindBy(className = "eDYbanner_close")
    public WebElement closeGiftWindowBtn;

    @FindBy(className = "bHeaderCategoryLinks ")
    public WebElement departmentsContainer;

    public void goToDepartment(String departmentName) {
        departmentsContainer
                .findElement(By.xpath(".//a[text()='" + departmentName + "']"))
                .click();
    }
}
