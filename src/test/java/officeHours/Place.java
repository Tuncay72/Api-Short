package officeHours;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString

@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @JsonProperty("Place name")
    private String placeName ;
    private String longitude ;
    private String state ;
    @JsonProperty("state abbreviation")
    private String stateAbbreviation ;
    private String latitude ;
}
