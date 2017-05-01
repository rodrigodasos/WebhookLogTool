# WebhookLogTool
Solução em Java para parser de arquivo de log com webhooks, com agrupamento dos resultados

Faz o parser de um arquivo de log com webhooks recebidos, contendo a URL do endereço do cliente e o Response Status recebido, agrupando as chamadas de mesma URL e mesmo Response Status. 

Lista as 3 URLs mais executadas com as quantidades, e também lista todos os Response Status e Quantidades recebidos.

Para testar:

1 - Copie o arquivo logWebhook.txt disponível em .\LogSample\ para o diretório desejado

2 - Altere o caminho do arquivo refenciado na classe .\WebhookLogTool\src\webhook\logtool\Main.java , dentro do método "main", variável "pathLog".

3 - Execute o projeto WebhookLogTool.

O resultado da execução será exibido no output do Console.


