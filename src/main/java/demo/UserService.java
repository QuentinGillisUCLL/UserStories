package demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import demo.ServiceException;

@Service
public class UserService {
    public UserService () {
    // User user1 = new User("Elke", 44, "elke@ucll.be", "rzeze");
    // User user2 = new User("John", 25, "john@ucll.be", "password123");
    // User user3 = new User("Alice", 35, "alice@ucll.be", "qwerty");

    // userRepository.save(user1);
    // userRepository.save(user2); 
    // userRepository.save(user3);
    // user1.addMembershipYear(2000);
    // user1.addMembershipYear(2010);
    // user2.addMembershipYear(2010);
    // user3.addMembershipYear(1999);
    }
    
    @Autowired
    private UserRepository userRepository;

    


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersWithAgeOlderThan(int age) throws ServiceException {
        List <User> users = userRepository.findUsersByAgeAfter(age);
        if (users.size() == 0) {
            throw new ServiceException("users", "no users with age " +age+ " found");
        }
        return userRepository.findUsersByAgeAfter(age);
    }

    public User getOldestUser() throws ServiceException {
        User oldest = null;
        List<User> users = userRepository.findAllByOrderByAgeDesc();
        if (users != null && !users.isEmpty()) {
            oldest = users.get(0);
        }
        else {
            throw new ServiceException("users", "no oldest user found");
        }
        return oldest;
    }


    public User getUserWithName(String name) {
        for (User user : userRepository.findAll()) {
            if (user.getEmail() == name) {
                userRepository.delete(user);
                return user;
            }
        }
        return null;
        // return userRepository.stream().filter(user -> user.getName().equals(name)).toList().get(0);
    }

    public User addUser(User user) throws ServiceException {
        try {
            getUserWithEmail(user.getEmail());
        } catch (ServiceException e) {
            userRepository.save(user);
            return user;
        }
        throw new ServiceException("email", "email already taken");
    }

    public User getUserWithEmail (String email) throws ServiceException {
    User user = userRepository.findUserByEmail(email);
    if (user == null) {
        throw new ServiceException("user", "no user found with email: "+ email);
    }
    else {
        return user;
    }
    }


    @Transactional
    public User removeUser(String email) throws ServiceException{
        try {
            User foundUser = getUserWithEmail(email);

            if (foundUser != null)
                userRepository.delete(foundUser);
            return foundUser;
        } catch (ServiceException e) {
            throw new ServiceException("user", "user with this email does not exist");
        }
    }

    
    public List<User> getAllUsersInYear (int year) {
        List <User> newlist  = new ArrayList <>();
        for (User user : userRepository.findAll()) {
            if(user.getMembershipYears().contains(year)) {
                newlist.add(user);
                

            }}
        return newlist;
        }
        // return userRepository.stream().filter(user -> user.isMemberInYear(year)).toList();
    

    public List<User> getUserWithEmailAndAge(String email, int age) {
        List <User> newlist  = new ArrayList <>();
        for (User user : userRepository.findAll()) {
            if(user.getEmail().equals(email) && user.getAge() == age) {
                newlist.add(user);
            }}
        return newlist;
        // return userRepository.stream().filter(user -> user.getAge() == age && user.getEmail().equals(email)).toList();
        

    }
    public List<User> getUsersWithAgeBetween(int min, int max) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.getAge() >= min && user.getAge() <= max) {
                filteredUsers.add(user);
            }
            
        }
        return filteredUsers;
    }

}
