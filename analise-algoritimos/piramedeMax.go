package main

import "fmt"

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func piramedeMaxMemoizado(n int, P, M [][]int, i, j int, iter *int) int {
	if M[i][j] > 0 {
		return M[i][j]
	}
	*iter++
	if i+1 == n {
		M[i][j] = P[i][j]
		return M[i][j]
	}
	M[i][j] = P[i][j] + max(
		piramedeMaxMemoizado(n, P, M, i+1, j, iter),
		piramedeMaxMemoizado(n, P, M, i+1, j+1, iter))
	return M[i][j]
}

func piramedeMax(n int, P [][]int, i, j int, iter *int) int {
	*iter++
	if i+1 == n {
		return P[i][j]
	}
	return P[i][j] + max(
		piramedeMax(n, P, i+1, j, iter),
		piramedeMax(n, P, i+1, j+1, iter))
}

func main() {
	P := [][]int{
		{7},             //	    7
		{3, 8},          //	   3 8
		{8, 1, 0},       //	  8 1 0
		{2, 7, 4, 4},    //	 2 7 4 4
		{4, 5, 2, 6, 1}, //	4 5 2 6 1
	}
	var M [][]int
	for i := 0; i < len(P); i++ {
		row := make([]int, 0)
		for j := 0; j < i+1; j++ {
			row = append(row, 0)
		}
		M = append(M, row)
	}

	iter := 0
	r := piramedeMax(5, P, 0, 0, &iter)
	fmt.Printf("[Normal]\tresultado = %d\tinterações = %d\n", r, iter)

	iter = 0
	r = piramedeMaxMemoizado(5, P, M, 0, 0, &iter)
	fmt.Printf("[Memoizado]\tresultado = %d\tinterações = %d\n", r, iter)
}
