#!/usr/bin/env bash
# Compiles and runs FacadeDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/designpatterns/facade/checkoutfacade/example/*.java src/designpatterns/facade/checkoutfacade/exercise/*.java
java -cp out designpatterns.facade.checkoutfacade.example.FacadeDemo
