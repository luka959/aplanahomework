package com.company.steps;

import com.company.pages.MainYandexPage;
import ru.yandex.qatools.allure.annotations.Step;

public class MainYandexSteps {
    @Step("открыли Yandex Market")
    public void openYandexMarket() {
        MainYandexPage mainYandexPage = new MainYandexPage();
        mainYandexPage.marketButton.click();
    }
}
