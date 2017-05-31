package ecomoter.myapplication.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lwl on 2017/5/31.
 * Describe:
 */

@Entity
public class NewTable {
    private String a;
    private int b;
    @Generated(hash = 162720633)
    public NewTable(String a, int b) {
        this.a = a;
        this.b = b;
    }
    @Generated(hash = 368245787)
    public NewTable() {
    }
    public String getA() {
        return this.a;
    }
    public void setA(String a) {
        this.a = a;
    }
    public int getB() {
        return this.b;
    }
    public void setB(int b) {
        this.b = b;
    }
}
