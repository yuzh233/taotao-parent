package xyz.taotao.content.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.taotao.content.service.ContentService;
import xyz.taotao.mapper.TbContentMapper;
import xyz.taotao.pojo.TaotaoResult;
import xyz.taotao.pojo.TbContent;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yu_zh
 * @DateTime: 2018/07/08 16:14
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper mapper;

    @Override
    public TaotaoResult saveContent(TbContent content) {
        //1.注入mapper

        //2.补全其他的属性
        content.setCreated(new Date());
        content.setUpdated(content.getCreated());
        //3.插入内容表中
        mapper.insertSelective(content);
        return TaotaoResult.ok();
    }
}
