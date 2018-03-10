package com.company.utils;

import com.company.steps.BaseSteps;
import gherkin.formatter.model.Result;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.MakeAttachmentEvent;

/**
 * Created by 777 on 08.05.2017.
 */
public class AllureReporter extends ru.yandex.qatools.allure.cucumberjvm.AllureReporter {
    @Override
    public void result(Result result) {
        if ("failed".equals(result.getStatus())) takeScreenshot(result);
        super.result(result);
    }

    public void takeScreenshot(Result step) {
        WebDriver driver = BaseSteps.getDriver();
        if (driver != null) {
            Allure.LIFECYCLE.fire(
                    new MakeAttachmentEvent(
                            ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES),
                            "Скриншот в момент ошибки", "image/png"
                    )
            );
        }
    }
}
