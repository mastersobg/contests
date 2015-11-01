package com.ivangorbachev;

import java.util.HashSet;

public class SubstitutionCipher {
    public String decode(String a, String b, String y) {
        Character[] t = rTable(getTable(a, b));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < y.length(); i++) {
            char c = y.charAt(i);
            if (t[c - 'A'] == null) {
                return "";
            }
            sb.append(t[c - 'A']);
        }
        return sb.toString();
    }

    Character []rTable(Character []t) {
        Character[] ret = new Character[26];
        for (int i = 0; i < 26; i++) {
            if (t[i] != null) {
                ret[t[i] - 'A'] = (char)(i + 'A');
            }
        }
        return ret;
    }

    Character []getTable(String a, String b) {
        Character []ret = new Character[26];
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 0; i < 26; ++i)
            set.add((char) (i + 'A'));
        for (int i = 0; i < a.length(); i++) {
            ret[a.charAt(i) - 'A'] = b.charAt(i);
            set.remove(b.charAt(i));
        }

        int cnt = 0;
        int pos = 0;
        int idx = 0;
        for (Character c : ret) {
            if (c == null) {
                ++cnt;
                pos = idx;
            }
            ++idx;
        }
        if (cnt == 1) {
            ret[pos] = set.iterator().next();
        }

        return ret;
    }
}
