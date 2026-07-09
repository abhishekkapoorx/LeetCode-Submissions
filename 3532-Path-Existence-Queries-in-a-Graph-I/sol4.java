class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        Disjoint ds = new Disjoint(n);

        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] <= maxDiff) {
                ds.union(i, i + 1);
            }
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            ans[i] = ds.find(queries[i][0]) == ds.find(queries[i][1]);
        }

        return ans;
    }
}

class Disjoint {

    int[] parent;
    int[] rank;

    Disjoint(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if (pu == pv) return;

        if (rank[pu] < rank[pv]) {
            parent[pu] = pv;
        } else if (rank[pu] > rank[pv]) {
            parent[pv] = pu;
        } else {
            parent[pv] = pu;
            rank[pu]++;
        }
    }
}