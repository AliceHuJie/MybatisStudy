package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.User;
import utils.MybatisUtils;

import java.util.List;


public class UserDaoTest {

    @Test
    public void test() {
        // 获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.selectByID(1);
        System.out.println(user);
        sqlSession.close();
    }


    @Test
    public void test2() {
        // 获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> user = userDao.getUserList();
        System.out.println(user);
        sqlSession.close();
    }
}
