package com.wxyj.controller;

import com.wxyj.goods.pojo.Brand;
import com.wxyj.goods.pojo.StockBack;
import com.wxyj.service.BrandService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin //跨域
public class BrandController {
    @Autowired
    BrandService brandService;

    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand) ;
        return  new Result< Brand>(true, StatusCode.OK,"根据id查询品牌成功");

    }

    @GetMapping( value = "/{id }")
    public Result< Brand> findById(@PathVariable(value = "id") Integer id) {
        return  new Result< Brand>(true, StatusCode.OK,"根据id查询品牌成功",brandService.findById(id));

    }

    @GetMapping
    public Result<List<Brand>> findAll() {
        return  new Result<List<Brand>>(true, StatusCode.OK,"查询品牌集合成功",brandService.findAll());

    }

    @DeleteMapping( value = "/{id}")
    public Result delete(@PathVariable Integer id)
    {
        return  new Result(true,StatusCode.OK,"删除成功");
    }





}
