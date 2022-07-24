package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.User;
import utils.MybatisUtils;

import java.util.List;


public class UserMapperTest {

    @Test
    public void test() {
        // 获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        User user = userDao.selectByID(1);
        System.out.println(user);
        sqlSession.close();
    }


    @Test
    public void test2() {
        // 获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        List<User> user = userDao.getUserList();
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void test3() {
        // 获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        int nums = userDao.addUser(new User(1, "aa", "aa"));

        // 增删改必须提交事务，不然数据没有插入进去
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void test4() {
        // 获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        int nums = userDao.updateUser(new User(1, "bb", "bb"));

        // 增删改必须提交事务，不然数据没有插入进去
        sqlSession.commit();
        sqlSession.close();
    }
}
