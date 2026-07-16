func longestPalindrome(s string) string {
    ans := ""

    for i := 0; i < len(s); i++ {
        // odd case
        j1 := i
        j2 := i
        for j1 >= 0 && j2 < len(s) && s[j1] == s[j2] {
            if (j2-j1+1) > len(ans) {
                ans = s[j1:j2+1]
            }
            j1--;
            j2++;
        }
        // even case
        j1 = i-1
        j2 = i
        for j1 >= 0 && j2 < len(s) && s[j1] == s[j2] {
            if (j2-j1+1) > len(ans) {
                ans = s[j1:j2+1]
            }
            j1--;
            j2++;
        }
        
    }
    return ans;
}