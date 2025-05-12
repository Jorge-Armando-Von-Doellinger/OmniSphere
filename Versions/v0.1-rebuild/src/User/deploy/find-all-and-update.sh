#!/bin/bash

# Fazer a requisição GET para a API e armazenar o retorno
response=$(curl -s -X GET http://localhost:5000/api/admin-management)

if [[ -n "$response" ]]; then
  # Extrair os IDs da resposta
  ids=$(echo "$response" | jq -r '.[].id')
  
  for id in $ids; do
    # Enviar a requisição PATCH para cada ID
    curl -s -X PATCH "http://localhost:5000/api/admin-management/$id" \
          -H "Content-Type: application/json" \
          -d "{\"username\": \"batata-atualizadabb $id\"}" > /dev/null 2>&1
    
    echo "ID $id atualizado com sucesso."
  done
else
  echo "Nenhum dado encontrado."
fi

