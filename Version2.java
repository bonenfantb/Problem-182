/*
 * Ben Bonenfant
 * Project Euler P.182
 * 
 * FirstVersion was too slow to be viable. After taking time to experiment, I
 * found that the minimum number of unconcealed messages would be 9 and also
 * that the cases for when the message was equal to 0 or 1 were trivial 
 * because they would always fail to conceal any message. This is a revision of
 * FirstVersion 
 */
package secondversion;

import java.lang.Math;

public class SecondVersion {
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
        
        // Loops sums all exponents that are coprime to Phi and have 7
        // unconcealed messages (not including 0 and 1)
        for (int e = 2; e < Phi; e++) {
            if (GCD(e, Phi) != 1)
                continue;
            if (countUnconcealedRevised(N, e, 7))
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
    
    // Revised counter function that returns false if the number of unconcealed
    // messages is greater than the specified parameter
    public static boolean countUnconcealedRevised(int modulus, int e, int minUnconcealed) {
		int count = 0;
		for (int m = 2; m < modulus; m++) {
			if (powerMod(m, e, modulus) == m)
				count++;
                        if (count > minUnconcealed)
                            break;
		}
                if (count == minUnconcealed)
                    return true;
                else
                    return false;
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
    
