package demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/users")
public class UserRestController {
    @PostMapping()
    public boolean addUser(@RequestBody User user) throws ServiceException {
    return userService.addUser(user) == null ? false : true;
    }
    @DeleteMapping("/{email}")
    public User deleteUser (@PathVariable("email") String email) throws ServiceException {
        return userService.removeUser(email);
    }

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/oldest")
    public User getOldestUser() throws ServiceException {
        return userService.getOldestUser();
    }

    @GetMapping("/search/olderthan")
    public List<User> searchUsersWithAgeOlderThan(@RequestParam("age") int age) throws ServiceException {
        return userService.getUsersWithAgeOlderThan(age);
    }

    @GetMapping("/search/{name}")
    public User searchUserWithName(@PathVariable("name") String name) {
        return userService.getUserWithName(name);
    }

    @GetMapping("/adults")
    public List<User> getAllAdults ()throws ServiceException {
        return userService.getUsersWithAgeOlderThan(17);
    }

    @GetMapping("/search/email/{email}")
    public User getUserWithEmail (@PathVariable("email")String email) throws ServiceException {
        return userService.getUserWithEmail(email);

    }
    
    @GetMapping("/search")
    public List<User> searchUserWithEmailAndAge(@RequestParam("email") String email, @RequestParam("age") int age) {
        return userService.getUserWithEmailAndAge(email, age);
    }

    @GetMapping("/search/age/{min}/{max}")
    public List<User> getUserBetweenMaxandMin (@PathVariable("min")int min, @PathVariable("max") int max) {
        return userService.getUsersWithAgeBetween(min,max);
    }

    @GetMapping("/search/partofthatyear")
    public List<User> getUsersOfThatYear (@RequestParam("year")int year) {
        return userService.getAllUsersInYear(year);
    }


}
