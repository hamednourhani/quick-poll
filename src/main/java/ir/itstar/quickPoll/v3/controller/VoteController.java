package ir.itstar.quickPoll.v3.controller;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ir.itstar.quickPoll.domain.Vote;
import ir.itstar.quickPoll.repository.VoteRepository;

@RestController("voteControllerV3")
@RequestMapping("/v3/")
public class VoteController {
	
	@Autowired
	VoteRepository voteRepository;
	
	@RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.POST)
	@ApiOperation(value = "Casts a new vote for a given poll", notes="The newly created vote Id will be sent in the location response header", 
	response = Void.class)
	@ApiResponses(value ={@ApiResponse(code=201, message="Vote Created Successfully", response=Void.class )})
	public ResponseEntity<Void> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
		vote = voteRepository.save(vote);
		
		// Set the headers for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
		
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
	@ApiOperation(value = "Retrieves all the votes", response=Vote.class, responseContainer="List")
	public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
		return voteRepository.findByPoll(pollId);
	}
}
