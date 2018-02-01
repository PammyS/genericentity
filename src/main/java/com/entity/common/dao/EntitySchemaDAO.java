package com.entity.common.dao;

import com.entity.common.model.EntitySchema;

public interface EntitySchemaDAO {
	public String insert(EntitySchema entitySchema);
	public EntitySchema get(String name);
	public EntitySchema delete(String name);
	public EntitySchema update(EntitySchema entitySchema, String name);
}
