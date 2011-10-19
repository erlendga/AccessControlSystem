package no.ntnu.item.arctis.examples.smarthome.myspeechserviceproxy;

import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class MySpeechServiceProxy extends Block {

	public Message createSpeechMessage(String text) {
		Message m = new Message("speak");
		m.setPayload(text);
		return m;
	}

	public void found() {
	}

	public void notfound() {
		log("Could not find speech service.");
	}
}