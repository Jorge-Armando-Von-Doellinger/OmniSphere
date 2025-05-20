#!/bin/bash

create_user() {
    UUID=$(uuidgen)
    USERNAME="user_$UUID"
    EMAIL="user_${UUID}@example.com"
    PASSWORD="senhaSegura123"

    echo "[$(date +'%T')] Criando usuário com username $USERNAME"

    RESPONSE=$(curl -s -X POST http://localhost:5000/api/user \
        -H "Content-Type: application/json" \
        -H "X-USER-IDENTIFIER-STRINGVALUE: abc" \
        -d "{
              \"username\": \"$USERNAME\",
              \"email\": \"$EMAIL\",
              \"password\": \"$PASSWORD\"
            }")

    USER_ID=$(echo "$RESPONSE" | jq -e -r '.id' 2>/dev/null)

    if [ -n "$USER_ID" ]; then
        echo "✅ Usuário criado — ID: $USER_ID"
    else
        echo "❌ Falha ao criar usuário."
    fi
}

export -f create_user

# Executa 1000 vezes a função, com até 10 execuções paralelas
seq 1 100 | parallel -j 10 create_user

