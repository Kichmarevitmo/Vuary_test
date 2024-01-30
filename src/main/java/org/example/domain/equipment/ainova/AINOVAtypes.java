package org.example.domain.equipment.ainova;

public enum AINOVAtypes {
    STANDARD("Стандартные"),
    MINI_BOILERS("Мини-котлы");

    private final String description;

    AINOVAtypes(String description) {
        this.description = description;
    }

    public String GetDescription() {
        return description;
    }
}
