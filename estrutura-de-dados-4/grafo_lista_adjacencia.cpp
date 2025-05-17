#include <iostream>
#include <vector>

using namespace std;

class Grafo {
private:
  vector<vector<int>> adjacencia;

public:
  Grafo(int n) { adjacencia.resize(n); }

  bool tem_aresta(int u, int v) const {
    for (int i = 0; i < adjacencia[u].size(); i++) {
      if (adjacencia[u][i] == v) {
        return true;
      }
    }
    return false;
  }

  void insere_aresta(int u, int v) {
    if (!tem_aresta(u, v)) {
      // Para grafos, não dígrafos
      adjacencia[u].push_back(v);
      adjacencia[v].push_back(u);
    }
  }

  void remove_aresta(int u, int v) {
    if (!tem_aresta(u, v)) {
      return;
    }
    for (int i = 0; i < adjacencia[u].size(); i++) {
      // Remove v de u
      if (adjacencia[u][i] == v) {
        adjacencia[u].erase(adjacencia[u].begin() + i);
        break;
      }
    }
    for (int i = 0; i < adjacencia[v].size(); i++) {
      // Remove u de v
      if (adjacencia[v][i] == u) {
        adjacencia[v].erase(adjacencia[v].begin() + i);
        break;
      }
    }
  }

  void imprime_arestas() const {
    for (int u = 0; u < adjacencia.size(); u++) {
      for (int i = 0; i < adjacencia[u].size(); i++) {
        int v = adjacencia[u][i];
        cout << "{" << u << "," << v << "}" << endl;
      }
    }
  }

  int grau(int u) { return adjacencia[u].size(); }

  void imprime_vertices_solitarios() const {
    bool tem_solitario = false;
    cout << "Vértices solitários: ";

    for (int u = 0; u < adjacencia.size(); u++) {
      if (adjacencia[u].size() == 0) {
        if (tem_solitario) {
          cout << ", ";
        }
        tem_solitario = true;
        cout << u;
      }
    }

    if (!tem_solitario) {
      cout << "nenhum";
    }
    cout << "\n";
  }
};

int main(int argc, char *argv[]) {
  Grafo g(5);

  g.insere_aresta(0, 1);
  g.insere_aresta(0, 2);
  g.insere_aresta(1, 3);
  g.insere_aresta(3, 4);

  g.imprime_arestas();

  cout << "Grau de 0: " << g.grau(0) << endl;
  cout << "Grau de 1: " << g.grau(1) << endl;
  cout << "Grau de 2: " << g.grau(2) << endl;
  cout << "Grau de 3: " << g.grau(3) << endl;
  cout << "Grau de 4: " << g.grau(4) << endl;

  g.imprime_vertices_solitarios();

  g.remove_aresta(0, 1);
  g.remove_aresta(0, 2);
  g.remove_aresta(1, 3);
  g.imprime_vertices_solitarios();

  return 0;
}
