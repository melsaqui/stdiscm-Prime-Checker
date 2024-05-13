import java.util.Scanner;
public class Driver {
    public static void main(String[] args) {
        int limit;
        int nthreads;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter upper bound: ");
        limit = s.nextInt();
        System.out.println("Enter number of threads: ");
        nthreads = s.nextInt();
        s.close();
        int upper;
        //Thread []threads = new Thread[nthreads];
        int divisions= (limit-1)/nthreads + (limit-1)%nthreads;
        int lower =2;
        for(int i =0; i<nthreads;i++){
            if (lower+divisions-1>limit){
                 upper=limit;
            }
            else
                upper= lower+divisions-1;

            Prime p = new Prime(lower,upper) ;
            p.start();
            lower = upper+1;
            
        }      

    }
    
}

class Prime extends Thread{
    int upper;
    int lower;
    public Prime(int lower, int upper){
        this.upper= upper;
        this.lower= lower;
    }
    public void run() {
      for (int n =lower; n<=upper;n++){
        //System.out.println(n);
        if (isPrime(n))
            System.out.println("Prime " + n);
        else
            System.out.println("not Prime " + n);
      }

    }

    public boolean isPrime(int n){
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }

}