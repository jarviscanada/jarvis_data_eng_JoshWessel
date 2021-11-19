package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Count-Primes-ee685743c11a4d1ca71dd044033452dd
 */
public class CountPrimes {

    /**
    * Big-O: O(n^2)
    * Justification: The solution uses a nested for loop to iterate through all numbers, resulting in O(n^2)
    */
    public int countPrimes(int n) {
      int count = 0;
      for (int i = 0; i < n; i++) {
        if (isPrime(i))
          count++;
      }
      return count;
    }

    public boolean isPrime(int n) {
      if (n <= 1)
        return false;
      for (int i = 2; i * i <= n; i++) {
        if (n % i == 0)
          return false;
      }
      return true;
    }
}
