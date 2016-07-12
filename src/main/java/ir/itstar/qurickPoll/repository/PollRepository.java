package ir.itstar.qurickPoll.repository;

import org.springframework.data.repository.CrudRepository;

import ir.itstar.qurickPoll.domain.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {

}
