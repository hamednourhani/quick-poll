package ir.itstar.quickPoll.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import ir.itstar.quickPoll.domain.Poll;

public interface PollRepository extends PagingAndSortingRepository<Poll, Long>{

}
