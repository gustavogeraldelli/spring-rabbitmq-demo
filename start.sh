#!/bin/bash

echo "Iniciando a aplicação..."

# It's a good practice to clean up old log files first
echo "Limpando logs antigos..."
rm -f *.log

echo "Subindo containers de infraestrutura..."
docker compose up -d

echo "Aguardando containers..."
sleep 5

echo "Iniciando microsserviços..."

SERVICES=(
    "ms-proposta"
    "ms-analise-credito"
    "ms-notificacao"
)

for SVC in "${SERVICES[@]}"
do
    echo "Iniciando $SVC..."
    cd $SVC
    
    # This is the corrected line:
    # It runs the command in the background (&)
    # AND redirects its output (stdout and stderr) to a log file.
    mvn spring-boot:run > "../$SVC.log" 2>&1 &
    
    cd ..
    echo "$SVC iniciado. Logs em $SVC.log"
    sleep 3
done

echo "Concluído! Todos os serviços estão iniciando. 🚀"
echo "Para ver os logs, use o comando: tail -f <nome-do-serviço>.log"