package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.IdleGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.IdleGoodsPageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
public interface IdleGoodsService extends IService<IdleGoods> {
    int insert(IdleGoods idleGoods);

    boolean modify(IdleGoods idleGoods);

    /*boolean removeAllById(String id);*/


    IPage<IdleGoods> findPage(Page<IdleGoods> page, IdleGoodsPageVO idleGoods);


}
