package hello.service;

import hello.Client;

public interface MyService {

	public void create(String name);

	Client getByName(String name);

	Client getById(int id);
}
