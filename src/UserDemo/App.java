package UserDemo;

import StudentManagement.StudentManage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
//        将用户信息封装，并将用户类添加到集合
        ArrayList<User> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作1登录 2注册 3忘记密码 4退出系统");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("登录");
                    login(list);
                    break;
                case 2:
                    System.out.println("注册 ");
                    register(list);
                    break;
                case 3:
                    System.out.println("忘记密码");
                    forgetPassword(list);
                    break;
                case 4:
                    System.out.println("退出系统");
                    System.out.println("感谢您使用学生管理系统，再见！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("没有该业务，请重新选择");
                    break;
            }
        }
    }

    //登录的业务
    private static void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
//        一、键盘录入用户名
            System.out.println("请输入用户名");
            String username = sc.next();
            boolean flag1 = contains(list, username);
            if (!flag1) {
                System.out.println("用户名未注册，请先注册");
                //结束方法
                return;
            }
//        二、键盘录入密码
            System.out.println("请输入密码：");
            String password = sc.next();
//         三、自动生成一个验证码
            while (true) {
                String checkCode = getCode();
                System.out.println(" 系统生成的验证码：  " + checkCode);
                System.out.println("请输入正确的验证码：");
                String inputCode = sc.next();
                if (inputCode.equalsIgnoreCase(checkCode)) {
                    System.out.println("验证码输入正确");
                    break;
                } else {
                    System.out.println("验证码输入有误，请重新输入");
                    continue;
                }
            }
//        将用户名和密码进行封装，不去理会别的细节
            User user = new User(username, password, null, null);
            boolean result = checkUserInfo(list, user);
            if (result) {
                System.out.println("登录成功，可以开始使用学生管理系统了");
                //创建对象调用方法，启动学生管理系统
                StudentManage ss = new StudentManage();
                ss.StudentManage();
                break;
            } else {
                System.out.println("登录失败，用户名或密码错误");
                if (i == 2) {
                    System.out.println("当前账号" + username + "被锁定，请联系黑马程序员客服：XXX-XXXXX");
                    //当前账号锁定之后，直接结束方法即可
                    return;
                } else {
                    System.out.println("用户名或密码错误，还剩下" + (2 - i) + "次机会");
                }
            }
        }

    }

    //生成随机验证码的方法
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

    private static boolean checkUserInfo(ArrayList<User> list, User user) {
        for (int i = 0; i < list.size(); i++) {
            User user1 = list.get(i);
            if (user1.getUsername().equals(user.getUsername()) && (user1.getPassword().equals(user.getPassword()))) {
                return true;
            }
        }
        return false;
    }

    //注册的业务
    private static void register(ArrayList<User> list) {
        String username;
        String psw1;
        String personID;
        String phoneNumber;
//    一、键盘录入用户名
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入用户名：");
            username = sc.next();
//        先验证用户名的格式是否正确，在验证用户名是否唯一
            boolean flag1 = checkUserName(username);
            if (!flag1) {
                System.out.println("用户名格式有误，请重新输入");
                continue;
            }
//            验证用户名是否唯一
            boolean flag2 = contains(list, username);
            if (flag2) {
                System.out.println("用户名" + username + "已存在,请重新输入");
                continue;
            } else {
                System.out.println("用户名" + username + "可用,请继续录入数据");
                break;
            }
        }
//    二、键盘录入密码
        while (true) {
            System.out.println("请输入密码");
            psw1 = sc.next();
            System.out.println("请再次输入密码");
            String psw2 = sc.next();
            if (psw1.equals(psw2)) {
                System.out.println("密码录入成功");
                break;
            } else {
                System.out.println("两次密码输入不一致，请重新输入");
                continue;
            }
        }

//    三、键盘录入身份证号码
        System.out.println("请输入身份证号码：");
        while (true) {
            personID = sc.next();
            boolean flag3 = checkPersonID(personID);
            if (!flag3) {
                System.out.println("身份证号码格式有误，请重新输入");
                continue;
            } else {
                System.out.println("身份证号码正确，继续录入其他数据");
                break;
            }
        }
//    四、键盘录入手机号
        while (true) {
            System.out.println("请输入手机号码：");
            phoneNumber = sc.next();
            boolean flag4 = checkPhoneNumber(phoneNumber);
            if (!flag4) {
                System.out.println("手机号码不符合要求，请重新输入");
                continue;
            } else {
                System.out.println("手机号码录入完成");
                break;
            }
        }
//        五.将录入的信息进行封装
        User user = new User(username, psw1, personID, phoneNumber);
        list.add(user);
        System.out.println("注册成功！");
        printList(list);
    }

    private static void printList(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            System.out.println("=============================");
            System.out.println("用户名：" + user.getUsername() + "\n" + "用户密码：" + user.getPassword() + "\n"
                    + "身份证号：" + user.getPersonId() + "\n" + "手机号：" + user.getPhoneNumber());
            System.out.println("=============================");
        }
    }


    private static boolean checkPhoneNumber(String phoneNumber) {
        int len = phoneNumber.length();
        if (len != 11) {
            return false;
        }
        char c = phoneNumber.charAt(0);
        if (c == '0') {
            return false;
        }
        for (int i = 0; i < len; i++) {
            char ch = phoneNumber.charAt(i);
            if (!(ch >= '0' && ch <= '9')) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkPersonID(String personID) {
//        1.验证长度
        int len = personID.length();
        if (len != 18) {
            return false;
        }

//        2.验证格式
        if (personID.startsWith("0")) {
            return false;
        }
        for (int i = 0; i < len - 1; i++) {
            char c = personID.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
//        最后一位
        char endChar = personID.charAt(len - 1);
        if ((endChar >= '0' && endChar <= '9') || endChar == 'x' || endChar == 'X') {
            return true;
        } else {
            return false;
        }
    }

    private static boolean contains(ArrayList<User> list, String username) {
        return getIndex(list, username) >= 0;
    }

    private static int getIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean checkUserName(String username) {
//        长度验证
        int len = username.length();
        if (len < 3 || len > 15) {
            return false;
        }
//        格式验证
        for (int i = 0; i < len; i++) {
            char c = username.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                return false;
            }
        }
        int count = 0;
//        如果用户名中有非数字,count+1,
        for (int i = 0; i < len; i++) {
            char c = username.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                count++;
                break;
            }
        }
        return count > 0;
    }

    //忘记密码的业务
    private static void forgetPassword(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.next();
        boolean flag = contains(list, username);
        if (!flag) {
            System.out.println("用户未注册");
            return;
        }
//        到这里说明用户名存在，接着看身份证号码和手机号码
        int index = getIndex(list, username);
        System.out.println("请输入身份证号码");
        String personId = sc.next();
        System.out.println("请输入手机号码");
        String phoneNumber = sc.next();
//        通过用户名获得索引
        User user = list.get(index);
        if (!(user.getPersonId().equals(personId) && user.getPhoneNumber().equals(phoneNumber))) {
            System.out.println("账号信息不匹配，修改失败");
            return;
        }
//        System.out.println("账号信息匹配");
//            可以修改的情况
        while (true) {
            System.out.println("请输入要更改的密码：");
            String newPwd = sc.next();
            System.out.println("请再次输入要更改的密码：");
            String newPwdAgain = sc.next();
            if (newPwdAgain.equals(newPwd)) {
                user.setPassword(newPwd);
                System.out.println("密码修改成功！");
                break;
            } else {
                System.out.println("两次密码输入不一致，请重新输入");
                continue;
            }
        }
    }
}
