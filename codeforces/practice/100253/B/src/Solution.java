import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int[] camera;
    int[] scenes;
    int[] left, right;
    int r;
    Scene[] arr;

    static class Scene implements Comparable<Scene> {

        int id;
        int pos;

        Scene(int id, int pos) {
            this.id = id;
            this.pos = pos;
        }

        @Override
        public int compareTo(Scene o) {
            if (pos == o.pos)
                return id - o.id;
            return pos - o.pos;
        }
    }

    void solve() throws IOException {
        int cn = ni();
        int sn = ni();
        r = ni() * 10;
        camera = new int[cn];
        scenes = new int[sn];
        arr = new Scene[sn];
        for (int i = 0; i < camera.length; ++i)
            camera[i] = readDouble();
        for (int i = 0; i < scenes.length; ++i) {
            scenes[i] = readDouble();
            arr[i] = new Scene(i, scenes[i]);
        }

        Arrays.sort(arr);

        left = new int[scenes.length];
        right = new int[scenes.length];

        processLeft();
        processRight();

        int pos = 0;
        long ret = 0;
        for (int i = 0; i < scenes.length; ++i) {
            int l = left[i];
            int r = right[i];
            if (pos < l) {
                ret += (long) (camera[l] - camera[pos]);
                pos = l;
            } else if (pos > r) {
                ret += (long) (camera[pos] - camera[r]);
                pos = r;
            }
        }

        long q = ret / 10l;
        long w = ret % 10l;
        out.println(q + "." + w);
    }

    void processLeft() {
        int camP = 0;
        for (int i = 0; i < arr.length; ++i) {
            while (!can(camP, i)) {
                ++camP;
            }
            left[arr[i].id] = camP;
        }
    }

    void processRight() {
        int camP = camera.length - 1;
        for (int i = arr.length - 1; i >= 0; --i) {
            while (!can(camP, i)) {
                --camP;
            }
            right[arr[i].id] = camP;
        }
    }

    boolean can(int cam, int sce) {
        return square(camera[cam] - arr[sce].pos) + 100l <= (long) (r * r);
    }

    long square(long a) {
        return a * a;
    }

    int readDouble() throws IOException {
        String s = ns();
        int idx = s.indexOf('.');
        s = s.substring(0, idx) + s.substring(idx + 1, s.length());
        return Integer.valueOf(s);
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

    static boolean DEBUG = false;

    void dbg(Object... objs) {
        if (!DEBUG) {
            return;
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

