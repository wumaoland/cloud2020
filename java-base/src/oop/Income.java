package oop;

import java.math.BigDecimal;

class Income {
    protected BigDecimal income = BigDecimal.ZERO;

    public Income(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getTax() {
        return this.income.multiply(new BigDecimal("0.1"));
    }
}

class Salary extends Income {
    public Salary(BigDecimal income) {
        super(income);
    }

    @Override
    public BigDecimal getTax() {
        return income.subtract(new BigDecimal(5000));
    }
}
