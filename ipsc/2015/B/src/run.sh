#!/bin/bash

file=Main.java
name=${file%.*}

javac ${file} && echo "compiled" && java -Xmx14000m -Xms14000m -ea ${name} LOCAL_DEBUG
