package com.wxyj.goods.pojo;

import com.wxyj.goods.pojo.Sku;
import com.wxyj.goods.pojo.Spu;

import java.io.Serializable;
import java.util.List;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package package com.wxyj.goods.pojo; *
 * @since 1.0
 */
public class Goods implements Serializable {
    private Spu spu;
    private List<Sku> skuList;

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}
