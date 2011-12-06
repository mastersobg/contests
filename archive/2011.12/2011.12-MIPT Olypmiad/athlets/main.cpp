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
#define x first
#define y second

typedef vector<int> vi;
typedef vector<vi> vii;
typedef pair<int,int> pii;

int main() {
#ifdef DEBUG
    freopen("input.txt", "r", stdin);
#endif
    int n;
    cin >> n;
    vector<pii> v(n);
    for(int i = 0; i < n; ++i)
	cin >> v[i].x >> v[i].y;
    sort(v.begin(), v.end());
    int ret = 1;
    ll s = v[0].x;
    for(int i = 1; i < n; ++i)
	if(v[i].y >= s) {
	    s += v[i].x;
	    ++ret;
	}
    cout << ret << endl;
    return 0;
}
