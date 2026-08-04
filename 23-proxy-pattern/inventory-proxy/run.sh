#!/usr/bin/env bash
# Compiles and runs ProxyDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/designpatterns/proxy/inventoryproxy/example/*.java src/designpatterns/proxy/inventoryproxy/exercise/*.java
java -cp out designpatterns.proxy.inventoryproxy.example.ProxyDemo
