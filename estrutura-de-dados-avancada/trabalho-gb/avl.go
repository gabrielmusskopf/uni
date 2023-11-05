package trabalhogb

import (
	"fmt"
	"time"
)

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

type Ordered[T any] interface {
	Compare(T) int
	CompareWithLength(T) int
	Less(T) bool
}

type TreeNode[K Ordered[K], V any] struct {
	Key   K
	Value V
	BF    int
	Left  *TreeNode[K, V]
	Right *TreeNode[K, V]
}

func create[K Ordered[K], V any](k K, v V) *TreeNode[K, V] {
	return &TreeNode[K, V]{
		Key:   k,
		Value: v,
		BF:    0,
	}
}

func (n *TreeNode[K, V]) depth() int {
	if n == nil {
		return 0
	}
	return 1 + max(n.Left.depth(), n.Right.depth())
}

func (n *TreeNode[K, V]) printn() {
	fmt.Printf("(%v, %d)  ", n.Key, n.BF)
}

func (n *TreeNode[K, V]) PrettyPrint(padding string) {
	if n == nil {
		return
	}

	fmt.Printf("%s", padding)
	n.printn()
	fmt.Println()

	n.Left.PrettyPrint(padding + "  ")
	n.Right.PrettyPrint(padding + "  ")
}

func (n *TreeNode[K, V]) PreOrder() {
	if n == nil {
		return
	}
	fmt.Printf("%v \n", n.Key)
	n.Left.PreOrder()
	n.Right.PreOrder()
}

func (n *TreeNode[K, V]) InOrder() {
	if n == nil {
		return
	}
	n.Left.InOrder()
	fmt.Printf("%v ", n.Key)
	n.Right.InOrder()
}

func (n *TreeNode[K, V]) PostOrder() {
	if n == nil {
		return
	}
	n.Left.PostOrder()
	n.Right.PostOrder()
	fmt.Printf("%v ", n.Key)
}

func (n *TreeNode[K, V]) rotateRight() *TreeNode[K, V] {
	matches := n.Left
	t2 := matches.Right

	matches.Right = n
	n.Left = t2

	n.BF = n.balanceFactor()
	matches.BF = matches.balanceFactor()

	return matches
}

func (n *TreeNode[K, V]) rotateLeft() *TreeNode[K, V] {
	matches := n.Right
	t2 := matches.Left

	matches.Left = n
	n.Right = t2

	n.BF = n.balanceFactor()
	matches.BF = matches.balanceFactor()

	return matches
}

func (n *TreeNode[K, V]) balance() *TreeNode[K, V] {
	if n == nil {
		return n
	}

	n.BF = n.balanceFactor()

	//simples
	//rotação simples direita
	if n.BF >= 2 && n.Left.BF >= 0 {
		return n.rotateRight()
	}

	//rotação simples esquerda
	if n.BF <= -2 && n.Right.BF <= 0 {
		return n.rotateLeft()
	}

	//duplas
	//rotação dupla direita
	if n.BF >= 2 && n.Left.BF < 0 {
		n.Left = n.Left.rotateLeft()
		return n.rotateRight()
	}

	//rotação dupla esquerda
	if n.BF <= -2 && n.Right.BF > 0 {
		n.Right = n.Right.rotateRight()
		return n.rotateLeft()
	}

	return n
}

func (n *TreeNode[K, V]) addRec(k K, v V, i *int) *TreeNode[K, V] {
	*i++
	if n == nil {
		return create(k, v)
	}
	if k.Less(n.Key) {
		n.Left = n.Left.addRec(k, v, i)
	} else if n.Key.Less(k) {
		n.Right = n.Right.addRec(k, v, i)
	}

	return n.balance()
}

func (n *TreeNode[K, V]) Add(k K, v V) *TreeNode[K, V] {
	if n.Search(k) != nil {
		return n
	}
	var i int
	return n.addRec(k, v, &i)
}

func (n *TreeNode[K, V]) searchRec(k K, i *int) *TreeNode[K, V] {
	*i++
	if n == nil || n.Key.Compare(k) == 0 {
		return n
	}
	if k.Less(n.Key) {
		return n.Left.searchRec(k, i)
	}
	return n.Right.searchRec(k, i)
}

// TODO: Do not traverse the entire tree?
func (n *TreeNode[K, V]) searchAllByRec(k K, match *[]*TreeNode[K, V], matchFunc func(K, K) bool, iter *int) {
	if n == nil {
		return
	}
    *iter++
	if matchFunc(n.Key, k) {
		*match = append(*match, n)
	}
	if k.CompareWithLength(n.Key) == -1 || k.CompareWithLength(n.Key) == 0 {
		n.Left.searchAllByRec(k, match, matchFunc, iter)
	} 
	if k.CompareWithLength(n.Key) == 1 || k.CompareWithLength(n.Key) == 0 {
		n.Right.searchAllByRec(k, match, matchFunc, iter)
	}
}

func (n *TreeNode[K, V]) WalkAllBy(walkFunc func(TreeNode[K, V])) {
	if n == nil {
		return
	}
	walkFunc(*n)
	n.Left.WalkAllBy(walkFunc)
	n.Right.WalkAllBy(walkFunc)
}

func (n *TreeNode[K, V]) Search(k K) *TreeNode[K, V] {
	var t *TreeNode[K, V]
	_, _ = measure(func(i *int) {
		t = n.searchRec(k, i)
	})
	//fmt.Printf("buscado %v em %d interações\n", k, iter)
	return t
}

func (n *TreeNode[K, V]) SearchAllBy(k K, matchFunc func(K, K) bool) []*TreeNode[K, V] {
	matches := make([]*TreeNode[K, V], 0)
	_, _ = measure(func(i *int) {
		n.searchAllByRec(k, &matches, matchFunc, i)
	})
	//fmt.Printf("buscado %v em %d interações\n", k, iter)
	return matches
}

// FB(p) = h(sae(p)) - h(sad(p))
func (n *TreeNode[K, V]) balanceFactor() int {
	return n.Left.depth() - n.Right.depth()
}

func (n *TreeNode[K, V]) min() *TreeNode[K, V] {
	current := n
	for current.Left != nil {
		current = current.Left
	}
	return current
}

func (n *TreeNode[K, V]) removeRec(k K, i *int) *TreeNode[K, V] {
	*i++
	if n == nil {
		return n
	}

	if k.Less(n.Key) {
		n.Left = n.Left.removeRec(k, i)
	} else if n.Key.Less(k) {
		n.Right = n.Right.removeRec(k, i)
	} else {
		// está no node para ser deletado
		if n.Left == nil || n.Right == nil {
			var temp *TreeNode[K, V]
			if n.Left != nil {
				temp = n.Left
			} else {
				temp = n.Right
			}

			if temp == nil {
				// sem filho
				n = nil
			} else {
				// um filho
				*n = *temp
			}
		} else {
			temp := n.Right.min()
			n.Value = temp.Value
			n.Right = n.Right.removeRec(temp.Key, i)
		}
	}

	return n.balance()
}

func (n *TreeNode[K, V]) Remove(k K) *TreeNode[K, V] {
	var i int
	return n.removeRec(k, &i)
}

func measure(f func(*int)) (int, time.Duration) {
	iter := 0
	start := time.Now()
	f(&iter)
	return iter, time.Since(start)
}
