func gcdSum(nums []int) int64 {
    if len(nums) == 0 {
        return 0
    }

    mx := nums[0]
    prefixGcd := make([]int64, len(nums))

    for i := 0; i < len(nums); i++ {
        if nums[i] > mx {
            mx = nums[i]
        }
        prefixGcd[i] = gcd(int64(mx), int64(nums[i]))
    }

    slices.SortFunc(prefixGcd, func(a, b int64) int {
        if a < b {
            return 1
        }
        if a > b {
            return -1
        }
        return 0
    })

    i := 0
    j := len(prefixGcd) - 1

    var sum int64

    for i < j {
        sum += gcd(prefixGcd[i], prefixGcd[j])
        i++
        j--
    }

    return sum
}

func gcd(a, b int64) int64 {
    if b == 0 {
        return a
    }
    return gcd(b, a%b)
}