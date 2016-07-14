package ir.itstar.quickPoll.v2.controller;

import java.net.URI;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ir.itstar.quickPoll.domain.Poll;
import ir.itstar.quickPoll.exception.ResourceNotFoundException;
import ir.itstar.quickPoll.repository.PollRepository;

@RestController("pollControllerV2")
@RequestMapping("/v2/")
public class PollController {
	
	@Autowired
	PollRepository pollRepository;
	
	@RequestMapping(value="/polls",method=RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllPolls(){
		Iterable<Poll> polls = pollRepository.findAll();
		if(((ArrayList<Poll>)polls).isEmpty()){
			throw new ResourceNotFoundException("there is no poll.");
		}
		return new ResponseEntity<>(pollRepository.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls",method=RequestMethod.POST)
	public ResponseEntity<?> createPOll(@Valid @RequestBody Poll poll){
		
		poll = pollRepository.save(poll);
			URI location = ServletUriComponentsBuilder
								.fromCurrentRequest()
								.path("/{id}").buildAndExpand(poll.getId()).toUri();
								
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(location);
			
			return new ResponseEntity<>(null,headers,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/polls/{pollId}",method=RequestMethod.GET)
	public ResponseEntity<Poll> getPoll(@PathVariable long pollId){
		verfiyPoll(pollId);
		return new ResponseEntity<Poll>(pollRepository.findOne(pollId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls/{pollId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updatePoll(@PathVariable long pollId, @RequestBody Poll poll){
		verfiyPoll(pollId);
		return new ResponseEntity<>(pollRepository.save(poll), HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls/{pollId}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deletePoll(@PathVariable long pollId){
		verfiyPoll(pollId);
		pollRepository.delete(pollId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	public void verfiyPoll(long pollId) {
		Poll poll = pollRepository.findOne(pollId);
		if(poll == null){
			throw new ResourceNotFoundException("The Poll with Id "+ pollId +" is not exists");
		}
	}
	
}
