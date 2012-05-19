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
typedef long double ld;

#define x first
#define y second
#define mp make_pair
#define pb push_back
#define dbg(x) { cerr << #x << "=" << x << endl; }
#define dbgv(v) { cerr << #v << "={";for( int I=0;I<(int)(v).size();++I)cerr << " " << (v)[I];cerr<<" }\n"; }
#define dbgm(v, n) { cerr << #v << "={";for( int I=0;I<n;++I)cerr << " " << (v)[I];cerr<<" }\n"; }

const int INF = 1 << 29;
#define filename "gagarincup"

ld p[2][2];
char s[100];

int w1, w2;
int step[] = {0, 0, 1, 1, 0, 1, 0};
ld dp[5][5];

int main() {
#ifdef LOCAL
    stdin = freopen("input.txt", "r", stdin);
#else
    stdin =  freopen(filename ".in",  "r", stdin);
    stdout = freopen(filename ".out", "w", stdout);
#endif
    for(int i = 0; i < 2; ++i)
        for(int j = 0; j < 2; ++j)
            cin >> p[i][j];
    cin >> s;
    sscanf(s, "%d-%d", &w1, &w2);
    dp[0][0] = 1.0;
    for(int i = 0; i < 4; ++i) {
        for(int j = 0; j < 4; ++j) {
            int play = step[i + j];
            dp[i+1][j] += dp[i][j] * p[play][0];
            dp[i][j+1] += dp[i][j] * p[play][1];
        }
    }
    cout << dp[w1][w2] << endl;
    return 0;
}

