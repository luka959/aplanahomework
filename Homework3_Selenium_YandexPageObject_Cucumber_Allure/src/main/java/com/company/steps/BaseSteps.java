package com.company.steps;

import com.company.utils.TestProperties;
import com.google.common.base.Strings;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Properties;


public class BaseSteps {
    public static final Properties properties = TestProperties.getInstance().getProperties();
    protected static WebDriver driver;
    private static HashMap<String, Object> variables = new HashMap<>();

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp() {
        String browser = Strings.nullToEmpty(properties.getProperty("browser"));
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
        }
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(properties.getProperty("app.url"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public static <T> T getVariable(String key) {
        return (T) variables.get(key);
    }

    public static <T> void setVariable(String key, T value) {
        variables.put(key, value);
    }
}
