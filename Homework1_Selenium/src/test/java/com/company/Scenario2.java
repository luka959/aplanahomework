package com.company;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Scenario2 {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //1. Перейти на страницу http://www.sberbank.ru/ru/person.
        driver.get("http://www.sberbank.ru/ru/person");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test2() {
        //2. Нажать на кнопку выбора региона.
        driver.findElement(By.className("region-list__toggler")).click();

        //3. В появившемся "окне" при помощи поиска найти и выбрать Нижегородская область.
        Wait<WebDriver> wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//input[@placeholder='Введите название региона']")));
        driver.findElement(By.xpath(".//input[@placeholder='Введите название региона']")).sendKeys("Нижегородская область");
        driver.findElement(By.className("kit-autocomplete-input__option")).click();

        //4. Проверить, что на главной странице отображается выбранная область.
        Assert.assertEquals("Нижегородская область", driver.findElement(By.className("region-list__name")).getText());

        //5. Сделать скролл до footer объекта на главной странице.
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", driver.findElement(By.xpath(".//div[contains(@class, 'bp-area footer-white')]")));

        //6. Проверить, что footer содержит значки социальных сетей.
        WebElement footer = driver.findElement(By.xpath(".//div[contains(@class, 'bp-area footer-white')]"));

        Assert.assertFalse(
                "Не найден social__icon_type_fb",
                footer.findElements(By.className("social__icon_type_fb")).isEmpty()
        );
        Assert.assertFalse(
                "Не найден social__icon_type_tw",
                footer.findElements(By.className("social__icon_type_tw")).isEmpty()
        );
        Assert.assertFalse(
                "Не найден social__icon_type_yt",
                footer.findElements(By.className("social__icon_type_yt")).isEmpty()
        );
        Assert.assertFalse(
                "Не найден social__icon_type_ins",
                footer.findElements(By.className("social__icon_type_ins")).isEmpty()
        );
        Assert.assertFalse(
                "Не найден social__icon_type_vk",
                footer.findElements(By.className("social__icon_type_vk")).isEmpty()
        );
        Assert.assertFalse(
                "Не найден social__icon_type_ok",
                footer.findElements(By.className("social__icon_type_ok")).isEmpty()
        );
    }
}
