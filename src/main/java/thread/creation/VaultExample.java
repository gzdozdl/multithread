package thread.creation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VaultExample {

    public static final int MAX_PASSWORD = 9999;

    public static void main(String[] args) {
        Random random = new Random();

        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threadList = new ArrayList<>();
        threadList.add(new AscendingThread(vault));
        threadList.add(new DescendingThread(vault));
        threadList.add(new PoliceThread());

        for (Thread thread : threadList) {
            thread.start();
        }
    }

    public static class Vault {
        private int password;
        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.password == guess;
        }
    }

    private  static abstract class HackerThread extends Thread {
        protected  Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting thread:" + this.getName());
            super.start();
        }
    }

    private static class AscendingThread extends HackerThread {
        public AscendingThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess=0; guess<MAX_PASSWORD; guess++) {
                if(vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed password correctly " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingThread extends HackerThread {
        public DescendingThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess=MAX_PASSWORD; guess>=0; guess--) {
               if(vault.isCorrectPassword(guess)) {
                   System.out.println(this.getName() + " guessed password correctly " + guess);
                   System.exit(0);
               }
            }
         }
    }

    private  static class PoliceThread extends Thread {
        @Override
        public void run() {
            for(int i= 10; i>=0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println(i);
            }
            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }






}
