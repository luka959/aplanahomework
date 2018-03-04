package com.company;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ScenarioYandex {
    private static final By XPATH_FOR_ELEMENTS_LIST = By.xpath(
            "//div[contains(concat(' ', @class, ' '), ' n-snippet-card2 ')]//div[@class='n-snippet-card2__title']//a"
    );
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        //1. Открыть браузер и развернуть на весь экран.
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //2. Зайти на yandex.ru.
        driver.get("http://www.yandex.ru/");
        //3. Перейти в яндекс маркет.
        driver.findElement(By.xpath("//a[@data-id='market']")).click();
        marketPreparation();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testMarketTV() {
        //4. Выбрать раздел Электроника.
        goToElectronics();

        //5. Выбрать раздел Телевизоры
        gotoCategory("Телевизоры");

        setListMode();
        set12PerPage();

        // 6. Зайти в расширенный поиск
        advancedSearch();

        //7. Задать параметр поиска от 20000 рублей.
        defineMinCost(20000);

        //8. Выбрать производителей Samsung и LG
        defineVendors("LG", "Samsung");

        //9. Нажать кнопку Применить.
        advancedSearchApply();

        //10. Проверить, что элементов на странице 12.
        List<WebElement> products = findProducts();
        Assert.assertEquals("Количество предложений отличается", 12, products.size());

        //11. Запомнить первый элемент в списке.
        String firstProductName = products.get(0).getText();

        //12. В поисковую строку ввести запомненное значение.
        searchProduct(firstProductName);

        //13. Найти и проверить, что наименование товара соответствует запомненному значению.
        String findProductName = getProductTitleOnProductCard();
        Assert.assertEquals("Наименования товаров не совпадают", firstProductName, findProductName);
    }

    @Test
    public void testMarketBeats() {
        //4. Выбрать раздел Электроника.
        goToElectronics();

        //5. Выбрать раздел Наушники
        gotoCategory("Наушники и Bluetooth-гарнитуры");

        setListMode();
        set12PerPage();

        // 6. Зайти в расширенный поиск
        advancedSearch();

        //7. Задать параметр поиска от 5000 рублей.
        defineMinCost(5000);

        //8. Выбрать производителя Beats
        defineVendors("Beats");

        //9. Нажать кнопку Применить.
        advancedSearchApply();

        //10. Проверить, что элементов на странице 12.
        List<WebElement> products = findProducts();
        Assert.assertEquals("Количество предложений отличается", 12, products.size());

        //11. Запомнить первый элемент в списке.
        String firstProductName = products.get(0).getText();

        //12. В поисковую строку ввести запомненное значение.
        searchProduct(firstProductName);

        //13. Найти и проверить, что наименование товара соответствует запомненному значению.
        String findProductName = getProductTitleOnProductCard();

        Assert.assertEquals("Наименования товаров не совпадают", firstProductName, findProductName);
    }

    private List<WebElement> findProducts() {
        return driver.findElements(XPATH_FOR_ELEMENTS_LIST);
    }

    //перевод списка элементов в режим "списка"
    private void setListMode() {
        WebElement checkElement = driver.findElement(
                By.xpath("//input[@class='radio-button__control' and @value='list']")
        );
        String checkedValue = checkElement.getAttribute("checked");
        if (checkedValue != null && checkedValue.equals("checked")) {
            return;
        }
        checkElement.click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(XPATH_FOR_ELEMENTS_LIST));
    }

    private void set12PerPage() {
        driver.findElement(By.xpath("//*[@class='select__control']/..")).click();
        driver.findElement(By.xpath("//span[@class='select__text' and text()='Показывать по 12']")).click();
        new WebDriverWait(driver, 15).until(
                ExpectedConditions.numberOfElementsToBeLessThan(
                        XPATH_FOR_ELEMENTS_LIST,
                        13
                )
        );
    }

    private void gotoCategory(String categoryName) {
        driver.findElement(
                By.xpath(
                        "//*[contains(concat(' ', @class, ' '), ' catalog-menu ')]" +
                                "//div[@class='catalog-menu__list']/a[text()='" + categoryName + "']"
                )
        ).click();
    }

    private void goToElectronics() {
        List<WebElement> departmentFirstVariant = driver.findElements(
                By.xpath("//*[@class='topmenu__list']//a[text()='Электроника']")
        );
        if (!departmentFirstVariant.isEmpty()) {
            departmentFirstVariant.get(0).click();
        } else {
            driver.findElement(By.xpath("//button/span[contains(text(),'Все категории')]/parent::button")).click();
            new WebDriverWait(driver, 15)
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(
                            "//div[@class='n-navigation-vertical-category']/a/span[text()='Электроника']/parent::a"
                    )))
                    .click();
        }
    }

    private void marketPreparation() {
        //если появляется поп-ап с подтверждением региона Москва
        List<WebElement> region = driver.findElements(
                By.xpath("//span[contains(@class, 'n-region-notification__ok')]")
        );
        if (!region.isEmpty()) {
            region.get(0).click();
        }
    }

    private String getProductTitleOnProductCard() {
        List<WebElement> titles = driver.findElements(By.xpath("//div[@class='n-title__text']/h1"));
        return titles.isEmpty() ? null : titles.get(0).getText();
    }

    private void searchProduct(String firstProductName) {
        WebElement element = driver.findElement(By.id("header-search"));
        element.clear();
        element.sendKeys(firstProductName);
        driver.findElement(By.xpath("//span[@class='search2__button']/button")).click();
        String findProductName = getProductTitleOnProductCard();
        if (findProductName == null) {
            findProducts().get(0).click();
        }
    }

    private void advancedSearchApply() {
        driver.findElement(By.xpath("//span[text()='Показать подходящие']/parent::a")).click();
    }

    private void defineVendors(String... vendors) {
        for (int i = 0; i < vendors.length; i++) {
            String vendor = vendors[i];
            By vendorCheckboxXPath = By.xpath(
                    "//label[@class='checkbox__label' and contains(@for, '7893318') and text()='" + vendor + "']"
            );
            List<WebElement> elements = driver.findElements(vendorCheckboxXPath);
            if (!elements.isEmpty()) {
                elements.get(0).click();
            }
        }
    }

    private void defineMinCost(int minCost) {
        driver.findElement(By.id("glf-pricefrom-var")).sendKeys(String.valueOf(minCost));
    }

    private void advancedSearch() {
        driver.findElement(By.xpath("//a[text()='Перейти ко всем фильтрам']")).click();
    }
}
