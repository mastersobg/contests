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
#define PROBLEM "jediacademy"


int try_it(const vector<vector<int> > &g, const vector<int> &col, vector<int> p, int cur_col)
{
	const int n = g.size();
	queue<int> q[2];
	for(int i = 0; i < n; ++i)
		if(p[i] == 0)
			q[col[i]].push(i);

	// dbg(q[0].size());
	// dbg(q[1].size());
	int changes = 1;
	
	while(q[0].size() || q[1].size())
	{
		
		if(q[cur_col].empty())
		{
			cur_col ^= 1;
		    changes++;
		}
		while(!q[cur_col].empty())
		{
			int u = q[cur_col].front();
			q[cur_col].pop();
			for(int i = 0; i < g[u].size(); ++i)
			{
				int v = g[u][i];

				p[v]--;
				if(p[v] == 0)
					q[col[v]].push(v);
			}			
		}
		// dbgv(p);
	}
	return changes;	
}

int main()
{
	//freopen("input.txt", "r", stdin); 
	//freopen("output.txt", "w", stdout);
	freopen(PROBLEM ".in", "r", stdin);freopen(PROBLEM ".out", "w", stdout);

	int n;
	scanf("%d", &n);
    vector< vector<int> > g(n);
	vector<int> col(n);
	vector<int> p(n);	
	for(int i = 0; i < n; ++i)
	{
		scanf("%d", &col[i]);
		col[i]--;

		int cnt;
		scanf("%d", &cnt);
		for(int j = 0; j < cnt; ++j)
		{
			int v;
			scanf("%d", &v);
			v--;
			g[v].pb(i);
		}
		p[i] += cnt;
	}
	// for(int i = 0; i < n; ++i)
	// 	dbgv(g[i]);
	// dbgv(p);
	int time_a, time_b;
	scanf("%d%d", &time_a, &time_b);

	int v1 = try_it(g, col, p, 0);
	int v2 = try_it(g, col, p, 1);
	// dbg(v1);
	// dbg(v2);

	int best = min(v1, v2);
//	dbg(best);
	int ans = time_a * best + time_b * n;

	cout << ans << endl;

	return 0;
}

/*	TEST ZONE BEGIN
	TEST ZONE END */

#endif
