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
#define dbg(x) { cerr << #x << "=" << x << endl; }
#define dbgv(v) { cerr << #v << "={";for( int I=0;I<(int)(v).size();++I)cerr << " " << (v)[I];cerr<<" }\n"; }
#define dbgm(v, n) { cerr << #v << "={";for( int I=0;I<n;++I)cerr << " " << (v)[I];cerr<<" }\n"; }

const int INF = 1 << 29;
#define filename "photo"

int month[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

struct date {
    int d, m, y;
    
    void create(string s) {
        sscanf(s.c_str(), "%d.%d.%d", &d, &m, &y);
    }

    void update_month() {
        month[2] = is_leap(y) ? 29 : 28;
    }
    bool is_leap(int y) {
        return (y % 4 == 0 && y % 100 != 0) || y % 400 == 0;
    }

    bool equals(date & d1) {
        return d == d1.d && m == d1.m && y == d1.y;
    }

    void add_day() {
        ++d;
        if(d > month[m]) {
            d = 1;
            ++m;
            if(m > 12) {
                m = 1;
                ++y;
                update_month();
            }
        }
    }

    string to_string() {
        ostringstream ss;
        if(d < 10)
            ss <<  "0";
        ss << d << ".";
        if(m < 10)
            ss << "0";
        ss << m << "." << y;
        return ss.str();
    }
    bool check_pattern(string pattern) {
        string s = this->to_string();
        for(int i = 0; i < (int)s.size(); ++i)
            if(pattern[i] != '?' && pattern[i] != s[i])
                return false;
        return true;
    }
};

string pattern;
date begin, end;

int main() {
#ifdef LOCAL
    stdin = freopen("input.txt", "r", stdin);
#else
    stdin =  freopen(filename ".in",  "r", stdin);
    stdout = freopen(filename ".out", "w", stdout);
#endif
    cin >> pattern;
    string s1, s2;
    cin >> s1 >> s2;
    begin.create(s1);
    begin.update_month();
    end.create(s2);
    int ret = 0;
    while(!begin.equals(end)) {
        if(begin.check_pattern(pattern))
            ++ret;
        begin.add_day();
    }
    if(begin.check_pattern(pattern))
        ++ret;
    cout << ret << endl;
    return 0;
}

