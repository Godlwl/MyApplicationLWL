package ecomoter.myapplication.model;

import org.litepal.crud.DataSupport;

/**
 * Created by lwl on 2017/5/27.
 * Describe:
 */

public class Student extends DataSupport {
    private String num;
    private String name;
    private int age;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
