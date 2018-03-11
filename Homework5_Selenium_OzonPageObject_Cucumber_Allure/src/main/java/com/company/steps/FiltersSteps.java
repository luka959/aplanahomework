package com.company.steps;

import com.company.pages.FiltersPage;
import ru.yandex.qatools.allure.annotations.Step;

public class FiltersSteps extends AbstractSteps {
    @Step("выбран производитель {0}")
    public void selectManufacturer(String manufacturer) {
        new FiltersPage().selectManufacturer(manufacturer);
    }

    @Step("выбрана цена от {0}")
    public void minCost(int minCost) {
        new FiltersPage().minCost(minCost);
    }

    @Step("перешли на карточку первого товара")
    public void goToFirstProduct() {
        new FiltersPage().goToFirstProduct();
    }
}
