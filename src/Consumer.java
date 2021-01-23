public class Consumer implements Runnable{

    private Warehouse warehouse;
    String threadName;
    int p;

    Consumer(Warehouse warehouse,String threadName) {
        this.warehouse = warehouse;
        this.threadName = threadName;
        new Thread(this,threadName).start();
        p=0;

    }
    @Override
    public void run(){
        try{
            while (true) {
                if(warehouse.priviousThread() != Thread.currentThread()){p++;}
                warehouse.get();
                warehouse.isReady=true;
            }
        }
        catch (InterruptedException e){}
    }
}
