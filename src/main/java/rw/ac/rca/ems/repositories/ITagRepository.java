package rw.ac.rca.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.ems.models.Tag;

import java.util.List;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByTitleLikeOrDescriptionLike(String title, String description);
}
