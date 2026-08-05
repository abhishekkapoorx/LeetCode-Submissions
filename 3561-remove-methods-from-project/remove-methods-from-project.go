func remainingMethods(n int, k int, invocations [][]int) []int {
    // 1. Create adj list
    adj := make([][]int, n)

    for i := range n {
        adj[i] = []int{}
    }

    // 2. fill adj list
    for _, invocation := range invocations {
        src := invocation[0]
        target := invocation[1]

        adj[src] = append(adj[src], target)
    }

    suspiciousNodes := make([]bool, n)

    vis := make([]bool, n)

    // 3. mark group suspicious
    markSuspicious(adj, k, vis, suspiciousNodes)

    // 4. mark unsuspicious
    for node := range n {
        if !suspiciousNodes[node] && !vis[node] && isGood(adj, node, vis, suspiciousNodes) {
            result := []int{}
            for i := range n {
                result = append(result, i)
            }
            return result
        }
    }

    resultantNodes := []int{}
    for i, isSuspicious := range suspiciousNodes {
        if !isSuspicious {
            resultantNodes = append(resultantNodes, i)
        }
    }

    return resultantNodes
}

func markSuspicious(adj [][]int, i int, vis []bool, sn []bool) {
    if vis[i] {
        return
    }
    
    // mark suspicious
    sn[i] = true
    vis[i] = true

    // mark invoked functions
    for _, child := range adj[i] {
        if !vis[child] {
            markSuspicious(adj, child, vis, sn)
        }
    }
}

func isGood(adj [][]int, i int, vis []bool, sn []bool) bool {
    if vis[i] {
        return sn[i];
    }
    
    vis[i] = true;

    for _, nei := range adj[i] {
        if isGood(adj, nei, vis, sn) {
            return true
        }
    }
    return false
}