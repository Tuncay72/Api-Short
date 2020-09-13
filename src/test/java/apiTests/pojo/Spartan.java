package apiTests.pojo;




/**
 * GSON --> Json parser, object mapper
 * Map<String, Object> spartan1 = response.body().as(Map.Class)
 * POJO --> Plain Old Java Object
 *
 * JSON RESPONSE --> java object(pojo) --> De-serialization
 * java object to     JSON BODY        --> Serialization
 */

public class Spartan {

    private int id;
    private String name;
    private String gender;
    private Long phone;

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }

    public Spartan() {
    }

    public Spartan(int id, String name, String gender, Long phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Long getPhone() {
        return phone;
    }
}
