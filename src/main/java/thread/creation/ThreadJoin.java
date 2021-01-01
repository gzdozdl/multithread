package thread.creation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ThreadJoin {
    public static void main(String [] args) {
        BigInteger base1= BigInteger.valueOf(20000000);
        BigInteger power1= BigInteger.valueOf(800000);
        BigInteger base2 = BigInteger.valueOf(2);
        BigInteger power2 = BigInteger.valueOf(3);

        System.out.println("calculate, base1:" + base1 + " power1:" + power1
                +  " && base2:" + base2 + " power2:" + power2);
        BigInteger result = calculateResult(base1, power1, base2, power2);

        System.out.println("Result:" + result);
    }

    public static BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        PowerCalculatingThread firstThread = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread secondThread = new PowerCalculatingThread(base2, power2);

        List<PowerCalculatingThread> list = new ArrayList<>();
        list.add(firstThread);
        list.add(secondThread);

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join(2000);
            secondThread.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return firstThread.getResult().add(secondThread.getResult());
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for(int i = 0 ; i< power.intValue(); i++) {
                result = result.multiply(base);
            }

        }

        public BigInteger getResult() { return result; }
    }

}
