package com.unisys.omse.webmanager.po;

public class Count {
    private String id;
    private String key;
    private String value;
    private String count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Count() {
    }

    public Count(String id, String key, String value, String count) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Count{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
