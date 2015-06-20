#!/bin/bash

file=Main.java
name=${file%.*}

javac ${file} && echo "compiled" && java -Xmx15000m -Xms15000m -ea ${name} LOCAL_DEBUG
