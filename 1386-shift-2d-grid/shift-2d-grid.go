func shiftGrid(grid [][]int, k int) [][]int {
    for r:=0; r < k; r++ {
        for i, g := range grid {
            grid[i] = shiftList(g);
        }
        grid = shiftFirstRow(grid)
    }
    return grid
}

func shiftList(list []int) []int {
    rotated := make([]int, len(list))
    copy(rotated[1:], list[:len(list)-1])
    copy(rotated[:1], list[len(list)-1:])
    return rotated
}

func shiftFirstRow(grid [][]int) [][]int {
    firstRow := make([]int, len(grid))
    for i, r := range grid {
        firstRow[i] = r[0];
    }

    firstRow = shiftList(firstRow);
    for i, _ := range grid {
        grid[i][0] = firstRow[i];
    }
    return grid
}