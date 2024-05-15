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
     // lock.lock(); 
      //System.out.println("Thread " ); 
      for (int n =lower; n<=upper;n++){
        if (isPrime(n)){
           // System.out.println("Prime " + n); 
            addToList(n);
        }
        //else
        //   System.out.println("not Prime " + n);

      }
     //lock.unlock();
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


    public boolean isPrime(int n){
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }

}