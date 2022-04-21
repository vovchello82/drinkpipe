package de.drinkpipe.persistence;

import com.github.dozermapper.core.Mapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class RepoService<R extends JpaRepository<E, String>, E> {

  protected final Mapper mapper;
  protected final R repository;

  public Optional<E> findById(String id) {
    return repository.findById(id);
  }

  public List<E> findAll() {
    return repository.findAll();
  }

  public E save(E entity) {
    return repository.save(entity);
  }
}
