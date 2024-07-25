package com.bgcg.queryexecute.repository;

import com.bgcg.queryexecute.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users findByEmail(String email);
}
