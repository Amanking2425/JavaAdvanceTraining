package com.lpu.JUnitCaseStudy;

public class EmployeeSalaryCalculator {

    private double basicSalary;
    private double bonus;
    private double taxPercent;

    public EmployeeSalaryCalculator(double basicSalary, double bonus, double taxPercent) {
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        this.taxPercent = taxPercent;
    }

    public void validateSalary() {

        if (basicSalary <= 0) {
            throw new IllegalArgumentException("Basic salary must be greater than 0");
        }

        if (taxPercent < 0 || taxPercent > 100) {
            throw new IllegalArgumentException("Tax percent must be between 0 and 100");
        }
    }

    public double calculateHRA() {
        return basicSalary * 0.20;
    }

    public double calculateDA() {
        return basicSalary * 0.10;
    }

    public double calculateGrossSalary() {
        return basicSalary + calculateHRA() + calculateDA() + bonus;
    }

    public double calculateNetSalary() {

        double gross = calculateGrossSalary();
        double taxDeduction = (taxPercent / 100) * gross;

        return gross - taxDeduction;
    }
}
