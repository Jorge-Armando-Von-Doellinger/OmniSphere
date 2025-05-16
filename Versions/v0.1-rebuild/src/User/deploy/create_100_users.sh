#!/bin/bash

i=1  # Contador de usuários

# Criar até 100 usuários
while [ $i -le 100 ]; do
    # Gera username e email aleatórios
    UUID=$(uuidgen)
    USERNAME="user_$UUID"
    EMAIL="user_${UUID}@example.com"
    PASSWORD="senhaSegura123"
    
    echo "[$(date +'%T')] Criando usuário $i com username $USERNAME"

    # Envia requisição
    RESPONSE=$(curl -s -X POST http://localhost:5000/api/user \
        -H "Content-Type: application/json" \
        -H "X-USER-IDENTIFIER-STRINGVALUE: abc" \
        -d "{
              \"username\": \"$USERNAME\",
              \"email\": \"$EMAIL\",
              \"password\": \"$PASSWORD\"
            }")

    # Extrai ID com jq
    USER_ID=$(echo "$RESPONSE" | jq -e -r '.id' 2>/dev/null)

    if [ -n "$USER_ID" ]; then
        echo "✅ [$i] Usuário criado com sucesso — ID: $USER_ID"
        ((i++))
    else
        echo "❌ Falha ao criar usuário. Tentando novamente..."
        sleep 1
    fi
done

echo "🎉 Concluído: $((i-1)) usuários criados com sucesso."

