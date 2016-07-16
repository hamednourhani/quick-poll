package ir.itstar.quickPoll.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import ir.itstar.quickPoll.domain.Poll;

public class QuickPollClientV3Oauth2 {
	
	
	private OAuth2RestTemplate oAuth2RestTemplate;
	
	public QuickPollClientV3Oauth2(String username,String password) {
		this.oAuth2RestTemplate = restTemplate(username, password);
	}

	private static final String QUICK_POLL_URI_V3 = "http://localhost:8080/oauth2/v3/polls";
	
	public Poll getPollById(Long pollId) {
		return oAuth2RestTemplate.getForObject(QUICK_POLL_URI_V3 + "/{pollId}", Poll.class, pollId);
	}
	
	private OAuth2RestTemplate restTemplate(String username,String password) {
		ResourceOwnerPasswordResourceDetails resourceDetails = new
		ResourceOwnerPasswordResourceDetails();
		resourceDetails.setGrantType("password");
		resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
		resourceDetails.setClientId("quickpolliOSClient");
		resourceDetails.setClientSecret("top_secret");
		// Set scopes
		List<String> scopes = new ArrayList<>();
		scopes.add("read"); scopes.add("write");
		resourceDetails.setScope(scopes);
		// Resource Owner details
		resourceDetails.setUsername(password);
		resourceDetails.setPassword(username);
		return new OAuth2RestTemplate(resourceDetails);
	}

	public OAuth2RestTemplate getoAuth2RestTemplate() {
		return oAuth2RestTemplate;
	}

	
	
	
	
}
