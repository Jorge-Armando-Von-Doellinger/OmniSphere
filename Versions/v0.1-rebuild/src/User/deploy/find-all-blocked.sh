#!/bin/bash

# Fazer a requisição GET para a API e armazenar o retorno
response=$(curl -s -X GET http://localhost:5000/api/admin-management/blocked)

# Exibir a resposta bruta para depuração
echo "Resposta bruta da API: $response"

if [[ -n "$response" ]]; then
  # Verificar se a resposta é um JSON válido antes de prosseguir
  if echo "$response" | jq empty > /dev/null 2>&1; then
    # Contar o número de itens no JSON
    count=$(echo "$response" | jq length)
    echo "Número de itens retornados: $count"
    
    # Extrair todos os itens do JSON
    items=$(echo "$response" | jq -c '.[]')

    for item in $items; do
      # Extrair os dados de cada item
      id=$(echo "$item" | jq -r '.id')
      username=$(echo "$item" | jq -r '.username')
      email=$(echo "$item" | jq -r '.email')
      password=$(echo "$item" | jq -r '.password')
      
      # Extrair e formatar a data createdAt
      createdAtArray=$(echo "$item" | jq -r '.createdAt')
      createdAt=$(date -d "$(echo $createdAtArray | jq -r 'join("-")')" +"%Y-%m-%d %H:%M:%S")

      # Extrair e formatar a data updatedAt
      updatedAtArray=$(echo "$item" | jq -r '.updatedAt')
      updatedAt=$(date -d "$(echo $updatedAtArray | jq -r 'join("-")')" +"%Y-%m-%d %H:%M:%S")

      # Exibir os dados do item
      echo "ID: $id"
      echo "Username: $username"
      echo "Email: $email"
      echo "Password: $password"
      echo "CreatedAt: $createdAt"
      echo "UpdatedAt: $updatedAt"
      echo "---------------------------------------"
    done
  else
    echo "A resposta não contém um JSON válido."
  fi
else
  echo "Nenhum dado encontrado."
fi

