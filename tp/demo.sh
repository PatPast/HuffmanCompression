#!/bin/bash


echo "Compiling the program... press any key to continue"
read -n 1 -s

mvn clean compile package install

echo "Running compression... press any key to continue"
read -n 1 -s

time java -jar "./target/tp-1.0-SNAPSHOT.jar" --compress -i "../données/dictionnaire_utf8.fich" -o "../données/compressed.bin"
du -b ../données/dictionnaire_utf8.fich ../données/compressed.bin

echo "Running decompression... press any key to continue"
read -n 1 -s

time java -jar "./target/tp-1.0-SNAPSHOT.jar" --decompress -i "../données/compressed.bin" -o "../données/decompressed.fich"



cd ../tp/
