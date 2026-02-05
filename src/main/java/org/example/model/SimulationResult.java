package org.example.model;

import java.util.List;

public record SimulationResult(
        List<MonthlyRecord> records,
        double finalCash,
        double finalLoan,
        double finalNetWorth
) {
}
