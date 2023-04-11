package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserService () {
    // User user1 = new User("Elke", 44, "elke@ucll.be", "rzeze");
    // User user2 = new User("John", 25, "john@ucll.be", "password123");
    // User user3 = new User("Alice", 35, "alice@ucll.be", "qwerty");

    // userRepository.add(user1);
    // userRepository.add(user2); 
    // userRepository.add(user3);
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

    public List<User> getUsersWithAgeOlderThan(int age) {
        return userRepository.findUsersByAgeAfter(age);
    }

    public User getOldestUser() {
        User oldest = null;
        if (userRepository.size()>0) {
            oldest = userRepository.get(0);
            for (User user : userRepository) {
                if (user.getAge() > oldest.getAge())
                    oldest = user;
            }
        }
        return oldest;
    }

    public User getUserWithName(String name) {
        return userRepository.stream().filter(user -> user.getName().equals(name)).toList().get(0);
    }

    public boolean addUser(User user) {
        if (getUserWithEmail(user.getEmail()) != null)
            return false;
        userRepository.save(user);
        return true;
    }

    public User getUserWithEmail (String email) {
        return userRepository.findUserByEmail(email);
    }


    public User removeUser (String string) {
        for (User user : userRepository) {
            if (user.getEmail() == string) {
                userRepository.remove(user);
                return user;
            }
        }
        return null;
    }

    
    public List<User> getAllUsersInYear (int year) {
        return userRepository.stream().filter(user -> user.isMemberInYear(year)).toList();
    }

    public List<User> getUserWithEmailAndAge(String email, int age) {
        return userRepository.stream().filter(user -> user.getAge() == age && user.getEmail().equals(email)).toList();
        

    }
    public List<User> getUsersWithAgeBetween(int min, int max) {
        return userRepository.stream().filter(user -> user.getAge()>=min && user.getAge()<=max).toList();
    }

}