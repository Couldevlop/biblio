package com.openlab.biblio.service;

import com.openlab.biblio.entity.Book;
import com.openlab.biblio.entity.User;
import com.openlab.biblio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User save(User user){
       return userRepository.save(user);
    }


    public List<User> findAll(){
        return userRepository.findAll();
    }


    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }


    public User update(Long id, User user){
          Optional<User> userExist = findById(id);
          User userUpdate = null;

          if(userExist.isPresent()){
              User optionalUser = userExist.get();
              optionalUser.setName(user.getName());
              optionalUser.setEmail(user.getEmail());
              userUpdate = userRepository.save(optionalUser);
          }
        return userUpdate;
    }


    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
