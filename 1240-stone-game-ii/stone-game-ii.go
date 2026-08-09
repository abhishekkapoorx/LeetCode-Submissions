func stoneGameII(piles []int) int {
    n := len(piles)

    // suffix[i] = sum of piles[i:]
    suffix := make([]int, n+1)
    for i := n - 1; i >= 0; i-- {
        suffix[i] = suffix[i+1] + piles[i]
    }

    memo := make(map[[2]int]int)

    var findMax func(i, M int) int

    findMax = func(i, M int) int {
        if i >= n {
            return 0
        }

        key := [2]int{i, M}
        if val, ok := memo[key]; ok {
            return val
        }

        // Can take all remaining piles
        if i+2*M >= n {
            memo[key] = suffix[i]
            return suffix[i]
        }

        res := 0

        for x := 1; x <= 2*M; x++ {
            newM := max(M, x)

            // Current player's score =
            // all remaining stones - opponent's best score
            opponent := findMax(i+x, newM)

            current := suffix[i] - opponent

            res = max(res, current)
        }

        memo[key] = res
        return res
    }

    return findMax(0, 1)
}