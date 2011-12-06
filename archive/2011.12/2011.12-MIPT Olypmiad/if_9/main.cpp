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
    int a, b, c;
    cin >> a >> b >> c;
    if(a + b > c && a + c > b && b + c > a)
	cout << "YES";
    else
	cout << "NO";
    return 0;
}
