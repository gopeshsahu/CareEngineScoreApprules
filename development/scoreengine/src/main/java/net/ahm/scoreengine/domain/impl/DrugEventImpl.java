package net.ahm.scoreengine.domain.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import net.ahm.careengine.domain.DrugEvent;

public abstract class DrugEventImpl implements DrugEvent{
	
	private List<Integer> elementIds;
	private Date serviceDate;

	public DrugEventImpl(List<Integer> elementIds, Date serviceDate) {
		super();
		this.elementIds = elementIds;
		this.serviceDate = serviceDate;
	}

	public List<Integer> getElementIds() {
		return elementIds;
	}

	public void setElementIds(List<Integer> elementIds) {
		this.elementIds = elementIds;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	@Override
	public String getFactNamespace() {
		return DrugEventImpl.class.getName();
	}
	
	@Override
	public String toString() {

		StringBuilder str = new StringBuilder();

		for (Integer integer : elementIds) {

			str.append("elementId :: " + integer + "\t");
		}

		return str.toString();
	}


}
