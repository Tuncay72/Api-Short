package pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {

    private int region_id;
    private String region_name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return region_id == region.region_id &&
                region_name.equals(region.region_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(region_id, region_name);
    }

    @Override
    public String toString() {
        return "Region{" +
                "region_id=" + region_id +
                ", region_name='" + region_name + '\'' +
                '}';
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
