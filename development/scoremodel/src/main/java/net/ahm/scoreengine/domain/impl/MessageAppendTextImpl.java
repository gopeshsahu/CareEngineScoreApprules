package net.ahm.scoreengine.domain.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.ahm.scoreengine.domain.MessageAppendText;

/**
 * Implement {@link MessageAppendText} interface
 * 
 * @author gsahu
 *
 */
public class MessageAppendTextImpl implements MessageAppendText {
	/**
	 * identifier of the {@link MessageAppendText}
	 */
	private int id;

	/**
	 * {@link Map} maintains the {@link Locale} to {@link String} pairs
	 */
	private Map<Locale, String> localeBasedMessages = new HashMap<Locale, String>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * Set the identifier of {@link MessageAppendText}
	 * 
	 * @param id
	 *            identifier
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((localeBasedMessages == null) ? 0 : localeBasedMessages.hashCode());
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
		MessageAppendTextImpl other = (MessageAppendTextImpl) obj;
		if (id != other.id)
			return false;
		if (localeBasedMessages == null) {
			if (other.localeBasedMessages != null)
				return false;
		} else if (!localeBasedMessages.equals(other.localeBasedMessages))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage(Locale locale) {
		return localeBasedMessages.get(locale);
	}

	/**
	 * Add additional message for the specified {@link Locale}
	 * 
	 * @param locale
	 *            the {@link Locale} the message is for.
	 * @param message
	 *            the {@link String} message of this <code>locale</code>
	 */
	public void addMessage(Locale locale, String message) {
		this.localeBasedMessages.put(locale, message);
	}

	/**
	 * 
	 * @return all the {@link Locale} based {@link MessageAppendText} in
	 *         {@link String} format.
	 */
	public Map<Locale, String> getLocaleBasedMessages() {
		return localeBasedMessages;
	}

	/**
	 * Reset the {@link Locale} based messages
	 * 
	 * @param localeBasedMessages
	 *            the {@link Map} contains the new items to be used.
	 */
	public void setLocaleBasedMessages(Map<Locale, String> localeBasedMessages) {
		this.localeBasedMessages = localeBasedMessages;
	}

}
