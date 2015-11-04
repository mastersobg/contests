package com.ivangorbachev.util;

import java.util.Arrays;

/**
 * @author Ivan Gorbachev
 */
public class Dbg {

    private static boolean DEBUG_ENABLED;

    static {
        try {
            DEBUG_ENABLED = System.getProperty("LOCAL_DEBUG_ENABLED") != null;
        } catch (Exception e) {
            DEBUG_ENABLED = false;
        }
    }

    public static void dbg(Object ... objs) {
        if (!DEBUG_ENABLED) {
            return ;
        }
        System.out.println("=====Debug output=====");
        for (Object o : objs) {
            String printLine;
            if (o.getClass().isArray()) {
                printLine = arrayToString(o);
            } else {
                printLine = o.toString();
            }
            System.out.print(printLine + " ");
        }
        System.out.println();
        System.out.println("=====End of debug output=====");
        System.out.flush();
    }

    public static String arrayToString(Object o) {
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
