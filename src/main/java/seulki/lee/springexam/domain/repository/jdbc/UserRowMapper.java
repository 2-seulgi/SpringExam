package seulki.lee.springexam.domain.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import seulki.lee.springexam.domain.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
       User user = new User();
       //ResultSet 검색결과를 User인스턴스로 설정함
       user.setUserId(rs.getString("user_id"));
       user.setPassword(rs.getString("password"));
       user.setUserName(rs.getString("user_name"));
       user.setBirthday(rs.getDate("birthday"));
       user.setAge(rs.getInt("age"));
       user.setMarriage(rs.getBoolean("marriage"));
       user.setRole(rs.getString("role"));

       return user;
   }

}
