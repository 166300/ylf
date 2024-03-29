package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbContentExample.Criteria;
import e3.e3mall.common.poji.EasyUIDataGridResult;
import e3.e3mall.common.util.E3Result;

/**
 * 
 * 内容管理
 * @author StaryL
 *
 */
@Service
public class ContentServiceImpl implements ContentService{

		
	@Autowired
	private TbContentMapper contentMapper;
	/*
	 * 
	 * 商品添加(轮播图的添加)
	 * 
	 * */
	@Override
	public E3Result addContent(TbContent content) {
		//将内容数据插入内容表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入数据库
		contentMapper.insert(content);
		return E3Result.ok();
	}
	/*
	 * 
	 * 轮播图查询添加的内容
	 * 
	 * */
	@Override
	public List<TbContent> getContentListByCid(long cid) {
		TbContentExample example=new TbContentExample();
		Criteria createCriteria = example.createCriteria();
		//设置查询条件
		createCriteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<TbContent> selectByExampleWithBLOBs = contentMapper.selectByExampleWithBLOBs(example);
		return selectByExampleWithBLOBs;
	}
	
	
	
	
	
}
