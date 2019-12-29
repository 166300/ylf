package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;
import e3.e3mall.common.poji.EasyUIDataGridResult;
import e3.e3mall.common.util.E3Result;
import e3.e3mall.common.util.IDUtils;
/**
 * @author StaryL
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	
	@Override
	public TbItem getItemById(long itemId) {
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		return tbItem;
	}
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example=new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
 		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果信息
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}
	@Override
	public E3Result additem(TbItem item, String desc) {
		//生成一个商品ID(时间+随机数)
		long itemId = IDUtils.genItemId();
		//补全item的属性
		item.setId(itemId);
		//1-正常  2-下架  3-删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//项商品表插入数据
		itemMapper.insert(item);
		//创建一个商品描述对应的pojo对象
		TbItemDesc itemDesc=new TbItemDesc();
		//补全属性(商品描述对应的pojo对象)
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		//返回成功
		return E3Result.ok();
	}
	/**
	 * 
	 * 商品删除
	 * 
	 * 
	 */
	@Override
	public E3Result deleteItem(long id) {
		itemMapper.deleteByPrimaryKey(id);
		return E3Result.ok();
	}
	/**
	 * 
	 * 商品上下架
	 * 
	 * 
	 */
	@Override
	public E3Result instockItem(long l,int make) {
		//创建商品表
		TbItem record =new TbItem();
		//得到id
		record.setId(l);
		if(make==0){
			//上架状态
			record.setStatus((byte) 1);
		}if(make==1){
			//下架状态
			record.setStatus((byte) 2);
		}
		
		itemMapper.updateByPrimaryKeySelective(record );
		return E3Result.ok();
	}
	
	/**
	 * 
	 * 商品回显
	 * 
	 */
	@Override
	public E3Result queryItem(long id) {
		TbItem tbItem = itemMapper.selectByPrimaryKey(id);
		return E3Result.ok(tbItem);
	}
	@Override
	public E3Result querydesc(long id) {
		TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(id);
		return E3Result.ok(tbItemDesc);
		
	}
	@Override
	public E3Result updateItem(TbItem tbItem) {
		itemMapper.updateByPrimaryKey(tbItem);
		return E3Result.ok();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
