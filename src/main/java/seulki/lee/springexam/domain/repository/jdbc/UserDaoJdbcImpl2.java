package seulki.lee.springexam.domain.repository.jdbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import seulki.lee.springexam.domain.model.User;

import java.util.List;

@Repository("UserDaoJdbcImpl2")
public class UserDaoJdbcImpl2 extends UserDaoJdbcImpl {

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * 사용자 정보 1건 얻기
     * @param userId
     * @return
     */
    @Override
    public User selectOne(String userId) {
        //１건 얻는 SQL
        String sql = "SELECT * FROM m_user WHERE user_id = ?";
        //RowMapper생성
        UserRowMapper rowMapper = new UserRowMapper();
        //SQL실행
        return jdbc.queryForObject(sql, rowMapper, userId);
    }

    /**
     * 전체 사용자 정보 얻기
     * @return
     */
    @Override
    public List<User> selectMany() {
        //M_USER테이블 모든 데이터 검색 SQL
        String sql = "SELECT * FROM m_user";
        //RowMapper생성
        RowMapper<User> rowMapper = new UserRowMapper();
        //SQL실행
        return jdbc.query(sql, rowMapper);
    }
}
