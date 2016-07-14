package ir.itstar.quickPoll.repository;

import org.springframework.data.repository.CrudRepository;

import ir.itstar.quickPoll.domain.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {

}
