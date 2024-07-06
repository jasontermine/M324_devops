package com.example.demo.Model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representiert eine Aufgabe, die in der Todo-Liste angezeigt wird.
 
 */
@Entity
@Table(name = "Task")
public class Task {

	/**
	 * Die ID der Aufgabe.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int taskId;

	/**
	 * Die Beschreibung der Aufgabe.
	 */
	@NonNull
	private String taskDescription; // must have the EXACT name as his React state property and may not be ignored!

	/**
	 * Standardkonstruktor.
	 */
	public Task(){
		
	}

	/**
	 * Konstruktor mit Beschreibung.
	 * @param description
	 */
	public Task(String description){
		this.taskDescription = description;
	}

	/**
	 * Konstruktor mit ID und Beschreibung.
	 * @param id
	 * @param description
	 */
	public Task(int id, String description){
		this.taskId = id;
		this.taskDescription = description;
	}

	/**
	 * Gibt die ID der Aufgabe zurück.
	 * @return
	 */
	public int getTaskId(){
		return this.taskId;
	}

	/**
	 * Gibt die Beschreibung der Aufgabe zurück.
	 * @return
	 */
	public String getTaskDescription(){
		return this.taskDescription;
	}

	/**
	 * Setzt die ID der Aufgabe.
	 * @param id
	 */
	public void setTaskId(int id){
		this.taskId = id;
	}

	/**
	 * Setzt die Beschreibung der Aufgabe.
	 * @param description
	 */
	public void setTaskDescription(String description){
		this.taskDescription = description;
	}
}