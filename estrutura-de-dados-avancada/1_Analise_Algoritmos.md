# Análise de algoritimos

"Instância" conjunto de dados de entrada

Cormen

## Exercícios

1.1)
```clang
int conta = 0;
for(int i=0; i < n; i++){           // n + 1
    for(int j=0; j < n; j++){       // n * (n + 1)
        conta++;                    // n*n
    }
}
```

| Melhor | Pior | Médio |
|--------|--------|--------|
| n+1    | n+1    | n+1    |
| n*(n+1)| n*(n+1)| n*(n+1)|
| n*n    | n*n    | n*n    |

R: `2n² + 2n + 1`

1.2)

```clang
int conta = 0;
int i = 1;
while(i < n) {                      //n
    for(int j = 0; j < n; j++){     // (n-1)*(n+1)
        conta++;                    // (n-1)*n
    }
    i++;                            // n-1
}
```

```
n + (n-1)*(n+1) + (n-1)*n + n-1
n + (n² + n -n -1) + n² - n + n - 1
n + n² - 1 + n² -1

R: 2n² + n - 2
```

1.3) Dois algoritmos A e B possuem complexidade n^5 e 2^n, respectivamente. Você utilizaria o algoritmo B ao invés do A. Em qual caso? Desenvolva um gráfico com a análise dos dois algoritmos.

| |A|B|
|--|------|----|
| n | n^5  | 2^n| 
| 1 |  1   |  2 |
| 2 |  32  |  4 |
| 3 | 243  |  8 |
| 4 | 1024 | 16 |
| 5 | 3125 | 32 |
|20 |320000 |1048567|
|23 |6436343|8388608|

Usaria o B caso o número de entradas fosse menor que 23

1.4) 1.4. Baseando-se no algoritmo abaixo determine a ordem de complexidade. Podemos dizer que o algoritmo é O(n2)? Justifique.

```java
public void MaxMin(int[] a) {
    int max = a[0];
    int min = a[0];
    for(int i = 1; i < a.length; i++) {
        if(a[i] > max) max = a[i];
        if(a[i] < min) min = a[i];
    }
}
```

Não podemos, o algoritimo tem ordem O(n), pois tem somente um laço, fazendo com que seja feita uma oparação para cada elemento de entrada.
