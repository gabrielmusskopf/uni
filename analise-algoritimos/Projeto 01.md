
- [ ] Breve resumo sobre conceitos gerais de análise assintótica de algorítimos. 1 - 3 páginas
- [ ] Descrição de um algoritmo que possa ser analisado com base nos recursos de análise assintótica
- [ ] Análise desse algoritmo


# O que análise assintótica

Como desenvolvedores e profissionais da área da computação, saber avaliar o desempenho de um comando, ou conjunto de comandos (algoritmo) é essencial, porém, isso não é um problema tão atual como pensamos. A análise e redução de "operações" já era discutida desde o tempos egípcios, como ressalta Audrey A. Nasar, no seu artigo *The history of Algorithmic complexity*: "Historicamente, desde a idade média, há um interesse em otimizar algoritmos aritméticos. Métodos para reduzir o número de operações elementares distintas necessárias para cálculos são descritos em um texto árabe" (tradução livre).

Com a introdução de máquinas Turing, (_explicar brevemente_) a discussão foi expandida também para problemas computacionais, introduzindo a noção de problemas computáveis e não computáveis. Outra discussão que veio a tona foi como a complexidade de um algoritmo é medida. Inicialmente, a medição foi baseada em unidades de tempo em diferentes máquinas, porém, posteriormente, as noções de tempo e espaço computacional foram introduzidas por Kronsjö (1987) e Rosen (1999) e aceitas pela maioria do meio científico por serem medidas não relacionadas ao meio físico em que são executadas, como a unidade de tempo, mas sim, somente a formula matemática que representa determinada função. A complexidade espacial representa o espaço físico, memória, necessário para que o algoritmo seja executado, e a complexidade temporal, a quantidade de tempo necessário para executar o algoritmo.

Posteriormente, os autores descrevem a análise de forma matemática, como o poder computacional necessário para que determinada função seja executada. Essa análise leva em conta o número de operações computacionais que a operação realiza, como por exemplo, o número de comparações em uma ordenação de elementos ou o número de multiplicações em determinada função. Comumente foi denotado o número de elementos de entrada em uma função com a letra $n$. O significado de $n$ pode variar de acordo com o problema, porém, nesse artigo, será tratado como a quantidade de elementos.

Algoritmos são agora analisados e comparados de acordo com a quantidade de operações que ele realiza, e essas variam conforme $n$ (número de elementos). Exemplo, para ordenar um conjunto de números, o número de comparações e substituições vai crescer de acordo com $n$. Com essas informações é possível analisar e comparar algoritmos, auxiliando na escolha para a melhor resolução possível de determinado problema. Outra característica que interfere na quantidade de operações em algumas funções é a distribuição dos elementos no conjunto. A ordenação de um conjunto que já está ordenado demanda menos operações que um conjunto totalmente desordenado. Algoritmos que tem sua complexidade variante conforme a ordem dos elementos de entrada são chamados de "não óbvios". Nesses casos, a análise do melhor, médio e pior caso é necessária, tendo ao fim, três funções que representam a complexidade em cada cenário. Algoritmos que não variam em operações pela distribuição são chamados de "óbvios", e tem sua complexidade descrita em uma única função. Em geral, a escolha do algoritmo é baseada no cenário de pior caso, ou seja, o maior número de operações que a função exerce.

O nome "análise assintótica" vem da noção de assíntota, que na matemática representa um limite que nunca é atingido pela função, quando essa tende à algum número, ou infinito. Então, no contexto de computadores e algoritmos, análise assintótica significa analisar o desempenho de uma algoritmo quando o número de elementos de entrada desse tende ao infinito. E para atingirmos uma comparação mais eficiente dentre funções, utilizamos a notação "Big O", definida pelo alemão Paul Benchmann, em 1894. Essa notação consiste em representar a ordem de crescimento de uma função quando o $n$ tende a números infinitamente altos. A utilização dessa notação é muito difundida pois representa de maneira simples e objetiva o comportamento de uma função no pior caso conforme a quantidade de elementos cresce. Diferente do que possa parecer, o comportamento exato da função não é representado pela notação, pois conforme o número de elementos cresce tendendo ao infinito, os termos que não representam a parte mais significativa da função tendem a não interferir de maneira significativa no número de operações. Então, o Big O considera somente o termo de maior representatividade em uma função, por exemplo, o termo de maior grau em um polinômio.

A definição diz que $g(n) = O(f(n))$ quando $f(n)$ domina assintoticamente $g(n)$, dado que existe uma constante $c$ que $f(n) \leq cg(n)$, e que a cota assintótica superior de $g(n)$ é $cf(n)$, para todo $n \geq n_0$ . A representação geométrica é dada pela figura a baixo 

![[Pasted image 20230902155148.png]]

Alguns tipos de ordens de crescimento comuns são $O(1)$, $O(log n)$, $O(nlogn)$, $O(n²)$, $O(2^n)$ e $O(n!)$ estão representados na figura 

![[Pasted image 20230902155532.png]]
https://www.freecodecamp.org/portuguese/news/o-que-e-a-notacao-big-o-complexidade-de-tempo-e-de-espaco/


Historically, an interest in optimizing arithmetic algorithms can be traced back to
the Middle Ages (Chabert, 1999). Methods for reducing the number of separate
elementary steps needed for calculation are described in an Arabic text by Ibn al-Majdi, a
fourteenth century Egyptian astronomer