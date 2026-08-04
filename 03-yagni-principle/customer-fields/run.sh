#!/usr/bin/env bash
# Compiles and runs YagniDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/yagniprinciple/customerfields/YagniDemo.java
java -cp out yagniprinciple.customerfields.YagniDemo
