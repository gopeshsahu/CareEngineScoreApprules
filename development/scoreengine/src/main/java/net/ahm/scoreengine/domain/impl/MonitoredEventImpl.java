package net.ahm.scoreengine.domain.impl;

import net.ahm.careengine.domain.MemberHealthStateType;
import net.ahm.careengine.domain.MonitoredEvent;

/**
 * Implementation of {@link MonitoredEvent} interface.
 * 
 * @author gsahu
 *
 */
public class MonitoredEventImpl extends MemberHealthStateImpl implements MonitoredEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3911888861105008428L;

	@Override
	public MemberHealthStateType getType() {
		return MemberHealthStateType.MonitoredEvent;
	}

	@Override
	public String getFactNamespace() {
		return MonitoredEventImpl.class.getName();
	}
}
