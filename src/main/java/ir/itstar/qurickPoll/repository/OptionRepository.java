package ir.itstar.qurickPoll.repository;

import org.springframework.data.repository.CrudRepository;

import ir.itstar.qurickPoll.domain.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
