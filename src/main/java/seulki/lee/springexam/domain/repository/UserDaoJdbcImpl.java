package seulki.lee.springexam.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seulki.lee.springexam.domain.model.User;

import java.util.List;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao{
    @Autowired
    JdbcTemplate jdbc;

    // 사용자 테이블 수 가져옴
    public int count() throws DataAccessException {
        return 0;
    }
    // User 테이블에 데이터 1 건 insert.
    @Override
    public int insertOne(User user) throws DataAccessException {
        return 0;
    }
    // User 테이블의 데이터 1 건 얻음
    @Override
    public User selectOne(String userId) throws DataAccessException {
        return null;
    }
    // User 테이블의 모든 데이터 가져오기
    @Override
    public List<User> selectMany() throws DataAccessException {
        return null;
    }
    // User 테이블의 데이터 1 건 업데이트
    @Override
    public int updateOne(User user) throws DataAccessException {
        return 0;
    }
    // User 테이블의 데이터 1 건 삭제
    @Override
    public int deleteOne(String userId) throws DataAccessException {
        return 0;
    }
    //SQL 검색결과를 서버에 CSV 로 저장
    @Override
    public void userCsvOut() throws DataAccessException {
    }

}
