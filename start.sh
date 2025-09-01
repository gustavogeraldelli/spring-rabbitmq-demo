#!/bin/bash

# esse script é útil quando o docker compose só sobe os containers do rabbitmq e postgres

# redirecionava os logs do start do spring para limpar o terminal e não travar

echo "Iniciando a aplicação..."

#echo "Limpando logs antigos..."
#rm -f *.log

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
    
    mvn spring-boot:run & #> "../$SVC.log" 2>&1 & 
    
    cd ..
    echo "$SVC iniciado." #Logs em $SVC.log"
    sleep 3
done

echo "Concluído!"
