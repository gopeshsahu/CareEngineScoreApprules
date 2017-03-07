package net.ahm.scoreengine.domain.impl;

import java.util.Collection;
import java.util.HashSet;

import net.ahm.scoreengine.domain.AppendableMessages;
import net.ahm.scoreengine.domain.MessageAppendText;

/**
 * Implement {@link AppendableMessages} interface
 * 
 * @author gsahu
 *
 */
public class AppendableMessagesImpl implements AppendableMessages {

	private Collection<MessageAppendText> appendableMessages = new HashSet<MessageAppendText>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<MessageAppendText> getAppendableMessages() {
		return appendableMessages;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appendableMessages == null) ? 0 : appendableMessages.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppendableMessagesImpl other = (AppendableMessagesImpl) obj;
		if (appendableMessages == null) {
			if (other.appendableMessages != null)
				return false;
		} else if (!appendableMessages.equals(other.appendableMessages))
			return false;
		return true;
	}



	/**
	 * Reset the messages
	 * 
	 * @param appendableMessages
	 */
	public void setAppendableMessages(Collection<MessageAppendText> appendableMessages) {
		this.appendableMessages = appendableMessages;
	}

}
