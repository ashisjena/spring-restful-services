package com.exp.restful.services.user.services;

import com.exp.restful.services.user.beans.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoService {
  private static List<User> users = new ArrayList<>();

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
      user.setId(users.size() + 1);
    }
    users.add(user);
    return user;
  }

  public Optional<User> deleteById(int id) {
    Iterator<User> iterator = users.iterator();
    while (iterator.hasNext()) {
      User user = iterator.next();
      if (user.getId() == id) {
        iterator.remove();
        return Optional.of(user);
      }
    }
    return Optional.empty();
  }

  public Optional<User> findOne(int id) {
    return users.stream().filter(user -> user.getId() == id).findAny();
  }
}
