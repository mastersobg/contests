package com.ivangorbachev;

public class Drbalance {
    public int lesscng(String s, int k) {
        if (check(s, k))
            return 0;
        int changes = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '-') {
                StringBuilder sb = new StringBuilder(s);
                sb.setCharAt(i, '+');
                s = sb.toString();
                ++changes;
                if (check(s, k)) {
                    return changes;
                }
            }
        }
        return -1;
    }

    boolean check(String s, int k) {
        int []cnt = new int[s.length()];
        int count = 0;
        cnt[0] = s.charAt(0) == '-' ? -1 : 1;
        if (cnt[0] < 0)++count;
        for (int i = 1; i < s.length(); ++i) {
            cnt[i] = cnt[i - 1] + (s.charAt(i) == '-' ? -1 : 1);
            if (cnt[i] < 0)++count;
        }
        return count <= k;
    }
}
