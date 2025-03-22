package main

import "fmt"

type Node struct {
	value int
	left  *Node
	right *Node
}

func create(v int) *Node {
	return &Node{
		value: v,
	}
}

func (n *Node) pre_order() {
	if n == nil {
		return
	}
	fmt.Printf("%d ", n.value)
	n.left.pre_order()
	n.right.pre_order()
}

func (n *Node) addRec(v int) *Node {
	if n == nil {
		return create(v)
	}
	if v < n.value {
		n.left = n.left.addRec(v)
	} else if v > n.value {
		n.right = n.right.addRec(v)
	}
	return n
}

func (n *Node) add(v int) {
	n = n.addRec(v)
}

func (n *Node) serach(v int) *Node {
	if n == nil || n.value == v {
		return n
	}
	if v < n.value {
		return n.left.serach(v)
	}
	return n.right.serach(v)
}

func main() {
	tree := create(10)
	tree.add(5)
	tree.add(2)
	tree.add(7)
	tree.add(12)
	tree.pre_order()
	println()

	if r := tree.serach(10); r != nil {
		fmt.Printf("encontrou %d\n", r.value)
	}
}
