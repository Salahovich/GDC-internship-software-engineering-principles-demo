#!/usr/bin/env bash
# Compiles and runs LeastAstonishmentDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/leastastonishment/shoppingcart/before/*.java src/leastastonishment/shoppingcart/example/*.java src/leastastonishment/shoppingcart/exercise/*.java
java -cp out leastastonishment.shoppingcart.example.LeastAstonishmentDemo
