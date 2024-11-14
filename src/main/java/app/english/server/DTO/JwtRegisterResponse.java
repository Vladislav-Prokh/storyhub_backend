package app.english.server.DTO;

import lombok.Data;

@Data
public class JwtRegisterResponse {
	private String status;

	public JwtRegisterResponse(String status) {
		this.status = status;
	}

}
