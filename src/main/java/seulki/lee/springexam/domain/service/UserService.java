package seulki.lee.springexam.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seulki.lee.springexam.domain.repository.UserDao;

@Service
public class UserService {
    @Autowired
    UserDao dao;
}
