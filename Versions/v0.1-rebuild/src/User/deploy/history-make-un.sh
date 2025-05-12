#!/bin/bash

# Lista de IDs de exemplo (você pode substituir isso pela sua lógica de obtenção dos IDs)
user_ids=("8a65c1c0-b8eb-4bee-b64b-9c87b6102a2a" "some-other-id" "another-id")

# Função para processar cada ID e obter os detalhes da API
process_user_history() {
  local user_id=$1

  # Fazer a requisição GET para a API de histórico do usuário
  response=$(curl -s -X GET "http://localhost:5000/api/admin-management/history/$user_id")

  # Exibir a resposta bruta para depuração
  echo "Resposta da API para o ID: $user_id"
  echo "$response"
  
  # Verificar se a resposta contém um JSON válido
  if echo "$response" | jq empty > /dev/null 2>&1; then
    # Extrair as informações desejadas do JSON
    current_username=$(echo "$response" | jq -r '.current.username')
    current_email=$(echo "$response" | jq -r '.current.email')
    current_id=$(echo "$response" | jq -r '.current.id')
    current_createdAt=$(echo "$response" | jq -r '.current.createdAt | join("-")')
    current_updatedAt=$(echo "$response" | jq -r '.current.updatedAt | join("-")')

    echo "Detalhes do usuário atual:"
    echo "Username: $current_username"
    echo "Email: $current_email"
    echo "ID: $current_id"
    echo "Criado em: $current_createdAt"
    echo "Atualizado em: $current_updatedAt"
    echo "---------------------------------------"

    # Iterar sobre os updates
    echo "Atualizações:"
    updates_count=$(echo "$response" | jq '.updates | length')
    for ((i=0; i<$updates_count; i++)); do
      update_username=$(echo "$response" | jq -r ".updates[$i].username")
      update_email=$(echo "$response" | jq -r ".updates[$i].email")
      update_password=$(echo "$response" | jq -r ".updates[$i].password")
      update_createdAt=$(echo "$response" | jq -r ".updates[$i].createdAt | join('-')")
      update_updatedAt=$(echo "$response" | jq -r ".updates[$i].updatedAt | join('-')")

      echo "Atualização $((i+1))"
      echo "Username: $update_username"
      echo "Email: $update_email"
      echo "Senha: $update_password"
      echo "Criado em: $update_createdAt"
      echo "Atualizado em: $update_updatedAt"
      echo "---------------------------------------"
    done

    # Iterar sobre os blocks
    echo "Bloqueios:"
    blocks_count=$(echo "$response" | jq '.blocks | length')
    for ((i=0; i<$blocks_count; i++)); do
      block_reason=$(echo "$response" | jq -r ".blocks[$i].blockReason")
      unblock_reason=$(echo "$response" | jq -r ".blocks[$i].unblockReason")
      blocked_at=$(echo "$response" | jq -r ".blocks[$i].blockedAt | join('-')")
      unblocked_at=$(echo "$response" | jq -r ".blocks[$i].unblockedAt | join('-')")
      block_id=$(echo "$response" | jq -r ".blocks[$i].blockId")

      echo "Bloqueio $((i+1))"
      echo "Motivo do bloqueio: $block_reason"
      echo "Motivo do desbloqueio: $unblock_reason"
      echo "Bloqueado em: $blocked_at"
      echo "Desbloqueado em: $unblocked_at"
      echo "ID do bloqueio: $block_id"
      echo "---------------------------------------"
    done

  else
    echo "A resposta não contém um JSON válido para o ID: $user_id."
  fi
}

# Percorrer todos os IDs e obter as informações
for user_id in "${user_ids[@]}"; do
  process_user_history "$user_id"
done

echo "Processo concluído."

