package main

import (
	"bufio"
	"log"
	"os"
	"strings"
)

const (
	CPF = iota
	RG
	Name
	BirthDate
	BirthCity
)

type Person struct {
	CPF       string
	RG        string
	Name      string
	BirthDate string
	BirthCity string
}

func NewPerson(cpf, rg, name, birthDate, birthCity string) *Person {
	return &Person{
		CPF:       cpf,
		RG:        rg,
		Name:      name,
		BirthDate: birthDate,
		BirthCity: birthCity,
	}
}

func main() {

	file, err := os.Open("data.csv")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	people := make([]*Person, 0)

	scanner := bufio.NewScanner(file)
	scanner.Scan() // header

	for scanner.Scan() {
		tokens := strings.Split(scanner.Text(), ";")
		person := NewPerson(tokens[CPF], tokens[RG], tokens[Name], tokens[BirthDate], tokens[BirthCity])
		people = append(people, person)
	}

	cmdLoop(people)
}
