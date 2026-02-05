package org.example.service;

import org.example.model.MonthlyRecord;
import org.example.model.SimulationRequest;
import org.example.model.SimulationResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceSimulationService {

    public SimulationResult simulate(SimulationRequest request) {
        int totalMonths = request.getYears() * 12;
        double cash = request.getInitialCash();
        double loan = request.getInitialLoan();
        double monthlySavingsIncrease = request.getAnnualSavingsIncrease() / 12.0;
        double monthlyLoanRate = request.getLoanInterestRate() / 12.0;
        double monthlyInvestmentYield = request.getInvestmentYieldAnnual() / 12.0;

        List<MonthlyRecord> records = new ArrayList<>(totalMonths);

        for (int month = 1; month <= totalMonths; month++) {
            cash += monthlySavingsIncrease;

            if (loan > 0) {
                loan += loan * monthlyLoanRate;
                double payment = Math.min(request.getMonthlyLoanPayment(), loan);
                loan -= payment;
                cash -= payment;
            }

            cash *= (1 + monthlyInvestmentYield);

            double netWorth = cash - loan;
            records.add(new MonthlyRecord(month, cash, loan, netWorth));
        }

        double finalNetWorth = cash - loan;
        return new SimulationResult(records, cash, loan, finalNetWorth);
    }
}
