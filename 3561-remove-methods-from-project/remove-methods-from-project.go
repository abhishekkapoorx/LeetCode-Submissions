func remainingMethods(n int, k int, invocations [][]int) []int {
	edges := make([][]int, n)
	inDegree := make([]int, n)

	for _, inv := range invocations {
		u, v := inv[0], inv[1]
		edges[u] = append(edges[u], v)
		inDegree[v]++
	}

	queue := []int{k}
	suspicious := make([]bool, n)
	suspicious[k] = true

	for len(queue) > 0 {
		u := queue[0]
		queue = queue[1:]
		for _, v := range edges[u] {
			inDegree[v]--

			if !suspicious[v] {
				queue = append(queue, v)
				suspicious[v] = true
			}
		}
	}

	canRemoveAll := true
	remaining := []int{}

	for i := 0; i < n; i++ {
		if suspicious[i] && inDegree[i] > 0 {
			canRemoveAll = false
			break
		} else if !suspicious[i] {
			remaining = append(remaining, i)
		}
	}

	if !canRemoveAll {
		allNodes := make([]int, n)
		for i := 0; i < n; i++ {
			allNodes[i] = i
		}
		return allNodes
	}

	return remaining
}