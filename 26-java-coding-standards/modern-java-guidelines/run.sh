#!/usr/bin/env bash
# Compiles and runs ModernJavaGuidelinesDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/codingstandards/modernjava/example/*.java src/codingstandards/modernjava/exercise/*.java
java -cp out codingstandards.modernjava.example.ModernJavaGuidelinesDemo
