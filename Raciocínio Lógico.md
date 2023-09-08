# Noção
1. `Princípio terceiro excluído`: Toda proposição é verdadeira ou falsa, não existe terceira opção
2. `Princípio da não contradição`: Não pode ser verdadeiro e falso ao mesmo tempo
3. `Proposição fechada`: Consegue inferir valor lógico (verdadeiro ou falso). Exemplo: "3+4=7"
4. `Proposição aberta`: Não é possível inferir valor lógico. Exemplo: "x>2"
5. `Proposição simple`: Somente uma proposição
6. `Proposição composta`: Combinação de proposições

## Conectivos

| Significado | Lê-se | Símbolo |
|-------------|-------|---------|
| Negação | Não | ~ |
| Conjunção| E| ^ |
| Disjunção | Ou | U |
| Condicional | Se .. Então| -> |
| Bicondicional | Se e somente se .. Então| <-> |

## p -> q 
>Antecedente -> Consequente

**Recíproca**: q -> p
**Contrapositiva:** ~q -> ~p

- Ex. "Se x é par, então x^2 é par"
 Antecedente: x é par
 Consequente: x^2 é par
 Recíproca: se x^2 é par, então x é par
 Contrapositiva: se x² não é par, então x não é par

## Precedência

1. Dentro de parênteses
2. ~ : Negação
3. ^ e U : Conjunção e disjunção
4. -> : Condicional
5. <- : Bicondicional

### Tautologia
> P é uma **tautologia** se o conectivo principal é **apenas** verdade
> Mesmo/idêntico
### Contradição
>P é uma contradição se o conectivo principal for **apenas** falso
### Equivalência Lógica
> P e Q são proposições compostas. P é logicamente equivalente à Q se P<->Q for tautologia

# Regras de Equivalência

| Expressão | Equivalência | Nome regra | Abrev |
|------------|-------------|------------|-------|
| P u Q | Q u P | Comutatividade | com |
| P ^ Q | Q ^ P | Comutatividade | com |
| (P u Q) u R | P u (Q u R) | Associatividade | ass |
| (P ^ Q) ^ R | P ^ (Q ^ R) | Associatividade | ass |
| ~(P u Q) | ~P ^ ~Q | De Morgan | dmor |
| ~(P ^ Q) | ~P u ~Q | De Morgan | dmor |
| P -> Q | ~P u Q | Condicional | cond |
| P | ~(~P) | Dupla negação | dn |
| P -> Q | ~Q->~P | Contra-posição | cont |
| P | P ^ P | Auto-referência | auto |
| P u P | P | Auto-referência | auto |
| P ^ (Q u R) | (P ^ Q) u (P ^ R) | Distribuitividade | dist |
| P u (Q ^ R) | (P u Q) ^ (P u R) | Distribuitividade | dist |

## Implicação Lógica P => Q
Proposição composta P implica logicamente uma proposição Q se e somente se P->Q for tautologia

# Argumentos
> Sequencia de proposições, premissas (ou hipótese) que acarretam em uma conclusão final Q (ou tese)

- Valido se, e somente se, todas as hipóteses forem válidas
- Argumento é valido se, e somente se, nas linhas onde as hipóteses são verdadeiras, a tese é verdadeira
### $$P_1 \wedge P_2 \cdots P_n \rightarrow Q$$
## Cálculo de dedução natural
> Chegar na conclusão a partir de passos lógicos

- Exemplo: $A \rightarrow (B \wedge C), A \mapsto B \wedge C$ 
  1. $A \rightarrow (B \wedge C)$
  2. $A$
  3. $B \wedge C$, *1,2 m.p*
  $\blacksquare$ *(Símbolo demonstração)*

# Regras hipotéticas
> Adicionar algo que não tem certeza para demonstrar a tese
> Criar demonstração auxiliar

## Prova condicional (PC)

$$
\begin{equation}
\begin{split}
	1. & P \rightarrow Q & (hip)\\
	2. & Q \rightarrow R & (hip)\\
	3. & | P & (hip-pc)\\
	4. & | Q & 1,3 mp\\
	5. & | R & 2,4 mp\\
	6. & P \rightarrow R & 3,5 pc\\
\end{split}
\end{equation}
$$
## Regra Redução ao Absoluto (RAA)
> Supor algo e chegar no **absurdo**
> Chego em algo que não posso chegar. Significa que a proposição é falsa

$$
\begin{equation}
\begin{split}
	1. & P \rightarrow Q & (hip)\\
	2. & ~Q & (hip)\\
	3. & | P & (hip-pc)\\
	4. & | Q & 1,3 mp\\
	5. & | Q \vee ~Q & 2,4 cj\\
	6. & ~P & 3,5 raa\\
\end{split}
\end{equation}
$$
Linha 5 é um absurdo
Linha 6 negação da suposição

# Lógica de primeira ordem
> Sentenças sem valor lógico a priori

## Conjunto verdade
Conjunto de valores do universo que tornam a sentença verdade
$$
V_{\neg p} = (V_p)^c
$$
c => complementar

## Conectivos Lógicos
I. $V_{p \wedge q}$ => Intersecção => $V_p \wedge V_q$
II. $V_{p \vee q}$ => União => $V_p \vee V_q$
III. $V_{p \rightarrow q}$ => $V_{\neg p} \vee V_q$
IV. $V_{p \leftrightarrow q}$ => $V_{\neg p} \vee V_q \vee V_{\neg q \vee p}$

Condição universal: $V_p = U$
Condição impossível: $V_p = \varnothing$
Condição possível: $V_p \neq U$ e $V_p \neq \varnothing$

# Quantificadores
## Universal $\forall$
> "Para todo", "Qualquer", "Nenhum"

"Todo o número inteiro é par": Para todo x, se x é um inteiro, então par $\forall x, P(x) \rightarrow Q(x)$.

## Existencial $\exists$ 
>"Existe", "Algum"

"Existe um número inteiro que é positivo": 
P(x): x é um número inteiro
Q(x): x é um número positivo

Existe x **tal que** x é um número positivo e x é inteiro: $\exists x, P(x) \wedge Q(x)$

## Escopo quantificador
É a fórmula onde irão atuar os quantificadores e a variável quantificada

$$
\forall x (P(x) \rightarrow Q(x) \wedge R(x) \leftrightarrow S(n))
$$
Variável quantificada: $\forall x$
Escopo: $P(x) \rightarrow Q(x) \wedge R(x) \leftrightarrow S(n)$

1. Quantificadores diferentes não podem ser comutados:
$$
(\forall x) (\exists y) Q(x,y) \neq (\exists y)(\forall x)Q(x,y)
$$
"Para todo x existe algum y onde x<y" $\neq$ "Existe algum y maior que qualquer inteiro x"

2. Quantificadores do mesmo tipo podem ser comutados

# Simbolização de enunciados
$$
\begin{equation}
\begin{split}
	& d: Da Vinci  \\
	& p: Portinari \\
	& T(x): \ x \ é \ talentoso \\
	& F(x): \ x \ é \ famoso \\
\end{split}
\end{equation}
$$
