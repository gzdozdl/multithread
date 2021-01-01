package thread.creation;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw  new RuntimeException("Intended");
            }
        });

        thread.setName("Misbehaving thread");

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread:" + t.getName()
                        + " the error is:" + e.getMessage());
            }
        });

        thread.start();
     }

/*
 public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("we are in thread: " + Thread.currentThread().getName() );
                System.out.println("Current thread priority: " + Thread.currentThread().getPriority());
            }
        });

        thread.setName("new worker method");
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("we are in thread: " + Thread.currentThread().getName() + " before starting new thread");
        thread.start();
        System.out.println("we are in thread: " + Thread.currentThread().getName() + " after starting new thread");
    }

 */

}
