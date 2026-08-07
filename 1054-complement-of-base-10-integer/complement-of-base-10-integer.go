func bitwiseComplement(n int) int {
    if n == 0 || n == 1 {
        return n ^ 1
    }
    i := 32
    for (n >> i) & 1 != 1 && i >= 0 {
        i--
    }
    for i >= 0 {
        n ^= (1<<i)
        i--
    }
    return n
}