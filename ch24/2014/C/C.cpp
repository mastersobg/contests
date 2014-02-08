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
#include <fstream>
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

const int DIM = 65536;
const int LLEN = 16;
typedef unsigned char byte;
typedef unsigned short word;
typedef unsigned int uint;
const word CEOF = 256;
byte get_low(word a)
{
    return a & ((1u << 8) - 1);
}
byte get_high(word a)
{
    return a >> 8;
}
byte get_low4(byte a)
{
    return a & ((1u << 4) - 1);
}
byte get_high4(byte a)
{
    return a >> 4;
}

class PolyVM
{
private:
    struct color_t;
    struct poly_t;
    struct line_t;
    struct color_t
    {
        byte r, g, b, a;
    };
    struct coord_t
    {
        word x, y;

        LL to_int() const { return LL(y) * DIM + x; }
        friend bool operator < (const coord_t &a, const coord_t &b)
        {
            return a.to_int() < b.to_int();
        }
        friend bool operator == (const coord_t &a, const coord_t &b)
        {
            return a.to_int() == b.to_int();
        }
    };

    typedef pair< coord_t, line_t * > jump_t;



    struct poly_t
    {
        color_t fill, stroke;
        vector< jump_t > jumps;
    };

    struct line_t
    {
        color_t color;
        vector< coord_t > points;
        int from_poly, to_poly;
        const coord_t &get_start() const { return points[0]; }
        const coord_t &get_end() const { return points.back(); }
    };


    struct state_t
    {
        vector<word> registers;
        state_t() 
        {
            for (int i = 0; i < 16; ++i)
                registers.push_back(i);
            inp_ptr = 0;
            last_polygon_read = -1;
            finished = false;
        }
        void set_input(const vector<byte> &in)
        {
            in_data = in;
        }

        vector<byte> get_output() const { return out_data; }
        vector<byte> in_data;
        vector<byte> out_data;
        int inp_ptr;
        int last_polygon_read;
        word last_read_res;
        int polygon_ptr;
        bool finished;
        bool is_finished() const { return finished; }
        void write_register(int idx, word val)
        {
            registers[idx] = val;
            if (idx == 15)
            {
                if (val > 255)
                    finished = true;
                out_data.push_back(get_low(val));                
            }
        }

        word read_register(int idx)
        {
            if (idx == 15)
            {
                if (last_polygon_read == polygon_ptr)
                {
                    return last_read_res;
                }
                word res;
                if (inp_ptr >= in_data.size())
                    res = CEOF;
                else
                    res = in_data[inp_ptr];
                last_polygon_read = polygon_ptr;
                last_read_res = res;
                inp_ptr++;
                return res;
            } else
                return registers[idx];
        }        

        int get_current_polygon() const { return polygon_ptr; }
        void set_current_polygon(int polygon) { polygon_ptr = polygon; }
    };

    vector< poly_t > polys;
    vector< line_t > lines;
    state_t state;
    int min_polygon;

    void reset()
    {
        state = state_t();
        state.set_current_polygon(min_polygon);
    }
public:


    void init(const string &program)
    {
        iss is(program);
        int n, m;
        is >> n >> m;
        
        
        // read polys
        polys.resize(n);
        map<coord_t, jump_t*> coord_to_jump;
        map<coord_t, int> coord_to_poly;
        for (int i = 0; i < n; ++i)
        {
            poly_t &poly = polys[i];
            int fr, fg, fb, fa, sr, sg, sb, sa;
            is >> sr >> sg >> sb >> sa;
            poly.stroke.r = sr; poly.stroke.g = sg; poly.stroke.b = sb; poly.stroke.a = sa;
            is >> fr >> fg >> fb >> fa;
            poly.fill.r = fr; poly.fill.g = fg; poly.fill.b = fb; poly.fill.a = fa;
            int c;
            is >> c;
            for (int j = 0; j < c; ++j)
            {
                coord_t cur;
                is >> cur.x >> cur.y;
                poly.jumps.push_back(mp(cur, nullptr));
            }
            sort(poly.jumps.begin(), poly.jumps.end());
            for (int j = 0; j < c; ++j)
            {
                coord_t cur = poly.jumps[j].first;
                assert(coord_to_jump.count(cur) == 0);
                coord_to_jump[cur] = &poly.jumps[j];
                coord_to_poly[cur] = i;
            }            
        }
        
        // read lines
        lines.resize(m);
        for (int i = 0; i < m; ++i)
        {
            line_t &line = lines[i];
            int lr, lg, lb, la;
            is >> lr >> lg >> lb >> la;
            line.color.r = lr; line.color.b = lb; line.color.g = lg; line.color.a = la;
            int p;
            is >> p;
            for (int j = 0; j < p; ++j)
            {
                coord_t cur;
                is >> cur.x >> cur.y;
                lines[i].points.push_back(cur);
            }

            coord_t start = lines[i].points[0];
            coord_t end = lines[i].points.back();
            assert(coord_to_jump.count(start) != 0);
            assert(coord_to_jump.count(end) != 0);

            coord_to_jump[start]->second = &lines[i];
            coord_to_jump[end]->second = &lines[i];
            line.from_poly = coord_to_poly[start];
            line.to_poly = coord_to_poly[end];
        }

        // initial poly
        min_polygon = coord_to_poly.begin()->second;
    }


