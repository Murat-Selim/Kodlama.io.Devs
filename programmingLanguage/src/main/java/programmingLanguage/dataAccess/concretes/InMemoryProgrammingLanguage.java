package programmingLanguage.dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import programmingLanguage.dataAccess.abstracts.ProgrammingLanguageRepository;
import programmingLanguage.entities.concretes.ProgrammingLanguage;

@Repository
public class InMemoryProgrammingLanguage implements ProgrammingLanguageRepository{

	 List<ProgrammingLanguage> programmingLanguages;

	    public InMemoryProgrammingLanguage() {
	        programmingLanguages = new ArrayList<>();
	        programmingLanguages.add(new ProgrammingLanguage(1, "Java"));
	        programmingLanguages.add(new ProgrammingLanguage(2, "C#"));
	        programmingLanguages.add(new ProgrammingLanguage(3, "JavaScript"));
	        programmingLanguages.add(new ProgrammingLanguage(4, "Python"));
	        programmingLanguages.add(new ProgrammingLanguage(5, "PHP"));
	    }

	    @Override
	    public List<ProgrammingLanguage> getAll() {
	        return programmingLanguages;
	    }


	    @Override
	    public void add(ProgrammingLanguage programmingLanguage) {
	        programmingLanguages.add(programmingLanguage);
	    }

	    @Override
	    public void delete(int id) {
	        programmingLanguages.remove(id);

	    }

	    @Override
	    public void update(int id, ProgrammingLanguage programmingLanguage) {
	        programmingLanguages.set(id, programmingLanguage);

	    }


	    @Override
	    public ProgrammingLanguage getLanguageById(int id) {
	        return programmingLanguages.stream().filter(language -> language.getId() == id).findFirst().get();
	    }


	

}
