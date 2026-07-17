class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        // Frequency of each value
        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        // pairsDiv[d] = pairs where both numbers are divisible by d
        long[] pairsDiv = new long[max + 1];

        for (int d = 1; d <= max; d++) {
            long cnt = 0;
            for (int multiple = d; multiple <= max; multiple += d) {
                cnt += freq[multiple];
            }
            pairsDiv[d] = cnt * (cnt - 1) / 2;
        }

        // exact[d] = pairs whose gcd is exactly d
        long[] exact = new long[max + 1];

        for (int d = max; d >= 1; d--) {
            exact[d] = pairsDiv[d];
            for (int multiple = d + d; multiple <= max; multiple += d) {
                exact[d] -= exact[multiple];
            }
        }

        // Prefix sums
        long[] prefix = new long[max + 1];
        for (int d = 1; d <= max; d++) {
            prefix[d] = prefix[d - 1] + exact[d];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];

            int lo = 1, hi = max;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (prefix[mid] > q)
                    hi = mid;
                else
                    lo = mid + 1;
            }

            ans[i] = lo;
        }

        return ans;
    }
}