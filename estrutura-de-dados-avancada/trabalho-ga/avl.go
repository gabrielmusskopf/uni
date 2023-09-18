package main

import "fmt"

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

func create(v int) *TreeNode {
	return &TreeNode{
		value: v,
		bf:    0,
	}
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

func (n *TreeNode) balance() *TreeNode {
	if n == nil {
		return n
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

func (n *TreeNode) addRec(v int, i *int) *TreeNode {
	*i++
	if n == nil {
		return create(v)
	}
	if v < n.value {
		debug("%d é menor do que %d\n", v, n.value)
		n.left = n.left.addRec(v, i)
	} else if v > n.value {
		debug("%d é maior do que %d\n", v, n.value)
		n.right = n.right.addRec(v, i)
	}

	return n.balance()
}

func (n *TreeNode) add(v int) *TreeNode {
	i := 0
	t := n.addRec(v, &i)
	debug("%d interações para inserir %d\n", i, v)
	return t
}

func (n *TreeNode) serachRec(v int, i *int) *TreeNode {
	*i++
	if n == nil || n.value == v {
		return n
	}
	if v < n.value {
		return n.left.serachRec(v, i)
	}
	return n.right.serachRec(v, i)
}

func (n *TreeNode) serach(v int) *TreeNode {
	i := 0
	t := n.serachRec(v, &i)
	debug("%d interações para buscar %d\n", i, v)
	return t
}

// FB(p) = h(sae(p)) - h(sad(p))
func (n *TreeNode) balanceFactor() int {
	return n.left.depth() - n.right.depth()
}

func (n *TreeNode) min() *TreeNode {
	current := n
	for current.left != nil {
		current = current.left
	}
	return current
}

func (n *TreeNode) removeRec(v int, i *int) *TreeNode {
	*i++
	if n == nil {
		return n
	}

	if v < n.value {
		n.left = n.left.removeRec(v, i)
	} else if v > n.value {
		n.right = n.right.removeRec(v, i)
	} else {
		// está no node para ser deletado
		if n.left == nil || n.right == nil {
			var temp *TreeNode
			if n.left != nil {
				temp = n.left
			} else {
				temp = n.right
			}

			if temp == nil {
				// sem filho
				n = nil
			} else {
				// um filho
				*n = *temp
			}
		} else {
			temp := n.right.min()
			n.value = temp.value
			n.right = n.right.removeRec(temp.value, i)
		}
	}

	return n.balance()
}

func (n *TreeNode) remove(v int) *TreeNode {
	i := 0
	t := n.removeRec(v, &i)
	debug("%d interações para remover %d\n", i, v)
	return t
}

/*
type CountIter struct {
    i int
    operation func(int, *int) *TreeNode
}

func (n *TreeNode) countIter(op func(int, *int) *TreeNode) *CountIter {
    return &CountIter{
        operation: op,
    }
}

func (c *CountIter) exec(v int) *TreeNode {
	i := 0
    t := c.operation(v, &i)
	debug("%d interações\n", i)
    return t
}
*/
