#!/usr/bin/python

from __future__ import print_function
def solve(n, k):
    if n == 0 or k == 0: return 1
    s = 0
    for i in range(k - 1, n): s += solve(i, k - 1) # note: range(x,y) returns [x,x+1,...,y-1]
    return s


for n in range(1, 11):
    for k in range(1, n + 1):
        print(solve(n, k), end=" ")
    print('\n')

