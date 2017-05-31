package ecomoter.myapplication.mvpbase;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lwl on 2017/5/31.
 * Describe:
 */

@Entity
public class Student {
    private String num;
    private String name;
    private int age;
    @Generated(hash = 131313122)
    public Student(String num, String name, int age) {
        this.num = num;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
