import java.util.*;

class Solution {
    int MOD = (int)(1e9 + 7);
    Map<String, Integer> memo = new HashMap<>();

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private int findPairs(int[] nums, int i, int ga, int gb) {
        if (i == nums.length) {
            return (ga == gb && ga != -1) ? 1 : 0; // avoid both empty
        }

        String key = i + "," + ga + "," + gb;
        if (memo.containsKey(key)) return memo.get(key);

        int total = 0;

        // Put in A
        int newGa = (ga == -1) ? nums[i] : gcd(ga, nums[i]);
        total = (total + findPairs(nums, i+1, newGa, gb)) % MOD;

        // Put in B
        int newGb = (gb == -1) ? nums[i] : gcd(gb, nums[i]);
        total = (total + findPairs(nums, i+1, ga, newGb)) % MOD;

        // Skip
        total = (total + findPairs(nums, i+1, ga, gb)) % MOD;

        memo.put(key, total);
        return total;
    }

    public int subsequencePairCount(int[] nums) {
        return findPairs(nums, 0, -1, -1);
    }
}