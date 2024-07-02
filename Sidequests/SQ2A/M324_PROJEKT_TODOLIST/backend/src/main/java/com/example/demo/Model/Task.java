package com.example.demo.Model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Task")
public class Task {

	// Properties
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int taskId;

	@NonNull
	private String taskDescription; // must have the EXACT name as his React state property and may not be ignored!

	// Default Constructor
	public Task(){
		
	}

	// Constructor ohne ID
	public Task(String description){
		this.taskDescription = description;
	}

	// Constructor mit allen Props
	public Task(int id, String description){
		this.taskId = id;
		this.taskDescription = description;
	}

	// Getter
	public int getTaskId(){
		return this.taskId;
	}

	public String getTaskDescription(){
		return this.taskDescription;
	}

	// Setter
	public void setTaskId(int id){
		this.taskId = id;
	}

	public void setTaskDescription(String description){
		this.taskDescription = description;
	}
}