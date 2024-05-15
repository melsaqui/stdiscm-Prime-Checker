import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Driver {
    public static void main(String[] args) {
        /*TODO: 
            1.(Double check) Implement mutual exclusion for the `primes` list.
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
        System.out.println("");

        long start = System.nanoTime();
        s.close();
        int upper;
        //Implement threading to split the range of integers across the specified number of threads.
        int divisions= (limit-1)/nthreads + (limit-1)%nthreads;
        int lower =2;
        Prime []threads = new Prime[nthreads];
        for(int i =0; i<nthreads;i++){
            if (lower+divisions-1>limit){
                 upper=limit;
            }
            else
                upper= lower+divisions-1;

            Prime p = new Prime(lower,upper) ;
            threads[i]=p;
            p.start();
            lower = upper+1;
            try{
                threads[i].join();
            }  catch (InterruptedException e) {
                e.printStackTrace();
            }
           
        }   
        long end = System.nanoTime();
        
        for (int j =0; j< threads[nthreads-1].getList().size();j++){
            System.out.println(threads[nthreads-1].getList().get(j));
        } 
        System.out.println("Execution time: " + (end-start) + " nanoseconds");

    }
    
}

