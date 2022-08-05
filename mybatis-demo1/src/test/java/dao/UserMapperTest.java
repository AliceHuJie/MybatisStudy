package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.User;
import utils.MybatisUtils;

import java.util.HashMap;
import java.util.List;


public class UserMapperTest {

    @Test
    public void testQuery() {
        // 获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        User user = userDao.selectByID(1);
        System.out.println(user);
        sqlSession.close();
    }


    @Test
    public void testQueryAll() {
        // 获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        List<User> user = userDao.getUserList();
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void testAdd() {
        // 获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        int nums = userDao.addUser(new User(2, "aa", "aa"));

        // 增删改必须提交事务，不然数据没有插入进去
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testUpdate() {
        // 获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        int nums = userDao.updateUser(new User(1, "bb", "bb"));

        // 增删改必须提交事务，不然数据没有插入进去
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testMap() {
        // 获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", 1);
        map.put("userName", "bb");
        // 使用map的好处在于key不用严格和对象属性相同，可以任意自定义
        List<User> users = userDao.selectByMap(map);
        System.out.println(users);

        // 增删改必须提交事务，不然数据没有插入进去
        sqlSession.close();
    }

    @Test
    public void testFirstLevelCache() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        User user = userDao.selectByID(11);   // 会查数据库
        User user1 = userDao.selectByID(11);    //走一级缓存
        sqlSession.clearCache();                 // 清缓存
        User user2 = userDao.selectByID(11);     // 查库
        User use3 = userDao.selectByID(11);       // 缓存
        System.out.println(user);
        sqlSession.close();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();
        UserMapper userDao2 = sqlSession2.getMapper(UserMapper.class);
        User user2222 = userDao2.selectByID(11);   // 新的会话，再次走库 （缓存基于sqlsession）
        User user333 = userDao2.selectByID(11);     // 走缓存

        userDao2.updateUser(new User(13, "vv", "bb"));  // 同一个会话内修改其他数据
        User user33 = userDao2.selectByID(11);      // 再查11发现会再查数据库
        sqlSession2.close();
    }
}
