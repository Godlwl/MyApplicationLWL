package ecomoter.myapplication;

/**
 * Created by lwl on 2017/4/21.
 * Describe:
 */

public class ViewDataModel {
    /**
     * 颜色
     */
    private int color;
    /**
     * 数值
     */
    private int value;
    /**
     * 角度
     */
    private float angel;
    /**
     * 百分比
     */
    private float percent;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public float getAngel() {
        return angel;
    }

    public void setAngel(float angel) {
        this.angel = angel;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
