package thread.creation;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new NewThread();
        thread.start();
    }

    public static class NewThread extends  Thread {
        @Override
        public void run() {
            System.out.println("Hello from the outside:"  + this.getName());
        }
    }
}
