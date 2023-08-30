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
> [!NOTE]
> Ao subtrair o índice no $n$, subtrair de $i$, e vice-versa
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
	\sum_{i=0}^{n+1} x^i &= x^0 + \sum_{i=1}^{n+1} x^i & (Separação) \\
	& = 1 + \sum_{i=0}^{n} x^{i-1} & (Reindexação) \\
	& = 1 + \sum_{i=0}^{n} x^i * x^1 & (Distrib.) \\
	& = 1 + x + \sum_{i=0}^{n} x^i \\
	\\
	S_n + x^{n+1} &= 1 + x * S_n \\\\\\
	S_n &= \frac{1-x^{n+1}}{1-x}
\end{split}
\end{equation}
$$
### Exercícios
#### 1. $\sum_{k=1}^n a^k$

$$
\begin{equation}
	\begin{split}
	S_{n+1} &= S_n + a^{n+1} = \sum_{k=1}^{n+1} a^k \\
	\sum_{k=1}^{n+1} a^k &= a^1 + \sum_{k=2}^{n+1} a^k & Sep.\\
	 &= a + \sum_{k=1}^{n} a^{k+1} & Reindex.\\
	 &= a + \sum_{k=1}^{n} a^{k} * a^{1} & Distrib. \\
	 S_n &= a + a * \sum_{k=1}^{n} a^{k} \\
	 S_n &= a + a * \sum_{k=1}^{n} a^{k} \\
	 \\
	 \\
	 S_n + a^{n+1} &= a + a * S_n\\
	 S_n - a * S_n &= a - a^{n+1} \\
	 S_n (1 - a) &= a - a^{n+1} \\\\
	 S_n &= \frac{a - a^{n+1}}{1 - a} \\
	\end{split}
\end{equation}
$$

#### 2. $\sum_{i=1}^{n} \frac{2^{i+1}}{5^i}$
$$
\begin{equation}
\begin{split}
	S_{n+1} &= S_n + \frac{2^{n+2}}{5^{n+1}} = \sum_{i=1}^{n+1} \frac{2^{i+1}}{5^i} \\\\
	\sum_{i=1}^{n+1} \frac{2^{i+1}}{5^i} &= \frac{2²}{5^1} + \sum_{i=2}^{n+1} \frac{2^{i+1}}{5^i} \\
	&= \frac{4}{5} + \sum_{i=1}^{n} \frac{2^{i+1+1}}{5^{i+1}} \\
	&= \frac{4}{5} + \sum_{i=1}^{n} \frac{2^{i+1}*2}{5^i*5} \\
	&= \frac{4}{5} + \sum_{i=1}^{n} \frac{2^{i+1}}{5^i}\frac{2}{5} \\
	&= \frac{4}{5} + \frac{2}{5} * \sum_{i=1}^{n} \frac{2^{i+1}}{5^i}\\
 \\
 \\
	S_n + \frac{2^{n+2}}{5^{n+1}} &= \frac{4}{5} + \frac{2}{5} S_n\\
	S_n + \frac{2^{n+2}}{5^{n+1}} &= \frac{4}{5} + \frac{2S_n}{5} \\
	S_n + \frac{2^{n+2}}{5^{n+1}} &= \frac{2S_n + 4}{5} \\
	\frac{2^{n+2}}{5^{n+1}} &= \frac{2S_n + 4}{5} - S_n \\
	\frac{2^{n+2}}{5^{n+1}} &= \frac{2S_n + 4 + 5S_n}{5} \\
	\frac{2^{n+2}}{5^{n+1}} &= \frac{7S_n + 4}{5} \\
	5\frac{2^{n+2}}{5^{n+1}} &= 7S_n + 4 \\
	5\frac{2^{n+2}}{5^{n+1}} -4 &= 7S_n \\
	\frac{5\frac{2^{n+2}}{5^{n+1}} -4}{7} &= S_n \\
\end{split}
\end{equation}
$$

#### 3. $\sum_{i=1}^{n} 7i-3$

$$
\begin{equation}
\begin{split}
	S_{n+1} &= S_n + (7(i+1) - 3) = \sum_{i=1}^{n+1}7i-3\\
	\sum_{i=1}^{n+1}7i-3 &= \sum_{i=1}^{n+1}7i-3 \\
	&= 4 + \sum_{i=2}^{n+1}7i-3 \\
	&= 4 + \sum_{i=1}^{n}7(i+1)-3 \\
	&= 4 + \sum_{i=1}^{n}7i+7-3 \\
	&= 4 + \sum_{i=1}^{n}7i + \sum_{i=1}^{n}4 \\
	&= 4 + 7\sum_{i=1}^{n}i + 4\sum_{i=1}^{n}1 \\
	&= 4 + 7\frac{n(n+1)}{2}+ 4n \\
	&= 4 + \frac{7n^2+7n}{2}+ 4n \\
	&= \frac{8 + 7n^2 + 7n + 8n}{2} \\
	&= \frac{7n^2 + 15n + 8}{2} \\
 \\\\
 
	S_n + 7i + 4 &= \frac{7n^2 + 15n + 8}{2} \\
	S_n &= \frac{7n^2 + 15n + 8}{2} - 7n - 4 \\
	S_n &= \frac{7n^2 + 15n + 8 - 14n -8}{2} - 7n - 4 \\
	S_n &= \frac{7n^2 + n}{2} \\
\end{split}
\end{equation}
$$

Nota: Poderia ter aplicado diretamente, sem perturbação

Dicas:
$$
\begin{equation}
\begin{split}
\sum_{i=1}^n i &= \frac{n(n+1)}{2} \\
\sum_{i=1}^n 1 &= n
\end{split}
\end{equation}
$$

> [!WARNING]
> Método da perturbação só funciona quando índice em expoente
# Analisando Insertion Sort

```java
void insertionSort(int[] array) {
	for (int i = 1; i < array.length; i++) {
	int j = i;
	int temp = array[j];
	while (j > 0 && temp < array[j - 1]) {
		array[j] = array[j - 1];
		j--;
	}
	array[j] = temp;
	}
}
```
