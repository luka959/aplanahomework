package com.company.steps;

import com.company.pages.MainMarketPage;
import ru.yandex.qatools.allure.annotations.Step;

public class MainMarkerSteps {
    @Step("проверяем, отображается ли регион-нотификация")
    public boolean hasRegionNotification() {
        MainMarketPage page = new MainMarketPage();
        return !page.regionNotification.isEmpty();
    }

    @Step("выбран регион")
    public void chooseRegion() {
        MainMarketPage page = new MainMarketPage();
        page.regionNotification.get(0).click();
    }

    @Step("отображаются все разделы")
    public boolean hasAllDepartments() {
        MainMarketPage page = new MainMarketPage();
        return !page.allDepartment.isEmpty();
    }

    @Step("выбран раздел {0} из \"Все категории\"")
    public void goToDepartmentUseAllDepartments(String departmentName) {
        new MainMarketPage().goToDepartmentUseAllDepartments(departmentName);
    }

    @Step("выбран раздел {0} из основного меню")
    public void goToDepartmentFromTopMenu(String departmentName) {
        new MainMarketPage().goToDepartmentFromTopMenu(departmentName);
    }

    @Step("выбираем раздел {0}")
    public void goToDepartment(String departmentName) {
        if (hasAllDepartments()) {
            goToDepartmentUseAllDepartments(departmentName);
        } else {
            goToDepartmentFromTopMenu(departmentName);
        }
    }
}
