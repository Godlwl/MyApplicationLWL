package ecomoter.myapplication.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lwl on 2017/5/31.
 * Describe:
 */

@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private int age;

    private String carType;

    private String carNum;
    private Integer test;
    @Generated(hash = 804909636)
    public User(Long id, String name, int age, String carType, String carNum,
            Integer test) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.carType = carType;
        this.carNum = carNum;
        this.test = test;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getCarType() {
        return this.carType;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getCarNum() {
        return this.carNum;
    }
    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
    public Integer getTest() {
        return this.test;
    }
    public void setTest(Integer test) {
        this.test = test;
    }

}
