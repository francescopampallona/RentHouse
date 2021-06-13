package com.renthouse.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.renthouse.model.Host;
import com.renthouse.service.HostService;

@Controller
@RequestMapping("/host")
public class HostController {
	
	@Autowired
	private HostService hostService;
	
	@GetMapping
	public String getAll(HttpServletRequest request) {
		this.setHosts(request);
		return "hostsManagement";
		
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request,
			             @RequestParam("name") String name, 
			             @RequestParam("surname") String surname,
			             @RequestParam("email") String email,
			             @RequestParam("nation") String nation,
			             @RequestParam("birthCity") String city,
			             @RequestParam("birthDate") Date birthDate,
			             @RequestParam("telephoneNumber") String telephoneNumber,
			             @RequestParam("creditCardNumber") String creditCardNumber) 
	{		
		
		Host host = new Host(name, surname, nation, city, birthDate, email, telephoneNumber, creditCardNumber);
		this.hostService.insert(host);
		this.setHosts(request);
		return "hostsManagement";
	}
	
	@PostMapping("/update/{id}")
	public String update(HttpServletRequest request,
			             @PathVariable("id") long id,
			             @RequestParam("name") String name,
			             @RequestParam("surname") String surname,
			             @RequestParam("email") String email,
			             @RequestParam("nation") String nation,
			             @RequestParam("birthCity") String birthCity,
			             @RequestParam("birthDate") Date birthDate,
			             @RequestParam("telephoneNumber") String telephoneNumber,
			             @RequestParam("creditCardNumber") String creditCardNumber) {
		Host hostToUpdate = this.hostService.findById(id);
		hostToUpdate.setName(name);
		hostToUpdate.setSurname(surname);
		hostToUpdate.setEmail(email);
		hostToUpdate.setNation(nation);
		hostToUpdate.setBirthCity(birthCity);
		hostToUpdate.setBirthDate(birthDate);
		hostToUpdate.setTelephoneNumber(telephoneNumber);
		hostToUpdate.setCreditCardNumber(creditCardNumber);
		hostService.update(hostToUpdate);
		this.setHosts(request);
		return "hostsManagement";
	}
	
	//SET FOR UPDATE OF THE VIEW
	private void setHosts(HttpServletRequest request) {
		Iterable<Host> hosts = hostService.findAll();
		request.setAttribute("hosts", hosts);
	}
}
