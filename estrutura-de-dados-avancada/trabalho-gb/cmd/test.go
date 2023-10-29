package main

import (
	"fmt"

	"github.com/gabrielmusskopf/estrutura-de-dados-avancada/trabalhogb"
)

//import "github.com/gabrielmusskopf/avl"

func test() {
	//avl.TreeEvents = &avl.Queue[string]{}

	pessoas := map[int]*Pessoa{
		20: {Nome: "Zeli", CPF: "1"},
		40: {Nome: "Carlos", CPF: "2"},
		30: {Nome: "Albino", CPF: "3"},
		10: {Nome: "Bario", CPF: "4"},
		36: {Nome: "Maria", CPF: "5"},
	}

	var nomes *trabalhogb.TreeNode[string, *Pessoa]
	for _, pessoa := range pessoas {
		nomes = nomes.Add(pessoa.Nome, pessoa)
	}
	nomes.PrettyPrint("")

	n := nomes.Serach("Maria")
	if n != nil {
		fmt.Printf("Encontrou %v. Valor: %v\n", n.Key, n.Value)
        fmt.Printf("Dados de %v: %v no endereço: %v\n", n.Key, n.Value, &n.Value)
	}

	var cpfs *trabalhogb.TreeNode[string, *Pessoa]
	for _, pessoa := range pessoas {
		cpfs = cpfs.Add(pessoa.CPF, pessoa)
	}
	cpfs.PrettyPrint("")

	n2 := cpfs.Serach("5")
	if n2 != nil {
		fmt.Printf("Encontrou %v. Valor: %v\n", n2.Key, n2.Value)
        fmt.Printf("Dados de %v: %v no endereço: %v\n", n.Key, n.Value, &n.Value)
	}
}
