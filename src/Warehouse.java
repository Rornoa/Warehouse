import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Warehouse {

    private final static int SIZE = 10;
    private int[] array = new int[SIZE];
    private int head;
    private int tail;
    private Condition fullCondition;
    private Condition emptyCondition;
    private ReentrantLock locker;
    boolean isReady=true;
    Thread thread = new Thread();

    Warehouse(){
        head = 0;
        tail = 9;
        locker = new ReentrantLock();
        fullCondition = locker.newCondition();
        emptyCondition = locker.newCondition();
    }

    private boolean isEmpty(){return next(tail) == head;}

    private boolean isFull(){return next(next(tail)) == head;}

    private static int next(int i){return(i+1)%SIZE;}

    void print(){
        int i = head;
        while(i != next(tail)){
            System.out.print(array[i]+" ");
            i=next(i);
        }
        System.out.println();
    }

    void put(int val) throws  InterruptedException{
        locker.lock();
        thread = Thread.currentThread();
        try{
            while (isFull())
                fullCondition.await();
            System.out.print(Thread.currentThread().getName()+" производит item["+val+"] ");
            tail = next(tail);
            array[tail]=val;
            print();
            Thread.sleep(10);
            emptyCondition.signal();

        }
        finally{locker.unlock();}
    }

    int get() throws InterruptedException{
        locker.lock();
        thread = Thread.currentThread();

        try{
            while (isEmpty())
                emptyCondition.await();
            System.out.print(Thread.currentThread().getName()+"    получает   item["+array[head]+"] ");
            array[head]=1;
            head=next(head);
            print();
            Thread.sleep(20);
            fullCondition.signal();
            return array[head];
        }
        finally{ locker.unlock(); }
    }

    public Thread priviousThread(){
        return thread;
    }

}
