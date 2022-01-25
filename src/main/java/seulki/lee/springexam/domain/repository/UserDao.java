package seulki.lee.springexam.domain.repository;

import org.springframework.dao.DataAccessException;
import seulki.lee.springexam.domain.model.User;

import javax.xml.crypto.Data;
import java.util.List;

public interface UserDao {
    // user 테이블 수 가져옴
    public int count() throws DataAccessException;
    // user 테이블의 데이터를 1 건 insert
    public int insertOne(User user) throws DataAccessException;
    // user 테이블의 데이터를 1건 얻기
    public User selectOne(String userId) throws DataAccessException;
    // user 테이블의 모든 데이터 가져옴
    public List<User> selectMany() throws DataAccessException;
    // user 테이블의 한건 업데이트
    public int updateOne(User user) throws DataAccessException;
    // user 테이블 삭제
    public int deleteOne(String userId) throws DataAccessException;
    // SQL 검색결과를 서버에 CSV 로 저장
    public void userCsvOut() throws DataAccessException;
}
