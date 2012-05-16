#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cstdio>

using namespace std;

int gen() {
    int a = 0;
    while(a == 0 || a == 10 || a == 13) {
        a = rand() % 256;
    } 
    return a;
}
int main() {
    srand(time(NULL));
    freopen("test.txt", "w", stdout);
    cout << 10000 << endl;
    for(int i= 0; i < 10000; ++i) {
        for(int j = 0; j < 11; ++j){
            cout << (char)gen();
        }
        cout << endl;
    }
    cout << 9216 << endl;
    for(int i = 0; i < 9216; ++i) {
        for(int j = 0; j < 100; ++j) {
            cout << (char)gen();
        }
        cout << endl;
    }
    return  0;
}
