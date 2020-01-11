package com.wxyj.service;


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
}
