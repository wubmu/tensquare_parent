package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 吴亚斌
 * create : 2019-01-18 18:16
 * description
 */
@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public  Page<Label> pageQuery(Label label,int page,int size) {

        //封装一个分页对象
        PageRequest pageable = PageRequest.of(page-1,size);
        return   labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是封装条件
             * @param cq  查询的关键字，比如group by order by
             * @param cb 封装条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //模糊查询
                List<Predicate> list = new ArrayList<>();
                if (label.getLabelname()!=null&&!"".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (label.getState()!=null&&!"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class),label.getState());
                    list.add(predicate);
                }
                //new集合存放所有条件
                Predicate[] pres = new Predicate[list.size()];
                //把list转成数组
                list.toArray(pres);
                return cb.and(pres);
            }
        },pageable);


    }

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return  labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {


      return   labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是封装条件
             * @param cq  查询的关键字，比如group by order by
             * @param cb 封装条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //模糊查询
                List<Predicate> list = new ArrayList<>();
                if (label.getLabelname()!=null&&!"".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (label.getState()!=null&&!"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class),label.getState());
                    list.add(predicate);
                }
                //new集合存放所有条件
                Predicate[] pres = new Predicate[list.size()];
                //把list转成数组
                list.toArray(pres);
                return cb.and(pres);
            }
        });
    }
}
