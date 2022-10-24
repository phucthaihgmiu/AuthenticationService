#!/bin/bash

kubectl delete -f CommonConfig.yml

for dir in */ ; do
    echo "$dir"
    cd $dir
    bash delete.sh
    cd ..
done

