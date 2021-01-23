public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        String str1="cons1";
        String str2="cons2";
        new Supplier(warehouse);
        new Consumer(warehouse,str1);
        new Consumer(warehouse,str2);
    }
}