    void make_step()
    {
        // Stage 1: register operation
        int polygon_id = state.get_current_polygon();
        const poly_t& poly = polys[polygon_id];
        int reg_a = get_high4(poly.fill.r);
        int reg_b = get_low4(poly.fill.r);
        int reg_c = get_high4(poly.fill.g);
        int reg_d = get_low4(poly.fill.g);
        int reg_e = get_high4(poly.fill.b);
        int reg_f = get_low4(poly.fill.b);
        uint X = poly.fill.a;
        uint Y = poly.stroke.r;
        uint Z = poly.stroke.g * (int)256 + poly.stroke.b;
        uint T = poly.stroke.a;


        uint B = state.read_register(reg_b);
        uint C = state.read_register(reg_c);
        uint D = state.read_register(reg_d);
        uint E = state.read_register(reg_e);
        uint F = state.read_register(reg_f);
        uint rF = state.read_register(get_low4(F));

        uint val = (Y + B + C * D + rF) * (255 - X);
        uint denum = (255 - T) + E;
        assert(denum > 0);
        val /= denum;
        val += Z;
        word res = val & ((1u << 16) - 1);
        state.write_register(reg_a, res);

        // Stage 2: route selection
        int last_poly = -1;
        for (int i = 0; i < (int)poly.jumps.size(); ++i)
        {
            if (poly.jumps[i].second != nullptr)
            {
                line_t *line = poly.jumps[i].second;
                int cur_poly;
                if (line->from_poly == polygon_id)
                    cur_poly = line->to_poly;
                else
                    cur_poly = line->from_poly;
                
                last_poly = cur_poly;

                int reg_q = get_low4(poly.jumps[i].first.x);
                uint Q = state.read_register(reg_q);
                uint L = line->color.r * 256 + line->color.g;
                uint H = line->color.b * 256 + line->color.a;
                if (Q >= L && Q < H)
                    break;
            }
        }

        // Stage 3: jump
        assert(last_poly != -1);
        state.set_current_polygon(last_poly);
    }
    void run(const vector<byte> &in, vector<byte> &out)
    {
        reset();
        state.set_input(in);

        while (!state.is_finished())
        {
            make_step();
        }

        out = state.get_output();
        out.pop_back();
    }
};


vector<byte> read_data(const string &fname)
{
    vector<byte> data;
    ifstream file(fname.c_str(), ios::in | ios::binary | ios::ate);
    if (!file.is_open())
        throw runtime_error("couldn't open file");

    data.resize(file.tellg());

    file.seekg(0, ios::beg);
    if(!file.read((char *)&data[0], data.size()))
        throw runtime_error("failed to read from file");
    file.close();
    return data;
}

void write_data(const string &fname, const vector<byte> &data)
{
    ofstream file;
    file.open(fname.c_str(), ios::out | ios::binary);
    file.write( (char *)&data[0], data.size());
    file.close();
}

string read_program(const string &fname)
{
    ifstream ifs(fname.c_str(), ifstream::in);
    string res;
    string tmp;    
    while (ifs >> tmp)
    {
        res += " ";
        res += tmp;
    }
    return res;
}


vector<byte> run_vm(const string &program, const vector<byte> &in_data)
{
    PolyVM vm;
    vm.init(program);
    vector<byte> out_data;
    vm.run(in_data, out_data);

    return out_data;
}

int main(int argc, char * argv[])
{
    //freopen("input.txt", "r", stdin); //freopen("output.txt", "w", stdout);
    //freopen(NAME ".in","r",stdin);freopen(NAME ".out","w",stdout);
    string fname_data;
    string fname_test;
    string fname_out;
    if (argc > 1)
    {
        fname_data = "serial.in.bin";
        string test = argv[1];
        fname_test = test + ".pp";
        fname_out = "C" + test + ".out";
    } else
    {
        fname_data = "serial.in.bin";
        fname_test = "0.pp";
        fname_out = "C0.out";
    }

    vector<byte> in_data = read_data(fname_data);
    string program = read_program(fname_test);
    vector<byte> out_data = run_vm(program, in_data);
    write_data(fname_out, out_data);    

    return 0;
}
/*************************
*************************/
#endif
