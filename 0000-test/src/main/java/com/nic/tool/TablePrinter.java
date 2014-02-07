package com.nic.tool;

import java.util.Map;

/**
 * 工具类，打印数据表格
 *
 * @author willli
 */
public class TablePrinter {

    public static void printMap(Map<?, ?> map) {
        int maxKeyLen = 1;
        int maxValueLen = 1;
        for (Map.Entry<?, ?> en : map.entrySet()) {
            maxKeyLen = Math.max(maxKeyLen, en.getKey().toString().length());
            maxValueLen = Math.max(maxValueLen, en.getValue().toString().length());
        }
        int[] lens = new int[] {maxKeyLen, maxValueLen};

        printLine(lens);

        for (Map.Entry<?, ?> en : map.entrySet()) {
            String key = en.getKey().toString();
            String value = en.getValue().toString();

            System.out.print("|");
            fillBlank(maxKeyLen - key.length());
            System.out.print(key);

            System.out.print("|");
            fillBlank(maxValueLen - value.length());
            System.out.print(value);

            System.out.print("|");
            System.out.println();
            printLine(lens);
        }

    }

    private static void printLine(int[] lens) {
        for (int len : lens) {
            System.out.print("+");
            for (int i = 0; i < len; i++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    private static void fillBlank(int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(" ");
        }
    }
}
