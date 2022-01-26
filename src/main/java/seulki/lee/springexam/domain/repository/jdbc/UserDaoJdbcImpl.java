package seulki.lee.springexam.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seulki.lee.springexam.domain.model.User;
import seulki.lee.springexam.domain.repository.UserDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    // 사용자 테이블수 가져옴
    public int count() throws DataAccessException {

        // 모든 건 카운트
        int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
        return count;
        //return 0;
    }

    // User 테이블에 데이터1건 insert.
    @Override
    public int insertOne(User user) throws DataAccessException {
        //１건등록
        int rowNumber = jdbc.update("INSERT INTO m_user(user_id,"
                        + " password,"
                        + " user_name,"
                        + " birthday,"
                        + " age,"
                        + " marriage,"
                        + " role)"
                        + " VALUES(?, ?, ?, ?, ?, ?, ?)",
                user.getUserId(),
                user.getPassword(),
                user.getUserName(),
                user.getBirthday(),
                user.getAge(),
                user.isMarriage(),
                user.getRole());

        return rowNumber;
        //return 0;
    }

    // User 테이블의 데이터 1건 얻음
    @Override
    public User selectOne(String userId) throws DataAccessException {
        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_user"
                + " WHERE user_id = ?", userId);

        // 결과 리턴하기 위한 변수
        User user = new User();

        // 검색된 데이터를 결과 리턴 변수로 설정함
        user.setUserId((String) map.get("user_id")); //사용자ID
        user.setPassword((String) map.get("password")); //비밀번호
        user.setUserName((String) map.get("user_name")); //사용자명
        user.setBirthday((Date) map.get("birthday")); //생년월읠
        user.setAge((Integer) map.get("age")); //나이
        user.setMarriage((Boolean) map.get("marriage")); // 결혼 여부
        user.setRole((String) map.get("role")); // 역할

        return user;
       // return null;
    }

    // User 테이블의 모든 데이터 가져오기
    @Override
    public List<User> selectMany() throws DataAccessException {

        // M_USER 테이블의 모든 데이터 검색
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");
        // 결과 리턴을 위한 변수
        List<User> userList = new ArrayList<>();
        // 취득한 데이터를 결과 리턴용으로 List에 저장
        for (Map<String, Object> map : getList) {

            //User인스턴스 생성
            User user = new User();
            // User인스턴스에 검색된 데이터 설정
            user.setUserId((String) map.get("user_id")); //사용자ID
            user.setPassword((String) map.get("password")); //비밀번호
            user.setUserName((String) map.get("user_name")); //사용자명
            user.setBirthday((Date) map.get("birthday")); //생년월일
            user.setAge((Integer) map.get("age")); //나이
            user.setMarriage((Boolean) map.get("marriage")); //결혼여부
            user.setRole((String) map.get("role")); //역할
            // 결과리턴용 List에 추가
            userList.add(user);
        }
        return userList;
        //return null;
    }

    // User 테이블의 데이터 1건 업데이트
    @Override
    public int updateOne(User user) throws DataAccessException {

        int rowNumber = jdbc.update("UPDATE m_user"
                        + " SET"
                        + " password = ?,"
                        + " user_name = ?,"
                        + " birthday = ?,"
                        + " age = ?,"
                        + " marriage = ?"
                        + " WHERE user_id = ?",
                user.getPassword(),
                user.getUserName(),
                user.getBirthday(),
                user.getAge(),
                user.isMarriage(),
                user.getUserId());

        return rowNumber;

    }

    // User 테이블의 데이터 1건 삭제
    @Override
    public int deleteOne(String userId) throws DataAccessException {

        int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId);

        return rowNumber;
    }

    //SQL검색결과를 서버에 CSV로 저장
    @Override
    public void userCsvOut() throws DataAccessException {

        String sql = "SELECT * FROM m_user";

        UserRowCallbackHandler handler = new UserRowCallbackHandler();

        jdbc.query(sql, handler);

    }

}
