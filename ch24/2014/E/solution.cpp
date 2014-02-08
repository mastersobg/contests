#if 1
#include <cstdlib>
#include <cstdio>
#include <cmath>
#include <algorithm>
#include <functional>
#include <cstring>
#include <string>
#include <vector>
#include <list>
#include <set>
#include <map>
#include <stack>
#include <queue>
#include <ctime>
#include <cassert>
#include <sstream>
#include <iostream>
#include <bitset>

using namespace std;

typedef long long LL;
typedef long double LD;
typedef pair<int , int> pii;
typedef vector <int> veci;
typedef vector <veci> graph;
const LD eps = 1e-9;
const LD pi = acos(-1.0);
const int inf = 1000 * 1000 * 1000;
const LL inf64 = LL(inf) * inf;

#define mp make_pair
#define pb push_back
#define X first
#define Y second
#define iss istringstream
#define oss ostringstream
#define dbg(x) {cerr << #x << " = " << x << endl;}
#define dbgv(x) {cerr << #x << " ={"; for (int _i = 0; _i < x.size(); _i++) {if (_i) cerr << ", "; cerr << x[_i];} cerr << "}" << endl;}
#define NAME "problem"


const LD R = 6371;



struct location_t
{
    int n;
    LD x, y;
    location_t(int n= 0, LD X = 0, LD Y = 0)
        : x(X)        , y(Y), n(n)
    {
    }
    friend bool operator < (const location_t& a, const location_t& b)
    {
        return a.y < b.y;
    }
};

const location_t north(0, pi / 2, 0);
const location_t south(0, -pi / 2, 0);


struct solution_t
{
    LD dist;
    vector<int> ls;
    solution_t(LD dist, vector<int> ls)
        : dist(dist), ls(ls) {}
    friend bool operator < (const solution_t& a, const solution_t& b)
    {
        return a.dist < b.dist;
    }
};

struct node_t
{
    LD dist;
    int c1, c2;
    node_t (LD D, int C1, int C2)
        : dist(D), c1(C1), c2(C2) {}
    friend bool operator < (const node_t& a, const  node_t& b)
    {
        return a.dist < b.dist;
    }

    pair<int, int> prev()
    {
        return make_pair(c1, c2);
    }
};
bool check2(const location_t& a, const location_t& b)
{
    if (b.y > a.y && a.y + pi > b.y
        || b.y + 2 * pi > a.y && a.y > b.y + pi)
        return true;
    else
        return false;
}

LD dist_(const location_t& a, const location_t& b)
{
    return acos(sin(a.x) * sin(b.x) + cos(a.x) * cos(b.x) * cos(b.y - a.y));
}

LD dist (const location_t& a, const location_t& b)
{
    if (check2(a, b))
        return dist_(a, b);
    else
        return min(dist_(a, north) + dist_(north, b), dist_(a, south) + dist_(south, b));
}

bool check(const location_t& a, const location_t& b)
{
    return true;
}


void output(solution_t) {

}

