package main

import (
	"fmt"
)

const (
	HABILITAR_DEBUG = 1
	VER_ARVORE      = 2
	INSERIR_VALOR   = 3
	BUSCAR_VALOR    = 4
	VER_POST_ORDER  = 5
	VER_PRE_ORDER   = 6
	VER_IN_ORDER    = 7
	SAIR            = 8
)

var opcoes map[int]string

func init() {
	opcoes = make(map[int]string)
	opcoes[HABILITAR_DEBUG] = "Habiltar debug"
	opcoes[VER_ARVORE] = "Ver árvore"
	opcoes[INSERIR_VALOR] = "Inserir valor"
	opcoes[VER_POST_ORDER] = "Ver post order"
	opcoes[VER_IN_ORDER] = "Ver in order"
	opcoes[VER_PRE_ORDER] = "Ver pre order"
	opcoes[BUSCAR_VALOR] = "Buscar valor"
	opcoes[SAIR] = "Sair"

	logLevel = NONE
}

func showDebug() {
	var d string
	if isDebug() {
		d = "On"
	} else {
		d = "Off"
	}
	fmt.Printf("%d: %s (%s)\n", HABILITAR_DEBUG, opcoes[HABILITAR_DEBUG], d)
}

func showMenu() {
	fmt.Println()
	for k := 1; k <= len(opcoes); k++ {
		if k == HABILITAR_DEBUG {
			showDebug()
			continue
		}
		fmt.Printf("%d: %s\n", k, opcoes[k])
	}
	fmt.Printf("\nOpção: ")
}

func main() {
	fmt.Printf("Árvore AVL\n")

	var tree *TreeNode
	opt := -1

	for opt != SAIR {
		showMenu()
		fmt.Scanf("%d", &opt)
		switch opt {
		case HABILITAR_DEBUG:
			toggleDebug()
		case VER_ARVORE:
			if tree == nil {
				fmt.Printf("Árvore vazia\n")
				continue
			}
			fmt.Println()
			tree.prettyPrint("")
		case INSERIR_VALOR:
			var n int
			fmt.Printf("Digite o número: ")
			fmt.Scanf("%d", &n)
			tree = tree.add(n)
			fmt.Println("Ok!")
		case BUSCAR_VALOR:
			var d int
			fmt.Print("Digite o valor: ")
			fmt.Scanf("%d", &d)
			if r := tree.serach(d); r == nil {
				fmt.Println("Não existe na árvore")
			} else {
				fmt.Println("Existe na árvore")
			}
		case VER_PRE_ORDER:
			tree.preOrder()
			fmt.Println()
		case VER_IN_ORDER:
			tree.inOrder()
			fmt.Println()
		case VER_POST_ORDER:
			tree.postOrder()
			fmt.Println()
        case SAIR:
            fmt.Println("Desligando os motores")
        default:
            fmt.Println("Não conheço essa...")
		}
	}
}
