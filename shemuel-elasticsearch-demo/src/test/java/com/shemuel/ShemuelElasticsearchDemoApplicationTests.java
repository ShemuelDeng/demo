package com.shemuel;

import com.shemuel.model.User;
import com.shemuel.service.UserRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShemuelElasticsearchDemoApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void contextLoads() {
        System.out.println(elasticsearchTemplate);
        User user = new User();
        user.setUserName("yxx");
        user.setId(2);
        user.setCup("c");
        userRepository.index(user);
    }
    @Test
    public void search() {
//        SearchQuery sq = new NativeSearchQueryBuilder().withQuery()
//        userRepository.search();
        Iterable<User> all = userRepository.findAll();
        for (User user: all) {
            System.out.println(user);
        }
//        QueryBuilder queryBuilder = new QueryStringQueryBuilder("");
//        userRepository.search()
        userRepository.findByCup("a").forEach(System.out::print);

    }
    @Test
    public void test1 () {
        userRepository.findByUserNameNot("dsx").forEach(System.out::println);
    }
}
