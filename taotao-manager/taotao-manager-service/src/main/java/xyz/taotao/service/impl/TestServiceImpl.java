package xyz.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.taotao.mapper.TestMapper;
import xyz.taotao.service.TestService;

/**
 * Created with IntelliJ IDEA.
 * @Author: yu_zh
 * @DateTime: 2018/07/04 20:43
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper mapper;

    @Override
    public String queryNow() {

        return mapper.queryNow();
    }

}
