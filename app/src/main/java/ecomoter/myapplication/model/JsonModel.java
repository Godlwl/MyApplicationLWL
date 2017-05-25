package ecomoter.myapplication.model;

import java.util.List;

/**
 * Created by lwl on 2017/5/22.
 * Describe:
 */

public class JsonModel {


    /**
     * name : 张三
     * pet_name :
     * head_address : http://61.144.248.2:8085/pic/pic_20170406172933496_.jpg
     * card_id : 1704050927597009198978
     * email :
     * sex : 男
     * age : 23-27岁
     * comefrom : 1
     * company_id : 4feb6c17aa5b4853b3289d3f1526f0bb
     * consultant : {}
     * car_info : [{"car_id":457829,"card_id":"1704050927597009198978","brand":"","model":"","license_plate":"13**76","last_km":0,"spacing_km":0,"grade":"","frame_number":"","car_type":"","bdate":"","engine_number":"","remind_day":90,"next_by":"","next_bx":0,"next_ns":"","idate":"","lmdate":""}]
     * count_msg : 0
     * result : 0
     */

    private String name;
    private String pet_name;
    private String head_address;
    private String card_id;
    private String email;
    private String sex;
    private String age;
    private int comefrom;
    private String company_id;
    private int count_msg;
    private int result;
    /**
     * car_id : 457829
     * card_id : 1704050927597009198978
     * brand :
     * model :
     * license_plate : 13**76
     * last_km : 0
     * spacing_km : 0
     * grade :
     * frame_number :
     * car_type :
     * bdate :
     * engine_number :
     * remind_day : 90
     * next_by :
     * next_bx : 0
     * next_ns :
     * idate :
     * lmdate :
     */

    private List<CarInfoEntity> car_info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public String getHead_address() {
        return head_address;
    }

    public void setHead_address(String head_address) {
        this.head_address = head_address;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getComefrom() {
        return comefrom;
    }

    public void setComefrom(int comefrom) {
        this.comefrom = comefrom;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public int getCount_msg() {
        return count_msg;
    }

    public void setCount_msg(int count_msg) {
        this.count_msg = count_msg;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<CarInfoEntity> getCar_info() {
        return car_info;
    }

    public void setCar_info(List<CarInfoEntity> car_info) {
        this.car_info = car_info;
    }

    public static class CarInfoEntity {
        private int car_id;
        private String card_id;
        private String brand;
        private String model;
        private String license_plate;
        private int last_km;
        private int spacing_km;
        private String grade;
        private String frame_number;
        private String car_type;
        private String bdate;
        private String engine_number;
        private int remind_day;
        private String next_by;
        private int next_bx;
        private String next_ns;
        private String idate;
        private String lmdate;

        public int getCar_id() {
            return car_id;
        }

        public void setCar_id(int car_id) {
            this.car_id = car_id;
        }

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getLicense_plate() {
            return license_plate;
        }

        public void setLicense_plate(String license_plate) {
            this.license_plate = license_plate;
        }

        public int getLast_km() {
            return last_km;
        }

        public void setLast_km(int last_km) {
            this.last_km = last_km;
        }

        public int getSpacing_km() {
            return spacing_km;
        }

        public void setSpacing_km(int spacing_km) {
            this.spacing_km = spacing_km;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getFrame_number() {
            return frame_number;
        }

        public void setFrame_number(String frame_number) {
            this.frame_number = frame_number;
        }

        public String getCar_type() {
            return car_type;
        }

        public void setCar_type(String car_type) {
            this.car_type = car_type;
        }

        public String getBdate() {
            return bdate;
        }

        public void setBdate(String bdate) {
            this.bdate = bdate;
        }

        public String getEngine_number() {
            return engine_number;
        }

        public void setEngine_number(String engine_number) {
            this.engine_number = engine_number;
        }

        public int getRemind_day() {
            return remind_day;
        }

        public void setRemind_day(int remind_day) {
            this.remind_day = remind_day;
        }

        public String getNext_by() {
            return next_by;
        }

        public void setNext_by(String next_by) {
            this.next_by = next_by;
        }

        public int getNext_bx() {
            return next_bx;
        }

        public void setNext_bx(int next_bx) {
            this.next_bx = next_bx;
        }

        public String getNext_ns() {
            return next_ns;
        }

        public void setNext_ns(String next_ns) {
            this.next_ns = next_ns;
        }

        public String getIdate() {
            return idate;
        }

        public void setIdate(String idate) {
            this.idate = idate;
        }

        public String getLmdate() {
            return lmdate;
        }

        public void setLmdate(String lmdate) {
            this.lmdate = lmdate;
        }
    }
}
