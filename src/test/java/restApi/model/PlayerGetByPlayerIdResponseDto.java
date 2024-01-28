package restApi.model;

import lombok.Data;

@Data
public class PlayerGetByPlayerIdResponseDto{
	private String password;
	private String role;
	private String gender;
	private int id;
	private String screenName;
	private String login;
	private int age;
}