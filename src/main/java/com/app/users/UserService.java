package com.app.users;

import com.app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<User> read()
    {
        List<User> list=new ArrayList<>();
        userRepo.findAll().forEach(user->list.add(user));
        return list;
    }
    public User add(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public User update(User user)
    {
        User savedUser=userRepo.findById(user.getUserId()).get();
        savedUser.setPassword(user.getPassword());
        return userRepo.save(savedUser);
    }
    public User delete(long userId)
    {
        User savedUser=userRepo.findById(userId).get();
        userRepo.delete(savedUser);
        return savedUser;
    }
    public User read(long userId)
    {
        return userRepo.findById(userId).get();
    }

//    private List<User> list;
//
//    public List<User> getList() {
//        return list;
//    }
//
//    public void setList(List<User> list) {
//        this.list = list;
//    }
//
//    public UserService()
//    {
//        list=new ArrayList<>();
//        list.add(new User("abc","abc","9847983746"));
//        list.add(new User("xyz","xyz","9847934746"));
//        list.add(new User("aaaa","aaaa","9843452746"));
//        list.add(new User("yyy","yyy","984345283746"));
//        list.add(new User("zzz","zzz","984798398746"));
//        list.add(new User("kkk","uiy","984798374876"));
//        list.add(new User("kasd","hghg","9847983748876"));
//
//    }
//
//    public User add(User user)
//    {
//        list.add(user);
//        return user;
//    }
//    public User update(User updatedUser)
//    {
//        User savedUser=list.stream().filter(user->user.getUsername().equals(updatedUser.getUsername()))
//                .findAny().orElse(null);
//        savedUser.setPassword(updatedUser.getPassword());
//        return savedUser;
//    }
//    public User delete(String username)
//    {
//        User user=list.stream().filter(u->u.getUsername().equals(username)).findFirst().get();
//        list.remove(user);
//        return user;
//    }
//
//    public User read(String username)
//    {
//        return list.stream().filter(u->u.getUsername().equals(username)).findAny().orElse(null);
//    }
//    public List<User> read()
//    {
//        return list;
//    }
}
