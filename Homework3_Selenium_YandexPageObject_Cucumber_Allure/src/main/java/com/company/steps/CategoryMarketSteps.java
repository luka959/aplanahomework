package com.company.steps;

import com.company.pages.CategoryMarketPage;
import com.company.pages.ProductCard;
import org.junit.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public class CategoryMarketSteps {
    @Step("проверяем, является ли выбранным способом отображения \"список\"")
    public boolean isSelectListViewModel() {
        return new CategoryMarketPage().isListViewMode();
    }

    @Step("установлен способом отображения \"список\"")
    public void setListViewModel() {
        new CategoryMarketPage().setListViewMode();
    }

    @Step("отображается по {0} элементов")
    public void selectElementsPerPage(int elementsPerPage) {
        new CategoryMarketPage().setElementsPerPage(elementsPerPage);
    }

    @Step("открыт расширенный поиск")
    public void gotoAdvancedSearch() {
        new CategoryMarketPage().gotoAdvancedSearch();
    }

    @Step("количество предложений на странице равно {0}")
    public void checkProductsCount(int expectedProductsCount) {
        int productsCount = new CategoryMarketPage().getProductsCount();
        Assert.assertEquals("Количество предложений отличается", expectedProductsCount, productsCount);
    }

    @Step("получено название первого продукта")
    public String getFirstProduct() {
        return new CategoryMarketPage().getFirstProductName();
    }

    @Step("в поисковую строку введено {0}")
    public void enterToSearch(String variable) {
        new CategoryMarketPage().enterToSearch(variable);
    }

    @Step("нажата кнопка Найти")
    public void applySearch() {
        new CategoryMarketPage().applySearch();
    }

    @Step("проверили находимся ли мы на странице продукта")
    public boolean isProductPage() {
        return new ProductCard().isCorrect();
    }

    @Step("перешли на первую карточку продукта")
    public void goToFirstResult() {
        new CategoryMarketPage().getFirstProduct().click();
    }
}
