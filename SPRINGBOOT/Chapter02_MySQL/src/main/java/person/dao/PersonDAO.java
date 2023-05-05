package person.dao;

import java.util.List;

import person.bean.PersonDTO;

public interface PersonDAO {

	public void write(PersonDTO personDTO);

	public List<PersonDTO> getPersonList();

}
