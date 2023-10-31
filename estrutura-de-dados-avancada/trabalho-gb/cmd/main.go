package main

import (
	"github.com/gabrielmusskopf/estrutura-de-dados-avancada/trabalhogb"
	"github.com/gabrielmusskopf/estrutura-de-dados-avancada/trabalhogb/data"
)

func main() {
    reader := &data.CsvPersonReader{}
    people := reader.Read("data.test.csv")
    index := trabalhogb.BuildIndexes(people)

	cmdLoop(index)
}
