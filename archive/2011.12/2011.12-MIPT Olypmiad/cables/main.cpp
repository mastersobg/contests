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

int n, k;
vi v;

int check(int len) {
    int ret = 0;
    for(int i = 0; i < (int) v.size(); ++i) {
	ret += v[i] / len;
    }
    return ret;
}
int main() {
#ifdef DEBUG
    freopen("input.txt", "r", stdin);
#endif
    cin >> n >> k;
    v.resize(n);
    int mx = 0;
    for(int i = 0; i < n; ++i) {
	cin >> v[i];
	mx = max(mx, v[i]);
    }
    int l = 0, r = mx + 1;
    while(l + 1 < r) {
	int m = (l+r) >> 1;
	if(check(m) >= k)
	    l = m;
	else r = m;
    }
    if(check(r) >= k)
	cout << r;
    else cout << l;
    cout << endl;
    return 0;
}
