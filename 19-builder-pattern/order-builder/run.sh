#!/usr/bin/env bash
# Compiles and runs BuilderDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/designpatterns/builder/orderbuilder/example/*.java src/designpatterns/builder/orderbuilder/exercise/*.java
java -cp out designpatterns.builder.orderbuilder.example.BuilderDemo
