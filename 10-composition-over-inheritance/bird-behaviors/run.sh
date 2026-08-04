#!/usr/bin/env bash
# Compiles and runs CompositionOverInheritanceDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/compositionoverinheritance/birdbehaviors/CompositionOverInheritanceDemo.java
java -cp out compositionoverinheritance.birdbehaviors.CompositionOverInheritanceDemo
