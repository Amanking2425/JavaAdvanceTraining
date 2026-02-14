package com.lpu.JUnitCaseStudy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeSalaryCalculatorTest {

    @Test
    public void testValidSalaryCalculation() {

        EmployeeSalaryCalculator calc =
                new EmployeeSalaryCalculator(20000, 5000, 10);

        calc.validateSalary();

        double net = calc.calculateNetSalary();

        assertEquals(27900, net, 0.01);
    }

    @Test
    public void testHRA() {

        EmployeeSalaryCalculator calc =
                new EmployeeSalaryCalculator(20000, 0, 0);

        assertEquals(4000, calc.calculateHRA(), 0.01);
    }

    @Test
    public void testDA() {

        EmployeeSalaryCalculator calc =
                new EmployeeSalaryCalculator(20000, 0, 0);

        assertEquals(2000, calc.calculateDA(), 0.01);
    }

    @Test
    public void testGrossSalary() {

        EmployeeSalaryCalculator calc =
                new EmployeeSalaryCalculator(20000, 5000, 0);

        assertEquals(31000, calc.calculateGrossSalary(), 0.01);
    }

    @Test
    public void testNetSalary() {

        EmployeeSalaryCalculator calc =
                new EmployeeSalaryCalculator(20000, 5000, 10);

        assertEquals(27900, calc.calculateNetSalary(), 0.01);
    }

    @Test
    public void testZeroSalary() {

        EmployeeSalaryCalculator calc =
                new EmployeeSalaryCalculator(0, 5000, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            calc.validateSalary();
        });
    }

    @Test
    public void testNegativeSalary() {

        EmployeeSalaryCalculator calc =
                new EmployeeSalaryCalculator(-10000, 5000, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            calc.validateSalary();
        });
    }

    @Test
    public void testInvalidTax() {

        EmployeeSalaryCalculator calc =
                new EmployeeSalaryCalculator(20000, 5000, 150);

        assertThrows(IllegalArgumentException.class, () -> {
            calc.validateSalary();
        });
    }
}
