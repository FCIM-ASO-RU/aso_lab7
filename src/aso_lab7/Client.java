package aso_lab7;

class Client implements Runnable {
    private int id;
    private WorkDay workDay;

    public Client(int id, WorkDay workDay) {
        this.id = id;
        this.workDay = workDay;
    }

    public void run() {
        try {
            workDay.enterBarberShop(id);
            workDay.getHaircut(id);
            workDay.leaveBarberShop(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
