package com.company;

import com.company.page.YandexPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

public class TestYandex {
    private Properties properties = TestProperties.getInstance().getProperties();
    private YandexPage yandexPage;

    public TestYandex() {
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(properties.getProperty("start_page"));
        yandexPage = new YandexPage(driver);
    }

    @After
    public void tearDown() {
        yandexPage.getDriver().quit();
    }

    @Test
    public void testMarketTV() {
        yandexPage
                .gotoMarket()
                .gotoDepartment("Электроника")
                .gotoCategory("Телевизоры")
                .setListMode()
                .set12PerPage()
                .gotoAdvancedSearch()
                .setMinCost(20000)
                .selectVendors("LG", "Samsung")
                .applySearch()
                .checkProductsCount(12)
                .findFirstProduct()
                .getFirstResult()
                .checkProductName();
    }

    @Test
    public void testMarketBeats() {
        yandexPage
                .gotoMarket()
                .gotoDepartment("Электроника")
                .gotoCategory("Наушники и Bluetooth-гарнитуры")
                .setListMode()
                .set12PerPage()
                .gotoAdvancedSearch()
                .setMinCost(5000)
                .selectVendors("Beats")
                .applySearch()
                .checkProductsCount(12)
                .findFirstProduct()
                .getFirstResult()
                .checkProductName();
    }
}
