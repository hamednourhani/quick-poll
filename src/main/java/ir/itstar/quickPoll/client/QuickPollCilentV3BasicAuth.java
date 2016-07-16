package ir.itstar.quickPoll.client;



import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;


public class QuickPollCilentV3BasicAuth {
	
	private static final String QUICK_POLL_URI_V3 = "http://localhost:8080/v3/polls";
	private RestTemplate restTemplate = new RestTemplate();
	
	private HttpHeaders getAuthenticationHeader(String username,String password){
		String credentials = username + ":" + password;
		byte[] base64CredentialData = Base64.encodeBase64(credentials.getBytes());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic " + new String(base64CredentialData));
		return headers;
	}
	
	public void deletePoll(long pollId,String username,String password){
		HttpHeaders authenticationHeaders = getAuthenticationHeader(username, password);
		restTemplate.exchange(QUICK_POLL_URI_V3 + "/{pollId}",
				HttpMethod.DELETE, new HttpEntity<Void>(authenticationHeaders), Void.class,
				pollId);
	}
	

}
