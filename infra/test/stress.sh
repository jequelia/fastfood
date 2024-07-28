#!/bin/bash
echo "iniciando testes"
for i in {1..1000000}; do
    curl localhost:30001
    echo "Request $i"
    sleep $1
done
