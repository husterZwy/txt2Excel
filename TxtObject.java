/**
 * @auth Abel
 * @date 2019/1/13 下午6:52
 */
public class TxtObject {
    String name;
    String author;
    String time;
    String serialNum;
    String category = "";

    public TxtObject(){}

    public TxtObject(String name, String author, String time, String serialNum) {
        this.name = name;
        this.author = author;
        this.time = time;
        this.serialNum = serialNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
