#include<iostream>
#include<cstdio>
#include<set>
#include<map>
#include<vector>
#include<queue>
#include<algorithm>
#include<string>
#include<cstring>
#include<memory.h>
#include<cmath>
#include<cassert>
#include<sstream>
#include<cstdlib>

using namespace std;

typedef long long ll;
typedef pair<int,int> pii;
typedef vector<int> vi;
typedef vector<vi> vii;
typedef vector<pii> vpii;

#define x first
#define y second
#define mp make_pair
#define pb push_back
#define dbg( x ) { cerr << #x << "=" << x << endl; }
#define dbgv( v ) { cerr << #v << "={";for( int I=0;I<(int)(v).size();++I)cerr << " " << (v)[i];cerr<<" }\n"; }
#define dbgm( v, n ) { cerr << #v << "={";for( int I=0;I<n;++I)cerr << " " << (v)[i];cerr<<" }\n"; }

const int INF = 1 << 29;

int can_be_valid(string &s) {
    int opened = 0;
    for(int i = 0; i < s.size(); ++i){
        if(s[i] == '(')
            ++opened;
        else
            --opened;
    }
    return !opened;
}
int main() {
#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
#endif
    string s;
    cin >> s;
    if(!can_be_valid(s)) {
        cout << 0 << endl;
        return 0;
    }
    s += s;
    int start = 0;
    int slen = s.size() >> 1;
    int pos = 0;
    int balance = 0;
    while(start + pos < s.size() && pos < slen) {
        char c = s[start + pos];
        if(c == '(')
            ++balance;
        else
            --balance;
        if(balance < 0) {
            start = start + pos + 1;
            pos = -1;
            balance = 0;
        }
        ++pos;
    }
    if(pos != slen) {
        cout << 0 << endl;
        return 0;
    }
    balance = 0;
    int ret = 0;
    for(int i = start, j = 0; j < slen; ++i, ++j) {
        if(s[i] == '(')
            ++balance;
        else
            --balance;
        if(!balance)
            ++ret;
    }
    cout << ret << endl;
    return 0;
}

