package com.okava.pay.repositories;

import com.okava.pay.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByTitleLikeOrDescriptionLike(String title, String description);
}
