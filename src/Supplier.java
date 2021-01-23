public class Supplier implements  Runnable{
    private  Warehouse warehouse;
    int p;

    Supplier(Warehouse warehouse){
        this.warehouse=warehouse;
        p=0;
        new Thread(this).start();
    }
    @Override
    public void run(){
        try {
            while(true) {
                if (warehouse.priviousThread()!=Thread.currentThread()) p++;
                warehouse.put(p);
                warehouse.isReady = false;
            }
        }
        catch (InterruptedException e){}
    }
}