int main()
{
    //freopen("input.txt", "r", stdin); //freopen("output.txt", "w", stdout);
    //freopen(NAME ".in","r",stdin);freopen(NAME ".out","w",stdout);

    int n;
    cin >> n;
    vector<location_t> ls(n);
    for (size_t i = 0; i < n; ++ i)
    {
        cin >> ls[i].x >> ls[i].y;
        ls[i].x /= 180;
        ls[i].x *= pi;
        ls[i].y /= 180;
        ls[i].y *= pi;
        ls[i].n = i;
    }
    dbg(dist(ls[4], ls[1]));
    dbg(dist(ls[1], ls[5]));
    dbg(dist(ls[5], ls[2]));
    dbg(dist(ls[2], ls[0]));
    dbg(dist(ls[0], ls[3]));
    dbg(dist(ls[3], ls[4]));



    sort(ls.begin(), ls.end());

    // one circle

    solution_t best(0, vector<int>());
    best.ls.pb(ls[0].n);
    for (int i = 0; i < n; ++ i)
    {
        int next = (i + 1) % n;
        best.dist += dist(ls[i], ls[next]);
        best.ls.pb(ls[next].n);
    }

    // two circles
    for (int z = 1; z < n; ++ z)
    {
        vector<vector<node_t> > dp(n, vector<node_t>(n, node_t(inf, -1, -1) ));
        vector<node_t> t0;
        vector<node_t> t1;
        t0.push_back(node_t(0, 0, z));
        for (int i = 1; i < z; ++ i)
        {
//            sort(t0.begin(), t0.end());
            for (int j = 0; j < t0.size(); ++ j)
            {
                if (dp[t0[j].c1][t0[j].c2].dist >= t0[j].dist - eps)
                {
                    if (check(ls[t0[j].c1], ls[i]))
                    {
                        LD d = dist(ls[t0[j].c1], ls[i]) + t0[j].dist;
                        if (dp[i][t0[j].c2].dist > d)
                        {
                            dp[i][t0[j].c2] = t0[j];
                            dp[i][t0[j].c2].dist = d;
                            t1.pb(node_t(d, i, t0[j].c2));
                        }
                    }
                    if (check(ls[t0[j].c2], ls[i]))
                    {
                        LD d = dist(ls[t0[j].c2], ls[i]) + t0[j].dist;
                        if (dp[t0[j].c1][i].dist > d)
                        {
                            dp[t0[j].c1][i] = t0[j];
                            dp[t0[j].c1][i].dist = d;
                            t1.pb(node_t(d, t0[j].c1, i));
                        }
                    }
                }
            }
            swap(t1, t0);
            t1.clear();
        }
//        sort(t0.begin(), t0.end());
        for (int j = 0; j < t0.size(); ++ j)
        {
            if (dp[t0[j].c1][t0[j].c2].dist >= t0[j].dist - eps)
            {
                if (check(ls[t0[j].c1], ls[z]))
                {
                    LD d = dist(ls[t0[j].c1], ls[z]) + t0[j].dist;
                    if (dp[z][t0[j].c2].dist > d)
                    {
                        dp[z][t0[j].c2] = t0[j];
                        dp[z][t0[j].c2].dist = d;
                        t1.pb(node_t(d, z, t0[j].c2));
                    }
                }
            }
        }
        swap(t1, t0);
        t1.clear();
        for (int i = z + 1; i < n; ++ i)
        {
//            sort(t0.begin(), t0.end());
            for (int j = 0; j < t0.size(); ++ j)
            {
                if (dp[t0[j].c1][t0[j].c2].dist >= t0[j].dist - eps)
                {
                    if (check(ls[t0[j].c2], ls[i]))
                    {
                        LD d = dist(ls[t0[j].c2], ls[i]) + t0[j].dist;
                        if (dp[t0[j].c1][i].dist > d)
                        {
                            dp[t0[j].c1][i] = t0[j];
                            dp[t0[j].c1][i].dist = d;
                            t1.pb(node_t(d, t0[j].c1, i));
                        }
                    }
                }
            }
            swap(t1, t0);
            t1.clear();
        }
//        sort(t0.begin(), t0.end());
        for (int j = 0; j < t0.size(); ++ j)
        {
                if (dp[t0[j].c1][t0[j].c2].dist >= t0[j].dist - eps)
                {
                    if (check(ls[t0[j].c2], ls[0]))
                    {
                        LD d = dist(ls[t0[j].c2], ls[0]) + t0[j].dist;
                        if (dp[t0[j].c1][0].dist > d)
                        {
                            dp[t0[j].c1][0] = t0[j];
                            dp[t0[j].c1][0].dist = d;
                            t1.pb(node_t(d, t0[j].c1, 0));
                        }
                    }
                }
        }
        swap(t1, t0);
        t1.clear();
        if (dp[z][0].dist < best.dist)
        {
            best.dist = dp[z][0].dist;
            best.ls.clear();
            pii cur = mp(z, 0);
            vector<int> f, s;
            s.push_back(cur.Y);
            while (dp[cur.X][cur.Y].prev() != mp(-1, -1))
            {
                pii prev = dp[cur.X][cur.Y].prev();
                if (prev.X != cur.X)
                    f.pb(prev.X);
                else if (prev.Y != cur.Y)
                    s.pb(prev.Y);
                cur = prev;
            }
            reverse(f.begin(), f.end());
            reverse(s.begin(), s.end());
            f.insert(f.end(), s.begin(), s.end());
            best.ls = f;
        }
    }
    cerr.precision(9);
    cerr.setf(ios::fixed);
    cout.precision(9);
    cout.setf(ios::fixed);
    LD checker = 0;
    for (int i = 0; i + 1 < best.ls.size(); ++ i)
    {
        if (check(ls[best.ls[i]], ls[best.ls[i + 1]]))
            checker += dist(ls[best.ls[i]], ls[best.ls[i + 1]]);
        else
            checker += 2 * pi - dist(ls[best.ls[i]], ls[best.ls[i + 1]]);
    }
    cerr << checker * R << endl;
    cout << best.dist * R << endl;
    for (int i = 0; i < best.ls.size(); ++ i)
    {
        if (i)
            cout << " " << ls[best.ls[i]].n;
        else
            cout << ls[best.ls[i]].n;
    }
    cout << endl;


    return 0;
}
/*************************
*************************/
#endif