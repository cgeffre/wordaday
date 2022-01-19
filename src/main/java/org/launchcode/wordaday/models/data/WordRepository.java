package org.launchcode.wordaday.models.data;

import org.launchcode.wordaday.models.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends CrudRepository<Word, Integer> {
}
