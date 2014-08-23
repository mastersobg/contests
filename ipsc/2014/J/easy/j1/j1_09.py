#!/usr/bin/python

from __future__ import print_function
import sys
sys.setrecursionlimit(100000)

def solve(n, k):
    state = 47
    good = 0
    for step in range(8**n):
        heads = 0
        for i in range(n):
            state = ((state * 1103515245) + 12345) % 2**31
            coin = (state >> 15) & 1  # note: >> is bitwise shift right, & is bitwise and
            heads += coin
        if heads == k: good += 1
    return round(good / 4**n)         # note: / is floating-point division


for n in range(1, 11):
    for k in range(1, n + 1):
        print(solve(n, k), end=" ")
    print('\n')





