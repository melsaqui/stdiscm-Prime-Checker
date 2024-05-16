import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;


public class Prime extends Thread{
    private int upper;
    private int lower;
    private static ArrayList<Integer> PrimeList = new ArrayList<Integer>(); 
    private static Lock lock = new ReentrantLock();

    public Prime(int lower, int upper){
        this.upper= upper;
        this.lower= lower;
    }
    public void run() {
     
      for (int n =lower; n<=upper;n++){
        if (isPrime(n)){
            addToList(n);
        }
       

      }
    
    }
    public static synchronized void addToList(int n) {
        try{
            lock.lock();
            PrimeList.add(n);
        }finally{
            lock.unlock();
        }
       
       
    }
    public static synchronized ArrayList<Integer> getList() {
        return PrimeList;
    }


    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    

}