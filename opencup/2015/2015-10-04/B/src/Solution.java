import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int rounds;
    String []teams;
    String gameResults;
    HashMap<String, Integer> tm = new HashMap<>();
    int []levelCache;
    
    static class Node {
        String winner;
        Node l, r;

        Node(String w) {
            winner = w;
        }
    }

    void solve() throws IOException {
        // buildTest();
        Timer t = new Timer();
        t.start();
        rounds = ni();
        teams = new String[1 << rounds];
        for (int i = 0; i < teams.length; ++i) {
            teams[i] = ns();
            tm.put(teams[i], i);
        }
        gameResults = ns();
        lvlCache(rounds + 1);
        Node root = buildTree(0, 0);
        t.print();
        int q = ni();
        for (int i = 0; i < q; ++i) {
            String name1 = ns();
            String name2 = ns();
            out.println(solve(root, tm.get(name1), tm.get(name2), 0, teams.length - 1));
            //dbg("lol");
        }
        t.print();
        // print(root);
    }

    String solve(Node root, int idx1, int idx2, int l, int r) {
        int m = l + ((r - l) >> 1);
        // dbg(idx1, idx2, l, r, m);
        if (idx1 <= m && idx2 <= m)
            return solve(root.l, idx1, idx2, l, m);
        if (idx1 > m && idx2 > m) {
            return solve(root.r, idx1, idx2, m + 1, r);
        }
        if (root.winner.equals(teams[idx1]))
            return "Win";
        if (root.winner.equals(teams[idx2])) 
            return "Lose";
        return "Unknown";
    }

    void buildTest() throws IOException {
        PrintWriter out = new PrintWriter("test");
        out.println(18);
        HashSet<String> names = new HashSet<>();
        String []arr = new String[1 << 18];
        int idx = 0;
        for (int i = 0; i < (1 << 18); ++i) {
            String name = genName();
            if (names.contains(name)) {
                throw new IllegalStateException();
            }
            arr[idx++] = name;
            names.add(name);
            out.println(name);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (1 << 18) - 1; ++i) {
            sb.append(rnd.nextBoolean() ? 'W' : 'L');
        }
        out.println(sb.toString());
        out.println(100000);
        for(int i = 0; i < 100000; ++i) {
            int idx1 = 0;//rnd.nextInt(100000);
            int idx2 = 1;//idx1;
            while (idx2 == idx1) {
                idx2 = rnd.nextInt(100000);
            }
            out.println(arr[idx1] + " " + arr[idx2]);
        }
        out.flush();
        out.close();
    }

    Random rnd = new Random();

    String genName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            sb.append((char)(rnd.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    Node buildTree(int level, int idx) {
        // dbg(level, idx);
        if (level == rounds) {
            return new Node(teams[idx]);
        }
        Node left = buildTree(level + 1, idx << 1);
        Node right = buildTree(level + 1, (idx << 1) + 1);
        // dbg("level", level, idx);
        char c = gameResults.charAt(levelCache[level] + idx);
        Node node = new Node(c == 'W' ? left.winner : right.winner);
        node.l = left;
        node.r = right;
        return node;
    }

    void lvlCache(int level) {
        levelCache = new int[level];
        for (int i = 0; i < level; ++i)
            levelCache[i] = getIdx(i);
    } 

    int getIdx(int level) {
        int x = 0;
        int a = rounds - 1;
        while (a > level) {
            x += 1 << a;
            --a;
        }
        return x;
    }

    void print(Node n) {
        if (n == null)
            return ;
        dbg(n.winner);
        print(n.l);
        print(n.r);
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
        solve();
        in.close();
        out.close();
    }

    String ns() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(in.readLine());
        return st.nextToken();
    }

    int ni() throws IOException {
        return Integer.valueOf(ns());
    }

    long nl() throws IOException {
        return Long.valueOf(ns());
    }

    double nd() throws IOException {
        return Double.valueOf(ns());
    }

    public static void main(String[] args) throws IOException {
        new Solution().run();
    }

    class Timer {

        long time;

        void start() {
            time = System.currentTimeMillis();
        }
        long time() {
            return System.currentTimeMillis() - time;
        }
        void print() {
            print("Time spent = ");
        } 

        void print(String message) {
            dbg(message, time());
        }

    }

    static boolean DEBUG = false;

    void dbg(Object ... objs) {
        if (!DEBUG) {
            return ;
        }
        for (Object o : objs) {
            String printLine;
            if (o.getClass().isArray()) {
                printLine = arrayToString(o);
            } else {
                printLine = o.toString();
            }
            System.err.print(printLine + " ");
        }
        System.err.println();
    }

    String arrayToString(Object o) {
        if (o instanceof long[]) 
            return Arrays.toString((long[]) o);
        if (o instanceof int[])
            return Arrays.toString((int[]) o);
        if (o instanceof short[])
            return Arrays.toString((short[]) o);
        if (o instanceof char[])
            return Arrays.toString((char[]) o);
        if (o instanceof byte[])
            return Arrays.toString((byte[]) o);
        if (o instanceof double[])
            return Arrays.toString((double[]) o);
        if (o instanceof float[])
            return Arrays.toString((float[]) o);
        if (o instanceof boolean[])
            return Arrays.toString((boolean[]) o);
        if (o instanceof Object[])
            return Arrays.deepToString((Object[]) o);
        throw new IllegalStateException();
    }


}

