package com.renthouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class AbstractService<E> implements Service<E> {
	@Autowired
	private CrudRepository<E, Long> repository;
	
	@Override
	public E insert(E entity) {
	  return repository.save(entity);
		
	}

	@Override
	public E findById(long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public Iterable<E> findAll() {
		return repository.findAll();
	}

	@Override
	public E update(E entity) {
		return repository.save(entity);
		
	}

	@Override
	public void delete(long id) {
		repository.deleteById(id);
		
	}

}
