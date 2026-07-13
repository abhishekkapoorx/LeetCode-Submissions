func sequentialDigits(low int, high int) []int {
    ans := make([]int, 0, 0)

    for i := 1; i <= 9; i++ {
        starter := i
        for starter <= high {
            if starter >= low {
                ans = append(ans, starter)
            }

            nextInt := starter % 10 + 1
            if nextInt > 9 {
                break;
            }
            starter = starter * 10
            starter = starter + nextInt;
        }
    }
    sort.Ints(ans);
    return ans;
}