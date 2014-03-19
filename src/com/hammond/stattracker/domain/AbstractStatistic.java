package com.hammond.stattracker.domain;

public abstract class AbstractStatistic extends AbstractDomainClass{
	
	private static final long serialVersionUID = 1L;
	
	private String statisticType;

	private Integer count;
	
	public AbstractStatistic(String statisticType){
		this.statisticType = statisticType;
	}
	
	
	public String getStatisticType() {
		return statisticType;
	}

	public void setStatisticType(String statisticType) {
		this.statisticType = statisticType;
	}


	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}	
	
}
