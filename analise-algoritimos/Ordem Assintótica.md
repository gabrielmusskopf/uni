**Dia:** 15/08/2023 
**Matéria:** Análise de algorítimos
**Assunto:**  Ordem assintótica
**Tags:** #algoritimos #faculdade 
**Material :** [[complexidade_de_algoritmos_crescimento_de_funcoes_ordens_assintoticas.pdf]]

## Constantes de crescimento
- $t(n)=1$ Constante  
- $t(n)=\log n$ Logarítmico 
	- Viável em problemas grandes
	- Taxa de crescimento pequena
- $t(n)=n$ Linear 
- $t(n)=n^2$ Quadrático 
	- Viáveis para problemas pequenos
- $t(n)=n^3$ Cúbico 
- $t(n)= n^k$, Polinomial, para k com valores pequenos
- $t(n)= n^n$ Exponencial 
	- Não é viável
## Análise assintótica

Existe um ponto (`m`) e uma constante (`c`), em que uma função domina assintoticamente

![[Pasted image 20230815194944.png]]

##### Exercícios comuns
- Dadas as funções e a constante, encontre o $m$
- Dado um suposto $m$, encontre $c$

O $c$ vem da função com dominância assintótica, exemplo
$$
\begin{equation} 
\begin{split}
f(x) & = n²\\
g(x) & = 2n + 5\\
\end{split}
\end{equation}
$$
O $c = 1$ pois a função que domina assintoticamente tem esse multiplicador

## Notação O (Big O)
>Maior elemento de uma função. 

Pior caso de uma função. Diz que $g(n) = O(f(n))$ quando quando $f(n)$ domina assintoticamente $g(n)$
$$
\begin{equation}
\begin{split}
(n + 1)² & = O(n²) \\
n² + 2n + 1 & = O(n²)
\end{split}
\end{equation}
$$
Para Big O
$$
\begin{equation}
\begin{split}
G(n) \leq CF(n)
\end{split}
\end{equation}
$$


Exemplo, para $m = 1$, primeiro a análise **matemática**:
$$
\begin{equation}
\begin{split}
m = 1\\\\
n² + 2n + 1 & \leq O(n²) \\
n² + 2n + 1 & \leq Cn² \\
\frac{n² + 2n + 1}{n²} & \leq C \\
4 & \leq C \\\\
h(n) = 1 + & \frac{2}{n} + \frac{1}{n²} \\
\end{split}
\end{equation}
$$

Após isso, é preciso da parte de analisar com o $n$ tendendo ao infinito
$$
\begin{equation}
\begin{split}
h(n) = 1 + \frac{2}{n} + \frac{1}{n²} \\
 \lim_{x\to\infty} h(n) =  1 + 0 + 0 = 1 \\
\end{split}
\end{equation}
$$

## Notação $\Omega$  (Ômega)
>Menor elemento da função. 

Mesma ideia do pior caso, porém, $G(n)$ maior igual $G(n) \geq CF(n)$
##### Falso: $3n³ + 2n² + n = \Omega(n³)$
Provando:
$$
\begin{equation}
\begin{split}
3n³ + 2n² + n & \geq Cn³ \\
3 + \frac{2}{n} + \frac{1}{n²} & \geq C \\
C & \geq 6
\end{split}
\end{equation}
$$
E analisando
$$
\begin{equation}
\begin{split}
\lim_{x\to\infty} h(n) & =  3 + 0 + 0 = 3 \\
3 & \ngeq 6
\end{split}
\end{equation}
$$
##### Correto: $3n³ + 2n² + n = \Omega(n)$
Provando:
$$
\begin{equation}
\begin{split}
3n³ + 2n² + n & \geq Cn \\
3n² + 2n + 1 & \geq C \\
3n² + 2n + 1 & = h(x) \\\\
3 + 2 + 1 & = 6 \\
C & = 6
\end{split}
\end{equation}
$$
E analisando
$$
\begin{equation}
	\begin{split}
		\lim_{x\to\infty} h(n) & =  \infty + \infty + 1 \geq C \\
	\end{split}
\end{equation}
$$

**Nota**: Não é possível que $n = 0$

