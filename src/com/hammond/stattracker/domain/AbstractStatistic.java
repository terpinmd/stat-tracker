package com.hammond.stattracker.domain;

public abstract class AbstractStatistic {

	private int count = 0;
	
	public void increment(){
		count++;
	}
	
	public void decrement(){
		count--;
	}	
	
	public void reset(){
		count = 0;
	}
	
	public int getCount(){
		return count;
	}
	
}
