#!/bin/bash

echo "Initializing DB..."
cd db || exit
./start-local.sh || exit
echo "DB initialized"

echo "Building project..."
cd ..
./mvnw clean install
echo "Project built"
