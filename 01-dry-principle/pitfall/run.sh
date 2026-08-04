#!/usr/bin/env bash
# Compiles and runs DryPitfallDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/dryprinciple/pitfall/DryPitfallDemo.java
java -cp out dryprinciple.pitfall.DryPitfallDemo
