#include <cstdlib>
#include <iostream>

using namespace std;

struct Retangulo {
  int altura;
  int largura;
};

int main(int argc, char *argv[]) {
  cout << "Hello" << endl;

  int Z[3] = {1, 2, 3};

  std::cout << sizeof(Z) << endl;

  for (int i; i < 3; i++) {
    std::cout << i << endl;
  }

  for (int x : Z) {
    std::cout << x << endl;
  }

  int n;
  std::cout << "Digite o tamanho do array: ";
  cin >> n;

  int B[n];
  B[0] = 2;
  B[1] = 9;
  B[2] = 13;

  for (int x : B) {
    cout << x << " ";
  }
  cout << endl;

  struct Retangulo r = {10, 5};
  cout << "Retângulo: " << sizeof(r) << " bytes Altura: " << r.altura
       << " Largura: " << r.largura << endl;

  int *p;
  p = (int *)malloc(5 * sizeof(int)); // alocando espaço no heap

  // para array não precisa de &
  int *h = Z;

  for (int i = 0; i < 3; i++) {
    cout << h[i] << " ";
  }
  cout << endl;

  int *a = new int[5]; // cria elementos na heap (versão c++);

  delete[] a; // versão c++
  // free(a); // versão c

  // cria um alias para uma variável, alterações em qualquer um altera o outro
  // útil para passar por parâmetro
  // só funciona no c+
  int R = 10;
  int &ref = R;

  return 0;
}
