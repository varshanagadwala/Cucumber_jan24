package pages;

import base.ControlAction;

public class CommonPage extends ControlAction {

	public String getCurrentPageURL() {
		return driver.getCurrentUrl();
	}
}
