package dao;

import pojo.Cache;

public interface CacheMapper {
    Cache selectByID(int id);

    int update(Cache cache);

    int add(Cache cache);
}
