package main;


public class BearDartsDiv2 {
    public long count(int[] w) {
        int []count = new int[1000001];
        for (int i = 1; i < w.length; ++i)
            for (int j = i + 1; j < w.length; ++j) {
                if (w[j] % w[i] == 0) {
                   int a = w[j] / w[i];
                    count[a]++;
                }
            }
        long ret = 0;
        for (int i = 1; i < w.length; ++i) {
            for (int j = i + 1; j < w.length; ++j) {
                if (w[j] % w[i] == 0) {
                    int a = w[j] / w[i];
                    count[a]--;
                }
            }
            for (int j = 0; j < i; ++j) {
                long x = (long) w[i] * w[j];
                if (x >= 0 && x <= 1000000) {
                    ret += count[(int) x];
                }
            }
        }
        return ret;
    }
}
