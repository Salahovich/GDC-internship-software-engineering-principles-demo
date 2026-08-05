#!/usr/bin/env bash
# Compiles and runs FailFastDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/failfast/discountbatch/example/*.java src/failfast/discountbatch/exercise/*.java
java -cp out failfast.discountbatch.example.FailFastDemo
