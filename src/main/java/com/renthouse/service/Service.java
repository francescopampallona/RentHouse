package com.renthouse.service;



public interface Service<E> {
	public E insert(E e);

	public E findById(long id);

	public Iterable<E> findAll();

	public E update(E e);

	public void delete(long id);
}
