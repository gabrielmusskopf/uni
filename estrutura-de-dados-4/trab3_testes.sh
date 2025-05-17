#!/bin/bash

CPP_FILE="GABRIEL_MUSSKOPF_TRAB3_GB.cpp"
BIN="./trab3"

bin=$(g++ $CPP_FILE -o $BIN)

SUCCESS=(
    "./grafo_ok_1.txt"
    "./grafo_ok_2.txt"
    "./grafo_ok_3.txt"
    "./grafo_ok_4.txt"
    "./grafo_ok_5.txt"
    "./grafo_ok_6.txt"
)

ERROR=(
    "./grafo_erro_1.txt"
    "./grafo_erro_2.txt"
    "./grafo_erro_3.txt"
    "./grafo_erro_4.txt"
)

for teste in "${SUCCESS[@]}"; do
    echo -n "🟢 $teste ... "
    $BIN "$teste" > /dev/null 2>&1 && echo "✅ OK" || echo "❌ FALHOU (esperado sucesso)"
done

for teste in "${ERROR[@]}"; do
    echo -n "🔴 $teste ... "
    $BIN "$teste" > /dev/null 2>&1 && echo "❌ FALHOU (esperado erro)" || echo "✅ OK"
done

rm $BIN
