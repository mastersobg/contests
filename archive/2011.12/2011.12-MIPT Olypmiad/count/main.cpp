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
#define dbgv(v) for(int i = 0; i < (v).size(); ++i) cerr << v[i] << " "; cerr << endl;

typedef vector<int> vi;
typedef vector<vi> vii;
typedef pair<int,int> pii;

int k;

bool check(int m) {
    vi v(k << 1);
    for(int i = 0; i < v.size(); ++i)
	v[i] = i;
    vi ret;
    int p = 1;
    dbgv(v);
    int all = k << 1;
    for(int i = 0; i < all; ++i) {
	p = (p + m - 1) % v.size();
	ret.pb(v[p]);
	v.erase(v.begin() + p);
	dbgv(v);
    }
    dbgv(ret);
    for(int i = 0; i < k; ++i)
	if(ret[i] < k)
	    return false;
    return true;
}
int main() {
#ifdef DEBUG
    freopen("input.txt", "r", stdin);
#endif
    cin >> k;
    for(int m = 30; m < 31; ++m)
	if(check(m)) {
	    cout << m << endl;
	    exit(0);
	}
    return 0;
}
