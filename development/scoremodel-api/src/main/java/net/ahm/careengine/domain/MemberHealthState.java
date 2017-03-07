package net.ahm.careengine.domain;
import net.ahm.careengine.domain.fact.Fact;

/**
 * Object represents the member health state which is the output of care engine
 * 
 * @author gsahu
 * @version 1.0
 * @created 27-Feb-2017 11:33:44 AM
 */
public interface MemberHealthState extends Fact {

	public MemberHealthStateType getType();

}