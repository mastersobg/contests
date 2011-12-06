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
    string s;
    cin >> s;
    int open = 0;
    for(int i = 0; i < s.size(); ++i) {
	if(s[i] == '(')++open;
	else --open;
	if(open < 0){
	    cout << "NO";
	    exit(0);
	}
    }
    if(open)
	cout << "NO";
    else cout << "YES";
    return 0;
}
