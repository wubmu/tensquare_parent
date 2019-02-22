package com.tensquare.spit.Dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 吴亚斌
 * create : 2019-02-22 13:26
 * description
 */
public interface SpitDao extends MongoRepository<Spit, String> {
    public Page<Spit> findByParetid(String parentid, Pageable pageable);
}
