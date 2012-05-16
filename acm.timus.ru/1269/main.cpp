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
#include<ctime>

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
const int MAX_N = 16050; 

struct node_t {
    map<char,int> next;
    map<char,int> go;
    short p;
    char ch;
    short link;
    char leaf;
    char term;

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
    inline void clear() {
        link = -1;
        next.clear();
        go.clear();
        leaf = term = false;
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
    for(int i = 0; i < MAX_N; ++i) {
        node_t &node = nodes[i];
        node.clear();
    }
    nodes_used = 1;
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
        int next;
        if(node.has_next(c)){
            next = node.get_next(c);
        }
        else if(root == 0){
            next = 0;
        }
        else {
            next = go(link(root), c);
        }
        node.set_go(c, next);
    }
    return node.get_go(c);
}

const int MAX_WORDS_CNT = 10000;

string words[MAX_WORDS_CNT];
vector<string> text;

int boards(int it, int &start, int &end, int n) {
    int shift = n >> 3;
    if(shift == 0) {
        if(it == 0) {
            start = 0;
            end = n;
            return 1;
        }
        else {
            return 0;
        }
    }
    start = shift * it;
    end = start + shift;
    end = min(end, n);
    return start < n;
}

clock_t _time;
void ts() {
    _time = clock();
}
double te() {
    return (double)(clock() - _time) / (double) CLOCKS_PER_SEC;
}
int main() {
#ifndef ONLINE_JUDGE
    stdin = freopen("input.txt", "r", stdin);
#endif
    ts();
    int n;
    cin >> n;
    getchar();
    for(int i = 0; i < n; ++i){
        getline(cin, words[i]);
    }
    random_shuffle(words, words + n);
    int lines;
    cin >> lines;
    getchar();
    text.resize(lines);
    for(int i = 0; i < lines; ++i)
        getline(cin, text[i]);
    //dbg(n);
    //dbg(lines);
    pii ret = mp(INF,INF);
    int start, end;
    for(int iter = 0; boards(iter, start, end, n); ++iter) {
        init();
        for(;start < end; ++start)
            add_string(words[start]);
        //ts();
        for(int it = 0; it < lines; ++it) {
            string line = text[it];
            int root = 0;
            int found = 0;
            for(int i = line.size() - 1; i >= 0; --i) {
                char c = line[i];
                root = go(root, c);
                if(nodes[root].term) {
                    ret = min(ret, mp(it+1, i+1));
                    found = 1;
                }
                if(te() > 1.9) 
                    goto ptr;
            }
            if(found)
                break;
        }
        //dbg(te());
    }
ptr:if(ret.x == INF && ret.y == INF)
        cout << "Passed\n";
    else
        cout << ret.x << " " << ret.y << endl;
    return 0;
}

