package org.example.model;

public record MonthlyRecord(
        int month,
        double cash,
        double loan,
        double netWorth
) {
}
