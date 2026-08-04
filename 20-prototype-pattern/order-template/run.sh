#!/usr/bin/env bash
# Compiles and runs PrototypeDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/designpatterns/prototype/ordertemplate/example/*.java src/designpatterns/prototype/ordertemplate/exercise/*.java
java -cp out designpatterns.prototype.ordertemplate.example.PrototypeDemo
