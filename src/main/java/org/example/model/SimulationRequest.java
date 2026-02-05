package org.example.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SimulationRequest {
    @NotNull
    @DecimalMin("0.0")
    private Double initialCash;

    @NotNull
    @DecimalMin("0.0")
    private Double initialLoan;

    @NotNull
    @DecimalMin("0.0")
    private Double annualSavingsIncrease;

    @NotNull
    @DecimalMin("0.0")
    private Double loanInterestRate;

    @NotNull
    @DecimalMin("0.0")
    private Double investmentYieldAnnual;

    @NotNull
    @DecimalMin("0.0")
    private Double monthlyLoanPayment;

    @NotNull
    @Min(1)
    private Integer years;

    public static SimulationRequest defaultValues() {
        SimulationRequest request = new SimulationRequest();
        request.initialCash = 3_000_000d;
        request.initialLoan = 35_000_000d;
        request.annualSavingsIncrease = 1_200_000d;
        request.loanInterestRate = 0.005d;
        request.investmentYieldAnnual = 0.03d;
        request.monthlyLoanPayment = 100_000d;
        request.years = 35;
        return request;
    }

    public Double getInitialCash() {
        return initialCash;
    }

    public void setInitialCash(Double initialCash) {
        this.initialCash = initialCash;
    }

    public Double getInitialLoan() {
        return initialLoan;
    }

    public void setInitialLoan(Double initialLoan) {
        this.initialLoan = initialLoan;
    }

    public Double getAnnualSavingsIncrease() {
        return annualSavingsIncrease;
    }

    public void setAnnualSavingsIncrease(Double annualSavingsIncrease) {
        this.annualSavingsIncrease = annualSavingsIncrease;
    }

    public Double getLoanInterestRate() {
        return loanInterestRate;
    }

    public void setLoanInterestRate(Double loanInterestRate) {
        this.loanInterestRate = loanInterestRate;
    }

    public Double getInvestmentYieldAnnual() {
        return investmentYieldAnnual;
    }

    public void setInvestmentYieldAnnual(Double investmentYieldAnnual) {
        this.investmentYieldAnnual = investmentYieldAnnual;
    }

    public Double getMonthlyLoanPayment() {
        return monthlyLoanPayment;
    }

    public void setMonthlyLoanPayment(Double monthlyLoanPayment) {
        this.monthlyLoanPayment = monthlyLoanPayment;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }
}
