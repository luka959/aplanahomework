package com.company.steps;

import ru.yandex.qatools.allure.annotations.Step;

public abstract class AbstractSteps {
    @Step("значение \"{0}\" = \"{1}\"")
    public void log(String name, String value) {
    }
}
