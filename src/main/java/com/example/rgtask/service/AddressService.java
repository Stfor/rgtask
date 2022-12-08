package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rgtask.pojo.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.AddressPageVO;
import com.example.rgtask.vo.AddressVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
public interface AddressService extends IService<Address> {
    Boolean insert(AddressVO addressVO);

    Boolean updateAllById(AddressVO addressVO);

    IPage<Address> findPage(AddressPageVO pageVO);

}
