package xupt.chioar.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xupt.chioar.domain.User;

@Repository
public interface UserDao {

    public User getUser(@Param("username")String  username,@Param("password")String password);
}
