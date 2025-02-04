#!/bin/bash

# gestion des parametres : le premier est un fichier d'entree, le second est un fichier de sortie
if [ "$1" = '-h' -o "$1" = --help ]; then
    echo "Usage: $0 <fichier_entree> <fichier_sortie>"
    exit 1
fi

if [ $# -ne 2 ]; then
    file_in=../données/dictionnaire_utf8.fich
    file_out=../données/compressed.bin
else
    file_in="$1"
    file_out="$2"
fi


echo "Compiling the program... press any key to continue"
read -n 1 -s

mvn clean compile package install

echo "Running compression... press any key to continue"
read -n 1 -s

time java -jar "./target/maven-1.0-SNAPSHOT.jar" --compress -i "$file_in" -o "$file_out"

echo "Running decompression... press any key to continue"
read -n 1 -s

time java -jar "./target/maven-1.0-SNAPSHOT.jar" --decompress -i "$file_out" -o "$file_out.decompressed"

echo "File sizes:"
du -b "$file_in" "$file_out" "$file_out.decompressed"

echo "Comparing $file_in and $file_out.decompressed:"
if cmp "$file_in" "$file_out.decompressed"
then
    echo "Same content"
else
    echo "Different content"
fi
