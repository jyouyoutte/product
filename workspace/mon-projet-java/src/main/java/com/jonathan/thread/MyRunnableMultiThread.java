package com.jonathan.thread;

public class MyRunnableMultiThread implements Runnable{
    private final String text;

    public MyRunnableMultiThread(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " " + i + " " + text);

            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted" + i);
            }
        }
    }
}
