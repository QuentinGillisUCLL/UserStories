package demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    public long id;
    public User (){}

    public User (String name,int age) {
        this.name = name;
        if (age>= 0)
            this.age = age;
    }

    private String name;
    private int age;
    private String email;
    private String password;
    @Transient
    private List<Integer> membershipYears = new ArrayList<Integer>();

    public User(String name, int age,String email, String password) {
        this.name = name;
        if (age >= 0) 
            this.age = age;
        this.email = email;
        this.password = password;
        
    }

    public int countMembershipYearsAfter1999 () {
        int result = 0;
        for(Integer year: membershipYears) {
            if (year > 1999)
                result++;
        }
        return result;
    }

    public int countYearsOfMembership () {
        return membershipYears.size();
    }

    public void addMembershipYear (int year) {
        membershipYears.add(year);
    }

    public int getAge() {
        return this.age;
    }

    public String getName () {
        return name;
    }

    public String getEmail () {
        if (email.contains("@"))
            return email;
        return null;
    }

    public String getPassword () {
        if (password.isBlank())
            return "@$-t&%#";
            
        
        return "@$-"+password+"&%#";
    }

    public List<Integer> getMembershipYears () {
        return membershipYears;
    }

    public int getFirstMembershipYear () {
        int oldest = 3000;
        if (membershipYears.size()> 0) {
            for (Integer years : this.membershipYears) {
                if (years < oldest)
                    oldest = years;

        }}
        else  {
            oldest = 0;
        }
    return oldest;
    }

    public String toString () {
        return name + " is " + age + " years old and has as email " + email;
    }

    public int getNumberOfMembershipYearsIn2000 () {
        int counter = 0;
        for (Integer Year : membershipYears) {
            if (Year >= 2000 && Year < 3000) {
                counter++;
            }
        }
        return counter;
    }

    public boolean isPasswordCorrect (String password) {
        if (password == "ikgahetnietvertellenhoor") {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isMemberInYear(int year) {
        return membershipYears != null && membershipYears.contains(year);
    }

    // public boolean equals (User otherUser) {
    //     if (this.getName().equals(otherUser.getName()) && this.getAge() == otherUser.getAge() && this.getEmail().equals(otherUser.getEmail())) {
    //         return true;
    //     }
    //     return false;

    // }
    

    

}
