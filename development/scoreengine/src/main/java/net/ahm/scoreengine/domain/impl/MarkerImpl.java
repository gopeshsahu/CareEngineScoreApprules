package net.ahm.scoreengine.domain.impl;

import net.ahm.careengine.domain.Marker;
import net.ahm.careengine.domain.MemberHealthStateType;

/**
 * Implement {@link Marker} interface.
 * 
 * @author gsahu
 *
 */
public class MarkerImpl extends MemberHealthStateImpl implements Marker {

	/**
	 * 
	 */
	private static final long serialVersionUID = 474374031892408030L;

	@Override
	public MemberHealthStateType getType() {
		return MemberHealthStateType.Mark;
	}

	@Override
	public String getFactNamespace() {
		return MarkerImpl.class.getName();
	}
}
