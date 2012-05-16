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
const int MAX_N = 102400; //100kb

struct node_t {
    map<char,int> next;
    map<char,int> go;
    int p;
    char ch;
    int link;
    bool leaf;
    bool term;

    inline bool has_next(char c) {
        return next.find(c) != next.end();
    }
    inline int get_next(char c) {
        return next[c];
    }

    inline void set_next(char c, int value) {
        next[c] = value;
    }

    inline bool has_go(char c) {
        return go.find(c) != go.end();
    }

    inline int get_go(char c) {
        return go[c];
    }

    inline void set_go(char c, int value) {
        go[c] = value;
    }

    inline void set_data(int p, int ch) {
        this->p = p;
        this->ch = ch;
        this->link = -1;
    }

    inline void clear_next(){
        next.clear();
    }

    inline void clear_go() {
        go.clear();
    }
};

node_t nodes[MAX_N];
int nodes_used = 0;

void init() {
    node_t &root = nodes[nodes_used++];
    root.link = -1;
    root.clear_next();
    root.clear_go();
}
void add_string(string & s, int root = 0) {
    for(int i = s.size() - 1; i >= 0; --i) {
        char c = s[i];
        node_t &node = nodes[root];
        if(!node.has_next(c)) {
            int next_node_idx = nodes_used++;
            node_t &next_node = nodes[next_node_idx];
            next_node.set_data(root, c);
            node.set_next(c, next_node_idx);
        }
        root = node.get_next(c);
    }
    nodes[root].leaf = true;
    nodes[root].term = true;
}

int go(int root, char c);

int link(int root) {
    node_t &node = nodes[root];
    if(node.link == -1) {
        int next;
        if(root == 0 || node.p == 0)
            next = 0;
        else {
            next = go(link(node.p), node.ch);
        }
        node.link = next;
        node.term |= nodes[next].term;
    }
    return node.link;
}
int go(int root, char c) {
    node_t &node = nodes[root];
    if(!node.has_go(c)) {
        //dbg(root);
        //dbg(node.has_next(c));
        //dbg((int)c);
        int next;
        if(node.has_next(c)) {
            next = node.get_next(c);
        }
        else if(root == 0){
            next = 0;
        }
        else {
            //dbg("here");
            next = go(link(root), c);
        }
        node.set_go(c, next);
    }
    return node.get_go(c);
}
int main() {
#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
#endif
    init();
    int n;
    cin >> n;
    getchar();
    for(int i = 0; i < n; ++i) {
        string s;
        getline(cin, s);
        add_string(s);
    }
    int lines;
    cin >> lines;
    getchar();
    for(int it = 0; it < lines; ++it) {
        string line;
        getline(cin, line);
        int root = 0;
        for(int i = line.size() - 1; i >= 0; --i){
            char c = line[i];
            root = go(root, c);
            //dbg(root);
            //dbg(nodes[1].term);
            if(nodes[root].term) {
                //dbg(i);
                cout << (it + 1) << " " << (i + 1) << endl;
                return 0;
            }
        }
    }
    cout << "Passed\n";
    return 0;
}

