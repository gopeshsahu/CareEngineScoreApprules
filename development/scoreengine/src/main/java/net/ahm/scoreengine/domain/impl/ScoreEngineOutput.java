package net.ahm.scoreengine.domain.impl;

import net.ahm.scoreengine.domain.MessageAppendText;
import net.ahm.scoreengine.engine.ScoreEngineOutputIF;

/**
 * Implementation of {@link ScoreEngineOutputIF} interface
 * 
 * @author gsahu
 * @version 1.0
 * @created 27-Feb-2017 11:34:43 AM
 */
public class ScoreEngineOutput implements ScoreEngineOutputIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3095294813569279469L;
	/**
	 * the append text id which can be used to find the
	 * {@link MessageAppendText} object
	 */
	public int appendTextId;

	public boolean isCompliant;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAppendTextId() {
		return appendTextId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAppendTextId(int appendTextId) {
		this.appendTextId = appendTextId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MessageAppendText getMessageAppendText(){
		// TODO implement the method
		return null;
	}

	@Override
	public void setCompliance(boolean isComliance) {

		this.isCompliant = 	isComliance;	
	}

}