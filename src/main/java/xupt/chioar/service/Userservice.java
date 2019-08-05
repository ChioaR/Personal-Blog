package xupt.chioar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xupt.chioar.dao.UserDao;
import xupt.chioar.domain.User;

@Service
public class Userservice {

    @Autowired
    private UserDao userDao;

    public boolean login(String username, String password) {
        User user = userDao.getUser(username, password);
        if (user == null) {
            return false;
        }else{
            return true;
        }
    }
}
