package com.company;

import com.company.sweets.Sweet;
import com.company.sweets.SweetType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class NewYearPresent {
    private final Map<SweetType, List<Sweet>> sweetsByType =
            new EnumMap<SweetType, List<Sweet>>(SweetType.class);

    public NewYearPresent() {
    }

    public List<Sweet> getSweetsByType(SweetType type) {
        return sweetsByType.get(type);
    }

    public void removeAllSweetsByType(SweetType type) {
        sweetsByType.remove(type);
    }

    public void addSweet(Sweet sweet) {
        List<Sweet> sweets = sweetsByType.get(sweet.getType());
        if (sweets == null) {
            sweets = new ArrayList<>();
            sweetsByType.put(sweet.getType(), sweets);
        }
        sweets.add(sweet);
    }

    public double getWeight() {
        double weight = 0;
        for (List<Sweet> sweets : sweetsByType.values()) {
            for (Sweet sweet : sweets) {
                weight += sweet.getWeight();
            }
        }
        return weight;
    }

    public double getCost() {
        double cost = 0;
        for (List<Sweet> sweets : sweetsByType.values()) {
            for (Sweet sweet : sweets) {
                cost += sweet.getCost();
            }
        }
        return cost;
    }

    public String getShortInfo() {
        if (sweetsByType.isEmpty()) {
            return "Подарок пуст";
        }
        String shortInfo = "Подарок содержит: ";
        boolean first = true;
        for (Map.Entry<SweetType, List<Sweet>> entry : sweetsByType.entrySet()) {
            if (first) {
                first = false;
            } else {
                shortInfo += ", ";
            }

            shortInfo += entry.getValue().size() + " конфет типа " + entry.getKey();
        }
        return shortInfo;
    }
}