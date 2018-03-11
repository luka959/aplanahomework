package com.company.steps;

import com.company.pages.MainOzonPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

public class MainOzonSteps extends AbstractSteps {
    @Step("проверяем, отображается ли окно с подарком")
    public boolean hasGiftWindow() {
        try {
            new WebDriverWait(BaseSteps.driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(new MainOzonPage().closeGiftWindowBtn));
            return true;
        } catch (TimeoutException ignore) {
            return false;
        }
    }

    @Step("закрываем окно с подарком")
    public void closeGiftWindow() throws InterruptedException {
        new MainOzonPage().closeGiftWindowBtn.click();
    }

    @Step("выбрали раздел {0}")
    public void goToDepartment(String departmentName) {
        new MainOzonPage().goToDepartment(departmentName);
    }
}
