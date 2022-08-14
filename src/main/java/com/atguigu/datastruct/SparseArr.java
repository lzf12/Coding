package com.atguigu.datastruct;

import java.io.*;

/**
 * description 利用稀疏数组实现棋盘存档功能
 *
 * @Author 汪玉龙
 * @Date 2020/12/3 22:24
 */
public class SparseArr {
    /**
     * 初始化二维数组
     * <p>
     * 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 1 0 0 0 0 0 0 0 0
     * 0 0 0 0 2 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0
     * </p>
     */
    public static void main(String[] args) {
        // 初始化原始数组
        int[][] arr = new int[11][11];
        arr[1][2] = 1;
        arr[2][4] = 2;
        // 打印原数组
        System.out.println("原数组为:");
        for (int[] row : arr) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }

        // 得到非0的数据数
        int sum = 0;
        for (int[] row : arr) {
            for (int item : row) {
                if (item != 0) {
                    sum++;
                }
            }
        }
        // 创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 第一行是原数组的大小和数组中有效元素的个数
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历数组，把不为0的数据放入稀疏数组
        // 记录有效数值的位置, 默认1开始,因为0位置已经存放了原数组大小和有效数据量
        int count = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count++][2] = arr[i][j];
                }
            }
        }

        // 遍历稀疏数组
        System.out.println("稀疏数组为:");
        for (int[] ints : sparseArr) {
            System.out.printf("%d\t%d\t%d\t", ints[0], ints[1], ints[2]);
            System.out.println();
        }
        // 利用IO流写到磁盘上面去
        try {
            System.out.println("存档功能开始: ");
            File file = new File("D:\\sparse.data");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);
            for (int[] ints : sparseArr) {
                // 存档
                bw.write(ints[0] + "," + ints[1] + "," + ints[2]);
                bw.newLine();
                bw.flush();
            }
            outputStreamWriter.close();
            fos.close();
            System.out.println("存档功能结束");
            // 文件读取
            FileInputStream fis = new FileInputStream("D:\\sparse.data");
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = null;
            int[][] recoverArr = new int[sum + 1][3];
            int left = 0;
            int right = 0;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                // 恢复成稀疏数组
                for (String data : split) {
                    recoverArr[left][right++] = Integer.parseInt(data);
                    System.out.println(left + "," + right);
                }
                left++;
                right = 0;
            }
            System.out.println("恢复稀疏数组: ");
            for (int[] ints : recoverArr) {
                System.out.printf("%d\t%d\t%d\t", ints[0], ints[1], ints[2]);
                System.out.println();
            }
            // 原始数组的恢复
            int[][] oldArr = new int[recoverArr[0][0]][recoverArr[0][1]];
            for (int index = 1; index < recoverArr.length; index++) {
                oldArr[recoverArr[index][0]][recoverArr[index][1]] = recoverArr[index][2];
            }
            // 得到的原始二维数组
            for (int[] row : oldArr) {
                for (int item : row) {
                    System.out.printf("%d\t", item);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
