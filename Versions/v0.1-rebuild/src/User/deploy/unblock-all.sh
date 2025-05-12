#!/bin/bash

# Fazer a requisição GET para a API e armazenar o retorno
response=$(curl -s -X GET http://localhost:5000/api/admin-management)

# Verificar se a resposta não está vazia
if [[ -n "$response" ]]; then
  # Usar jq para pegar os valores de "id" de cada objeto na lista
  ids=$(echo "$response" | jq -r '.[].id')
  
  for id in $ids; do
    curl -s -X POST "http://localhost:5000/api/admin-management/block/unblock/$id" \
         	-d "pq eu quero, porra" > /dev/null> /dev/null
    echo "ID $id desbloqueado com sucesso."
  done
else
  echo "Nenhum dado encontrado."
fi
