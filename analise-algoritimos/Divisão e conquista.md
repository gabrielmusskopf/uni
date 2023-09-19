**Dia:** 12/09/2023 
**Matéria:** Análise de algoritmos
**Assunto:** Divisão e conquista
**Tags:** #faculdade #algoritmos 


> [!INFO]
> Perguntar algoritmos que usam somatório

# Equação de recorrência
Para merge-sort
$$
\begin{equation}
\begin{split}
	T(n)=\begin{cases}0, &if \quad n=1. \\ T([\frac{n}{2}]) + T([\frac{n}{2}]) + T_{merge}(n); &if \quad n > 1;
	\end{cases}
\end{split}
\end{equation}
$$