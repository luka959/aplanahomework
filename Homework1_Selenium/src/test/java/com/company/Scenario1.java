package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class Scenario1 {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //1. Перейти по ссылке http://www.rgs.ru
        driver.get("https://www.rgs.ru/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test1() throws InterruptedException {
        WebElement navbar = driver.findElement(By.xpath(".//div[contains(@id, 'main-navbar')]"));

        //2. Выбрать пункт меню - Страхование
        navbar.findElement(By.xpath(".//a[contains(text(), 'Страхование')]")).click();

        //3. Выбрать категорию - ДМС
        navbar.findElement(By.xpath(".//a[contains(text(), 'ДМС')]")).click();

        //4. Проверить наличие заголовка - Добровольное медицинское страхование
        assertEquals("Заголовок ДМС не соответствует ожидаемому", "ДМС 2018 \uD83D\uDE91 Добровольное медицинское страхование, рассчитать стоимость в Росгосстрахе", driver.getTitle());

        //5. Нажать на кнопку - Отправить заявку
        driver.findElement(By.xpath(".//a[contains(text(),'Отправить заявку')]")).click();

        //6. Проверить, что открылась страница , на которой присутствует текст - Заявка на добровольное медицинское страхование
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//*[@class='modal-title']/b"))));
        assertEquals("У модального окна неправильный заголовок h4", "Заявка на добровольное медицинское страхование",
                driver.findElement(By.xpath(".//*[@class='modal-title']/b")).getText());

        //7.Заполнить поля
        driver.findElement(By.xpath(".//label[text()='Фамилия']/following-sibling::input")).sendKeys("Петров");
        driver.findElement(By.xpath(".//label[text()='Имя']/following-sibling::input")).sendKeys("Иван");
        driver.findElement(By.xpath(".//label[text()='Отчество']/following-sibling::input")).sendKeys("Сергеевич");

        WebElement element = driver.findElement(By.name("Region"));
        Select select = new Select(element);
        select.selectByVisibleText("Тверская область");

        Thread.sleep(1000);
        driver.findElement(By.xpath(".//label[text()='Телефон']/following-sibling::input")).sendKeys("9563331122");
        driver.findElement(By.xpath(".//label[text()='Эл. почта']/following-sibling::input")).sendKeys("qwertyqwerty");
        driver.findElement(By.xpath(".//label[text()='Комментарии']/following-sibling::textarea")).sendKeys("Комментарий");
        driver.findElement(By.cssSelector("input.checkbox")).click();

        //8. Проверить, что все поля заполнены введенными значениями
        assertEquals("Петров", driver.findElement(By.xpath(".//label[text()='Фамилия']/following-sibling::input")).getAttribute("value"));
        assertEquals("Иван", driver.findElement(By.xpath(".//label[text()='Имя']/following-sibling::input")).getAttribute("value"));
        assertEquals("Сергеевич", driver.findElement(By.xpath(".//label[text()='Отчество']/following-sibling::input")).getAttribute("value"));
        assertEquals("Тверская область", new Select(driver.findElement(By.name("Region"))).getFirstSelectedOption().getText());
        assertEquals("+7 (956) 333-11-22", driver.findElement(By.xpath(".//label[text()='Телефон']/following-sibling::input")).getAttribute("value"));
        assertEquals("qwertyqwerty", driver.findElement(By.xpath(".//label[text()='Эл. почта']/following-sibling::input")).getAttribute("value"));
        assertEquals("Комментарий", driver.findElement(By.xpath(".//label[text()='Комментарии']/following-sibling::textarea")).getAttribute("value"));

        //9. Нажать Отправить
        driver.findElement(By.id("button-m")).click();

        //10. Проверить, что у Поля - Эл. почта присутствует сообщение об ошибке - Введите корректный email
        assertEquals("Введите адрес электронной почты", driver.findElement(By.xpath(".//label[text()='Эл. почта']/following-sibling::input/..//span[@class='validation-error-text']")).getText());
    }
}
