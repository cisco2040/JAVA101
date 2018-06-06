package com.softtek.javaweb.repository;

public abstract class Repository {
	
	static final int CRUD_CREATE = 1;
	static final int CRUD_READ = 2;
	static final int CRUD_UPDATE = 3;
	static final int CRUD_DELETE = 4;

	public abstract StringBuilder getSQL(final int action);

}
