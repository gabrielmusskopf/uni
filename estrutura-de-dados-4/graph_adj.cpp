#include <iostream>
#include <vector>

using namespace std;

class Grafo {
private:
  vector<vector<int>> adj;
  int n;

public:
  Grafo(int n) {
    this->n = n;
    this->adj = vector<vector<int>>(n, vector<int>(n, 0));
  }

  void insere_aresta(int u, int v) {
    adj[u][v] = 1;
    adj[v][u] = 1;
  }

  void remove_aresta(int u, int v) {
    adj[u][v] = 0;
    adj[v][u] = 0;
  }

  bool tem_aresta(int u, int v) { return adj[u][v] == 1; }

  int grau(int u) const {
    // quantas conexões tem na linha
    int grau = 0;
    for (int v = 0; v < n; v++) {
      if (adj[u][v]) {
        ++grau;
      }
    }
    return grau;
  }

  int mais_popular() const {
    // linha com mais conexões
    int max_vertice = 0;
    int grau_max = grau(0);
    for (int u = 1; u < n; u++) {
      int grau_atual = grau(u);
      if (grau_atual > grau_max) {
        grau_max = grau_atual;
        max_vertice = u;
      }
    }
    return max_vertice;
  }

  void imprime_recomendacoes(int u) const {
    // vértices conectados aos vizinhos do u, que não sejam u
    for (int v = 0; v < n; v++) {
      if (adj[u][v]) {
        for (int w = 0; w < n; w++) {
          if (adj[v][w] && w != u && !adj[u][w]) {
            // 1. se há uma ligação entre o vizinho e w
            // 2. se w não é o u
            // 3. se w não tem conexão com u
            cout << w << endl;
          }
        }
      }
    }
  }

  void imprime_aresta() const {
    for (int u = 0; u < n; u++) {
      for (int v = u + 1; v < n; v++) {
        if (adj[u][v]) {
          cout << "{" << u << ", " << v << "}" << endl;
        }
      }
    }
  }

  static Grafo le_grafo() {
    // n = número de vértices
    // m = número de arestas
    int n, m;
    cin >> n >> m;
    Grafo g(n);
    for (int i = 0; i < m; i++) {
      // adjacência: { u, v }
      int u, v;
      cin >> u >> v;
      g.insere_aresta(u, v);
    }
    return g;
  }
};

int main(int argc, char *argv[]) {

  Grafo g(5);

  g.insere_aresta(0, 1);
  g.insere_aresta(0, 2);
  g.insere_aresta(1, 2);
  g.insere_aresta(1, 3);
  g.insere_aresta(3, 4);

  cout << "Arestas:\n";
  g.imprime_aresta();

  cout << "Tem aresta entre 0 e 2? " << (g.tem_aresta(0, 2) ? "Sim" : "Nao")
       << endl;

  g.remove_aresta(0, 2);
  g.imprime_aresta();

  cout << "Grau do vertice 3: " << g.grau(3) << endl;
  cout << "Mais popular: " << g.mais_popular() << endl;

  cout << "Recomendações de 0:" << endl;
  g.imprime_recomendacoes(0);
  cout << "Recomendações de 3:" << endl;
  g.imprime_recomendacoes(3);

  cout << "Digite o novo grafo" << endl;
  Grafo h = Grafo::le_grafo();
  h.imprime_aresta();

  return 0;
}
