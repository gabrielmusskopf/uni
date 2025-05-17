#include <iostream>
#include <vector>

using namespace std;

class Grafo {
private:
    vector<vector<int>> adj;    // Matriz de adjacencia
    int n;                      // N�mero de v�rtices

public:
    // Construtor que inicia nosso Grafo nxn com tudo zerado
    Grafo(int n) {
        this->n = n;
        adj = vector<vector<int>>(n, vector<int>(n, 0));
    }

    // Insere uma aresta entre u e v
    void insere_aresta(int u, int v) {
        adj[u][v] = 1;
        adj[v][u] = 1;
    }

    // Remove uma aresta entre u e v
    void remove_aresta(int u, int v) {
        adj[u][v] = 0;
        adj[v][u] = 0;
    }

    // Verifica se h� uma aresta (liga��o) entre u e v
    bool tem_aresta(int u, int v) const {
        return adj[u][v] == 1;
    }

    // Calcula o grau do nosso v�rtice alvo (u)
    int grau(int u) const {
        int grau = 0;
        for (int v = 0; v < n; ++v)
            if (adj[u][v])
                ++grau;

        return grau;
    }

    // Descobre o v�rtice com maior vizinhan�a (maior grau)
    int mais_popular() const {
        int max_vertice = 0;
        int max_grau = grau(0); // Assumo que o 0 possui o maior grau
        for (int u = 1; u < n; ++u) {
            int grau_atual = grau(u); // Verifico o grau do v�rtice atual

            if (grau_atual > max_grau) {
                max_grau = grau_atual;
                max_vertice = u;
            }
        }

        return max_vertice;
    }

    // Faz recomenda��es de vertices n�o conectados ao v�rtice alvo (u)
    void imprime_recomendacoes(int u) const {
        for (int v = 0; v < n; ++v)
            if (adj[u][v]) // Se � amigo da Ana
                for (int w = 0; w < n; ++w)
                    // Verifica se � amigo do amigo && n�o � a pr�pria Ana && n�o � amigo da Ana ainda.
                    if (adj[v][w] && w != u && !adj[u][w])
                        cout << w << endl; // Faz a sugest�o
    }

    // Imprime todas as arestas do grafo
    void imprime_arestas() const {
        for (int u = 0; u < n; ++u)
            for (int v = u + 1; v < n; ++v)
                if (adj[u][v])
                    cout << "{" << u << "," << v << "}\n";
    }

    bool completo() {
      for(int u = 0; u < n; u++) {
        for(int v = u + 1; v < n; v++) {
            if (!adj[u][v]) return false;
        }
      }
      return true;
    }

    int quantidade_arestas() {
      int arestas = 0;
      for(int u = 0; u < n; u++) {
        for(int v = u + 1; v < n; v++) {
            if (adj[u][v]) arestas++;
        }
      }
      return arestas;
    }

    // Fun��o est�tica para ler nosso grafo via teclado
    /**
    Exemplo de input do teclado:
    4 3 --> diz que criar� 4 v�rtices com 3 arestas entre eles
    0 1 --> aresta entre 0 e 1
    1 2 --> aresta entre 1 e 2
    3 0 --> aresta entre 3 e 0
    */
    static Grafo le_grafo() {
        int n, m;
        cin >> n >> m;
        Grafo g(n); // Cria o grafo com o tamanho digitado pelo usu�rio
        for (int i = 0; i < m; ++i) {
            int u, v;
            cin >> u >> v;
            g.insere_aresta(u, v);
        }

        return g;
    }
};

int main()
{
    Grafo h(4);

    h.insere_aresta(0, 1);
    h.insere_aresta(0, 2);
    h.insere_aresta(0, 3);
    h.insere_aresta(1, 0);
    h.insere_aresta(1, 2);
    h.insere_aresta(1, 3);
    h.insere_aresta(2, 0);
    h.insere_aresta(2, 1);
    h.insere_aresta(2, 3);

    cout << "Arestas do Grafo h:\n";
    h.imprime_arestas();
    cout << "E completo? " << (h.completo() ? "Sim" : "Nao") << endl;
    cout << "Quantidade de arestas: " << h.quantidade_arestas() << endl;

    return 0;
}
