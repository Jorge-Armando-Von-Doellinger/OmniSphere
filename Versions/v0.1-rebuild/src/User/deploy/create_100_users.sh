#!/bin/bash

i=1  # Inicia a variável i

# Loop para tentar criar até 100 usuários
while [ $i -le 100 ]; do
    # Gerar valores aleatórios para username e email usando UUID
    USERNAME="user_$(uuidgen)"
    EMAIL="user_$(uuidgen)@example.com"

    # Inicializa a variável USER_ID
    USER_ID=""

    # Loop para tentar criar o usuário até obter um ID válido
    while [ -z "$USER_ID" ]; do
        USER_RESPONSE=$(curl -s -X POST http://localhost:5000/api/user \
          -H "Content-Type: application/json" \
          -H "X-USER-IDENTIFIER-STRINGVALUE: abc" \
          -d "{
            \"username\": \"$USERNAME\",
            \"email\": \"$EMAIL\",
            \"password\": \"senhaSegura123\"
          }")
        
        # Capturar o ID da resposta
        USER_ID=$(echo "$USER_RESPONSE" | jq -e -r '.id' 2>/dev/null)

        # Se um ID for encontrado, exibe uma mensagem
        if [ -n "$USER_ID" ]; then
            echo "Usuário criado com ID: $USER_ID"
            # Atualizar username e email com o ID retornado
            USERNAME="user_$USER_ID"
            EMAIL="user_$USER_ID@example.com"
            echo "Nome de usuário e e-mail atualizados com base no ID: $USER_ID"
        else
            echo "Tentativa falhou, tentando novamente..."
        fi
    done

    # Incrementa o contador de tentativas
    i=$((i + 1))  # Aumenta o valor de i em 1
done

echo "Processo concluído após $i tentativas."

