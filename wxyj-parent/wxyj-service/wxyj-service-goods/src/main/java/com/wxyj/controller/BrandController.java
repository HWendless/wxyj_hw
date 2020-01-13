package com.wxyj.controller;

import com.github.pagehelper.PageInfo;
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


    /***
     * 分页搜索实现
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) Brand brand, @PathVariable  int page, @PathVariable  int size){
        //执行搜索
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }


    /***
     * 分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //分页查询
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }





}
