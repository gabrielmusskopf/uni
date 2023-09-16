package main

import (
	"fmt"
	"log"
)

var debug bool

func info(s string, v... any) {
    if debug {
        fmt.Printf(s, v...)
    }
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

func (n *TreeNode) printn() {
    fmt.Printf("(%d, %d)  ", n.value, n.bf)
}

func printNTimes(c string, n int) {
    for i:=0; i<n; i++ {
        fmt.Print(c)
    }
}

func (n *TreeNode) prettyPrint(sizeTree int) {
    if n == nil {
        return
    }

    spaces := sizeTree - n.depth()
    printNTimes(" ", spaces*2)
    fmt.Printf("%d\n", n.value)

    n.left.prettyPrint(sizeTree)
    n.right.prettyPrint(sizeTree)
    fmt.Println()
}

func (n *TreeNode) prettyPrintC(sizeTree int) {
    if n == nil {
        return
    }

    spaces := sizeTree - n.depth()
    printNTimes(" ", spaces*3)
    n.printn()
    fmt.Println()

    n.left.prettyPrintC(sizeTree)
    n.right.prettyPrintC(sizeTree)
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

func (n *TreeNode) pre_order() {
	if n == nil {
		return
	}
	fmt.Printf("v: %d fb: %d\n", n.value, n.bf)
	n.left.pre_order()
	n.right.pre_order()
}

func (n *TreeNode) in_order() {
	if n == nil {
		return
	}
	n.left.pre_order()
	fmt.Printf("%d ", n.value)
	n.right.pre_order()
}

func (n *TreeNode) post_order() {
	if n == nil {
		return
	}
	n.left.pre_order()
	n.right.pre_order()
	fmt.Printf("%d ", n.value)
}

func (n *TreeNode) rotateRight() *TreeNode {
    /*
    * Node *x = y->left;
    Node *T2 = x->right;
 
    // Perform rotation
    x->right = y;
    y->left = T2;
 
    // Update heights
    y->height = max(height(y->left),
                    height(y->right)) + 1;
    x->height = max(height(x->left),
                    height(x->right)) + 1;
 
    // Return new root
    return x;/
    */

    x := n.left
    t2 := x.right

    x.right = n
    n.left = t2


    n.bf = n.balanceFactor()
    x.bf = x.balanceFactor()

    return x
}

func (n *TreeNode) addRec(v int) *TreeNode {
	if n == nil {
		return create(v)
	}
	if v < n.value {
		n.left = n.left.addRec(v)
	} else if v > n.value {
		n.right = n.right.addRec(v)
	}
	n.bf = n.balanceFactor()

    //if n.bf >= 2 { //rotação simples direita
    //    info("Nó %d com FB: %d. Rotação simples a direita\n", n.value, n.bf)
    //    return n.rotateRight()
    //}

	return n
}

func (n *TreeNode) add(v int) {
	n = n.addRec(v)
    info("Adicionado %d\n", v)
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

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

// FB(p) = h(sae(p)) - h(sad(p))
func (n *TreeNode) balanceFactor() int {
	return n.left.depth() - n.right.depth()
}

func (n *TreeNode) depth() int {
	if n == nil {
		return 0
	}
	return 1 + max(n.left.depth(), n.right.depth())
}

func main() {
    debug = true

	tree := create(20)
	tree.add(10)
    tree.prettyPrintC(tree.depth())
	tree.add(30)
    tree.prettyPrintC(tree.depth())
	tree.add(5)
    tree.prettyPrintC(tree.depth())

    fmt.Printf("Árvore balanceada:\n")
    tree.prettyPrintC(tree.depth())
}

