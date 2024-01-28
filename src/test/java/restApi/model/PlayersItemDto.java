package restApi.model;

import lombok.Data;

@Data
public class PlayersItemDto {
	private String gender;
	private int id;
	private String screenName;
	private int age;
}