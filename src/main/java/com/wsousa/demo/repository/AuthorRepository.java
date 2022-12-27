package com.wsousa.demo.repository;

import java.util.Optional;

import com.wsousa.demo.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>{

	Optional<Author> findByEmail(String email);

}
