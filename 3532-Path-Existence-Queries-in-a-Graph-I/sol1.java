class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] adj = new int[n][n];
        for (int[] a: adj) {
            Arrays.fill(a, (int)(1e8));
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) <= maxDiff) {
                    adj[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[j][k]);
                }
            }
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            // System.out.printf("start: %d, end: %d, dist: %d", start, end, adj[start][end]);
            ans[i] = adj[start][end] != (int)(1e8);
        }

        return ans;
    }
}