package ir.itstar.qurickPoll.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ir.itstar.qurickPoll.domain.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

		
	@Query(value="SELECT v.* FROM POLL_OPTION o,Vote v WHERE o.POLL_ID= ?1 AND v.OPTION_ID = o.OPTION_ID",
			nativeQuery = true	)
	public Iterable<Vote> findByPoll(long pollId);
}
