package com.entity.common.dao;

import com.entity.common.model.GenericEntity;

public interface GenericEntityDAO {
	public String insert(GenericEntity entity);
	public GenericEntity get(String id);
	public GenericEntity delete(String id);
	public String update(GenericEntity genericEntity, String id);
}
