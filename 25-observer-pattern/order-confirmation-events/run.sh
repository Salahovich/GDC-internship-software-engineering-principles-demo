#!/usr/bin/env bash
# Compiles and runs ObserverDemo (requires JDK 21+).
set -e
cd "$(dirname "$0")"
mkdir -p out
javac --release 21 -d out src/designpatterns/observer/orderconfirmationevents/example/*.java src/designpatterns/observer/orderconfirmationevents/exercise/*.java
java -cp out designpatterns.observer.orderconfirmationevents.example.ObserverDemo
