package ir.itstar.quickPoll.v2.controller;

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

import ir.itstar.quickPoll.domain.Vote;
import ir.itstar.quickPoll.repository.VoteRepository;

@RestController("voteControllerV2")
@RequestMapping("/v2/")
public class VoteController {
	
	@Autowired
	VoteRepository voteRepository;
	
	@RequestMapping(value="/polls/{pollId}/votes",method=RequestMethod.GET)
	public ResponseEntity<Iterable<Vote>> getAllVotes(@PathVariable long pollId){
		return new ResponseEntity<>(voteRepository.findByPoll(pollId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls/{pollId}/votes",method=RequestMethod.POST)
	public ResponseEntity<Vote> createVote(@RequestBody Vote vote,@PathVariable long pollId){
		vote = voteRepository.save(vote);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{pollId}/votes/{votId}")
						.buildAndExpand(pollId,vote.getId()).toUri();
		responseHeaders.setLocation(location);
		return new ResponseEntity<>(vote ,responseHeaders,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/polls/{pollId}/votes/{voteId}",method=RequestMethod.GET)
	public ResponseEntity<Vote> getVote(@PathVariable long voteId){
		return new ResponseEntity<>(voteRepository.findOne(voteId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls/{pollId}/votes/{voteId}",method=RequestMethod.PUT)
	public ResponseEntity<Vote> updateVote(
			@RequestBody Vote vote,
			@PathVariable long pollId,
			@PathVariable long voteId
			){
		vote = voteRepository.save(vote);
		return new ResponseEntity<>(vote,HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls/{pollId}/votes/{voteId}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteVote(@PathVariable long pollId,@PathVariable long voteId){
		voteRepository.delete(voteId);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
}
