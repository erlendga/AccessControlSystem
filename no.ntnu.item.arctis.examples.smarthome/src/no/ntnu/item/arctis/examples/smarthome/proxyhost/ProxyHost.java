package no.ntnu.item.arctis.examples.smarthome.proxyhost;


import java.util.HashMap;
import java.util.Map;

import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class ProxyHost extends Block {
	
	static ProxyHost instance;

	public static interface MessageListener {
		public void receive(Message message);
	}

	private static Map<String,MessageListener> listeners = new HashMap<String, ProxyHost.MessageListener>();
	public no.ntnu.item.arctis.library.proxies.Message message;
	public no.ntnu.item.arctis.library.proxies.Address local;
	
	public static final void register(String sessionID, MessageListener listener) {
		if(listeners.containsKey(sessionID)) {
			// TODO warn
		}
		System.err.println("register listener on " + sessionID);
		listeners.put(sessionID, listener);
	}
	
	public static final void send(Message message) {
		assert instance!=null;
		assert message.getReceiver()!=null;
		assert message.getSender()!=null;
		System.err.println("send message: " + message.getSignalID());
		instance.sendToBlock("OUTBOUND", message);
	}
	
	public void receive(Message message) {
		System.err.println("route message: " + message.getSignalID());
		MessageListener listener = listeners.get(message.getReceiver().getSessionID());
		if(listener!=null) {
			listener.receive(message);
		} else {
			System.err.println("Message discarded!" + message.getSignalID() + " " + message.getReceiver().getSessionID());
		}
	}

	public static void deregister(String sessionID) {
		listeners.remove(sessionID);
	}

	public boolean isLocal() {
		
		return local.getIP().equals(message.getReceiver().getIP()) && local.getPort().equals(message.getReceiver().getPort());
	}

	public void stop() {
		listeners.clear();
	}
	
	public ProxyHost() {
		instance = this;
	}

}