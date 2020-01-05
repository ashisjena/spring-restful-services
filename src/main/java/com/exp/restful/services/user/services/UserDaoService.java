package com.exp.restful.services.user.services;

import com.exp.restful.services.user.beans.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {
  private static List<User> users = new ArrayList<>();
  private static int nextUserId = 4;

  static {
    users.add(new User(1, "Ram", new Date()));
    users.add(new User(2, "Hari", new Date()));
    users.add(new User(3, "Shayam", new Date()));
  }

  public List<User> findAll() {
    return users;
  }

  public User save(User user) {
    if (user.getId() == null) {
      user.setId(nextUserId++);
    }
    users.add(user);
    return user;
  }

  public Optional<User> findOne(int id) {
    return users.stream().filter(user -> user.getId() == id).findAny();
  }
}
