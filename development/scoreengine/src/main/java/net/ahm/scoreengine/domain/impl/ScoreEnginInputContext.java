package net.ahm.scoreengine.domain.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.ahm.careengine.domain.DrugEvent;
import net.ahm.careengine.domain.MemberHealthState;
import net.ahm.careengine.domain.member.MemberInfo;
import net.ahm.scoreengine.engine.ScoreEngingInputContextIF;

/**
 * Implementation of {@link ScoreEngingInputContextIF} interface
 * 
 * @author gsahu
 * @version 1.0
 * @created 27-Feb-2017 11:34:35 AM
 */
public class ScoreEnginInputContext implements ScoreEngingInputContextIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7210178907511349818L;

	/**
	 * All {@link MemberHealthState} the member has
	 */
	private Collection<MemberHealthState> memberHealthStates = new HashSet<MemberHealthState>();

	/**
	 * object represents the member
	 */
	private MemberInfo memberInfo;


	/**
	 * {@inheritDoc}
	 */
	public Collection<MemberHealthState> getMemberHealthStates(){
		return memberHealthStates;
	}

	/**
	 * {@inheritDoc}
	 */
	public MemberInfo getMemberInfo(){
		return memberInfo;
	}	
	
	public List<String> getMonitoredEventIds() {
		return monitoredEventIds;
	}

	public void setMonitoredEventIds(List<String> monitoredEventIds) {
		this.monitoredEventIds = monitoredEventIds;
	}

	private List <String> monitoredEventIds;


	@Override
	public DrugEvent getDrugEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}