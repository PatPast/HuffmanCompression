#!/bin/bash

# gestion des parametres : le premier est un fichier d'entree, le second est un fichier de sortie
if [ $# -ne 2 ]; then
    echo "Usage: $0 <fichier_entree> <fichier_sortie>"
    exit 1
fi


echo "Compiling the program... press any key to continue"
read -n 1 -s

mvn clean compile package install

echo "Running compression... press any key to continue"
read -n 1 -s

time java -jar "./target/tp-1.0-SNAPSHOT.jar" --compress -i $1 -o $2

echo "Running decompression... press any key to continue"
read -n 1 -s

time java -jar "./target/tp-1.0-SNAPSHOT.jar" --decompress -i $2 -o $2.decompressed

du -b $1 $2 $2.decompressed
