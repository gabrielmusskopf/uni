package main

import (
	"fmt"

	"github.com/gabrielmusskopf/avl"
	"github.com/gabrielmusskopf/avl/http"
)

const (
	HABILITAR_DEBUG = 1
	VER_ARVORE      = 2
	INSERIR_VALOR   = 3
	BUSCAR_VALOR    = 4
	REMOVER_VALOR   = 5
	VER_POST_ORDER  = 6
	VER_PRE_ORDER   = 7
	VER_IN_ORDER    = 8
	INICIAR_HTTP    = 9
	SAIR            = 10
)

var opcoes map[int]string

func init() {
	opcoes = map[int]string{
		SAIR:            "Sair",
		HABILITAR_DEBUG: "Habiltar debug",
		VER_ARVORE:      "Ver árvore",
		INSERIR_VALOR:   "Inserir valor",
		VER_POST_ORDER:  "Ver post order",
		VER_IN_ORDER:    "Ver in order",
		VER_PRE_ORDER:   "Ver pre order",
		BUSCAR_VALOR:    "Buscar valor",
		REMOVER_VALOR:   "Remover valor",
		INICIAR_HTTP:    "Iniciar servidor HTTP",
	}

	avl.LogLevel = avl.NONE
}

func showDebug() {
	var d string
	if avl.IsDebug() {
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

func askInt() int {
	var n int
	fmt.Printf("Digite o número: ")
	fmt.Scanf("%d", &n)
	return n
}

func doOr(n *avl.TreeNode, fail, sucess string) {
	if n != nil {
		fmt.Print(sucess)
	} else {
		fmt.Print(fail)
	}
}

func main() {
	fmt.Printf("Árvore AVL\n")

	opt := -1

	for opt != SAIR {
		showMenu()
		fmt.Scanf("%d", &opt)

		switch opt {
		case HABILITAR_DEBUG:
			avl.ToggleDebug()

		case VER_ARVORE:
			if avl.Tree == nil {
				fmt.Printf("Árvore vazia\n")
				continue
			}
			avl.Tree.PrettyPrint("")

		case INSERIR_VALOR:
			d := askInt()
			avl.Tree = avl.Tree.Add(d)
			doOr(avl.Tree, "Não pôde adicionar", "Ok!")

		case BUSCAR_VALOR:
			d := askInt()
			doOr(avl.Tree.Serach(d), "Não existe na árvore", "Existe na árvore")

		case REMOVER_VALOR:
			d := askInt()
			avl.Tree = avl.Tree.Remove(d)
			fmt.Print("Ok!")

		case VER_PRE_ORDER:
			avl.Tree.PreOrder()

		case VER_IN_ORDER:
			avl.Tree.InOrder()

		case VER_POST_ORDER:
			avl.Tree.PostOrder()

		case INICIAR_HTTP:
			go http.InitHttp()
			fmt.Printf("Servidor iniciado em http://127.0.0.1:3333")

		case SAIR:
			fmt.Print("Desligando os motores")

		default:
			fmt.Print("Não conheço essa...")
		}
		fmt.Println()
	}
}

//Ideia: servidor HTTP para visualização da árvore
