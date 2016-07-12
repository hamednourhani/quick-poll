package ir.itstar.qurickPoll.controller;

import java.net.URI;

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

import ir.itstar.qurickPoll.domain.Poll;
import ir.itstar.qurickPoll.repository.PollRepository;

@RestController
public class PollController {
	
	@Autowired
	PollRepository pollRepository;
	
	@RequestMapping(value="/polls",method=RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllPolls(){
		Iterable<Poll> polls = pollRepository.findAll();
		return new ResponseEntity<>(pollRepository.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls",method=RequestMethod.POST)
	public ResponseEntity<?> createPOll(@RequestBody Poll poll){
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
		Poll p = pollRepository.findOne(pollId);
		return new ResponseEntity<Poll>(pollRepository.findOne(pollId),HttpStatus.OK);
	}
	
}
