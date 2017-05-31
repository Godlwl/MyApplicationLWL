package ecomoter.myapplication.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by lwl on 2017/5/31.
 * Describe:
 */

@Entity
public class Teacher {
    @Id
    private Long id;
    private String name;
    private String course;

    private String sexs;

    @Generated(hash = 194611513)
    public Teacher(Long id, String name, String course, String sexs) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.sexs = sexs;
    }

    @Generated(hash = 1630413260)
    public Teacher() {
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

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSexs() {
        return this.sexs;
    }

    public void setSexs(String sexs) {
        this.sexs = sexs;
    }




}
