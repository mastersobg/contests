#!/usr/bin/python

from __future__ import print_function
import sys
from itertools import permutations

sys.setrecursionlimit(100000)

def solve(n, k):
    brackets = '(' * n + ')' * n
    res = 0
    for perm in set(permutations(brackets)):
        # the following is executed once for each distinct permutation of our 2n brackets
        opened = 0
        maxopened = 0
        ok = True
        for b in perm:
            if b == '(': opened += 1
            if b == ')': opened -= 1
            if opened < 0: ok = False
            maxopened = max( opened, maxopened )
        if ok and maxopened == k: res += 1
    return res


for n in range(1, 11):
    for k in range(1, n + 1):
        print(solve(n, k), end=" ")
    print('\n')








