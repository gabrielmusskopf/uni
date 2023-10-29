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
)

//BirthDate ordenado por data, não por ordem lexisógrafica

const DDMMYYYY = "02/01/2006"

const (
	SEE_NAME_TREE = iota + 1
	SEE_CPF_TREE
	SEE_RG_TREE
	SEE_BIRTH_DATE_TREE
	SEE_BIRTH_CITY_TREE
	SEARCH_NAME
	SEARCH_CPF
	SEARCH_RG
	SEARCH_BIRTH_DATE
	SEARCH_BIRTH_CITY
	LEAVE
)

var indexDistance int
var opcoes map[int]string

func init() {
	opcoes = map[int]string{
		LEAVE:               "Sair",
		SEE_NAME_TREE:       "Ver árvore indexada por nome",
		SEE_CPF_TREE:        "Ver árvore indexada por CPF",
		SEE_RG_TREE:         "Ver árvore indexada por RG",
		SEE_BIRTH_DATE_TREE: "Ver árvore indexada por data de nascimento",
		SEE_BIRTH_CITY_TREE: "Ver árvore indexada por cidade de nascimento",
		SEARCH_NAME:         "Buscar por nome",
		SEARCH_CPF:          "Buscar por CPF",
		SEARCH_RG:           "Buscar por RG",
		SEARCH_BIRTH_DATE:   "Buscar por data de nascimento",
		SEARCH_BIRTH_CITY:   "Buscar por cidade de nascimento",
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

func printIfExist(p *Person) {
	if p != nil {
		fmt.Printf("Nome:\t\t\t%s\n", p.Name)
		fmt.Printf("CPF:\t\t\t%s\n", p.CPF)
		fmt.Printf("RG:\t\t\t%s\n", p.RG)
		fmt.Printf("Data nascimento:\t%s\n", p.BirthDate)
		fmt.Printf("Cidade nascimento:\t%s\n", p.BirthCity)
	} else {
		fmt.Printf("Não existe na árvore")
	}
}

type Date time.Time
type String string

func (d Date) Less(other Date) bool {
    return time.Time(other).Before(time.Time(d))
}

func (d Date) Compare(other Date) int {
    return time.Time(other).Compare(time.Time(d))
}

func (d Date) String() string {
    return time.Time(d).Format(DDMMYYYY)
}

func (s String) Compare(other String) int {
    return strings.Compare(string(s), string(other))
}

func (s String) Less(other String) bool {
    return strings.Compare(string(other), string(s)) == -1
}

func StringFromDate(s Date) string {
    return time.Time(s).Format(DDMMYYYY)
}

func DateFromString(s string) Date {
    time, err := time.Parse(DDMMYYYY, s)
    if err != nil {
        log.Fatal(err)
    }
    return Date(time)
}

func cmdLoop(pessoas []*Person) {

	var nomes *trabalhogb.TreeNode[String, *Person]
	var cpfs *trabalhogb.TreeNode[String, *Person]
	var rgs *trabalhogb.TreeNode[String, *Person]
	var birthDates *trabalhogb.TreeNode[Date, *Person]
	var birthCities *trabalhogb.TreeNode[String, *Person]

	for _, pessoa := range pessoas {
		nomes = nomes.Add(String(pessoa.Name), pessoa)
		cpfs = cpfs.Add(String(pessoa.CPF), pessoa)
		rgs = rgs.Add(String(pessoa.RG), pessoa)
		birthDates = birthDates.Add(DateFromString(pessoa.BirthDate), pessoa)
		birthCities = birthCities.Add(String(pessoa.BirthCity), pessoa)
	}

	opt := -1
	for opt != LEAVE {
		showMenu()
		fmt.Scanf("%d", &opt)

		switch opt {
		case SEE_NAME_TREE:
			if nomes == nil {
				fmt.Printf("Árvore vazia\n")
				continue
			}
			nomes.PrettyPrint("")

		case SEE_CPF_TREE:
			if cpfs == nil {
				fmt.Printf("Árvore vazia\n")
				continue
			}
			cpfs.PrettyPrint("")

		case SEE_RG_TREE:
			if rgs == nil {
				fmt.Printf("Árvore vazia\n")
				continue
			}
			rgs.PrettyPrint("")

		case SEE_BIRTH_DATE_TREE:
			if birthDates == nil {
				fmt.Printf("Árvore vazia\n")
				continue
			}
			birthDates.PrettyPrint("")

		case SEE_BIRTH_CITY_TREE:
			if birthCities == nil {
				fmt.Printf("Árvore vazia\n")
				continue
			}
			birthCities.PrettyPrint("")

		case SEARCH_NAME:
			fmt.Printf("Digite a chave: ")
			match := nomes.SearchAllBy(String(askValue()), func(k1, k2 String) bool {
				return strings.Contains(string(k1), string(k2))
			})
			for _, node := range match {
				printIfExist(node.Value)
				println()
			}

		case SEARCH_CPF:
			fmt.Printf("Digite a chave: ")
			r := cpfs.Search(String(askValue()))
			printIfExist(r.Value)

		case SEARCH_RG:
			fmt.Printf("Digite a chave: ")
			r := rgs.Search(String(askValue()))
			printIfExist(r.Value)

		case SEARCH_BIRTH_DATE:
			fmt.Printf("Digite uma data inicial: ")
			input := askValue()
			start, err := time.Parse(DDMMYYYY, input)
			if err != nil {
				log.Fatalf("ERRO ao ler data %s\n", input)
			}

			fmt.Printf("Digite uma data final: ")
			input = askValue()
			end, err := time.Parse(DDMMYYYY, input)
			if err != nil {
				log.Fatalf("ERRO ao ler data %s\n", input)
			}

            matches := make([]*Person, 0)
			birthDates.WalkAllBy(func(node trabalhogb.TreeNode[Date, *Person]) {
				date, err := time.Parse(DDMMYYYY, StringFromDate(node.Key))
				if err != nil {
					log.Fatalf("ERRO no parse de %s", node.Key)
				}
				if date.After(start) && date.Before(end) {
                    matches = append(matches, node.Value)
                }
			})
			for _, node := range matches {
                println()
				printIfExist(node)
			}

		case SEARCH_BIRTH_CITY:
			fmt.Printf("Digite a chave: ")
			r := birthCities.Search(String(askValue()))
			printIfExist(r.Value)

		case LEAVE:
			fmt.Print("Desligando os motores")

		default:
			fmt.Print("Não conheço essa...")
		}
		fmt.Println()
	}
}
