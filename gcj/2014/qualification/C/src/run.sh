#!/bin/bash

file=Solution1.java
name=${file%.*}

javac ${file} && echo "compiled" && java -Xmx64m -ea -DLOCAL_DEBUG=true ${name}  $1
