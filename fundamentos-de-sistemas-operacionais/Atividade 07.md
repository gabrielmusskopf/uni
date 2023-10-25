Analisando prioridade de processos com o projeto  https://github.com/scheduler-tools/schedtool-dl.git

1. Processo: `sleep 15000 &`

```shell
$  schedtool  3895107

PID 3895107: PRIO   0, POLICY N: SCHED_NORMAL  , NICE   5, AFFINITY 0xff
```

2. Mudando a prioridade
```shell
$  sudo schedtool -R -p 20 3895107
$  schedtool  3895107

PID 3895107: PRIO  20, POLICY R: SCHED_RR      , NICE   5, AFFINITY 0xff
```

3. Mudando o processador
```shell
$  sudo schedtool -a 0,1 3895107
$  schedtool  3895107

PID 3895107: PRIO  20, POLICY R: SCHED_RR      , NICE   5, AFFINITY 0x3

$  sudo schedtool -a 0,2 3895107
$  schedtool  3895107

PID 3895107: PRIO  20, POLICY R: SCHED_RR      , NICE   5, AFFINITY 0x5

```