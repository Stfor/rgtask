package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Errand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.pojo.User;
import com.example.rgtask.vo.ErrandPageVO;
import com.example.rgtask.vo.ErrandReturnVO;
import com.example.rgtask.vo.ErrandVO;
import com.example.rgtask.vo.UserPageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
public interface ErrandService extends IService<Errand> {
    int insert(ErrandVO errandVO);

    IPage<ErrandReturnVO> findPage(ErrandPageVO pageVO);

    int updateAllById(ErrandVO errandVO);

    Boolean removeAllById(String errandId);

    Boolean receive(Errand errand);
}
