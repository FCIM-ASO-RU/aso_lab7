package aso_lab7;

import java.util.concurrent.Semaphore;

public class WorkDay {
    private int chairs;
    private Semaphore barber = new Semaphore(0);
    private Semaphore customer = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    private boolean sleeping = true;
    private int currentCustomerId = -1;

    public WorkDay(int chairs) {
        this.chairs = chairs;
    }

    public void enterBarberShop(int customerId) throws InterruptedException {
        mutex.acquire();

        if (sleeping) {
            sleeping = false;
            currentCustomerId = customerId;
            System.out.println("Customer " + customerId + " wakes up the barber.");
            barber.release();
        } else if (chairs > 0) {
            chairs--;
            System.out.println("Customer " + customerId + " takes a seat in the waiting room.");
            customer.release();
        } else {
            System.out.println("Customer " + customerId + " leaves because there are no free chairs.");
            mutex.release();
            return;
        }

        mutex.release();
        customer.acquire();
    }

    public void getHaircut(int customerId) throws InterruptedException {
        System.out.println("Customer " + customerId + " gets a haircut.");
        Thread.sleep(2000);
    }

    public void leaveBarberShop(int customerId) throws InterruptedException {
        mutex.acquire();

        chairs++;

        if (currentCustomerId == customerId) {
            currentCustomerId = -1;
            sleeping = true;
            System.out.println("Customer " + customerId + " wakes up the barber and leaves.");
            barber.release();
        } else {
            System.out.println("Customer " + customerId + " leaves.");
        }

        mutex.release();
    }
}