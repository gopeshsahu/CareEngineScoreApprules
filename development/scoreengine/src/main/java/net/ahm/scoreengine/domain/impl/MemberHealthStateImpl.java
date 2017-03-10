package net.ahm.scoreengine.domain.impl;

import java.util.Date;

import net.ahm.careengine.domain.MemberHealthState;
import net.ahm.careengine.domain.impl.fact.AbstractFact;

/**
 * Default implementation of {@link MemberHealthState} interface
 * 
 * @author gsahu
 *
 */
public abstract class MemberHealthStateImpl extends AbstractFact implements MemberHealthState {
	
	private Date creationDate;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	

}
