import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Scenario2 {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
        driver.findElement(By.className("region-list__arrow")).click();

        //3. В появившемся "окне" при помощи поиска найти и выбрать Нижегородская область.
        Wait<WebDriver> wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[7]/div/div[2]/div[2]/div/div[1]/div/div[1]/input")));
        driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div[2]/div/div[1]/div/div[1]/input")).sendKeys("Нижегородская область");
        driver.findElement(By.className("kit-autocomplete-input__option")).click();


        //4. Проверить, что на главной странице отображается выбранная область.
        Assert.assertEquals("Нижегородская область", driver.findElement(By.className("region-list__name")).getText());

        //5. Сделать скролл до footer объекта на главной странице.
        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/table/tbody/tr/td/div/div/div/div/div/div[3]/div/div[2]/div/div/div[3]/div/div/div")));

        //6. Проверить, что footer содержит значки социальных сетей.
        Assert.assertNotNull(driver.findElement(By.className("social__icon_type_fb")).isDisplayed());
        Assert.assertNotNull(driver.findElement(By.className("social__icon_type_tw")).isDisplayed());
        Assert.assertNotNull(driver.findElement(By.className("social__icon_type_yt")).isDisplayed());
        Assert.assertNotNull(driver.findElement(By.className("social__icon_type_vk")).isDisplayed());
        Assert.assertNotNull(driver.findElement(By.className("social__icon_type_ok")).isDisplayed());
    }

}
