#!/usr/bin/env bash
# Compiles and runs SingletonDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/designpatterns/singleton/storeconfig/example/*.java src/designpatterns/singleton/storeconfig/exercise/*.java
java -cp out designpatterns.singleton.storeconfig.example.SingletonDemo
