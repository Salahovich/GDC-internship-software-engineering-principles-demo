#!/usr/bin/env bash
# Compiles and runs ReadabilityGuidelinesDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/codingstandards/readability/example/*.java src/codingstandards/readability/exercise/*.java
java -cp out codingstandards.readability.example.ReadabilityGuidelinesDemo
