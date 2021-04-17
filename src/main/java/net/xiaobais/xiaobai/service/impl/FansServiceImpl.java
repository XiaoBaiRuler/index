package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.FansMapper;
import net.xiaobais.xiaobai.model.Fans;
import net.xiaobais.xiaobai.model.FansExample;
import net.xiaobais.xiaobai.service.FansService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/12 21:16
 * @Version 1.0
 */
@Service
public class FansServiceImpl implements FansService {

    @Resource
    private FansMapper fansMapper;

    @Override
    public int addFansByUserIdAndFansId(Integer userId, Integer fansId) {
        FansExample example = new FansExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andFansIdEqualTo(fansId);
        List<Fans> fans = fansMapper.selectByExample(example);
        Fans f = null;
        if (fans != null && fans.size() != 0){
            f = fans.get(0);
            f.setUpdateDate(new Date());
            f.setStatus(true);
            return fansMapper.updateByExample(f, example);
        }
        else{
            Fans fan = new Fans();
            fan.setUserId(userId);
            fan.setFansId(fansId);
            fan.setStatus(true);
            fan.setFansDate(new Date());
            fan.setUpdateDate(new Date());
            return fansMapper.insert(fan);
        }
    }

    @Override
    public int deleteFansByUserIdAndfansId(Integer userId, Integer fansId) {
        FansExample example = new FansExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andFansIdEqualTo(fansId);
        List<Fans> fans = fansMapper.selectByExample(example);
        Fans f = null;
        if (fans != null && fans.size() != 0){
            f = fans.get(0);
            f.setUpdateDate(new Date());
            f.setStatus(false);
            return fansMapper.updateByExample(f, example);
        }
        return -1;
    }

    @Override
    public List<Fans> getAllFansByUserId(Integer userId) {
        FansExample example = new FansExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return fansMapper.selectByExample(example);
    }
}
