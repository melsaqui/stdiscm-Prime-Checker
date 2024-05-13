import java.util.Scanner;
public class Driver {
    public static void main(String[] args) {
        /*TODO: 
            1. Implement mutual exclusion for the `primes` list.
            2. Implement the computation of the runtime of your algorithm. 
                - The timer starts after user input is collected, and the timer stops before printing the number of primes found.
            3. Double check?*/
        int limit;
        int nthreads =1;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter upper bound: ");
        limit = s.nextInt();
        System.out.println("Enter number of threads: ");
        nthreads = s.nextInt();
        s.close();
        int upper;
        //Implement threading to split the range of integers across the specified number of threads.
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