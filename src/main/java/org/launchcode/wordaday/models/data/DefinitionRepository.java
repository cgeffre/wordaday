package org.launchcode.wordaday.models.data;

import org.launchcode.wordaday.models.Definition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefinitionRepository extends CrudRepository<Definition, Integer> {
}
