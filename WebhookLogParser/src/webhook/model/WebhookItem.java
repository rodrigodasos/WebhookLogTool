package webhook.model;

import java.util.Date;

public class WebhookItem {
	private String clientURL;
	private String responseStatus;
	private Date creationDate; //Data do recebimento do webhook, caso precise filtrar por data
	
	public WebhookItem(String clientUrl, String responseStatus, Date creationDate){
		this.clientURL = clientUrl;
		this.responseStatus = responseStatus;
		this.creationDate = creationDate;
	}

	public String getClientURL() {
		return clientURL;
	}
	public String getResponseStatus() {
		return responseStatus;
	}	
	public Date getCreationDate() {
		return creationDate;
	}		
	
}
