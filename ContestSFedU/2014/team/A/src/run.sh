#!/bin/bash

file=Program.java
name=${file%.*}

javac ${file} && echo "compiled" && java -Xmx64m -ea ${name} LOCAL_DEBUG
