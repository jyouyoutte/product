package com.jonathan.thread;

public class MultiThreadStart {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnableMultiThread("Ping"));
        Thread thread2 = new Thread(new MyRunnableMultiThread("Pong"));
        System.out.println("GAME START");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            System.out.println("Main Thread was interrupted");
        }
        System.out.println("GAME OVER");
    }
}
