package main;

import main.Reader;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public static Random rnd = new Random();

    public void solve(int testNumber, Reader in, PrintWriter out) {
        long time = System.currentTimeMillis();
//        out(generate(125000), "test");
        int n = in.ni();
//        System.err.println(Arrays.toString(generate(500000)));
        n = 1001;
        int[] list = new int[n];
        int []next = new int[n];
//        for (int i = 0; i < n; i++) {
//            list[i] = in.ni();
//        }
        list = generate(n);
        boolean found = false;
        int step = 0;
        for (int i = 0; ; ++i) {
            boolean eq = apply(list, next);
            if (eq) {
                step = i;
                found = true;
                break;
            }
            int []t = list;
            list = next;
            next = t;
//            if(System.currentTimeMillis() - time > 1980) {
//                break;
//            }
        }
        if (found) {
            StringBuilder sb = new StringBuilder();
            sb.append(step).append("\n");
            for (int i = 0; i < n; i++) {
                sb.append(list[i]).append(" ");
            }
            out.println(sb.toString());
        } else {
            out.println("-1");
        }
//        while (true) {
//            List<Integer> list = generate(500000);
////            System.err.println("init = " + list.toString());
//            boolean found = false;
//            for (int i = 0; i < 100; i++) {
//                List<Integer> next = apply(list);
//                if (list.equals(next)) {
//                    found = true;
//                    System.err.println(i);
////                    System.err.println("success = " + i + " " + list.toString());
//                    break;
//                }
//                list = next;
//            }
//            if (!found) {
//                System.err.println("fail " + list.toString());
//                return;
//            }
//        }
    }

    boolean apply(int[] list, int []next) {
        next[0] = list[0];
        boolean eq = true;
        for (int i = 1; i < list.length - 1; i++) {
            next[i] = getMedian(list[i-1], list[i], list[i+1]);
            eq &= next[i] == list[i];
        }
        next[next.length - 1] = list[next.length - 1];
        return eq;
    }

    int getMedian(int a, int b, int c) {
        int cnt = 0;
        if (a == 0)++cnt;
        if (b == 0)++cnt;
        if (c == 0)++cnt;
        return cnt > 1 ? 0 : 1;
    }

    int[] generate(int n) {
        int [] list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = (i&1) == 1 ? 0 : 1;
        }
        return list;
    }

    void out(int[]a, String f) {
        try {
            PrintWriter out = new PrintWriter(f);
            out.println(a.length);
            for (int x : a)
                out.print(x + " ");
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
