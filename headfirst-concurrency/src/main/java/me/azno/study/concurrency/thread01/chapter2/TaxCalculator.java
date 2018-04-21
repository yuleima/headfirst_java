package me.azno.study.concurrency.thread01.chapter2;

/**
 * Created by yulei.ma on 2017/7/13.
 */
public class TaxCalculator {
    private double salary;
    private double bonus;

    public TaxCalculator(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    public static void main(String[] args) {
        /*通过匿名内部类实现计算策略*/
        TaxCalculator calculator = new TaxCalculator(10000d, 1500d) {
            @Override
            protected double calculate() {
                return getSalary() * 0.1 + getBonus() * 0.15;
            }
        };
        double tax = calculator.calculateTax();
        System.out.println(tax);

    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public double calculateTax() {
        return calculate();
    }

    protected double calculate() {
        return 0.0d;
    }
}
