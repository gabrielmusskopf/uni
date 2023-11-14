package main

import "fmt"

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

// Sequência Continua Mais Longa
// Algoritmo sem otimizações
func scml(n int, arr []int, i int, iter *int) int {
	*iter++
	if i == n {
		return 1
	}
	m := 1
	for j := i + 1; j < n; j++ {
		if arr[j] > arr[i] {
			m = max(m, 1+scml(n, arr, j, iter))
		}
	}
	return m
}

// Sequência Continua Mais Longa
// Memoizado
func scmlMemo(n int, arr, C []int, i int, iter *int) int {
	if C[i] != -1 {
		return C[i]
	}
	*iter++
	if i == n {
		C[i] = 1
		return C[i]
	}
	m := 1
	for j := i + 1; j < n; j++ {
		if arr[j] > arr[i] {
			m = max(m, 1+scmlMemo(n, arr, C, j, iter))
		}
	}
	C[i] = m
	return m
}

// Sequência Continua Mais Longa
// Top-down
//TODO


func main() {
	arr := []int{7, 6, 10, 3, 4, 1, 8, 9, 5, 2}
	fmt.Print("array: [ ")
	for _, e := range arr {
		fmt.Printf("%d ", e)
	}
	fmt.Print("]\n\n")

	iter := 0
	largest := 0
	res := make([]int, 0)
	for i := 0; i < len(arr); i++ {
		l := scml(len(arr), arr, i, &iter)
		res = append(res, l)
		largest = max(largest, l)
	}

	fmt.Printf("[brute force] Seqência mais longa para cada índice: %d interações\n", iter)
	for i := 0; i < len(res); i++ {
		fmt.Printf("%d ", res[i])
	}
    fmt.Printf("=> %d\n\n", largest)

	// Bottom-up
	iter = 0
	largest = 0
	res = make([]int, 0)
	cache := make([]int, len(arr))
	for i := 0; i < len(arr); i++ {
		cache[i] = -1
	}
	for i := 0; i < len(arr); i++ {
		l := scmlMemo(len(arr), arr, cache, i, &iter)
		res = append(res, l)
		largest = max(largest, l)
	}

	fmt.Printf("[bottom up] Seqência mais longa para cada índice: %d interações\n", iter)
	for i := 0; i < len(res); i++ {
		fmt.Printf("%d ", res[i])
	}
    fmt.Printf("=> %d\n", largest)

}
