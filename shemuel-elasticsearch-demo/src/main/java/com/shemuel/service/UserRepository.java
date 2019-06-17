package com.shemuel.service;

import com.shemuel.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/14 17:18
 * @Description:
 */
public interface UserRepository extends ElasticsearchRepository<User, Integer> {
    List<User> findByCup(String cup);
    List<User> findByUserNameNot(String name);
}
