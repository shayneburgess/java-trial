#!/bin/bash

echo "Initializing DB..."
cd db || exit
./start-local.sh || exit
echo "DB initialized"

echo "Building project..."
cd ..
mvn clean install
echo "Project built"
