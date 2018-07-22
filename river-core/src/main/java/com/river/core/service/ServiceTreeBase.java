package com.river.core.service;

import java.util.List;

import com.river.core.dao.IDAOBase;
import com.river.core.entity.TreeEntityBase;

/**
 * 
 * @author Yinovo
 *
 */
public abstract class ServiceTreeBase<D extends IDAOBase<T>,T extends TreeEntityBase> extends ServiceBase<D,T> implements IServiceTreeBase<T>{

	
	public List<T> getChildren(long entityId) {
		// TODO Auto-generated method stub
		List<T> list = getChild(entityId);
		for (T t : list) {
			getChildrens(t);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private void getChildrens(T t) {
		t.setParent(getParent(t.getParentId()));
		List<T> list=getChild(t.getEntityId());
		t.setChildren((List<TreeEntityBase>)list);
		if (list != null && list.size()>0) {
			for (T t2 : list) {
				getChildrens(t2);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.river.core.service.IServiceTreeBase#getChild(long)
	 */
	
	public List<T> getChild(long entityId) {
		// TODO Auto-generated method stub
		return getDao().getChild(entityId);
	}

	/* (non-Javadoc)
	 * @see com.river.core.service.IServiceTreeBase#getParent(long)
	 */
	
	public T getParent(long entityId) {
		// TODO Auto-generated method stub
		return getDao().getParent(entityId);
	}

	public T getTreeEntityById(long entityId) {
		T entity = getById(entityId);
		getChildrens(entity);
		return entity;
	}
	
}
