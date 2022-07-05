package com.github.eiriksgata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class RunTest {

    @Test
    void prisonerTest() {
        int[] room = randomFillInteger(100);
        int[] prisoners = new int[100];

        for (int i = 0; i < 100; i++) {
            prisoners[i] = i + 1;
        }

        System.out.println("房间编码数组:" + Arrays.toString(room));

        for (int i = 0; i < 100; i++) {
            int count = 0;
            for (int prisoner : prisoners) {
                int[] randomList = randomFillInteger(100);
                //System.out.println(Arrays.toString(randomList));
                for (int j = 0; j < 50; j++) {
                    if (room[randomList[j] - 1] == prisoner) {
                        count++;
                        //System.out.println(prisoner + " , " + randomList[j] + ",房间:" + room[randomList[j] - 1]);
                        break;
                    }
                }
            }
            System.out.println("count: " + count);
            if (count >= 100) {
                System.out.println("全部抽中！！！ 所有人都不用死拉！！");
                break;
            }
        }
    }

    @Test
    void prisonerTest2() {
        int successCount = 0;
        int[] prisoners = new int[100];
        for (int i = 0; i < 100; i++) {
            prisoners[i] = i + 1;
        }

        for (int i = 0; i < 10000; i++) {
            int count = 0;
            int[] room = randomFillInteger(100);
            for (int prisoner : prisoners) {
                if (ergodic(prisoner, room, prisoner, 0) <= 50) {
                    count++;
                }
            }
            if (count >= 100) {
                successCount++;
            }
        }
        System.out.println("成功次数:" + successCount);
    }


    public int ergodic(int index, int[] room, int code, int count) {
        if (room[index - 1] == code) {
            return count;
        } else {
            return ergodic(room[index - 1], room, code, ++count);
        }
    }

    public int[] randomFillInteger(int max) {
        int[] result = new int[max];
        int[] ints = new int[max];
        int randomMax = max;
        for (int i = 0; i < max; i++) {
            ints[i] = i + 1;
        }
        for (int i = 0; i < max; i++) {
            int random = new Random().nextInt(randomMax);
            int temp = ints[random];
            result[i] = temp;
            ints[random] = ints[ints.length - i - 1];
            ints[ints.length - i - 1] = temp;
            randomMax--;
        }
        return result;
    }

    public int[] sort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i] < data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }

            }
        }
        return data;
    }
}
