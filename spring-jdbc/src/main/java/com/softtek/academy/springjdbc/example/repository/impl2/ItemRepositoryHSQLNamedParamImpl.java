package com.softtek.academy.springjdbc.example.repository.impl2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.academy.springjdbc.example.domain.Item;
import com.softtek.academy.springjdbc.example.repository.ItemRepository;

@Repository
public class ItemRepositoryHSQLNamedParamImpl implements ItemRepository {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public ItemRepositoryHSQLNamedParamImpl(final DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public String getItemDescription(Integer id) {
		return namedParameterJdbcTemplate.queryForObject("SELECT DESCRIPTION FROM ITEM WHERE ID = :id", Collections.singletonMap("id", id), String.class);
	}

	public Double getItemValue(Integer id) {
		return namedParameterJdbcTemplate.queryForObject("SELECT ITEM_VALUE FROM ITEM WHERE ID = :id", Collections.singletonMap("id", id), Double.class);
	}

	public Item getItemById(Integer id) {
		return namedParameterJdbcTemplate.queryForObject("SELECT * FROM ITEM WHERE ID = :id", Collections.singletonMap("id", id), new ItemRowMapper());
	}
	
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		List<Map<String, Object>> results = namedParameterJdbcTemplate.queryForList("SELECT * FROM ITEM", (Map<String,Object>)null);
		
		for (Map<String, Object> rowMap : results) {
			items.add(new Item((Integer)rowMap.get("ID"), (String)rowMap.get("DESCRIPTION"), Double.valueOf(rowMap.get("ITEM_VALUE").toString())));
		}
		
		return items;
	}
	
	public List<Item> getAllItems2() {
		return namedParameterJdbcTemplate.query("SELECT * FROM ITEM", new ItemRowMapper());
	}

	private Map<String, Object> buildItem (final Item item) {
		Map<String, Object> myItem = new HashMap<String, Object>();
	
		myItem.put("id", item.getId());
		myItem.put("desc", item.getDescription());
		myItem.put("value", item.getValue());
		
		return myItem;
	}
	
	public void insertItem(final Item item) {
		Map<String, Object> args = buildItem(item);
		namedParameterJdbcTemplate.update("INSERT INTO ITEM (ID, DESCRIPTION, ITEM_VALUE) VALUES (:id, :desc, :value)", args);
	}
	
	public void updateItem(final Item item) {
		Map<String, Object> args = buildItem(item);
		namedParameterJdbcTemplate.update("UPDATE ITEM SET DESCRIPTION=:desc, ITEM_VALUE=:value WHERE ID=:id", args);
	}
	
	public void removeItem(final Integer itemId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", itemId);		
		namedParameterJdbcTemplate.update("DELETE FROM ITEM WHERE ID=:id", args);
	}

}
