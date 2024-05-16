/*
 *  FILE
 *  USED FOR TESTING THE PERFORMANCE OF THE PRIME NUMBER CALCULATION
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
import java.io.FileWriter;
import java.io.IOException;

public class Testing {
    public static void main(String[] args) {
        int limit = 10000000;  // Set appropriate upper bounds as needed, 10^7 for final tests
        int[] threadCounts = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
        String csvFile = "results.csv";
    
        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write CSV Header
            writer.append("Thread Count,Run 1,Run 2,Run 3,Run 4,Run 5,Average\n");
    
            for (int nthreads : threadCounts) {
                System.out.println("Testing with " + nthreads + " threads.");
                long[] runtimes = new long[5];  // to store times of 5 runs in nanoseconds
                for (int i = 0; i < 5; i++) {
                    System.out.println("Run " + (i + 1) + " for " + nthreads + " threads.");
                    long startTime = System.nanoTime();
                    performPrimeCalculation(limit, nthreads);
                    long endTime = System.nanoTime();
                    runtimes[i] = endTime - startTime;
                    System.out.println("Completed run " + (i + 1) + " in " + runtimes[i] + " nanoseconds.");
                }
                long average = calculateAverage(runtimes);
                // Write results to CSV
                writer.append(String.valueOf(nthreads));
                for (long runtime : runtimes) {
                    writer.append(",").append(String.valueOf(runtime));
                }
                writer.append(",").append(String.valueOf(average)).append("\n");
                System.out.println("Average time for " + nthreads + " threads: " + average + " nanoseconds.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private static long calculateAverage(long[] runtimes) {
        long sum = 0;
        for (long runtime : runtimes) {
            sum += runtime;
        }
        return sum / runtimes.length;
    }

    private static void performPrimeCalculation(int limit, int nthreads) {
        // Calculating divisions per thread
        int rangePerThread = (limit - 1) / nthreads;
        int remainder = (limit - 1) % nthreads;
        int start = 2; // Start calculating primes from 2

        // Array to hold the threads
        Prime[] threads = new Prime[nthreads];

        // Create and start threads
        for (int i = 0; i < nthreads; i++) {
            int end = start + rangePerThread - 1;
            if (i < remainder) { // Distribute the remainder among the first few threads
                end++;
            }
            if (end > limit) {
                end = limit;
            }

            // Initialize the thread to calculate primes in the range [start, end]
            threads[i] = new Prime(start, end);
            threads[i].start(); // Start the thread
            System.out.println("Thread " + i + " started for range " + start + " to " + end + ".");
            start = end + 1; // Update start for the next thread
        }

        // Wait for all threads to finish
        for (int i = 0; i < nthreads; i++) {
            try {
                threads[i].join();
                System.out.println("Thread " + i + " has completed.");
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}
