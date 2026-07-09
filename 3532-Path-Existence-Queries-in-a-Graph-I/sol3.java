class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Disjoint ds = new Disjoint(n);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) <= maxDiff) {
                    ds.union(i, j);
                }
            }
        }
        
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            // System.out.printf("start: %d, end: %d, dist: %d", start, end, adj[start][end]);
            ans[i] = ds.isParentSame(start, end);
        }
        return ans;
    }
}

class Disjoint {
    int[] rank;
    int[] parent;
    Disjoint (int n) {
        rank = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int findParent(int i) {
        if (parent[i] == i) return i;
        parent[i] = findParent(parent[i]);
        return parent[i];
    }

    public void union(int u, int v) {
        int parentU = findParent(u);
        int parentV = findParent(v);

        if (parentU == parentV) return;
        else if (rank[u] > rank[v]) {
            parent[v] = parentU;
        } else {
            parent[u] = parentV;
        }
    }

    public boolean isParentSame(int u, int v) {
        return findParent(u) == findParent(v);
    }
}