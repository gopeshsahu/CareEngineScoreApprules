package net.ahm.scoreengine.engine;
import java.util.Collection;
import java.util.List;

import net.ahm.careengine.command.CommandContextIF;
import net.ahm.careengine.domain.MemberHealthState;
import net.ahm.careengine.domain.member.MemberInfo;

/**
 * Defines the input object to be used by the engine
 * 
 * @author gsahu
 * @version 1.0
 * @created 27-Feb-2017 11:35:31 AM
 */
public interface ScoreEngingInputContextIF extends CommandContextIF {

	/**
	 * @return a {@link Collection} of {@link MemberHealthState} to be used by
	 *         the engine
	 */
	public Collection<MemberHealthState> getMemberHealthStates();

	/**
	 * @return {@link MemberInfo} object to be used by the engine
	 */
	public MemberInfo getMemberInfo();	
	
		
	public List<String> getMonitoredEventIds();
	public void setMonitoredEventIds(List<String> monitoredEventIds);

}