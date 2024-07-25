package com.bgcg.queryexecute.services;

import com.bgcg.queryexecute.entity.Users;
import com.bgcg.queryexecute.exception.UserAlreadyExistsException;
import com.bgcg.queryexecute.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users addNew(Users users){
        if (usersRepository.findByEmail(users.getEmail()) != null) throw new UserAlreadyExistsException("User already exists");
        return usersRepository.save(users);
    }


    public List<Users> getUsers(){

        List<Users> all = usersRepository.findAll();
        System.out.println("Inside users service: " + all);
        return all;
    }
}
