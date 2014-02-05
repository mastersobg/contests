import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    char [] T = "ACGT".toCharArray();

    class Node {
        List<Integer> leaves = new ArrayList<Integer>();
        Node []next = new Node[4];    

        int getIdx(char c) {
            for (int i = 0; i < T.length; ++i)
                if (T[i] == c)
                    return i;
            return -1;
        }
        
        boolean hasNext(char c) {
            return next[getIdx(c)] != null;
        }

        void setNext(char c, Node node) {
            next[getIdx(c)] = node;
        }

        Node getNext(char c) {
            return next[getIdx(c)];
        }

        @Override
        public String toString() {
            return "[" +
                "next=" + Arrays.toString(next) + " " +
                "]";
        }
    }

    class Result {
        int free;
        int sum = 0;

        Result(int sum) {
            this.sum = sum;
            free = -1;
        }

        Result(int free, int sum) {
            this.sum = sum;
            this.free = free;
        }  
    }

    void addWord(Node root, String s, int idx) {
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (!root.hasNext(c)) {
                Node next = new Node();
                root.setNext(c, next);
            }
            root = root.getNext(c);
        }
        root.leaves.add(idx);
    }

    List<int[]> pairs = new ArrayList<int[]> ();

    Result dfs(Node root, int depth) {
        int sum = 0;
        for (int i = 0; i < 4; ++i) {
            if (root.next[i] != null) {
                Result r = dfs(root.next[i], depth + 1);
                if (r.free != -1) {
                    root.leaves.add(r.free);
                }
                sum += r.sum;
            }
        }
        int i;
        for (i = 0; i + 1 < root.leaves.size(); i += 2) {
            pairs.add(new int[] {root.leaves.get(i), root.leaves.get(i + 1)});
            sum += depth;
        }
        return i < root.leaves.size() ? new Result(root.leaves.get(i), sum) :
            new Result(sum);
    }

    void solve() throws IOException {
        int n = ni();
        Node root = new Node();
        for (int i = 0; i < n; ++i) {
            String s = ns();
            addWord(root, s, i + 1);
        }
        Result res = dfs(root, 0);
        out.println(res.sum);
        for (int []p : pairs) {
            out.println(p[0] + " " + p[1]);
        }
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
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

    static boolean DEBUG = true;

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

