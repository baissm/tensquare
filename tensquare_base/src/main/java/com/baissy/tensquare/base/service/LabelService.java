package com.baissy.tensquare.base.service;

import com.baissy.tensquare.base.dao.LabelDao;
import com.baissy.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlin
 * @create 2020/5/1/1:57
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private util.IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
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
        return labelDao.findAll(new Specification<Label>() {
            /**
             * 内部类实现重写其中的方法
             * @param root 根对象，也就是把条件封装到那个对象中 where 类名=label.getis
             * @param criteriaQuery 封装的都是查询关键字，比如 group by order by等
             * @param criteriaBuilder 封装条件对象的
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(label.getLabelname()!=null&&!"".equals(label.getLabelname())) {
                    // where labelname like %***%
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if(label.getState()!=null&&!"".equals(label.getState())) {
                    // state = *
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }

                Predicate[] predicatesArray=new Predicate[list.size()];
                predicatesArray = list.toArray(predicatesArray);
                return criteriaBuilder.and(predicatesArray);
            }
        });
    }

    public Page pageQuery(Label label, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             * 内部类实现重写其中的方法
             *
             * @param root            根对象，也就是把条件封装到那个对象中 where 类名=label.getis
             * @param criteriaQuery   封装的都是查询关键字，比如 group by order by等
             * @param criteriaBuilder 封装条件对象的
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    // where labelname like %***%
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (label.getState() != null && !"".equals(label.getState())) {
                    // state = *
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }

                Predicate[] predicatesArray = new Predicate[list.size()];
                predicatesArray = list.toArray(predicatesArray);
                return criteriaBuilder.and(predicatesArray);
            }
        }, pageable);
    }
}
