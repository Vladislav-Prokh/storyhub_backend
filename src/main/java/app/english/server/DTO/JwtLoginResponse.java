package app.english.server.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtLoginResponse {
	
	private String JwtToken;
	
	private List<String> userRoles;

	public JwtLoginResponse(String token) {
		this.JwtToken = token;
	}
}
