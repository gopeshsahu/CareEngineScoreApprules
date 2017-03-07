package net.ahm.scoreengine.domain;

import java.util.Collection;

/**
 * Represent all the messages configured
 * @version 1.0
 * @created 27-Feb-2017 11:33:24 AM
 */
public interface AppendableMessages {
	/**
	 * @return all the {@link MessageAppendText} available to the application
	 */
	public Collection<MessageAppendText> getAppendableMessages();
}