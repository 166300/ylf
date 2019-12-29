package cn.e3mall.service;

import cn.e3mall.pojo.TbItem;
import e3.e3mall.common.poji.EasyUIDataGridResult;
import e3.e3mall.common.util.E3Result;

public interface ItemService {
	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page ,int rows);
	E3Result additem(TbItem item,String desc);
	E3Result deleteItem(long id);
	E3Result instockItem(long l, int make);
	E3Result queryItem(long id);
	E3Result querydesc(long id);
	E3Result updateItem(TbItem tbItem);
}
