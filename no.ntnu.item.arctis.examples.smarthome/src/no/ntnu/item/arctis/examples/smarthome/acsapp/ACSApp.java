package no.ntnu.item.arctis.examples.smarthome.acsapp;

import no.ntnu.item.arctis.runtime.Block;

public class ACSApp extends Block {

		public String cast(int in) {
			return String.valueOf(in);
		}

		public void fail(boolean b) {
		}

		public void success(boolean b) {
		}

		public void panelclose() {
			System.out.println("panel is closed..");
		}
}