package no.ntnu.item.arctis.examples.smarthome.myspeechservice;

import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class MySpeechService extends Block {

	public String extractText(Message message) {
	
		System.out.println("received message at service side.."+ message);
		return (String) message.getPayload();
	}
}