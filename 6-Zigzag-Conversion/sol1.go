func convert(s string, numRows int) string {
    ansMap := make(map[int]string)
    ans := ""

    cnt := 0
    for cnt < len(s) {
        i := 0
        for i < numRows && cnt < len(s) {
            ansMap[i] += string(s[cnt])
            cnt++
            i++
        }
        i = numRows - 2
        for i > 0 && cnt < len(s) {
            ansMap[i] += string(s[cnt])
            cnt++
            i--
        }
    }
    for i:=0; i < numRows; i++ {
        ans += ansMap[i]
    }
    return ans
}