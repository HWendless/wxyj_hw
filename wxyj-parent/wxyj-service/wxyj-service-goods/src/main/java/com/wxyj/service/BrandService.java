package com.wxyj.service;


import com.github.pagehelper.PageInfo;
import com.wxyj.goods.pojo.Brand;

import java.util.List;

public interface BrandService {
    /**
     *
     * @return
     */
    List<Brand> findAll();



    Brand findById(Integer id);

    void add(Brand brand);

    void delete (Integer id);

    /**
     * 条件搜索
     * @param brand
     * @return
     */

    List<Brand> findList(Brand brand);


    /***
     * 多条件分页查询
     * @param brand
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Brand brand, int page, int size);


    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(int page, int size);
}
