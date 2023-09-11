package UserDemo;

import java.util.ArrayList;
import java.util.Random;

public class TestCode {
    private static String getCode() {
        ArrayList<Character> list = new ArrayList<>();
//1.创建一个集合添加所有的大写和小写字母
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
//2.要随机抽取4个字符
        for (int i = 0; i < 4; i++) {
//            生成随机索引
            int index = r.nextInt(list.size());
            char c = list.get(index);
            sb.append(c);
        }
//        生成随机数
        int number = r.nextInt(10);
//        将随机数添加到末尾
        sb.append(number);
//        将该sb转换成字符数组
        char[] arr = sb.toString().toCharArray();
        int index = r.nextInt(arr.length);
//        将最后一位数字与字符数组里的元素交换
        char tmp = arr[index];
        arr[index] = arr[arr.length - 1];
        arr[arr.length - 1] = tmp;
//        通过字符数组创建字符(一种创建字符串对象的方法)
        return new String(arr);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            String code = getCode();
            System.out.println(code);
        }
    }
}
