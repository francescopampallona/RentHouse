package com.renthouse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renthouse.model.Host;

@Repository
public interface HostRepository extends CrudRepository<Host, Long>  {
}
