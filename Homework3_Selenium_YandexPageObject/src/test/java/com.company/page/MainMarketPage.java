package com.company.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class MainMarketPage extends BasePage {
    @FindBy(className = "topmenu__list")
    private List<WebElement> departmentsContainer;

    @FindBy(xpath = "//button/span[contains(text(),'Все категории')]/parent::button")
    private WebElement allDepartment;

    public MainMarketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        prepareMarketPage(driver);
    }

    public DepartmentMarketPage gotoDepartment(String departmentName) {
        List<WebElement> departmentsList =
                departmentsContainer.isEmpty() ?
                        Collections.emptyList() :
                        departmentsContainer.get(0).findElements(
                                By.xpath(".//a[text()='" + departmentName + "']")
                        );
        if (!departmentsList.isEmpty()) {
            departmentsList.get(0).click();
        } else {
            By departmentXPath = By.xpath(
                    "//div[@class='n-navigation-vertical-category']/a/span[text()='" + departmentName + "']/parent::a"
            );
            allDepartment.click();
            new WebDriverWait(driver, 15)
                    .until(ExpectedConditions.elementToBeClickable(departmentXPath))
                    .click();
        }
        return new DepartmentMarketPage(driver);
    }

    private static void prepareMarketPage(WebDriver driver) {
        List<WebElement> region = driver.findElements(
                By.xpath("//span[contains(@class, 'n-region-notification__ok')]")
        );
        if (!region.isEmpty()) {
            region.get(0).click();
        }
    }
}
