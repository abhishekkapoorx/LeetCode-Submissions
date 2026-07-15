class Solution {
    private int gcd(int a, int b) {
        if (b == 0) return a;
        if (a < b) return gcd(b, a);
        return gcd(b, a % b);
    }
    public int gcdOfOddEvenSums(int n) {
        int sumOdd = n*n;
        int sumEven = n*(n+1);
        return gcd(sumOdd, sumEven);
    }
}