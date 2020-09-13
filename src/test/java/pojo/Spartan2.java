package pojo;

// a class for Plain Old Java Object (POJO)
// to represent data

public class Spartan2 {
    private int id ;
    private String name;
    private String gender;
    private long phone;

    public Spartan2() {
        //Does not do anything but required for Jacksonto work if any other
        // constructor is provided
    }


    @Override
    public String toString() {
        return "Spartan2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
