echo "Parando containers e serviços"

docker compose down

pkill -f 'spring-boot:run'

echo "Concluído"