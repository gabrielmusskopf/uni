package main

import (
	"fmt"
	"log"
)

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

type TreeNode struct {
	value int
	bf    int
	left  *TreeNode
	right *TreeNode
}

func Create(v int) *TreeNode {
	return &TreeNode{
		value: v,
		bf:    0,
	}
}

type Queue struct {
	values []*TreeNode
}

func (q *Queue) enqueue(v *TreeNode) {
	q.values = append(q.values, v)
}

func (q *Queue) dequeue() *TreeNode {
	if q.isEmpty() {
		log.Fatal("nada para remover da fila!")
	}
	v := q.values[0]
	q.values = q.values[1:]
	return v
}

func (q *Queue) isEmpty() bool {
	return len(q.values) == 0
}

func (n *TreeNode) depth() int {
	if n == nil {
		return 0
	}
	return 1 + max(n.left.depth(), n.right.depth())
}

func (n *TreeNode) printn() {
	fmt.Printf("(%d, %d)  ", n.value, n.bf)
}

func (n *TreeNode) prettyPrint(padding string) {
	if n == nil {
		return
	}

	fmt.Printf("%s", padding)
	n.printn()
	fmt.Println()

	n.left.prettyPrint(padding + "  ")
	n.right.prettyPrint(padding + "  ")
}

func (n *TreeNode) print() {
	if n == nil {
		return
	}
	n.printn()
	q := Queue{}
	q.enqueue(n.left)
	q.enqueue(n.right)

	for !q.isEmpty() {
		l, r := q.dequeue(), q.dequeue()
		if l == nil && r == nil {
			continue
		}
		l.printn()
		r.printn()
		q.enqueue(l.left)
		q.enqueue(l.right)
		q.enqueue(r.left)
		q.enqueue(r.right)
	}
}

func (n *TreeNode) preOrder() {
	if n == nil {
		return
	}
	fmt.Printf("%d \n", n.value)
	n.left.preOrder()
	n.right.preOrder()
}

func (n *TreeNode) inOrder() {
	if n == nil {
		return
	}
	n.left.inOrder()
	fmt.Printf("%d ", n.value)
	n.right.inOrder()
}

func (n *TreeNode) postOrder() {
	if n == nil {
		return
	}
	n.left.postOrder()
	n.right.postOrder()
	fmt.Printf("%d ", n.value)
}

func (n *TreeNode) rotateRight() *TreeNode {
	x := n.left
	t2 := x.right

	x.right = n
	n.left = t2

	n.bf = n.balanceFactor()
	x.bf = x.balanceFactor()

	return x
}

func (n *TreeNode) rotateLeft() *TreeNode {
	x := n.right
	t2 := x.left

	x.left = n
	n.right = t2

	n.bf = n.balanceFactor()
	x.bf = x.balanceFactor()

	return x
}

func (n *TreeNode) add(v int) *TreeNode {
	if n == nil {
		return Create(v)
	}
	if v < n.value {
		debug("%d é menor do que %d\n", v, n.value)
		n.left = n.left.add(v)
	} else if v > n.value {
		debug("%d é maior do que %d\n", v, n.value)
		n.right = n.right.add(v)
	}

	n.bf = n.balanceFactor()

	//simples
	//rotação simples direita
	if n.bf >= 2 && n.left.bf >= 0 {
		debug("Nó %d com FB: %d. Rotação simples a direita\n", n.value, n.bf)
		return n.rotateRight()
	}

	//rotação simples esquerda
	if n.bf <= -2 && n.right.bf <= 0 {
		debug("Nó %d com FB: %d. Rotação simples a esquerda\n", n.value, n.bf)
		return n.rotateLeft()
	}

	//duplas
	//rotação dupla direita
	if n.bf >= 2 && n.left.bf < 0 {
		debug("Nó %d com FB: %d. Rotação dupla a direita\n", n.value, n.bf)
		n.left = n.left.rotateLeft()
		return n.rotateRight()
	}

	//rotação dupla esquerda
	if n.bf <= -2 && n.right.bf > 0 {
		debug("Nó %d com FB: %d. Rotação dupla a esquerda\n", n.value, n.bf)
		n.right = n.right.rotateRight()
		return n.rotateLeft()
	}

	return n
}

func (n *TreeNode) serach(v int) *TreeNode {
	if n == nil || n.value == v {
		return n
	}
	if v < n.value {
		return n.left.serach(v)
	}
	return n.right.serach(v)
}

// FB(p) = h(sae(p)) - h(sad(p))
func (n *TreeNode) balanceFactor() int {
	return n.left.depth() - n.right.depth()
}

func (n *TreeNode) remove(v int) bool {
    //TODO: Implementar
    return false
}

