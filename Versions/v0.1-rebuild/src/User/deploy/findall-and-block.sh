#!/bin/bash

# Fazer a requisição GET para a API e armazenar o retorno
response=$(curl -s -X GET http://localhost:5000/api/admin-management)

# Verificar se a resposta não está vazia
if [[ -n "$response" ]]; then
  # Usar jq para pegar os valores de "id" de cada objeto na lista
  ids=$(echo "$response" | jq -r '.[].id')
  
  # Loop para iterar sobre cada "id" encontrado
  for id in $ids; do
    # Enviar a requisição para bloquear o "id" na nova rota
    curl -s -X POST "http://localhost:5000/api/admin-management/block/$id" \
         	-d "pq eu quero, porra" > /dev/null> /dev/null
    echo "ID $id bloqueado com sucesso."
  done
else
  echo "Nenhum dado encontrado."
fi

