package seulki.lee.springexam.domain.repository.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import seulki.lee.springexam.domain.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserResultSetExtractor implements ResultSetExtractor<List<User>> {

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        //User 저장용 List
        List<User> userList = new ArrayList<>();

        //얻을 건수 반복처리
        while(rs.next()) {

            //List에 추가할 인스턴스 생성
            User user = new User();

            //검색된 레코드를 User인스턴스로 설정
            user.setUserId(rs.getString("user_id"));
            user.setPassword(rs.getString("password"));
            user.setUserName(rs.getString("user_name"));
            user.setBirthday(rs.getDate("birthday"));
            user.setAge(rs.getInt("age"));
            user.setMarriage(rs.getBoolean("marriage"));
            user.setRole(rs.getString("role"));

            //List에 User추가
            userList.add(user);
        }

        //１건의 데이터도 없을 경우 예외처리
        if(userList.size() == 0) {
            throw new EmptyResultDataAccessException(1);
        }

        return userList;
    }
}
