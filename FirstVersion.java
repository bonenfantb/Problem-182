/*
 * Ben Bonenfant
 * Project Euler P.182
 * 
 * The first attempt at the program using brute force. 
 */
package firstversion;

import java.lang.Math;

public class FirstVersion {
    /*
     * P and Q are primes
     * N is the product of the primes and the modulus used for encryption
     * Phi is the solution Euler's Phi function for N
     */
    public static void main(String[] args) {          
        int P = 1009;
        int Q = 3643;
        int N = P * Q;
        int Phi = (P - 1)*(Q - 1);
        
        // Creates array to store the number of unconcealed messages for 
        // for each exponent e using function
        int[] numUnconcealed = countAllUnconcealed(N);
        
        // Loop to sort through the array and find the minimum number of 
        // concealed messages
        int minUnconcealed = Integer.MAX_VALUE;
        for (int x : numUnconcealed)
                minUnconcealed = Math.min(x, minUnconcealed);
        
        // Sums the exponents that corresponds to the minimum number of 
        // concealed messages
        long sum = 0;
        for (int e = 0; e < Phi; e++) {
            if (numUnconcealed[e] == minUnconcealed) {
                sum += e;
                System.out.println(sum + " , " + minUnconcealed);
            }
        }
        
    }   
    
    // Simple GCD function using Euler's method
    public static int GCD(int p, int q) {
        if (q == 0)
            return p;
        return GCD(q, p%q);   
    }
    
    // Function that assign the number of unconcealed messages to array with
    // index being the exponent if e and N are coprime
    public static int[] countAllUnconcealed(int N) {
        int[] numUnconcealed = new int[N - 1];
        for (int e = 0; e < numUnconcealed.length; e++) {
            if (GCD(e, N) == 1)
                    numUnconcealed[e] = countUnconcealed(N, e);
            else
                    numUnconcealed[e] = Integer.MAX_VALUE;
        }
        return numUnconcealed;
    }
	
    // Function to loop through all possible messages and count the number
    // of message that are not concealed (Very inefficient) 
    public static int countUnconcealed(int modulus, int e) {
            int count = 0;
            for (int m = 0; m < modulus; m++) {
                    if (powerMod(m, e, modulus) == m)
                            count++;
            }
            return count;
    }
    
    // Function that calculates modular congruence of a base raised to an
    // arbitrary power using successive squaring (Could be more efficient)
    public static int powerMod(int base, int exp, int mod){
        double product = 1;
        double modRef = base % mod;
        int expBin = exp;
        double size = Math.floor(Math.log(exp)/Math.log(2.0)) + 1;
        int[] powersOfTwo = new int[(int)size];
        for (int n = (int)size - 1; n >= 0; n--) {
            if ((expBin / (int)Math.pow(2, n)) == 1) {
                powersOfTwo[n] = 1;
                expBin -= (int)Math.pow(2, n);
            }
            else 
                powersOfTwo[n] = 0;
        }
        for (int i = 0; i < size; i++) {
            if (powersOfTwo[i] == 1)
                product = product * modRef;
            modRef = (modRef * modRef) % mod;
        }
        return ((int)product % mod);
    }
}
