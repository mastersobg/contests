#include <iostream>
#include <vector>
#include <set>

using namespace std;

void generate_max_test() {
    int n = 4000;
    for(int it = 0; it < 3; ++it){
        cout << n << endl;
        for(int i = 0; i < n; ++i)
            cout << i << " ";
        cout << endl;
    }
}
int check_contains(vector<set<int> > & data, int element) {
    int ret = 1;
    for(int i = 1; i < data.size(); ++i){
        ret &= (data[i].find(element) != data[i].end());
    }
    return ret;
}
int main(){
    //generate_max_test();
    vector<set<int> > data;
    int n;
    while(cin >> n) {
        set<int> s;
        for(int i = 0; i < n; ++i) {
            int a;
            cin >> a;
            s.insert(a);
        }
        data.push_back(s);
    }
    set<int> first = data[0];
    int ret = 0;
    for(set<int>::iterator it = first.begin(); it != first.end(); ++it) {
        if(check_contains(data, *it))
            ++ret;
    }
    cout << ret << endl;
    return 0;
}

