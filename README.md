# L3 POO - TP : Compression et Décompression avec Huffman

## **Description**
Ce projet implémente un algorithme de compression et de décompression basé sur les arbres de Huffman.

## **Auteurs**
- Boulanger Matthieu
- Pastouret Patrick

## **Structure**
- `hints/` : Documents d'accompagnement (rapport type, guide des bonnes pratiques).  
- `TP/*.pdf` : Sujets détaillés des TP composant le mini-projet.
- `maven/` : racine du projet Maven structurant le code du mini-projet.
- `données/` : fichiers de données de test.
- `doc/` : documentation du projet.

## **Prérequis**
- Java JDK 16+  
- Apache Maven  

## **Instructions**
1. Clonez le projet :  
   ```bash
   git clone https://git.univ-lemans.fr/s123690/tp2_arbres_boulanger_pastouret.git
   cd tp2_arbres_boulanger_pastouret/maven
    ```
2. Compilez :
```bash
    mvn clean package
 ```
 3. Exécutez :
  ```bash
    java -jar target/tp-1.0-SNAPSHOT.jar -c -i ../données/dictionnaire_utf8.fich -o ../données/compressed.bin
    java -jar target/tp-1.0-SNAPSHOT.jar -d -i ../données/compressed.bin -o ../données/decompressed.fich
```
 Vous pouvez alternativement utiliser le script de démonstration :
 ```bash
    bash demo.sh
 ``` 
 4. Vérifiez les fichiers générés `compressed.bin` et `decompressed.fich`.
 Pour vérifier le ratio de compression :
 ```bash
 du -b ../données/dictionnaire_utf8.fich ../données/compressed.bin
 ```
 Pour vérifier la conservation des données après un cycle compression/décompression :
 ```bash
 cmp ../données/dictionnaire_utf8.fich ../données/decompressed.fich
 ```