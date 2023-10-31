package types

import (
	"log"
	"strings"
	"time"
)

const DDMMYYYY = "02/01/2006"

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

type String string

func (s String) Compare(other String) int {
	return strings.Compare(string(s), string(other))
}

func (s String) Less(other String) bool {
	return s.Compare(other) == -1
}

type Date time.Time

func (d Date) Less(other Date) bool {
	return d.Compare(other) == -1
}

func (d Date) Compare(other Date) int {
	return time.Time(d).Compare(time.Time(other))
}

func (d Date) String() string {
	return time.Time(d).Format(DDMMYYYY)
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
