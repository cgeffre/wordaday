package org.launchcode.wordaday.models.data;

import org.launchcode.wordaday.models.Favorites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites, Integer> {
}
