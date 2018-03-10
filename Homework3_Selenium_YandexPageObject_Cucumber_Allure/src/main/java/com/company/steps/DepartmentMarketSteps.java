package com.company.steps;

import com.company.pages.DepartmentMarketPage;
import ru.yandex.qatools.allure.annotations.Step;

public class DepartmentMarketSteps {
    @Step("выбрана категория {0}")
    public void goToCategory(String categoryName) {
        new DepartmentMarketPage().goToCategory(categoryName);
    }
}
