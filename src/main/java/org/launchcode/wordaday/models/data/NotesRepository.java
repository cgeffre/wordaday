package org.launchcode.wordaday.models.data;

import org.launchcode.wordaday.models.Notes;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Notes, Integer> {
}
