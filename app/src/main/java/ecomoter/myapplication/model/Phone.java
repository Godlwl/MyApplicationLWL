package ecomoter.myapplication.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lwl on 2017/6/1.
 * Describe:
 */

@Entity
public class Phone {
    private String color;
    private String brand;
    private String model;
    @Generated(hash = 149508879)
    public Phone(String color, String brand, String model) {
        this.color = color;
        this.brand = brand;
        this.model = model;
    }
    @Generated(hash = 429398894)
    public Phone() {
    }
    public String getColor() {
        return this.color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getBrand() {
        return this.brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return this.model;
    }
    public void setModel(String model) {
        this.model = model;
    }
}
