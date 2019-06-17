package com.shemuel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/14 17:14
 * @Description:
 */
public class EsService {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    public void save() {
//        elasticsearchTemplate.
        System.out.println(elasticsearchTemplate);
    }
}
