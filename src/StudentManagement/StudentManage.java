package StudentManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManage {
    public static void StudentManage() {

        ArrayList<Student> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        loop:
        while (true) {
            System.out.println("=============欢迎来到学生管理系统=============\n");
            System.out.println("1:添加学生");
            System.out.println("2:删除学生");
            System.out.println("3:修改学生");
            System.out.println("4:查询学生");
            System.out.println("5:退出");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("1:添加学生");
                    addStudent(list);
                    break;
                case 2:
                    System.out.println("2:删除学生");
                    deleteStudent(list);
                    break;
                case 3:
                    System.out.println("3:修改学生");
                    updateStudent(list);
                    break;
                case 4:
                    System.out.println("4:查询学生");
                    queryStudent(list);
                    break;
                case 5:
                    System.out.println("5:退出");
                    break loop;
                default:
                    System.out.println("没有该服务，请重新输入");
                    break;
            }
        }
    }

    //修改学生信息
    private static void updateStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改学生的id");
        String newId = sc.next();
        int index = getIndex(list, newId);
        if (index == -1) {
            System.out.println("要修改的id" + newId + "不存在，请重新输入");
            return;
        }
//        代码运行到这里表明我们要查询的id是存在的
        Student stu = list.get(index);
        System.out.println("请输入要修改学生的姓名:");
        String newName = sc.next();
        stu.setName(newName);
        System.out.println("请输入要修改学生的年龄:");
        int newAge = sc.nextInt();
        stu.setAge(newAge);
        System.out.println("请输入要修改学生的所在地:");
        String newAddress = sc.next();
        stu.setAddress(newAddress);
        Student s = new Student(newId, newName, newAge, newAddress);
        System.out.println("学生信息修改成功");
    }

    //删除学生信息
    private static void deleteStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要删除学生的id");
            String id = sc.next();
            boolean flag = contains(list, id);
            if (flag) {
                //            获得要删除的对象在集合中的索引
                int index = getIndex(list, id);
                list.remove(index);
                System.out.println("id为" + id + "的学生信息删除成功");
                break;
            } else {
                System.out.println("该id不存在，请重新输入");
            }
        }


    }

    private static int getIndex(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            if (s.getSid().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    //查询学生信息
    private static void queryStudent(ArrayList<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            if (stu != null) {
                System.out.println("id：" + stu.getSid() + " 姓名： " + stu.getName() + " 年龄： " + stu.getAge() + " 学生所在地： " + stu.getAddress());
            }
        }
    }

    //添加学生信息
    private static void addStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要添加学生的id:");
            String id = sc.next();
            System.out.println("请输入要添加学生的姓名:");
            String name = sc.next();
            System.out.println("请输入要添加学生的年龄:");
            int age = sc.nextInt();
            System.out.println("请输入要添加学生的所在地:");
            String address = sc.next();
            Student stu = new Student(id, name, age, address);
//        将学生对象添加到集合中
            boolean flag = contains(list, id);
            if (!flag) {

                list.add(stu);
                System.out.println("学生信息添加成功！");
                break;
            } else {
                System.out.println("该学生信息已存在，请重新输入");
            }

        }

    }


    public static boolean contains(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            if (s.getSid().equals(id)) {
                return true;
            }
        }
        return false;
    }
}

