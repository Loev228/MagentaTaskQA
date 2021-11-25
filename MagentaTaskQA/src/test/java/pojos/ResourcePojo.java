package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public @Data class ResourcePojo {
	private String color;
	private int year;
	private String name;
	private int id;
	@JsonProperty("pantone_value")
	private String pantoneValue;
}
