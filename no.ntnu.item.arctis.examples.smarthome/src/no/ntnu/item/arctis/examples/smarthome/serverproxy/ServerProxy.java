package no.ntnu.item.arctis.examples.smarthome.serverproxy;

import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.library.proxies.proxyhost.ProxyHost;
import no.ntnu.item.arctis.library.proxies.proxyhost.ProxyHost.MessageListener;
import no.ntnu.item.arctis.runtime.Block;

public class ServerProxy extends Block {

	public java.lang.String proxySessionID;
	
	public void send(Message message) {
		assert message.getReceiver()!=null;
		Address local = ((Address)getProperty("local-address")).getCopy();
		local.setSessionID(proxySessionID);
		message.setSender(local);
		ProxyHost.send(message);
	}

	public void subscribe() {
		ProxyHost.register(proxySessionID, new MessageListener() {
			public void receive(Message message) {
				sendToBlock("RECEIVE", message);
			}
		});
	}

	public void unsubscribe() {
		ProxyHost.deregister(proxySessionID);
	}

}