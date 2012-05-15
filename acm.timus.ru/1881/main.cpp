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

const int MAX_N = 10000;

string words[MAX_N];

int main() {
#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
#endif
    int h, w, n;
    cin >> h >> w >> n;
    for(int i = 0; i < n; ++i)
        cin >> words[i];
    int pages = 0;
    int pos = 0;
    int lines = 0;
    for(int i = 0; i < n; ++i) {
        dbg(words[i]);
        int space = (pos > 0) ? 1 : 0;
        if(pos + space + words[i].length() > w) {
            pos = 0;
            space = 0;
            lines++;
            if(lines >= h) {
                lines = 0;
                ++pages;
            }
        }
        pos += space + words[i].length();
        dbg(pos);
        dbg(lines);
        dbg(pages);
    }
    cout << pages + 1 << endl;
    return 0;
}

