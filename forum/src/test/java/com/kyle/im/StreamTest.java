package com.kyle.im;

import com.kyle.im.common.util.Console;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangkaile
 * @date 2019-09-03 15:24:40
 *
 * JDK 1.8 的流式数据处理示例
 *
 * 1. 过滤：按照给定的要求对集合进行筛选满足条件的元素，java8提供的筛选操作包括：filter、distinct、limit、skip。
 */
public class StreamTest {
    private List<Student> students = new ArrayList<Student>() {
        {
            add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
            add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
            add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
            add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
            add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
            add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
            add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
            add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
            add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
            add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
        }
    };

    /**
     * 过滤
     */
    @Test
    public void filter(){
        List<Student> list =
                students.stream()
                        .filter(student -> "武汉大学".equals(student.getSchool()))
                        .collect(Collectors.toList());
        Console.print("筛选出学校为武汉大学的学生",list);

    }
    /**
     * 去重
     */
    @Test
    public void distinct(){
        List<String> list =
                students.stream()
                        .map(Student::getSchool)
                        .distinct()
                        .collect(Collectors.toList());
        Console.print("筛选出所有学校名，不重复显示",list);
    }
    @Test
    public void sum(){
        int total = students.stream()
                            .filter(student -> "武汉大学".equals(student.getSchool()))
                            .mapToInt(Student::getAge)
                            .sum();
        Console.print("统计武汉大学学生年龄总和",total);
    }
    @Test
    public void allMatch(){
        boolean isAdult = students.stream()
                                    .allMatch(student -> student.getAge() >= 18);
        Console.print("是否所有学生都已经成年",isAdult);
    }

    @Test
    public void anyMatch(){
        boolean has = students.stream()
                                .anyMatch(student -> "武汉大学".equals(student.getSchool()));
        Console.print("是否有来自武汉大学的学生",has);
    }
    @Test
    public void findFirst(){

    }
}
