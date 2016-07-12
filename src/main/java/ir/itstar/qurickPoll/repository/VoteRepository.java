package ir.itstar.qurickPoll.repository;

import org.springframework.data.repository.CrudRepository;

import ir.itstar.qurickPoll.domain.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

}
