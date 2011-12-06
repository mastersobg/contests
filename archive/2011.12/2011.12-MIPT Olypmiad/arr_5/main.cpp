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

int main() {
#ifdef DEBUG
    freopen("input.txt", "r", stdin);
#endif
    ll n;
    cin >> n;
    for(ll i = 2; i * i <= n; ++i) {
	while(n%i==0) {
	    cout << i << " ";
	    n /= i;
	}
    }
    if(n != 1)
	cout << n << " "<< endl;
    return 0;
}
