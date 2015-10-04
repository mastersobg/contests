import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    void solve() throws IOException {
        int test = ni();
        for (int t = 0; t < test; ++t) {
            go(t);
        }
    }

    int n, k, t;
    int []fish;
    int [][][]ponds;
    int [][][]plates;
    List<ArrayWraper> []trans;

    static class ArrayWraper {
        int [][]array;
        int idx;
        byte mirror;
        byte angle;

        public ArrayWraper(int[][] array, byte mirror, byte angle, int idx) {
            this.array = array;
            this.mirror = mirror;
            this.angle = angle;
            this.idx = idx;
        }

        @Override
        public boolean equals(Object other) {
            ArrayWraper aw = (ArrayWraper) other;
            return Arrays.deepEquals(array, aw.array);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(array);
        }
    }

    private void go(int test) throws IOException {
        if (test == 6 || test == 7)
            return ;
        n = ni();
        k = ni();
        t = ni();
        fish = new int[t];
        for (int i = 0; i < t; ++i)
            fish[i] = ni();
        ponds = read(n, k);
        plates = read(n, k);
        genTrans();
        ArrayWraper []ret = new ArrayWraper[k];
        boolean found = false;
        try {
            rec(0, 0, fish, ret);

        } catch (RuntimeException e) {
            found = true;
            dbg("test = ", test);
            for (ArrayWraper aw : ret) {
                out.println((aw.idx + 1) + " " + aw.mirror + " " + aw.angle);
            }
            out.flush();
        }
        if (!found) {
            out.println("not found");
        }
    }

    Random rnd = new Random();

    void rec(int plate, int pondsUsed, int []ans, ArrayWraper[] result) {
        if (isZero(ans)) {
            throw new RuntimeException();
        }
        if (plate == k)
            return ;
        for (int pond = 0; pond < k; ++pond) {
            if (notInMask(pondsUsed, pond)) {
                final int lambdaIdx = pond;
//                Collections.sort(trans[plate], (o1, o2) -> {
//                    int []newFish1 = applyPlate(o1.array, ponds[lambdaIdx]);
//                    int []newFish2 = applyPlate(o2.array, ponds[lambdaIdx]);
//                    return Arrays.stream(newFish2).sum() - Arrays.stream(newFish1).sum();
//                });
                for (ArrayWraper transformation : trans[plate]) {
                    int []newFish = applyPlate(transformation.array, ponds[pond]);
//                    int []nextAns = new int[t];
                    boolean correct = true;
                    for (int i = 0; i < t; ++i) {
                        ans[i] -= newFish[i];
                        if (ans[i] < 0)
                            correct = false;
                    }
                    if (correct) {
                        result[pond] = transformation;
                        rec(plate + 1, pondsUsed | (1 << pond), ans, result);
                    }
                    for (int i = 0; i < t; ++i)
                        ans[i] += newFish[i];
                }
            }
        }

    }

    boolean notInMask(int mask, int bit) {
        return (mask & (1 << bit)) == 0;
    }

    int []applyPlate(int [][]plate, int [][]pond) {
        int []ret = new int[t];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (plate[i][j] == 1 && pond[i][j] != 0)
                    ret[pond[i][j] - 1]++;
        return ret;
    }

    boolean isZero(int []v) {
        for (int a : v)
            if (a != 0)
                return false;
        return true;
    }

    void genTrans() {
        trans = new ArrayList[k];
        for (int it = 0; it < k; ++it) {
            int [][]arr;
            trans[it] = new ArrayList<>();
            for (int mirror = 0; mirror < 2; ++mirror) {
                if (mirror == 1) {
                    arr = mirror(plates[it]);
                } else {
                    arr = plates[it];
                }
                for (int rot = 0; rot < 4; ++rot) {
                    int [][]rotated = rotate(arr, rot);
                    trans[it].add(new ArrayWraper(rotated, (byte) mirror, (byte) rot, it));
                }
            }
        }
    }

    int [][]mirror(int [][]v) {
        int [][]ret = new int[v.length][v.length];
        for (int i = 0; i < v.length; ++i)
            for (int j = 0; j < v.length; ++j) {
                ret[i][j] = v[i][v.length - j - 1];
            }
        return ret;
    }

    int [][]rotate(int [][]v, int angle) {
        if (angle == 0) {
            return copyOf(v);
        }
        for (int it = 0; it < angle; ++it) {
            int [][]ret = new int[v.length][v.length];
            for (int i = 0; i < v.length; ++i)
                for (int j = 0; j < v.length; ++j) {
                    ret[i][j] = v[v.length - j - 1][i];
                }
            v = ret;
        }
        return v;
    }

    int [][] copyOf(int [][]v) {
        int [][]ret = new int[n][];
        for (int i = 0; i < n; ++i) {
            ret[i] = Arrays.copyOf(v[i], n);
        }
        return ret;
    }

    int [][][]read(int n, int k) throws IOException {
        int [][][]v = new int[k][n][n];
        for (int it = 0; it < k; ++it) {
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    v[it][i][j] = ni();
        }
        return v;
    }

    public Solution() throws IOException {
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
        if (args.length > 0 && args[0].equals("LOCAL_DEBUG")) {
            DEBUG = true;
        }
        new Solution();
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
