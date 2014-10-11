#!/usr/bin/python

from __future__ import print_function

def solve(n, k):
    if n < 0 or k < 0 or k > n:
        return 0
    if n == 0 or k == 0 or n == k:
        return 1
    s = 0
    step = k
    if n > 47: step = 10
    for i in range(n + 1): # note: range(x) returns [0, 1, ..., x-1]
        s += solve(n - step, i) * solve(step, k - i)
    return s


for n in range(1, 101):
    for k in range(1, n + 1):
        print(solve(n, k), end=" ")
    print('\n')
