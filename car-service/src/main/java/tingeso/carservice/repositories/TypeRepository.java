package tingeso.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tingeso.carservice.entities.TypeEntity;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
    public TypeEntity findByName(String name);

    List<TypeEntity> findAll();

}
