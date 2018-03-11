package com.company.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ScenarioSteps {
    private static final String FIRST_PRODUCT_NAME = "first_product_name";
    private static final String FIRST_PRODUCT_PRICE = "first_product_price";

    MainOzonSteps mainOzonSteps = new MainOzonSteps();
    CategorySteps categorySteps = new CategorySteps();
    FiltersSteps filtersSteps = new FiltersSteps();
    ProductCardSteps productCardSteps = new ProductCardSteps();
    BasketSteps basketSteps = new BasketSteps();

    @When("^выбран раздел (.+) на Ozon$")
    public void stepSelectDepartment(String department) throws InterruptedException {
        if (mainOzonSteps.hasGiftWindow()) {
            mainOzonSteps.closeGiftWindow();
        }
        mainOzonSteps.goToDepartment(department);
    }

    @When("^выбрана категория (.+)$")
    public void stepSelectCategory(String category) {
        categorySteps.goToCategory(category);
    }

    @When("^выбирается производитель (.+)$")
    public void stepSelectManufacturer(String manufacturer) {
        filtersSteps.selectManufacturer(manufacturer);
    }

    @When("^заполняется Цена от (.+)$")
    public void stepMinCost(int minCost) {
        filtersSteps.minCost(minCost);
    }

    @When("^запомним название и цену 1-ого товара$")
    public void stepSaveFirstProduct() {
        filtersSteps.goToFirstProduct();
        BaseSteps.setVariable(FIRST_PRODUCT_NAME, productCardSteps.getFirstProductName());
        BaseSteps.setVariable(FIRST_PRODUCT_PRICE, productCardSteps.getFirstProductPrice());
    }

    @When("^добавить 1-ый товар в корзину$")
    public void stepAddFirstProductInBasket() {
        productCardSteps.addProductInBasket();
    }

    @When("^перейти в корзину$")
    public void stepGoToBasket() {
        productCardSteps.goToBasket();
    }

    @Then("^проверить, что в корзине есть добавленный товар$")
    public void stepCheckAddedProduct() throws Throwable {
        basketSteps.checkProductName(BaseSteps.getVariable(FIRST_PRODUCT_NAME));
        basketSteps.checkProductCost(BaseSteps.getVariable(FIRST_PRODUCT_PRICE));
    }

    @When("^нажмём на Удалить всё$")
    public void stepCleanBasket() {
        basketSteps.clean();
    }

    @Then("^проверить, что корзина пуста$")
    public void stepCheckBasketIsEmpty() {
        basketSteps.checkBasketIsEmpty();
    }
}
