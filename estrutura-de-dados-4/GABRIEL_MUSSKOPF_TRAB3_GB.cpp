#include <algorithm>
#include <fstream>
#include <iostream>
#include <sstream>
#include <stdexcept>
#include <string>
#include <vector>

using namespace std;

enum LOG_LEVELS { ERROR = 1, INFO = 2, DEBUG = 3, TRACE = 4 };

#ifndef LOG_LEVEL
#define LOG_LEVEL DEBUG
#endif

void error(string s) {
  if (LOG_LEVEL >= LOG_LEVELS::ERROR) {
    cerr << "[error] " << s << endl;
  }
}

void debug(string s) {
  if (LOG_LEVEL >= LOG_LEVELS::DEBUG) {
    cout << "[debug] " << s << endl;
  }
}

void trace(string s) {
  if (LOG_LEVEL >= LOG_LEVELS::TRACE) {
    cout << "[trace] " << s << endl;
  }
}

void info(string s) {
  if (LOG_LEVEL >= LOG_LEVELS::INFO) {
    cout << "[info] " << s << endl;
  }
}

class formato_invalido : public std::exception {
private:
  string mensagem;
  int n_linha;

public:
  explicit formato_invalido(int &n_linha, const string &msg) : n_linha(n_linha) {
    std::ostringstream oss;
    oss << "linha " << n_linha << ": " << msg;
    mensagem = oss.str();
  }

  const char *what() const noexcept override { return mensagem.c_str(); }
};

class Grafo {
private:
  vector<vector<int>> adj;

  void dfs_util(int u, vector<bool> &visitado) {
    visitado[u] = true;
    cout << u << " -> ";

    for (int v : adj[u]) {
      if (!visitado[v])
        dfs_util(v, visitado);
    }
  }

  bool dfs_util(int u, int destino, vector<bool> &visitado, vector<int> &pai) {
    trace("dfs_util atual=" + to_string(u) + " destino=" + to_string(destino));
    visitado[u] = true;

    if (u == destino) {
      return true;
    }

    for (int v : adj[u]) {
      if (!visitado[v]) {
        pai[v] = u;
        if (dfs_util(v, destino, visitado, pai)) {
          return true;
        }
      }
    }

    return false;
  }

  static void le_vertices(string &linha, int n_linha, int &a, int &b) {
    stringstream ss(linha);
    string primeiro, segundo;

    trace("transformando linha " + linha);
    if (!getline(ss, primeiro, ',') || !getline(ss, segundo)) {
      throw formato_invalido(n_linha, "linha incompleta = " + linha);
    }
    try {
      a = stoi(primeiro);
      b = stoi(segundo);
    } catch (const std::exception &e) {
      throw formato_invalido(n_linha, "nao está no formato esperado = " + linha);
    }
  }

public:
  Grafo(int n) {
    debug("Iniciando grafo com " + to_string(n) + (n == 1 ? " vertice" : " vertices"));
    adj.resize(n);
  }

  void insere_aresta(int u, int v) {
    trace("inserindo " + to_string(u) + " -> " + to_string(v));
    if (adj.size() <= u) {
      string vs = to_string(u);
      throw invalid_argument("o vertice " + vs + " não está no grafo");
    }
    if (adj.size() <= v) {
      string vs = to_string(v);
      throw invalid_argument("o vertice " + vs + " não está no grafo");
    }
    if (!tem_aresta(u, v)) {
      adj[u].push_back(v);
      adj[v].push_back(u);
    }
  }

  void remove_aresta(int u, int v) {
    if (!tem_aresta(u, v)) {
      return;
    }

    for (int i = 0; i < adj[u].size(); ++i) {
      if (adj[u][i] == v) {
        adj[u].erase(adj[u].begin() + i);
        break;
      }
    }

    for (int i = 0; i < adj[v].size(); ++i) {
      if (adj[v][i] == u) {
        adj[v].erase(adj[v].begin() + i);
        break;
      }
    }
  }

  bool tem_aresta(int u, int v) {
    for (int i = 0; i < adj[u].size(); ++i) {
      if (adj[u][i] == v)
        return true;
    }
    return false;
  }

  void imprime_arestas() const {
    for (int u = 0; u < adj.size(); ++u) {
      for (int i = 0; i < adj[u].size(); ++i) {
        int v = adj[u][i];
        cout << "{" << u << ", " << v << "}" << endl;
      }
    }
  }

  void dfs(int origem) {
    vector<bool> visitado(adj.size(), false);
    dfs_util(origem, visitado);
  }

  vector<int> dfs(int origem, int destino) {
    vector<bool> visitado(adj.size(), false);
    vector<int> pai(adj.size(), -1);

    info("Iniciando a busca de " + to_string(origem) + " ate " + to_string(destino) + ":");
    bool encontrou = dfs_util(origem, destino, visitado, pai);
    vector<int> caminho;

    if (!encontrou) {
      info("Caminho entre " + to_string(origem) + " e " + to_string(destino) +
           " nao encontrado...");
      return caminho;
    }

    for (int v = destino; v != -1; v = pai[v]) {
      caminho.push_back(v);
    }

    reverse(caminho.begin(), caminho.end());
    return caminho;
  }

  static void le_linha(ifstream &arquivo, string &linha, int &n_linha) {
    getline(arquivo, linha);
    n_linha++;
  }

  static int inicAndSearch(string &nomeArquivo) {
    ifstream arquivo(nomeArquivo);

    if (!arquivo.is_open()) {
      throw runtime_error("não foi possível abrir o arquivo " + nomeArquivo);
      return 1;
    }

    string linha;
    int vertices, arestas, n_linha = 0;
    le_linha(arquivo, linha, n_linha);
    le_vertices(linha, n_linha, vertices, arestas);

    debug(nomeArquivo + " espera " + to_string(vertices) + " vertices e " + to_string(arestas) +
          " arestas");
    Grafo g(vertices);
    int origem = 0;

    for (int i = 0; i < arestas; i++) {
      le_linha(arquivo, linha, n_linha);

      int u, v;
      le_vertices(linha, n_linha, u, v);
      g.insere_aresta(u, v);

      // Decisão arbitrária para tomar o primeiro inserido como origem do DFS
      if (i == 0) {
        origem = u;
      }
    }

    // Deve ser a última linha
    le_linha(arquivo, linha, n_linha);

    if (arquivo.peek() != EOF) {
      throw formato_invalido(n_linha, "existem mais arestas do que o esperado");
    }
    arquivo.close();

    // DFS da origem e tchau
    if (linha == "0") {
      g.dfs(origem);
      return 0;
    }

    // DFS da origem e destino definidos na última linha
    int u, v;
    le_vertices(linha, n_linha, u, v);

    vector<int> caminho = g.dfs(u, v);
    if (caminho.empty()) {
      info("Nenhum caminho encontrado entre " + to_string(u) + " e " + to_string(v));
      return 0;
    }

    for (int v : caminho) {
      cout << v << " -> ";
    }
    cout << endl;

    return 0;
  }
};

int main(int argc, char *argv[]) {
  try {
    info("Trabalho 3 GB - Gabriel Grahl Musskopf");
    cout << "Uso: " << argv[0] << " [nome arquivo|grafo.txt por padrão]\n" << endl;

    string nomeArquivo = "grafo.txt";
    if (argc > 1) {
      nomeArquivo = argv[1];
    }

    Grafo::inicAndSearch(nomeArquivo);
  } catch (const std::exception &e) {
    error(e.what());
    return 1;
  }
}
