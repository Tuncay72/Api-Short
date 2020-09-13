package pojo;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

//TODO Country POJO class that have same encapsulated field as Countries table
public class Countries {
    private String country_id;
    private String country_name;
    private int region_id ;
}
