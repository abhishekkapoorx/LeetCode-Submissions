class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[][] dp = new int[max + 1][max + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            int[][] next = new int[max + 1][max + 1];

            for (int g1 = 0; g1 <= max; g1++) {
                for (int g2 = 0; g2 <= max; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    // Skip current number
                    next[g1][g2] = (next[g1][g2] + dp[g1][g2]) % MOD;

                    // Put in first subsequence
                    int ng1 = (g1 == 0) ? x : gcd(g1, x);
                    next[ng1][g2] = (next[ng1][g2] + dp[g1][g2]) % MOD;

                    // Put in second subsequence
                    int ng2 = (g2 == 0) ? x : gcd(g2, x);
                    next[g1][ng2] = (next[g1][ng2] + dp[g1][g2]) % MOD;
                }
            }

            dp = next;
        }

        int ans = 0;
        for (int g = 1; g <= max; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}