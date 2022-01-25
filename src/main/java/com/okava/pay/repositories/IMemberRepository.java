package com.okava.pay.repositories;

import com.okava.pay.models.EventMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository extends JpaRepository<EventMember, Long> {

}
