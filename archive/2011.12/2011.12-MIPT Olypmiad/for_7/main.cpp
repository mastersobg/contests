#include <iostream>
#include <cstdio>
#include <vector>
#include <map>
#include <set>
#include <cmath>
#include <cstring>
#include <string>
#include <memory.h>
#include <algorithm>

using namespace std;

#define ll long long
#define pb push_back
#define mp make_pair

typedef vector<int> vi;
typedef vector<vi> vii;
typedef pair<int,int> pii;

ll gcd(ll a, ll b) {
    return b == 0 ? a : gcd(b, a % b);
}
ll lca(ll a, ll b) {
    return a * b / gcd(a, b);
}
int main() {
#ifdef DEBUG
    freopen("input.txt", "r", stdin);
#endif
    ll a, b;
    cin >> a >> b;
    cout << gcd(a, b) << " " << lca(a, b) << endl;
    return 0;
}
