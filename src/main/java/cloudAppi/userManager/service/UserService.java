package cloudAppi.userManager.service;

import cloudAppi.userManager.dao.UserRepository;
import cloudAppi.userManager.modelo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.beans.DefaultPersistenceDelegate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(){

    }

    public User createUser(User user){
        System.out.println("User: " + user.toString());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        System.out.println("UserRepository: " + userRepository);
        Iterable<User> users = userRepository.findAll();
        List<User> finUsers = new ArrayList<User>();

        for(User u : users)
            finUsers.add(u);

        return finUsers;
    }
    public User getUserById(Integer id){
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Se prioriza el valor del path sobre el del body ante posible incoherencia
    public User updateUserById(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
