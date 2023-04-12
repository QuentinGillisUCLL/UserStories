package demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

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

    public List<User> getUsersWithAgeOlderThan(int age) {
        return userRepository.findUsersByAgeAfter(age);
    }

    public User getOldestUser() {
        User oldest = null;
        List<User> users = userRepository.findAllByOrderByAgeDesc();
        if (users != null && !users.isEmpty()) {
            oldest = users.get(0);
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

    public boolean addUser(User user) {
        if (getUserWithEmail(user.getEmail()) != null)
            return false;
        userRepository.save(user);
        return true;
    }

    public User getUserWithEmail (String email) {
        return userRepository.findUserByEmail(email);
    }


    @Transactional
    public User removeUser(String email){
        User user = getUserWithEmail(email);
        if (user == null){
            return null;
  }
    userRepository.deleteByEmail(email);
    return user;
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
