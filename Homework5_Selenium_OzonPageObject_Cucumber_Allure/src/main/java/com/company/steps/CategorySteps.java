package com.company.steps;

import com.company.pages.CategoryPage;
import ru.yandex.qatools.allure.annotations.Step;

public class CategorySteps extends AbstractSteps {
    @Step("выбрана категория {0}")
    public void goToCategory(String categoryName) {
        new CategoryPage().goToCategory(categoryName);
    }
}
