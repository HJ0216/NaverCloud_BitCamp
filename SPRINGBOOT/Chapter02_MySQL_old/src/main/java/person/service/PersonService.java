package person.service;

import java.util.List;

import person.bean.PersonDTO;

public interface PersonService {

	public void write(PersonDTO personDTO);

	public List<PersonDTO> getPersonList();

}
