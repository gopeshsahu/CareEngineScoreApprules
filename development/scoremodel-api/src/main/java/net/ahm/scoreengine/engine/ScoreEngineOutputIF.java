package net.ahm.scoreengine.engine;
import net.ahm.careengine.command.CommandOutputIF;
import net.ahm.scoreengine.domain.MessageAppendText;

/**
 * Defines the interface to be used by message notification engine
 * 
 * @author gsahu
 * @version 1.0
 * @created 27-Feb-2017 11:35:25 AM
 */
public interface ScoreEngineOutputIF extends CommandOutputIF {

	
	/**
	 * @return the {@link MessageAppendText} produced by the engine.
	 */
	public MessageAppendText getMessageAppendText();


	/**
	 * 
	 * @return the id of the {@link MessageAppendText}
	 */
	public int getAppendTextId();

	/**
	 * Set the append text id so that {@link #getMessageAppendText()} can use it
	 * to retrieve the actual {@link MessageAppendText} object.
	 * 
	 * @param appendTextId
	 */
	public void setAppendTextId(int appendTextId);

}