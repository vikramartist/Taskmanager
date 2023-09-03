package com.uis.taskmanagerapp.controlmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.uis.taskmanagerapp.util.TaskUtility;

public class TaskBean implements Comparable<Object>,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -126682544149839681L;
	
	private String taskName;
	private String description;
	private Date plannedDate;
	private int priority;
	private String tags;
	
	public TaskBean() {
		
	}
	
	public TaskBean(String taskName, String description, Date plannedDate, int priority, String tags) {
		super();
		if(TaskUtility.isValidTaskName(taskName)) 
		{
			this.taskName = taskName;
		}else 
		{
			throw new IllegalArgumentException("Taskname "+taskName+" is Invalid!!");
		}
		
		if(TaskUtility.isValidDescription(description)) 
		{
			this.description = description;
		}else 
		{
			throw new IllegalArgumentException("Description "+description+" is Invalid");
		}
		
		if(TaskUtility.validateDate(plannedDate)!=null) 
		{
			this.plannedDate=plannedDate;
			
		}else 
		{
			throw new NullPointerException("Date has to be instantiated, it cannot be null");
		}
		
		if(priority>=0 && priority<=10) {
			this.priority = priority;
		}
		else
		{
			throw new IllegalArgumentException("Priority has to be within the range(1-10)");
		}
		
		if(tags!=null && !tags.trim().isBlank()) 
		{
			this.tags = tags;
		}else
		{
			throw new IllegalArgumentException("Tags must not be empty or null!!!");
		}
	}
	
	public String getTaskName() 
	{
		return taskName;
	}

	public void setTaskName(String taskName) 
	{
		if(TaskUtility.isValidTaskName(taskName)) 
		{
			this.taskName = taskName;
		}else 
		{
			throw new IllegalArgumentException("Taskname "+taskName+" is Invalid!!");
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(TaskUtility.isValidDescription(description)) 
		{
			this.description = description;
		}else 
		{
			throw new IllegalArgumentException("Description "+description+" is Invalid");
		}
	}

	public Date getPlannedDate() {
		return plannedDate;
	}

	public void setPlannedDate(Date plannedDate) {
		if(TaskUtility.validateDate(plannedDate)!=null) 
		{
			this.plannedDate=plannedDate;
			
		}else 
		{
			throw new NullPointerException("Date has to be instantiated, it cannot be null");
		}
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		if(priority>=0 && priority<=10) {
			this.priority = priority;
		}
		else
		{
			throw new IllegalArgumentException("Priority has to be within the range(1-10)");
		}
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		if(tags!=null && !tags.trim().isBlank()) 
		{
			this.tags = tags;
		}else
		{
			throw new IllegalArgumentException("Tags must not be empty or null!!!");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, plannedDate, priority, tags, taskName);
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskBean other = (TaskBean) obj;
		return Objects.equals(description, other.description) && Objects.equals(plannedDate, other.plannedDate)
				&& priority == other.priority && Objects.equals(tags, other.tags)
				&& Objects.equals(taskName, other.taskName);
	}
	
	@Override
	public String toString() 
	{
		return "TaskBean [taskName=" + taskName + ", description=" + description + ", plannedDate=" + plannedDate
				+ ", priority=" + priority + ", tags=" + tags + "]\n";
	}

	@Override
	public int compareTo(Object o) 
	{
		if(o instanceof Date) 
		{
			Date dt = (Date)o;
			return dt.compareTo(getPlannedDate());
		}
		return 0;
		
	}
	
	
	
}
