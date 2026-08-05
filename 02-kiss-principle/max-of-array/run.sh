#!/usr/bin/env bash
# Compiles and runs KissDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/kissprinciple/maxofarray/before/*.java src/kissprinciple/maxofarray/example/*.java src/kissprinciple/maxofarray/exercise/*.java
java -cp out kissprinciple.maxofarray.example.KissDemo
