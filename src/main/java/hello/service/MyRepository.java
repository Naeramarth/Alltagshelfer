package hello.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hello.Client;

public interface MyRepository extends CrudRepository<Client, Integer> {
	
	public List<Client> findByName(String name);

}
