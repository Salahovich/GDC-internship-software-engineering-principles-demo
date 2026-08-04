#!/usr/bin/env bash
# Compiles and runs ChainOfResponsibilityDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/designpatterns/cor/ordervalidationchain/example/*.java src/designpatterns/cor/ordervalidationchain/exercise/*.java
java -cp out designpatterns.cor.ordervalidationchain.example.ChainOfResponsibilityDemo
