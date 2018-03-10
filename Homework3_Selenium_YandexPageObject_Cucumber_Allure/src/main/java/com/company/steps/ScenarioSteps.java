package com.company.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.function.Consumer;

public class ScenarioSteps {
    private static final String FIRST_PRODUCT_KEY = "first_product";
    MainYandexSteps mainYandexSteps = new MainYandexSteps();
    MainMarkerSteps mainMarkerSteps = new MainMarkerSteps();
    DepartmentMarketSteps departmentMarketSteps = new DepartmentMarketSteps();
    CategoryMarketSteps categoryMarketSteps = new CategoryMarketSteps();
    AdvancedSearchMarketSteps advancedSearchMarketSteps = new AdvancedSearchMarketSteps();
    ProductSteps productSteps = new ProductSteps();

    @When("^открыт Yandex Market$")
    public void openYandexMarket() {
        mainYandexSteps.openYandexMarket();
    }


    @When("^выбран раздел (.+)$")
    public void selectDepartment(String department) {
        if (mainMarkerSteps.hasRegionNotification()) {
            mainMarkerSteps.chooseRegion();
        }
        mainMarkerSteps.goToDepartment(department);
    }

    @When("^выбрана категория (.+)$")
    public void selectCategory(String category) {
        departmentMarketSteps.goToCategory(category);
    }

    @When("^выбран способ отображения \"список\"")
    public void selectViewModel() {
        if (!categoryMarketSteps.isSelectListViewModel()) {
            categoryMarketSteps.setListViewModel();
        }
    }

    @When("^отображается по (\\d+) эелементов$")
    public void selectElementsPerPage(int elementsCount) {
        categoryMarketSteps.selectElementsPerPage(elementsCount);
    }

    @When("^открыт расширенный поиск$")
    public void goToAdvancedSearch() {
        categoryMarketSteps.gotoAdvancedSearch();
    }

    @When("^заполняются поля:$")
    public void fillFieldsInAdvancedSearch(DataTable fields) {
        fields.raw().forEach(raw -> advancedSearchMarketSteps.fillField(raw.get(0), raw.get(1)));
    }

    @When("^нажата кнопка Применить$")
    public void applyAdvancedSearch() {
        advancedSearchMarketSteps.applySearch();
    }

    @Then("^количество элементов на странице (\\d+)$")
    public void checkProductsCount(int productsCount) {
        categoryMarketSteps.checkProductsCount(productsCount);
    }

    @When("^запомним первый элемент на странице$")
    public void saveFirstProduct() {
        BaseSteps.setVariable(FIRST_PRODUCT_KEY, categoryMarketSteps.getFirstProduct());
    }

    @When("^введём в поисковую строку запомненное значение$")
    public void enterSavedProduct() {
        categoryMarketSteps.enterToSearch(BaseSteps.getVariable(FIRST_PRODUCT_KEY));
    }

    @When("^нажата кнопка Найти$")
    public void applyMainSearch() {
        categoryMarketSteps.applySearch();
    }

    @When("^возьмём первый продукт$")
    public void getFirstProduct() {
        if (!categoryMarketSteps.isProductPage()) {
            categoryMarketSteps.goToFirstResult();
        }
    }

    @Then("^проверим, что наименование товара соответствует запомненному значению$")
    public void checkProductNameEqualsSaved() {
        productSteps.checkProductNameEqualsSaved(BaseSteps.getVariable(FIRST_PRODUCT_KEY));
    }
}
