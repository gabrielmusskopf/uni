package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
	"time"

	"github.com/gabrielmusskopf/estrutura-de-dados-avancada/trabalhogb"
	"github.com/gabrielmusskopf/estrutura-de-dados-avancada/trabalhogb/types"
)

const (
	SEE_NAME_TREE = iota + 1
	SEE_CPF_TREE
	SEE_BIRTH_DATE_TREE
	SEARCH_NAME
	SEARCH_CPF
	SEARCH_BIRTH_DATE
	LEAVE
)

var indexDistance int
var opcoes map[int]string

func init() {
	opcoes = map[int]string{
		LEAVE:               "Sair",
		SEE_NAME_TREE:       "Ver árvore indexada por nome",
		SEE_CPF_TREE:        "Ver árvore indexada por CPF",
		SEE_BIRTH_DATE_TREE: "Ver árvore indexada por data de nascimento",
		SEARCH_NAME:         "Buscar por nome",
		SEARCH_CPF:          "Buscar por CPF",
		SEARCH_BIRTH_DATE:   "Buscar por data de nascimento",
	}

	indexDistance = 5
}

func printOption(opt int, s string) {
	nsize := strconv.Itoa(opt)
	ndots := indexDistance - len(nsize)
	var dots string
	for i := 0; i < ndots; i++ {
		dots += "."
	}
	fmt.Printf("%d%s%s\n", opt, dots, s)
}

func showMenu() {
	fmt.Println()
	for k := 1; k <= len(opcoes); k++ {
		printOption(k, opcoes[k])
	}
	fmt.Printf("\nOpção: ")
}

func askValue() string {
	reader := bufio.NewReader(os.Stdin)
	input, err := reader.ReadString('\n')
	if err != nil {
		log.Fatal(err)
	}
	return strings.TrimSpace(strings.TrimSuffix(input, "\n"))
}

func printPerson(p *types.Person) {
        fmt.Println()
		fmt.Printf("Nome:\t\t\t%s\n", p.Name)
		fmt.Printf("CPF:\t\t\t%s\n", p.CPF)
		fmt.Printf("RG:\t\t\t%s\n", p.RG)
		fmt.Printf("Data nascimento:\t%s\n", p.BirthDate)
		fmt.Printf("Cidade nascimento:\t%s\n", p.BirthCity)
}

func printIfExist[T trabalhogb.Ordered[T]](n *trabalhogb.TreeNode[T, *types.Person]) {
	if n != nil {
        printPerson(n.Value)
	} else {
		fmt.Printf("\nNão existe na árvore")
	}
}


func cmdLoop(index *trabalhogb.Index) {
	opt := -1
	for opt != LEAVE {
		showMenu()
		fmt.Scanf("%d", &opt)

		switch opt {
		case SEE_NAME_TREE:
			if index.Names == nil {
				fmt.Printf("Árvore vazia\n")
				continue
			}
			index.Names.PrettyPrint("")

		case SEE_CPF_TREE:
			if index.CPF == nil {
				fmt.Printf("Árvore vazia\n")
				continue
			}
			index.CPF.PrettyPrint("")

		case SEE_BIRTH_DATE_TREE:
			if index.BirthDate == nil {
				fmt.Printf("Árvore vazia\n")
				continue
			}
			index.BirthDate.PrettyPrint("")

		case SEARCH_NAME:
			fmt.Printf("Digite a chave: ")
			match := index.Names.SearchAllBy(types.String(askValue()), func(k1, k2 types.String) bool {
				return strings.HasPrefix(string(k1), string(k2))
			})
			for _, node := range match {
				printIfExist(node)
			}

		case SEARCH_CPF:
			fmt.Printf("Digite a chave: ")
			r := index.CPF.Search(types.String(askValue()))
			printIfExist(r)

		case SEARCH_BIRTH_DATE:
			fmt.Printf("Digite uma data inicial: ")
			input := askValue()
			start, err := time.Parse(types.DDMMYYYY, input)
			if err != nil {
				log.Fatalf("ERRO ao ler data %s\n", input)
			}

			fmt.Printf("Digite uma data final: ")
			input = askValue()
			end, err := time.Parse(types.DDMMYYYY, input)
			if err != nil {
				log.Fatalf("ERRO ao ler data %s\n", input)
			}

            matches := make([]*types.Person, 0)
			index.BirthDate.WalkAllBy(func(node trabalhogb.TreeNode[types.Date, *types.Person]) {
				date, err := time.Parse(types.DDMMYYYY, types.StringFromDate(node.Key))
				if err != nil {
					log.Fatalf("ERRO no parse de %s", node.Key)
				}
				if date.After(start) && date.Before(end) {
                    matches = append(matches, node.Value)
                }
			})
            if len(matches) == 0 {
                fmt.Printf("\nNão existem datas no período informado\n")
                continue
            }
			for _, node := range matches {
				printPerson(node)
			}

		case LEAVE:
			fmt.Print("Desligando os motores")

		default:
			fmt.Print("Não conheço essa...")
		}
		fmt.Println()
	}
}
