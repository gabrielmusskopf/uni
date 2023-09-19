**Dia:** 19/09/2023 
**Matéria:** Análise de algoritmos
**Assunto:** Recorrência
**Tags:** #faculdade #algoritmos 

Caso base: começa a desempilhar
Caso recursivo: empilha

# Complexidade
Como calcular complexidade a partir da fórmula de recorrência
#### Torre de Hanoi
$$
\begin{equation}
\begin{split}
	T(n)=\begin{cases}&1, &, \quad n=0. \\ 
	&2T(n-1) &, \quad n > 0
	\end{cases}
\end{split}
\end{equation}
$$
## Método backward substitution (ou Derivation)

#### 1. Generalização
$$
\begin{equation}
\begin{split}
	T(n) &= 2T(n-1)+1\\
	T(n-1) &= 2T(n-2)+1\\
	T(n-2) &= 2T(n-3)+1\\
	& \dots\\\ 
	\\
	T(n) &= 2(\ 2T(n-1)+1\ ) + 1 = 2^2T(n-1)+2+1\\
	T(n) &= 4(\ 2T(n-2)+1\ +2 ) + 1 = 2^3T(n-1)+4+2+1\\
	& \dots \\
	\\
	Generalizando\\
	T(n) &= 2^k\ T(n-k)+2^{k-1} + 2^{k-2} + \dots + 2 + 1
\end{split}
\end{equation}
$$

#### 2. Substituir para o pior caso
Assume $k-n = 0$, pois 0 é o **caso base**, por consequência, $n=k$
$$
\begin{equation}
\begin{split}
	T(n) &= 2^k\ T(0) + 2^{k-1} + \dots + 2 + 1\\
	T(n) &= 2^k * 1 + 2^k - 1\\
	T(n) &= 2^k + 2^k - 1\\
	\\
	 & Como\ n=k\\
	T(n) &= 2^n + 2^n - 1\\
	T(n) &= 2^{n+1} - 1\\
	 \\
	O(n) &= 2^n\\
\end{split}
\end{equation}
$$