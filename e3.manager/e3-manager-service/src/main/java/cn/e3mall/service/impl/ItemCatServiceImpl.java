package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.service.ItemCatService;
import e3.e3mall.common.poji.EasyUITreeNode;

/**
 * 
 * 商品分类管理(新增商品的选择类目)
 * @author StaryL
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	
	@Override
	public List<EasyUITreeNode> getItemCatlist(long parentId) {
		//根据子节点列表的ID-->parentId查询子节点列表
		TbItemCatExample example=new TbItemCatExample();
		Criteria createCriteria = example.createCriteria();
		//设置查询条件
		createCriteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//创建返回结果的list
		List<EasyUITreeNode> reList = new ArrayList<>();
		//把列表转换成EasyUITreeNoce列表
		for (TbItemCat cat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			//设置属性
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent()?"closed":"open");
			//添加到结果列表
			reList.add(node);
		}
		//返回结果
		return reList;
	}

}
