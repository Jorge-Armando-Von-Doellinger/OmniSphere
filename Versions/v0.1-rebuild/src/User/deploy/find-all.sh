#!/bin/bash

# Fazer a requisição GET para a API e armazenar o retorno
response=$(curl -s -X GET http://localhost:5000/api/admin-management)

if [[ -n "$response" ]]; then
  # Extrair todos os itens do JSON
  items=$(echo "$response" | jq -c '.[]')

  for item in $items; do
    # Extrair os dados de cada item
    id=$(echo "$item" | jq -r '.id')
    username=$(echo "$item" | jq -r '.username')
    email=$(echo "$item" | jq -r '.email')
    password=$(echo "$item" | jq -r '.password')
    created_at=$(echo "$item" | jq -r '.createdAt')
    updated_at=$(echo "$item" | jq -r '.updatedAt')

    # Imprimir os dados do usuário
    echo "ID: $id"
    echo "Username: $username"
    echo "Email: $email"
    echo "Password: $password"
    echo "Created At: $created_at"
    echo "Updated At: $updated_at"
    echo "---------------------------------------"
  done
else
  echo "Nenhum dado encontrado."
fi

