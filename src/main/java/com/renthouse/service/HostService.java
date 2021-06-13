package com.renthouse.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.renthouse.dao.HostRepository;
import com.renthouse.model.Host;


@Service
public class HostService extends AbstractService<Host>{
	 @Autowired
	 HostRepository hostRepository;

}
