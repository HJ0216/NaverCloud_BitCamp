package person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import person.bean.PersonDTO;
import person.dao.PersonDAO;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;
	
	@Override
	public void write(PersonDTO personDTO) {
		personDAO.write(personDTO);	
	}

	@Override
	public List<PersonDTO> getPersonList() {
		return personDAO.getPersonList();
	}
	
}
