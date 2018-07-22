package com.river.core.dao;

import com.river.core.entity.EntityBase;
/**
 * 
 * @author Yinovo
 *
 * @param <T>
 */
public interface IDAOBase<T extends EntityBase>
		extends ISelectDAOBase<T>, IUpdateDAOBase<T>, IInsertDAOBase<T>, IDeleteDAOBase<T>,ITreeDAOBase<T> {

}
