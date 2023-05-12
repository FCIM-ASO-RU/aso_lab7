package aso_lab7;

public class WorkDay extends Thread {
    
    private int time;
    
    public WorkDay() {
        time = 0;
    }
    
    public int getTime() {
        return time;
    }
    
    @Override
    public void run() {
        while(true) {
            try{
                sleep(1000);
                time++;
                if(time>60)
                    break;
            }
            catch(InterruptedException e) {
                
            }
        }
    }
    
}