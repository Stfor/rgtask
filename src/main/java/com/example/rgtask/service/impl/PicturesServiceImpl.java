package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rgtask.pojo.Pictures;
import com.example.rgtask.mapper.PicturesMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.vo.PicturesVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-11-02
 */
@Service
public class PicturesServiceImpl extends ServiceImpl<PicturesMapper, Pictures> implements PicturesService {
    private PicturesMapper picturesMapper;
    @Autowired
    private void setPicturesMapper(PicturesMapper picturesMapper){
        this.picturesMapper = picturesMapper;
    }
    @Override
    public int insert(PicturesVO picturesVO) {
        Pictures pictures = new Pictures();
        BeanUtils.copyProperties(picturesVO,pictures);
        pictures.setId(UUID.randomUUID().toString());
        pictures.setCreateDate(LocalDateTime.now());
        if (picturesMapper.insert(pictures) > 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public List<String> findPictures(String areaId) {
        QueryWrapper<Pictures> wrapper = new QueryWrapper<>();
        wrapper.eq("area_id",areaId);
        List<String> pictures = new ArrayList<>();
        for (Pictures p : picturesMapper.selectList(wrapper)){
            pictures.add(p.getPicture());
        }
        return pictures;
    }

    @Override
    public int removeByAreaId(String areaId) {
        QueryWrapper<Pictures> wrapper = new QueryWrapper<>();
        wrapper.eq("area_id",areaId);
        return picturesMapper.delete(wrapper);
    }

    @Override
    public boolean insertList(List<String> pictures, String areaId) {
        while (pictures.size() < 2){
            pictures.add("http://43.142.99.39:8080/pictures/202211/3.jpg");
        }
        if (pictures != null){
            for (String picture : pictures){
                Pictures pictures1 = new Pictures(picture,areaId);
                if (picturesMapper.insert(pictures1) <= 0){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<Pictures> getByAreaId(String voteId) {
        QueryWrapper<Pictures> wrapper = new QueryWrapper<>();
        wrapper.eq("area_id",voteId);
        return picturesMapper.selectList(wrapper);
    }

}
