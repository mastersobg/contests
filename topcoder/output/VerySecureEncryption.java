import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class VerySecureEncryption {
    public String encrypt(String message, int[] key, int K) {
        for (int i = 0; i < K; i++) {
            message = solve(message, key);
        }
        return message;
    }

    private String solve(String s, int[] key) {
        char[] ret = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ret[key[i]] = s.charAt(i);
        }
        return new String(ret);
    }

}
