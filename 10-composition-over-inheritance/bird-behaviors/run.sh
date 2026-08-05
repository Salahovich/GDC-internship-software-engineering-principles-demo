#!/usr/bin/env bash
# Compiles and runs CompositionOverInheritanceDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/compositionoverinheritance/birdbehaviors/example/*.java src/compositionoverinheritance/birdbehaviors/exercise/*.java
java -cp out compositionoverinheritance.birdbehaviors.example.CompositionOverInheritanceDemo
