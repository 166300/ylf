package cn.e3mall.service;

import java.util.List;

import e3.e3mall.common.poji.EasyUITreeNode;

public interface ItemCatService {
	List<EasyUITreeNode> getItemCatlist(long parentId);

}
