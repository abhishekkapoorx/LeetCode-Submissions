class Solution {
    public int[] arrayRankTransform(int[] arr) {
        Map<Integer, List<Integer>> tmap = new TreeMap<>();
        int[] ans = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            tmap.computeIfAbsent(arr[i], k -> new ArrayList<Integer>()).add(i);
        }

        int rank = 1;
        for (Map.Entry<Integer, List<Integer>> e: tmap.entrySet()) {
            for (int i: e.getValue()) {
                ans[i] = rank;
            }
            rank++;
        }
        return ans;
    }
}