package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;
import e3.e3mall.common.poji.EasyUITreeNode;
import e3.e3mall.common.util.E3Result;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		//根据parentid查询子节点列表
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria create = example.createCriteria();
		//设置查询条件
		create.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);
		//转换成EasyUITreeNode列表
		List<EasyUITreeNode> noList=new ArrayList<>();
		for (TbContentCategory contentCategory : catList) {
			EasyUITreeNode node=new EasyUITreeNode();
			node.setId(contentCategory.getId());
			node.setText(contentCategory.getName());
			node.setState(contentCategory.getIsParent()?"closed":"open");
			//添加到列表
			noList.add(node);
		}
		return noList;
	}
	@Override
	public E3Result addContentCategory(long parentId, String name) {
		//创建一个tb_content_category表对应的pojo对象
		TbContentCategory category=new TbContentCategory();
		//设置pojo的属性
		category.setParentId(parentId);
		category.setName(name);
		//1正常  2删除
		category.setStatus(1);
		//默认排序是1
		category.setSortOrder(1);
		//新添加的节点一定是叶子节点
		category.setIsParent(false);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		//插入数据库
		int insert = contentCategoryMapper.insert(category);
		//判断父节点的isparent属性。如果不是true改为true
		//根据parentId查询父节点
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parent.getIsParent()){
			parent.setIsParent(true);
			//跟新数据库
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		//返回结果返回E3Result,包含pojo
		return E3Result.ok(category);
	}
	

}
