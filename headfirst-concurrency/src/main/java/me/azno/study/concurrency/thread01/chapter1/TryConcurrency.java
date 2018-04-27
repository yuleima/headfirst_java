package me.azno.study.concurrency.thread01.chapter1;

/**
 * Created by yulei.ma on 2017/7/12.
 */
public class TryConcurrency {
    public static void main(String[] args) throws InterruptedException {
        float sec = 5f;
        System.out.println("wait " + sec + " seconds");
        // 停止几秒是为了等待打开jvm工具观察
        sleep(sec);
        new Thread("MyThread-1") {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    println(" 1 > " + i);
                    TryConcurrency.sleep(1);
                }
            }
        }.start();
        new Thread("MyThread-2") {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    println(" 2 > " + i);
                    TryConcurrency.sleep(1);
                }
            }
        }.start();
    }

    private static void println(String msg) {
        System.out.println(msg);
    }

    private static void sleep(float sec) {
        try {
            Thread.sleep((long) (Math.random() * 1000 * sec));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
