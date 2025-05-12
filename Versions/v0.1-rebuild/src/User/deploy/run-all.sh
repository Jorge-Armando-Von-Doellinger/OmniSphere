#!/bin/bash

# Obter o diretório do script atual
SCRIPT_DIR=$(dirname "$0")

# Navegar até o diretório do script (caso não esteja no mesmo diretório)
cd "$SCRIPT_DIR" || exit

# Loop através de todos os arquivos .sh no diretório atual
for script in *.sh; do
    # Verifica se é um arquivo e se tem permissão de execução
    if [[ -f "$script" && -x "$script" ]]; then
        echo "Executando $script..."
        ./"$script"   # Executa o script
    else
        echo "Ignorando $script (não é um arquivo executável)"
    fi
done

echo "Todos os scripts foram executados."
