package com.in28minutes.rest.webservices.restfulwebservices.user;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
@Component
public class UserDaoService {
  // JPA/Hibernate > DataBase
	// UserDaoService > static List
  private static final Logger LOGGER = LogManager.getLogger(UserDaoService.class);
  private static List<User> users = new ArrayList<>();
	
    private static int userCount = 0;
     static {
    	 users.add(new User (++userCount, "KKKKadam",LocalDate.now().minusYears(30)));
    	 users.add(new User (++userCount, "Eve",LocalDate.now().minusYears(25)));
    	 users.add(new User (++userCount, "Jim",LocalDate.now().minusYears(20)));
     }
     
    public List<User> findAll(){
    	return users;
    } 
    public User save(User user) {
       LOGGER.info("user creating"  +user);
         user.setId(++userCount);
       users.add(user);
       LOGGER.info("User created"  +user);
      return user;
    }
    
    public User findOne(int id) {
        LOGGER.info("Received Request get user details from USER DAOService.Java and user id :  " + id);
         Predicate<? super User> predicate = user -> user.getId().equals(id);
    	return users.stream().filter(predicate).findFirst().orElse(null);
    }
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}

}
