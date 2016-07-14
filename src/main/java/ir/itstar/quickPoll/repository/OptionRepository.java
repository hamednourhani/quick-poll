package ir.itstar.quickPoll.repository;

import org.springframework.data.repository.CrudRepository;

import ir.itstar.quickPoll.domain.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
