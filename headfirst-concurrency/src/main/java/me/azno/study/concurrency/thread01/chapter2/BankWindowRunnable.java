package me.azno.study.concurrency.thread01.chapter2;

/**
 * Created by yulei.ma on 2017/7/12.
 */
public class BankWindowRunnable implements Runnable {
    private static final int maxCode = 50;
    private static int currentCode = 1;

    @Override
    public void run() {
        while (currentCode <= maxCode) {
            System.out.println("请" + currentCode + "号顾客请到" + Thread.currentThread().getName() + "办理");

            currentCode++;
            handleTheBusiness();
        }

    }

    /**
     * 业务办理时间1~2秒
     */
    private void handleTheBusiness() {
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
