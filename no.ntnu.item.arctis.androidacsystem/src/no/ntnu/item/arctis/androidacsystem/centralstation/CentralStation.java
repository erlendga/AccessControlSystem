package no.ntnu.item.arctis.androidacsystem.centralstation;

import android.util.Log;
import no.ntnu.item.arctis.library.objects.login.Credentials;
import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class CentralStation extends Block {

	public Message checkMessage(Message msg) {
		if(msg.getPayload() instanceof String){
//			System.out.println(msg.getPayload());
			Log.i("message", msg.getPayload().toString());
			if(msg.getPayload().equals("erlend")){
				Message response = new Message("access"); 
				response.setReceiver(msg.getSender().getCopy());
				response.setSender(msg.getReceiver().getCopy());	
				response.setPayload("un_true");
				return response;
			}
			else return null;
		}
		else{ 
//			System.out.println("not String: " + msg.getPayload());
			Log.i("not string or credential object", msg.getPayload().toString());
			return null;
		}
	}
}
