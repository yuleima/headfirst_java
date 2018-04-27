package me.azno.study.concurrency.thread01.chapter2;

/**
 * Created by yulei.ma on 2017/7/12.
 */
public class BankHallRunable {
    public static void main(String[] args) {
        BankWindowRunnable bankWindowRunnable = new BankWindowRunnable();
        for (int i = 1; i <= 4; i++) {
            new Thread(bankWindowRunnable, i + "号窗口").start();
        }

    }
}
