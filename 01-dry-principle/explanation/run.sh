#!/usr/bin/env bash
# Compiles and runs DryExplanationDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/dryprinciple/explanation/before/*.java src/dryprinciple/explanation/example/*.java src/dryprinciple/explanation/exercise/*.java
java -cp out dryprinciple.explanation.example.DryExplanationDemo
