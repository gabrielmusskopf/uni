**Dia:** 22/08/2023 
**Matéria:** Análise de algoritmos
**Assunto:** Somatório
**Tags:** #faculdade #algoritmos 

Quando a quantidade de operações não depende somente da quantidade de entrada, mas sim, também da disposição dos elementos
$$
\begin{equation}
\begin{split}
\sum_{i=0}^{n} & a_0 + a_1 + a_{n-1} + a_n\\
& 0 \leq i \leq n
\end{split}
\end{equation}
$$

Exemplos de somatórios
2)
$$
\begin{equation}
\begin{split}
	\sum_{k=0}^{2} (2k + 2) & = (2*0 + 2) + (2*1 + 2) + (2*2 + 2) \\
	& = 2 + 4 + 6\\
	& =12 
\end{split}
\end{equation}
$$
3)
$$
\begin{equation}
\begin{split}
	\sum_{k=1}^{2} (3 - k) & = (3 - 1) + (3 - 2) \\
	& = 2 + 1 \\
	& = 3 
\end{split}
\end{equation}
$$
4)
$$
\begin{equation}
\begin{split}
	\sum_{k=1}^{3} (3j) & = (3*1) + (3*2) + (3*3) \\
	& = 3 + 6 + 9 \\
	& = 18 
\end{split}
\end{equation}
$$

## Propriedades

### Associatividade
> Mesmos índices

$$
\begin{equation}
\begin{split}
\sum_{i=1}^n a_1 + \sum_{i=0}^n b_1 = (a_1 + ... + a_n) + (b_1 + ... + b_n) = \sum_{i=1}^n (a_i + b_i)
\end{split}
\end{equation}
$$

### Distributividade
$$
\begin{equation}
\begin{split}
\sum_{i=1}^n c*a_i = c * \sum_{i=1}^n
\end{split}
\end{equation}
$$
### Combinação
> Índice do próximo é o limite do anterior
> Mesma função

$$
\begin{equation}
\begin{split}
\sum_{k=1}^m a_k + \sum_{j=m}^n a_j = a_m + \sum_{i=1}^n a_k
\end{split}
\end{equation}
$$
Soma com $a_m$ no início pois esse elemento fica repetido (último do primeiro somatório e primeiro do segundo)
### Separação
$$
\begin{equation}
\begin{split}
\sum_{i=0}^n a_i = a_0 + \sum_{i=1}^n a_i
\end{split}
\end{equation}
$$

### Reindexação
> Alternar os limites da soma em $j$ unidades

$$
\begin{equation}
\begin{split}
\sum_{i=i}^n a_i = \sum_{i=i+j}^{n+j} a_{i-j}
\end{split}
\end{equation}
$$

## Formas fechadas

Com um limite grande, é difícil calcular o somatório (limite tendendo ao infinito), para isso precisamos da forma fechada

Para obter a forma fechada, usamos o **método da perturbação**

Exemplo:
$$
\begin{equation}
\begin{split}
S_n & = \sum_{i=0}^n x^i
\end{split}
\end{equation}
$$

1) Verificar $S_{n+1}$
$$
\begin{equation}
\begin{split}
	S_{n+1} & = S_n + x^{n+1} = \sum_{i=0}^{n+1} x^i \\
\end{split}
\end{equation}
$$
2) Do $S_{n+1}$ chegar em $S_n$
$$
\begin{equation}
\begin{split}
	\sum_{i=0}^{n+1} x^i &= x^0 + \sum_{i=1}^{n+1} x^i (Separação) \\
	& = 1 + \sum_{i=0}^{n} x^{i-1} (Reindexação) \\
	& = 1 + \sum_{i=0}^{n} x^i * x^1 (Distribuitividade) \\
	& = 1 + x + \sum_{i=0}^{n} x^i \\
	\\
	S_n + x^{n+1} &= 1 + x * S_n \\\\\\
	S_n &= \frac{1-x^{n+1}}{1-x}
\end{split}
\end{equation}
$$