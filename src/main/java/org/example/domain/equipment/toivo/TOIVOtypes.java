package org.example.domain.equipment.toivo;

public enum TOIVOtypes {
    ONE_CIRCUIT_CLOSED_CHAMBER_NO_THREE_WAY_VALVE("Одноконтурные (с закрытой камерой) без трёхходового клапана"),
    ONE_CIRCUIT_CLOSED_CHAMBER_WITH_THREE_WAY_VALVE("Одноконтурные (с закрытой камерой) с трёхходовым клапаном"),
    TWO_CIRCUIT_CLOSED_CHAMBER_FOR_APARTMENT_HEATING("Двухконтурные (с закрытой камерой) для поквартирного отопления");

    private final String description;

    TOIVOtypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
