package webhook.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WebhookModel {

	/*Agrupa e conta as URLs da lista de webhook*/
	public Map<String, Long> groupUrl(List<WebhookItem> listWebhook){
		 Map<String, Long> urlGroup = listWebhook.stream().collect(
	                Collectors.groupingBy(WebhookItem::getClientURL, Collectors.counting()
	                	)
	                );
		 
		 return urlGroup;		 
	}
	
	/*Agrupa, conta e ordena por ordem descrecente as URLs da lista de webhook*/
	public Map<String, Long> groupUrlSorted(List<WebhookItem> listWebhook){
			
		Map<String, Long> urlGroupSorted = new LinkedHashMap<>();

        //Ordenar o resultado do método groupUrl e armazenar no urlGroupSorted
		groupUrl(listWebhook)
			.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> urlGroupSorted.put(e.getKey(), e.getValue()));
		
		return urlGroupSorted;	 
	}
	
	/*Agrupa e conta os Response Status da lista de webhook*/
	public Map<String, Long> groupResponseStatus(List<WebhookItem> listWebhook){
		 Map<String, Long> responseStatusGroup = listWebhook.stream().collect(
	                Collectors.groupingBy(WebhookItem::getResponseStatus, Collectors.counting()
	                	)
	                );		 
		 return responseStatusGroup;
	}
	
}
