package webhook.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class WebhookLogParser  {
		
	/*Faz o parser de um arquivo de log de webhooks para uma lista de objetos Webhook*/
	public List<WebhookItem> parserWebhookLog(String pathFile) throws IOException{
		List<WebhookItem> listWebhook = new ArrayList<WebhookItem>();
		
		/*Leitura de arquivo de log grande, 
		processando linha a linha para não consumir muita memória*/
		FileInputStream inputStream = null;
		Scanner scan = null;
		try {
		    inputStream = new FileInputStream(pathFile);
		    scan = new Scanner(inputStream, "UTF-8");
		    while (scan.hasNextLine()) {
		        String logLine = scan.nextLine();
		        
		        //Parser de cada linha do Log
		        if (validateLine(logLine)){
		        	WebhookItem item = parseItemLog(logLine);		        	
		        	if (item != null) listWebhook.add(item);
		        }				
		    }

		    if (scan.ioException() != null) throw scan.ioException();
		    
		} finally {
		    if (inputStream != null) inputStream.close();		    
		    if (scan != null) scan.close();
		}

		return listWebhook;
	}	
	
	/*Faz o Parse da linha do log para um objeto WebhookItem*/
	private WebhookItem parseItemLog(String line){
		int posIni, posEnd = 0;
		String url ="", responseStatus = "";
		
		posIni = line.indexOf(" request_to=");
		if (posIni > 0) {
			posIni += 13;
			posEnd  = line.indexOf('"', posIni);
			url = line.substring(posIni, posEnd).toLowerCase(); 
		}
		
		posIni = line.indexOf(" response_status=");
		if (posIni > 0) {
			posIni += 18;
			posEnd  = line.indexOf('"', posIni);
			responseStatus = line.substring(posIni, posEnd); 
		}
		
		/*Se não tiver preenchido a URL e response Status 
		 considerar que a linha é inválida, então devolver nulo */
		if (!url.isEmpty() && !responseStatus.isEmpty() ) { 
			return new WebhookItem(url, responseStatus, (new Date()));
		} else
			return null;
	}
	
	/*Valida se a linha do Log corresponde a uma entrada de webhook*/
	private boolean validateLine(String line) {
		if (line.startsWith("level=info") ) return true;
		else return false;
	}

}
