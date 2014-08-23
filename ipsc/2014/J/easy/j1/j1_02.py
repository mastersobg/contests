#!/usr/bin/python

from __future__ import print_function

def solve(n, k):
    dx = [ 1, 0, -1, 0]
    dy = [ 0,-1,  0, 1]
    def move(x, y, d):
        if x == k and y == n - k: return 1
        if d == 0: return 0
        sol = 0
        for i in range(4):
            sol += move(x + dx[i], y + dy[i], d - 1)
        return sol
    return move(0, 0, n)


for n in range(1, 11):
    for k in range(1, n + 1):
        print(solve(n, k), end=" ")
    print('\n')
