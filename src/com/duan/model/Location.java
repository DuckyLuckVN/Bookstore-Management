package com.duan.model;

public class Location 
{

	private String id;
	private String locationName;
	private int maxStorage;
	private String description;
	
	public Location()
	{
		
	}
	
	public Location(String id, String locationName, int maxStorage, String description) {
		super();
		this.id = id;
		this.locationName = locationName;
		this.maxStorage = maxStorage;
		this.description = description;
	}

	public String getId() 
	{
		return id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getLocationName() 
	{
		return locationName;
	}
	
	public void setLocationName(String locationName) 
	{
		this.locationName = locationName;
	}
	
	public int getMaxStorage() 
	{
		return maxStorage;
	}
	
	public void setMaxStorage(int maxStorage) 
	{
		this.maxStorage = maxStorage;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
