package trabalhogb

import "github.com/gabrielmusskopf/estrutura-de-dados-avancada/trabalhogb/types"

type Index struct {
	Names     *TreeNode[types.String, *types.Person]
	CPF       *TreeNode[types.String, *types.Person]
	BirthDate *TreeNode[types.Date, *types.Person]
}

func BuildIndexes(people []*types.Person) *Index {
	indexed := &Index{}
	for _, person := range people {
		indexed.Names = indexed.Names.Add(types.String(person.Name), person)
		indexed.CPF = indexed.CPF.Add(types.String(person.CPF), person)
		indexed.BirthDate = indexed.BirthDate.Add(types.DateFromString(person.BirthDate), person)
	}
    return indexed
}
