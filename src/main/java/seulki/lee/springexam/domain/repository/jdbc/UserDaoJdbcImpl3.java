package seulki.lee.springexam.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import seulki.lee.springexam.domain.model.User;


import java.util.List;

@Repository("UserDaoJdbcImpl3")
public class UserDaoJdbcImpl3 extends UserDaoJdbcImpl {

    @Autowired
    private JdbcTemplate jdbc;

    // 사용자정보 1건
    @Override
    public User selectOne(String userId) {

        //１건 출력 SQL
        String sql = "SELECT * FROM m_user WHERE user_id = ?";

        //RowMapper 생성
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

        //SQL 실행
        return jdbc.queryForObject(sql, rowMapper, userId);
    }

    //사용자정보 전체
    @Override
    public List<User> selectMany() {

        String sql = "SELECT * FROM m_user";

        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

        return jdbc.query(sql, rowMapper);
    }
}
