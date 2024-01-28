package restApi.model;

import lombok.Data;

@Data
public class PlayerCreateResponseDto{
	private String password;
	private String role;
	private String gender;
	private int id;
	private String screenName;
	private String login;
	private int age;
}