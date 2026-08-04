#!/usr/bin/env bash
# Compiles and runs DefensiveCodeGuidelinesDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/codingstandards/defensivecode/example/*.java src/codingstandards/defensivecode/exercise/*.java
java -cp out codingstandards.defensivecode.example.DefensiveCodeGuidelinesDemo
