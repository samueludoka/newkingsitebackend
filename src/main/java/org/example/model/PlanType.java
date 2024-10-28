package org.example.model;

import lombok.Getter;

@Getter

public enum PlanType {
    BASIC (1.09, 200, 5000, 5),
    SILVER(1.14, 5000, 7000, 7),
    PLATINUM(1.18, 11000, 21000, 14),
    MASTER(1.23, 21000, 35000, 21),
    EXECUTIVE(1.27, 35000, 50000, 27),
    PREMIUM(1.30, 50000, 75000, 30),
    GOLD(1.35, 75000, 100000, 35);

    private final double percentage;
    private final double min;
    private final double max;
    private final int days;

    PlanType(double percentage, double min, double max, int days) {
        this.percentage = percentage;
        this.min = min;
        this.max = max;
        this.days = days;
    }






}
