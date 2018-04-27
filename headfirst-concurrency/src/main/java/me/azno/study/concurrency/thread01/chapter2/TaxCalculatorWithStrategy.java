package me.azno.study.concurrency.thread01.chapter2;

/**
 * Created by yulei.ma on 2017/7/13.
 */
public class TaxCalculatorWithStrategy {
    private double salary;
    private double bonus;
    private CalculateStrategy strategy;

    public TaxCalculatorWithStrategy(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    public static void main(String[] args) {
        /*通过设置计算策略实现计算*/
        TaxCalculatorWithStrategy calculator = new TaxCalculatorWithStrategy(10000d, 1500d);
        calculator.setStrategy((salary, bonus) -> salary * 0.1 + bonus * 0.15);
        double tax = calculator.calculateTax();
        System.out.println(tax);
    }

    public void setStrategy(CalculateStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateTax() {
        return strategy.calculate(salary, bonus);
    }

    @FunctionalInterface
    interface CalculateStrategy {
        double calculate(double salary, double bonus);
    }
}
