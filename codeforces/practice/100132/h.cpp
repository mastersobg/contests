#if 1
#include <numeric>
#include <iostream>
#include <vector>
#include <sstream>
#include <string>
#include <set>
#include <map>
#include <queue>
#include <stack>
#include <ctime>
#include <cassert>
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <cstring>
#include <algorithm>
#include <bitset>
using namespace std;

typedef long long LL;
typedef long double LD;
typedef pair<int, int> pii;
typedef vector<int> veci;
typedef vector<veci> graph;

const int inf = 1000 * 1000 * 1000;
const LL inf64 = inf * LL(inf);
const LD eps = 1e-9;
const LD pi = acos(-1.0);

#define pb push_back
#define mp make_pair
#define X first
#define Y second
#define dbg(x) {cerr << #x << " = " << x << endl; }
#define dbgv(x) { cerr << #x << " = {"; for(int _i = 0; _i < (x).size(); ++_i) { if(_i) cerr << ", "; cerr << (x)[_i]; } cerr << "}" << endl; }
#define iss istringstream
#define oss ostringstream
#define PROBLEM "odd"

const int maxn = 200005;
int dp[maxn];
void dinit()
{
	for(int i = 0; i < maxn; ++i) dp[i] = i;
}
int dfind(int x) { return dp[x] == x ? x : dp[x] = dfind(dp[x]); }
void dunion(int x, int y) { dp[dfind(x)] = dfind(y); }

struct edge_t
{
	int u, v, id;
	edge_t() {}
	edge_t(int u, int v, int id) : u(u), v(v), id(id) {}
};

void dfs(const vector<edge_t> &edges, const vector<vector<int> > &g, vector<int> &was, vector<int> &path, int u)
{
	was[u] = true;
	for(int i = 0; i < g[u].size(); ++i)
	{
		edge_t e = edges[g[u][i]];
		if(e.v == u)
			swap(e.u, e.v);
		if(!was[e.v])
		{
			path.pb(e.id);
			dfs(edges, g, was, path, e.v);
			path.pb(e.id);
		}
	}
}

int main()
{
	//freopen("input.txt", "r", stdin); 
	//freopen("output.txt", "w", stdout);
	freopen(PROBLEM ".in", "r", stdin); freopen(PROBLEM ".out", "w", stdout);
	int n, m;
	scanf("%d%d", &n, &m);


	dinit();

	vector< edge_t > edges;
	vector<int> p(n);
	vector<int> edges_xor(m, 0);
	vector<int> in_tree(m, 0);
	for(int i = 0; i < m; ++i)
	{
		int u, v;
		scanf("%d%d", &u, &v);
		u--; v--;
		p[u] ^= 1;
		p[v] ^= 1;
		edges.pb(edge_t(u, v, i));		
		if(dfind(u) != dfind(v))
		{
			in_tree[i] = true;
			dunion(u, v);
		}
	}

	vector< vector<int> > g(n);
	for(int i = 0; i < edges.size(); ++i)
		if(in_tree[i])
		{
			g[edges[i].u].pb(i);
			g[edges[i].v].pb(i);
		}

	vector<int> path;
	vector<int> was(n);

	for(int i = 0; i < n; ++i)
		if(!was[i])
		{
			path.clear();
			dfs(edges, g, was, path, i);
			if(path.size() == 0)
			{
				// dbg(i);
				// dbg("asdf");
				cout << -1 << endl;
				return 0;
			}

			int cur = 0;
			int prev = i;
			int cnt = 0;
			if(p[i] == 0)
			{
				cnt++;
				cur ^= 1;
				p[i] ^= 1;
			}
			for(int j = 0; j < path.size(); ++j)
			{
				edge_t e = edges[path[j]];
				if(e.v == prev)
					swap(e.u, e.v);
				edges_xor[e.id] ^= cur;
				if(p[e.v] == 0)
				{
					cnt++;
					cur ^= 1;
					p[e.v] ^= 1;
				}
				prev = e.v;
			}
			if(cnt % 2 == 1)
			{
//				dbg(cnt);
				cout << -1 << endl;
				return 0;
			}
		}

	vector<int> to_del;
	for(int i = 0; i < edges_xor.size(); ++i)
		if(!edges_xor[i])
			to_del.pb(i + 1);
	cout << to_del.size() << endl;
	for(int i = 0; i < to_del.size(); ++i)
	{
		if(i) printf(" ");
		printf("%d", to_del[i]);
	}

	return 0;
}

/*	TEST ZONE BEGIN
	TEST ZONE END */

#endif
