package aso_lab7;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WorkDay workDay = new WorkDay(5);

        for (int i = 0; i < 10; i++) {
            Client client = new Client(i, workDay);
            Thread thread = new Thread(client);
            thread.start();
            Thread.sleep(1000);
        }
    }
}