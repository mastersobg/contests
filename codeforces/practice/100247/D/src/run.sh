#!/bin/bash

file=Solution.java
name=${file%.*}

javac ${file} && java -Xmx64m -ea -DLOCAL_DEBUG=true ${name}
