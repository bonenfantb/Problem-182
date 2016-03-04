/*
 * Ben Bonenfant
 * Project Euler P.182
 *
 * After doing some research, I found a formula that states: the number of 
 * unconcealed messages for each exponent is equal to 
 *                  (GCD(e,P) + 1) * (GCD(e,Q) + 1)
 * Using this formula, I generalized the program to check the minimum number of
 * unconcealed messages for any user input (primes) and to export a file
 * (RSA_Unconcealed_Messages.txt) that contains the minimum number of
 * unconcealed messages, a list of which messages would not be concealed,
 * and the sum of all unconcealed messages.
 */
package p182;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class P182 {
    /*
     * P and Q are primes (user input)
     * N is the product of the primes and the modulus used for encryption
     * Phi is the solution Euler's Phi function for N
     */
    public static void main(String[] args) {
	
        // Creates File to write to and sets up the writing functions
        try{
            File file = new File("RSA_Unconcealed_Messages.txt");
            if (!file.exists()) {
                    file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
        
        // User input of primes
        Scanner in = new Scanner(System.in);
        int P, Q;
        System.out.println("Enter the two primes, P & Q: ");
        System.out.print("P = ");
        P = in.nextInt();
        System.out.print("Q = ");
        Q = in.nextInt();
        int N = P * Q;
	int Phi = (P - 1) * (Q - 1);
        int minimum = Integer.MAX_VALUE;
        long sum = 0;
        
        // Creates array to store exponents which satisfy the minimum number
        // of unconcealed messages
        int[] exponents = new int[Phi];
        
        // Uses formula to calculate the number of unconcealed messages for each
        // exponent, stores the exponents with minimum number of unconcealed
        // exponents in array, and sums them.
        for (int e = 2; e < Phi; e++) {
            int i = 0;
            if (GCD(e, Phi) != 1)
                continue;
            int numUnconcealed = ((1+GCD(e-1,P-1))*(1+GCD(e-1,Q-1)));
            if (numUnconcealed < minimum) {
                minimum = numUnconcealed;
                i = 0;
                exponents[i] = e;
                sum = e;
                i++;
            }
            else if (numUnconcealed == minimum) {
                exponents[i] = e;
                sum += e;
                i++;
            }
        }
        
        // Writes relevant data to File and formats
        bw.write("P = " + P); bw.newLine();
        bw.write("Q = " + Q); bw.newLine();
        bw.write("N = " + N); bw.newLine();
        bw.write("Phi = " + Phi);
        bw.newLine(); bw.newLine();
        bw.write("Minimum Number of Unconcealed Messages = " + minimum);
        bw.newLine(); 
        bw.write("Sum of Exponents = " + sum); 
        bw.newLine(); bw.newLine();
        bw.write("Exponents which Produce"); bw.newLine();
        bw.write(minimum + " Unconcealed Messages"); bw.newLine();
        bw.write("------------------------"); bw.newLine();
        
        // Counts how many exponents were collected
        int arraySize = 0;
        for(int n = 0; n < Phi; n++) {
            if (exponents[n] != 0)
                arraySize++;
            else
                break;
        }
        
        // Creates array of only exponents (no null spaces)
        int[] values = new int[arraySize];
        for(int n = 0; n < arraySize; n++)
            values[n] = exponents[n];
        
        // Writes exponents to file as an array of strings
        bw.write(Arrays.toString(values));

            // closes File
            bw.close();
            System.out.println("Done");
            
        // More file-writing code
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
        // Simple GCD function using Euler's method
        public static int GCD(int p, int q) {
            if (q == 0)
                return p;
            return GCD(q, p%q);   
        }
}  