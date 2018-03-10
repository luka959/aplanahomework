package com.company.steps;

import com.company.pages.AdvancedSearchMarketPage;
import ru.yandex.qatools.allure.annotations.Step;

public class AdvancedSearchMarketSteps {
    @Step("поле {0} заполняется значением {1}")
    public void fillField(String field, String value) {
        new AdvancedSearchMarketPage().fillField(field, value);
    }

    @Step("нажата кнопка Применить")
    public void applySearch() {
        new AdvancedSearchMarketPage().applySearch();
    }
}
