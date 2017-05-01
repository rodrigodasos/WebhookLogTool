package webhook.logtool;
import java.util.List;
import webhook.model.*;
import java.util.Map;

public class Main {

	/**
	 * Faz o parser de um arquivo de log com webhooks recebidos, contendo a URL do endereço do cliente 
	 * e também o Response Status recebido, agrupando as chamadas de mesma URL e mesmo Response Status. 
	 * Lista as 3 URLs mais executadas com as quantidades, 
	 * e também lista todos os Response Status e Quantidades recebidos
	 */
	public static void main(String[] args) {
		WebhookLogParser logParser = new WebhookLogParser();
		WebhookModel webhook = new WebhookModel();
		
		try {
			//Alterar para o caminho do Log no seu computador
			String pathLog = "C:\\Temp\\log\\logWebhook.txt";
			
			//Faz o parse das entradas webhook do log, para uma lista de objetos WebHook
			List<WebhookItem> listWebhook = logParser.parserWebhookLog(pathLog); 
			
			//Agrupa e conta as URLs do cliente webhook
			Map<String, Long> urls = webhook.groupUrlSorted(listWebhook);
			
			System.out.println("URLs mais chamadas: ");
			
			int i = 0;
			for (Map.Entry<String, Long> url : urls.entrySet())
			{
				if (i==3) break; //Retorna só os 3 maiores URLs chamadas
				
			    System.out.println("URL: " + url.getKey() + " - Qtde: " + url.getValue());
			    
			    i+=1;
			}
						
			//Agrupa e conta os Response Status recebidos nos webhooks
			Map<String, Long> listResponse = webhook.groupResponseStatus(listWebhook);
			
			System.out.println("Quantidade de chamadas por Response Status: ");
			
			for (Map.Entry<String, Long> responseStatus : listResponse.entrySet())
			{
			    System.out.println("ResponseStatus: " + responseStatus.getKey() + " - Qtde: " + responseStatus.getValue());
			}
			
			
		} catch (Exception ex) {
			System.out.println("Ocorreu um erro ao processar o arquivo de log: " + ex.getMessage() + " - StackTrace: " + ex.getStackTrace());
		}		
		
	}

}
