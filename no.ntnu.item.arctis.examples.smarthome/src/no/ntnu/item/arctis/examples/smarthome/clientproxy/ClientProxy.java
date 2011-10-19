package no.ntnu.item.arctis.examples.smarthome.clientproxy;


import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.library.proxies.proxyhost.ProxyHost;
import no.ntnu.item.arctis.library.proxies.proxyhost.ProxyHost.MessageListener;
import no.ntnu.item.arctis.runtime.Block;

public class ClientProxy extends Block {

	public no.ntnu.item.arctis.library.proxies.Address remote;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String manualSessionID;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String manualSessionSuffix;
	
	public void send(Message message) {
		assert remote!=null;
		if(message.getReceiver()==null) {
			message.setReceiver(remote);
			}
		Address local = ((Address) getProperty("local-address")).getCopy();
		local.setSessionID(getProxySessionID());
		message.setSender(local);
		ProxyHost.send(message);
	}

	public void subscribe() {
		ProxyHost.register(getProxySessionID(), new MessageListener() {
			public void receive(Message message) {
				sendToBlock("RECEIVE", message);
			}
		});
	}

	public void unsubscribe() {
		ProxyHost.deregister(getProxySessionID());
	}
	
	private String getProxySessionID() {
		if(manualSessionID!=null && manualSessionID.trim().length()>0) {
			return manualSessionID;
		} else if (manualSessionSuffix!=null && manualSessionSuffix.trim().length()>0) {
			return sessionID + "_" + manualSessionSuffix;
		}
		return sessionID + "_" + blockID;
	}

	// Do not edit this constructor.
	public ClientProxy(java.lang.String manualSessionID, java.lang.String manualSessionSuffix) {
	    this.manualSessionID = manualSessionID;
	    this.manualSessionSuffix = manualSessionSuffix;
	}

}