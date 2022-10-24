kubectl apply -f CommonConfig.yml

services=(
"Authenticationdb"
"Authentication"
)

for svc in "${services[@]}"; do
    echo "$svc"
    cd $svc
    bash deploy.sh
    cd ..
done

