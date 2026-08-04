#!/usr/bin/env bash
# Compiles and runs VisitorDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/designpatterns/visitor/orderitempricing/example/*.java src/designpatterns/visitor/orderitempricing/exercise/*.java
java -cp out designpatterns.visitor.orderitempricing.example.VisitorDemo
