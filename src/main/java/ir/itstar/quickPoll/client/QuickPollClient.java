package ir.itstar.quickPoll.client;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ir.itstar.quickPoll.domain.Poll;

public class QuickPollClient {
	private static final String  QUICK_POLL_URI_V1 = "localhost:8080/v1/polls";
	RestTemplate restTemplate = new RestTemplate();
	
	public Poll getPollById(long pollId){
		return new RestTemplate().getForObject(QUICK_POLL_URI_V1+"{/pollId}", Poll.class,pollId);
	}
	
	
	public List<Poll> getAllPolls(){
		ParameterizedTypeReference<List<Poll>> responseType = new ParameterizedTypeReference
				<List<Poll>>() {};
				ResponseEntity<List<Poll>> responseEntity = restTemplate.exchange(QUICK_POLL_URI_V1,
				HttpMethod.GET, null, responseType);
				List<Poll> allPolls = responseEntity.getBody();
				return allPolls;
	}
	
	public Poll[] getAllPolls2(){
		return restTemplate.getForObject(QUICK_POLL_URI_V1, Poll[].class);
	}
	
	public URI createPollforLocation(Poll poll){
		URI location = restTemplate.postForLocation(QUICK_POLL_URI_V1, poll);
		return location;
	}
	
	public Poll createPoll(Poll poll){
		return restTemplate.postForObject(QUICK_POLL_URI_V1, poll, Poll.class);
	}
	
	public void putPoll(Poll poll){
		restTemplate.put(QUICK_POLL_URI_V1, poll);
	}
	
	public void deletePoll(long pollId){
		 restTemplate.delete(QUICK_POLL_URI_V1+"{/pollId}",pollId);
	}
	
}
