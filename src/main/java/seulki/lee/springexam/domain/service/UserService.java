package seulki.lee.springexam.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import seulki.lee.springexam.domain.model.User;
import seulki.lee.springexam.domain.repository.UserDao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
@Service
public class UserService {
    @Autowired
    //@Qualifier("UserDaoJdbcImpl2")
    //@Qualifier("UserDaoJdbcImpl3")
    @Qualifier("UserDaoJdbcImpl4")
    UserDao dao;

    public boolean insert(User user) {
        // insert실행
        int rowNumber = dao.insertOne(user);
        // 처리확인용
        boolean result = false;

        if (rowNumber > 0) {
            // insert성공
            result = true;
        }
        return result;
    }

    // 카운트메소드
    public int count() {
        return dao.count();
    }

    //모든 건수 얻는 메소드
    public List<User> selectMany() {
        // 전체건수 얻음
        return dao.selectMany();
    }

    // 1건의 데이터 얻는 메소드
    public User selectOne(String userId) {
        return dao.selectOne(userId);
    }

    public boolean updateOne(User user) {

        // 구분변수
        boolean result = false;
        // １건 변경
        int rowNumber = dao.updateOne(user);
        if (rowNumber > 0) {
            // update 성공
            result = true;
        }

        return result;
    }

    public boolean deleteOne(String userId) {

        // １건 삭제
        int rowNumber = dao.deleteOne(userId);

        // 판정용 변수
        boolean result = false;

        if (rowNumber > 0) {
            // delete성공
            result = true;
        }
        return result;
    }

    // 사용자 목록 CSV로 출력
    public void userCsvOut() throws DataAccessException {
        // CSV출력
        dao.userCsvOut();
    }

    // 서버에 저장된 파일을 가져와서 bvte배열로 변환
    public byte[] getFile(String fileName) throws IOException {

        // 파일시스템(기본값) 얻기
        FileSystem fs = FileSystems.getDefault();

        // 파일 검색
        Path p = fs.getPath(fileName);

        Charset charset = Charset.forName("UTF-8");
        // 파일을 byte배열로 변환

        byte[] bytes = Files.readAllBytes(p);

        return bytes;
    }
}
