func smallestNumber(n int, t int) int {
    for findProduct(n) % t != 0 {
        n++
    }
    return n
}

func findProduct(n int) int {
    prod := 1
    for n > 0 {
        prod *= n % 10
        n /= 10
    }
    return prod
}