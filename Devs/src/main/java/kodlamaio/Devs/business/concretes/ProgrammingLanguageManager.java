package kodlamaio.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.Devs.business.abstracts.ProgrammingLanguageService;
import kodlamaio.Devs.business.requests.programmingLanguagesRequest.CreateProgrammingLanguageRequest;
import kodlamaio.Devs.business.requests.programmingLanguagesRequest.UpdateProgrammingLanguageRequest;
import kodlamaio.Devs.business.responses.programmingLanguagesResponse.GetAllProgrammingLanguagesResponse;
import kodlamaio.Devs.business.responses.programmingLanguagesResponse.GetByIdLanguageResponse;
import kodlamaio.Devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlamaio.Devs.entities.concretes.ProgrammingLanguage;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {
	
	private ProgrammingLanguageRepository programmingLanguageRepository;

	@Autowired
	public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository) {
		this.programmingLanguageRepository = programmingLanguageRepository;
	}

	@Override
	public List<GetAllProgrammingLanguagesResponse> getAll() {
		List<ProgrammingLanguage> languages = programmingLanguageRepository.findAll();
		List<GetAllProgrammingLanguagesResponse> programmingLanguagesResponse = new ArrayList<GetAllProgrammingLanguagesResponse>();
		
		for (ProgrammingLanguage language : languages) {
			GetAllProgrammingLanguagesResponse responseItem = new GetAllProgrammingLanguagesResponse();
			responseItem.setId(language.getId());
			responseItem.setName(language.getName());
			
			programmingLanguagesResponse.add(responseItem);
			
		}
		
		return programmingLanguagesResponse;

	}

	@Override
	public GetByIdLanguageResponse getByIdLanguage(int id) {
		ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(id).orElseThrow();
		
		GetByIdLanguageResponse response = new GetByIdLanguageResponse();
		response.setId(programmingLanguage.getId());
		
		return response;
	}

	@Override
	public void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {
		ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
		programmingLanguage.setName(createProgrammingLanguageRequest.getName());
		
		if (programmingLanguage.getName().isEmpty()) {
            throw new Exception("Programming language cannot be empty.");
        }
		
        for (ProgrammingLanguage language : programmingLanguageRepository.findAll()) {
            if (language.getName().equals(programmingLanguage.getName())) {
                throw new Exception("Programming language cannot be repeated");
            }
        }

        programmingLanguageRepository.save(programmingLanguage);
		
	}

	@Override
	public void update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {
		ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
		programmingLanguage.setId(updateProgrammingLanguageRequest.getId());
		programmingLanguage.setName(updateProgrammingLanguageRequest.getName());
		
		programmingLanguageRepository.save(programmingLanguage);
		
	}

	@Override
	public void delete(int id) {
		this.programmingLanguageRepository.deleteById(id);
		
	}

}
