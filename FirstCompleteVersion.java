/*
 * Ben Bonenfant
 * Project Euler P.182
 *
 * SecondVersion was still too slow to be viable. Through more experimenting
 * with FirstVersion, SecondVersion, and pen and paper I was able to find 
 * certain messages that, if the message was unconcealed, there would always be
 * more than 9 messages unconcealed. I implimented this into the program design
 * and created the first working program for this problem.
 */
package firstcompleteversion;

import java.lang.Math;

public class FirstCompleteVersion {
    /*
     * P and Q are primes
     * N is the product of the primes and the modulus used for encryption
     * Phi is the solution Euler's Phi function for N
     */
    public static void main(String[] args) {
        int P = 1009;
        int Q = 3643;
        int N = P * Q;
        int Phi = (P - 1) * (Q - 1);
        long total = 0;
        
        // Loop that sends each coprime exponent to check if the 6 identified
        // messages are unconcealed and sums the checked exponents
        for (int e = 2; e < Phi; e++) {
            if (GCD(e, Phi) != 1)
                continue;
            if (countUnconcealedRevised2(N, e))
                total += e;
        }
        System.out.println(total);
    }
    
    // Simple GCD function using Euler's method
    public static int GCD(int p, int q) {
        if (q == 0)
            return p;
        return GCD(q, p%q);   
    }
    
    // Function that checks to see if an exponent conceals 6 identified messages
    // and returns true if all 6 messages are concealed.
    public static boolean countUnconcealedRevised2(int modulus, int e) {
        int count = 0;
        int[] mValues = {3028, 3642, 28721, 33210, 73282, 116575};
        for (int m = 0; m < 6; m++) {
            if (powerModRevised(mValues[m], e, modulus) == mValues[m])
                return false;
        }
        return true;
    }
    
    // Revised function to calculate exponential modular congruence with
    // successive squaring (without using arrays)
    public static long powerModRevised(long base, double exponent, int modulus) {
        long result = 1;
        while (exponent > 0) {
            if ((exponent % 2) == 1) {
                result = (result * base) % modulus;
            }
        base = (base * base) % modulus;
        exponent = Math.floor(exponent / 2.0);
        }
        return result;
    }           
}
