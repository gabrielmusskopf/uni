#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ARR_LEN(x) (sizeof(x) / sizeof(x[0]))

// -DDEBUG
#ifdef DEBUG
#define debugPrintf(...)                                                       \
  {                                                                            \
    do {                                                                       \
      printf("[%s:%s %d] ", __FILE__, __func__, __LINE__);                     \
      printf(__VA_ARGS__);                                                     \
    } while (0);                                                               \
  }
#else
#define debugPrintf(...)
#endif // DEBUG

using namespace std;

const int TABLE_SIZE = 8;

typedef struct student {
  int registration;
  char name[30];
  float note1, note2, note3;
} Student;

typedef struct hashNode {
  Student data;
  hashNode *next;
} HashNode;

typedef struct hash {
  int tableSize;
  int numElements;
  HashNode **itens;
} Hash;

char *formatStudent(Student student) {
  char format[] = "{ name: %s, registration: %d }";

  int numParams = 2;
  int formatSize = ARR_LEN(format) - numParams;

  int nameSize = ARR_LEN(student.name) * sizeof(char);
  int registrationSize = sizeof(int);

  char *str = (char *)malloc(formatSize + nameSize + registrationSize);
  sprintf(str, format, student.name, student.registration);

  return str;
}

Hash *createHash(int tableSize) {
  debugPrintf("Creating hash with size %d\n", tableSize);
  Hash *ha = (Hash *)malloc(sizeof(Hash));
  if (ha != NULL) {
    ha->tableSize = tableSize;
    ha->itens = (HashNode **)malloc(tableSize * sizeof(HashNode *));

    if (ha->itens == NULL) {
      free(ha);
      return NULL;
    }

    ha->numElements = 0;
    for (int i = 0; i < ha->tableSize; i++) {
      ha->itens[i] = NULL;
    }
  }

  return ha;
}

void deleteHash(Hash *ha) {
  debugPrintf("Deleting hash\n");
  if (ha == NULL) {
    return;
  }

  for (int i = 0; i < ha->tableSize; i++) {
    HashNode *current = ha->itens[i];
    while (current != NULL) {
      HashNode *temp = current;
      current = current->next;
      free(temp);
    }
  }

  free(ha->itens);
  free(ha);
}

int divisionMethod(int chave, int tableSize) { return chave % tableSize; }

int insertSeparateChaining(Hash *ha, Student student) {
  if (ha == NULL) {
    return 0;
  }

  int pos = divisionMethod(student.registration, ha->tableSize);
  // Cria um novo nodo para o aluno
  HashNode *newNode = (HashNode *)malloc(sizeof(HashNode));
  if (newNode == NULL)
    return 0;

  debugPrintf("Inserting student %s at the head of position %d\n",
              formatStudent(student), pos);
  newNode->data = student;
  newNode->next = ha->itens[pos];
  ha->itens[pos] = newNode;
  ha->numElements++;

  return 1;
}

// Função para procurar por um aluno a partir de sua matrícula
int searchSeparateChaining(Hash *ha, int mat, Student *student) {
  if (ha == NULL) {
    return 0;
  }

  debugPrintf("Searching for student with registration %d\n", mat);
  int pos = divisionMethod(mat, ha->tableSize);
  HashNode *current = ha->itens[pos];

  // Percorre a lista encadeada do índice específico
  while (current != NULL) {
    if (current->data.registration == mat) {
      *student = current->data;
      return 1;
    }
    current = current->next;
  }

  return 0;
}

void printHash(Hash *ha) {
  printf("HASH TABLE:\n");
  if (ha == NULL) {
    return;
  }

  for (int i = 0; i < ha->tableSize; i++) {
    printf("[%d]: ", i);
    HashNode *current = ha->itens[i];
    while (current != NULL) {
      printf("-> [%d, %s] ", current->data.registration, current->data.name);
      current = current->next;
    }
    printf("\n");
  }
}

Hash *createHashFull(int tableSize, Student students[], int size) {
  debugPrintf("Creating hash with %d students\n", size);
  Hash *hash = createHash(tableSize);
  for (int i = 0; i < size; i++) {
    insertSeparateChaining(hash, students[i]);
  }
  return hash;
}

Hash *insertMultipleSeparateChaining(Hash *hash, Student students[], int size) {
  debugPrintf("Inserting %d students into hash\n", size);
  for (int i = 0; i < size; i++) {
    insertSeparateChaining(hash, students[i]);
  }
  return hash;
}

int main() {
  Hash *h = createHash(TABLE_SIZE);

  // Cria um aluno e o insere
  Student dio = {123, "Dio", 10, 9, 8};
  insertSeparateChaining(h, dio);

  printf("Imprimindo o HashTable depois da primeira insercao...");
  printHash(h);

  // Cria um aluno e o insere forçando uma colisão no índice 3
  Student humberto = {11, "Humberto", 3, 2, 1};
  insertSeparateChaining(h, humberto);

  printf("\nImprimindo o HashTable depois da segunda insercao...");
  printHash(h);

  Student studentFound;
  // Verifica a existência de um aluno com matrícula 123
  if (searchSeparateChaining(h, 123, &studentFound))
    printf("\nEncontrado: %s, Notas: %.1f, %.1f, %.1f\n", studentFound.name,
           studentFound.note1, studentFound.note2, studentFound.note3);
  else
    printf("\nAluno nao encontrado....\n");

  // Verifica a existência de um aluno com matrícula 11
  if (searchSeparateChaining(h, 11, &studentFound))
    printf("Encontrado: %s, Notas: %.1f, %.1f, %.1f\n", studentFound.name,
           studentFound.note1, studentFound.note2, studentFound.note3);
  else
    printf("Student not found.\n");

  // Procura por matrícula inexistente
  if (searchSeparateChaining(h, 40, &studentFound))
    printf("Encontrado: %s, Notas: %.1f, %.1f, %.1f\n", studentFound.name,
           studentFound.note1, studentFound.note2, studentFound.note3);
  else
    printf("Aluno nao encontrado....\n");

  // Libera a memória
  deleteHash(h);

  // Exercício
  // Criando um hash a partir de uma lista de alunos, tratando colisões
  printf("\n--------------------------------\n");
  printf("Exercício:\n");

  Student students[] = {dio, humberto};

  Hash *h2 = createHashFull(TABLE_SIZE, students, ARR_LEN(students));

  printf("\nImprimindo o HashTable depois da criação completa...");
  printHash(h2);

  Student newStudents[] = {
      Student{24, "Gabriel", 10, 9, 8},
      Student{25, "Juca", 10, 9, 8},
      Student{32, "Marcos", 10, 9, 8},
      Student{19, "Pedro", 10, 9, 8},
  };

  insertMultipleSeparateChaining(h2, newStudents, ARR_LEN(newStudents));
  printf("\nImprimindo o HashTable depois da inserção múltipla...");
  printHash(h2);

  // Libera a memória
  deleteHash(h2);

  return 0;
}
