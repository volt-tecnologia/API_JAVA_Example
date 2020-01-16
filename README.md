# Exemplo API Java para Filtro de Linha Smart Web

## 1. Importe as Classes necessárias
        import br.volt.filtrosmartweb.model.FiltroSmartWeb;

## 2. Instancie um objeto de FiltroSmartWeb

        /*
                O objeto deve ser instanciado passando 3 argumentos:
                -username: String = nome de usuário para autenticação
                -password: String = senha de usuário para autenticação
                -ip: Endereço IP do equipamento
        */
        FiltroSmartWeb equipamento = new FiltroSmartWeb(username, password, ip);
        
        

# Métodos

## 1. Atualizar informações salvas no objeto equip
        /* public int HTTPGETEquipInfo()

        Este método realiza uma requisição GET ao equipamento pedindo as informações atualizadas e retorna um valor inteiro com o código de resposta HTTP.
        É responsável por atualizar todos os atributos do objeto com as informações recebidas, como estado das tomadas, temperatura e outros parâmetros e configurações do equipamento.
           Retorno: 
           -200: Requisição realizada com sucesso e Informações atualizadas no objeto
           -401: Requisição recusada por falha na autenticação
           -500: Equipamento inacessível    
        */

        int resp = equipamento.HTTPGETEquipInfo();

### 1.2 Informações do equipamento
### Para manipular os dados salvos na última atualização de informações ( HTTPGETEquipInfo() ), há uma série de GETs na Classe "FiltroSmartWeb" que retornam seus valores:

### 1.2.1 Número de Tomadas do equipamento
        /*public int getNportas()

        retorna um número inteiro que é o número de tomadas do Filtro de linha Smart Web
        */
        equipamento.getNportas();
### 1.2.2. Temperatura
        /*public int getTemp()

        retorna um número inteiro que é o último valor de temperatura lido do Filtro de linha Smart Web
        */
        equipamento.getTemp()
### 1.2.3. Nome de usuário
        /*public String getUser()

        Retorna o usuário salvo para autenticação do dispositivo

        */
        equipamento.getUser()
### 1.2.3. Password
        /*public String getPass()

        Retorna a senha salva para autenticação do dispositivo
        */
        equipamento.getPass()

 ### 1.2.4. Uptime
        /*public String getUptime()

        Retorna o uptime do equipamento
        */
        equipamento.getUptime()
### 1.2.5. Data
        /*public String getDate()

        Retorna a data configurada no equipamento
        */
        equipamento.getDate()
### 1.2.6. Hora
        /*public String getTime()

        Retorna a hora configurada no equipamento
        */
        equipamento.getTime()
### 1.2.7. Status da tomada
        /*public int getAc(int porta)

        Retorna o status da tomada passada como argumento
        */
        equipamento.getAc(porta)
### 1.2.8. Nome da tomada
        /*public String getAc_nome(int porta)

        Retorna o nome configurado para a tomada passada como argumento
        */
        equipamento.getAc_nome(porta)
### 1.2.9. Status agendamento da tomada
        /*public boolean getEnTimer(int tomada)

        Informa se o agendamento está habilitado para a tomada passada como argumento, sendo True=habilitado e False=Desabilitado
        */
        equipamento.getEnTimer(tomada)
### 1.2.10. Horário para ligamento agendado da tomada
        /*public String getL_on(int tomada)

        Informa o horário de ligamento por agendamento configurado para a tomada passada como argumento 
        */
        equipamento.getL_on(tomada)
### 1.2.11. Horário para desligamento agendado da tomada
        /*public String getAl_off(int tomada)

        Informa o horário de desligamento por agendamento configurado para a tomada passada como argumento 
        */
        equipamento.getAl_off(tomada)
### 1.2.12. Status de dias habilitados par aagendamento
        /*public String getDias(int tomada)

        Informa em que dias da semana o agendamento está habilitado para a tomada passada como argumento (1-10)
        
        Retorna uma string com 7 caracteres numéricos binários em sequência onde 1 representa habilitado e 0 desabilitado para cada dia da semana, sendo na ordem de segunda-feira até domingo.
        exemplo: "1111100" = ativado de segunda a sexta-feira
        */
        equipamento.getDias(tomada)
### 1.2.13. Modelo do equipamento
        /*public String getModelo()

        Informa o código do modelo do equipamento
        */
        equipamento.getModelo()
### 1.2.14. Status do DHCP do equipamento
        /*public Boolean getDevdhcp()

        Retorna o status do DHCP configurado no equipamento

        */
        equipamento.getDevdhcp()
### 1.2.15. Endereço IP configurado no equipamento
        /*public String getDevip()

        Retorna o endereço IP configurado no equipamento

        */
        equipamento.getDevip()
### 1.2.16. Endereço MAC do equipamento
        /*public String getDevmac()

        Retorna o endereço MAC do equipamento

        */
        equipamento.getDevmac()
### 1.2.17. Hostname configurado no equipamento
        /*public String getDevhost()

        Retorna o Hostname configurado no equipamento
        */
        equipamento.getDevhost()
### 1.2.18. Endereço de gateway configurado no equipamento
        /*public String getDevgtw()

        Retorna o endereço de gateway configurado no equipamento

        */
        equipamento.getDevgtw()
### 1.2.19. Máscara de rede configurada no equipamento
        /*public String getDevmask()

        Retorna a máscara de rede no equipamento

        */
        equipamento.getDevmask()
### 1.2.20. Endereço DNS primário configurado no equipamento
        /*public String getDevdns1()

        Retorna o endereço de DNS primário configurado no equipamento
        */
        equipamento.getDevdns1()
### 1.2.21. Endereço DNS secundário configurado no equipamento
        /*public String getDevdns2()

        Retorna o endereço de DNS secundário configurado no equipamento
        */
        equipamento.getDevdns2()

## 2. Controle de Tomadas
    /*  public int controlTomada(int tomada, int op, String ac_name)

        Ao método controlTomda, devem ser passados os argumentos: 
        -tomada: number:(0-10) tomada que receberá o comando
        -op:Comando a ser enviado à tomada (0:liga/desliga ; 1:habilita/desabilita ; 2:trocar nome da porta)
        -ac_name: opcional string com um nome para a porta, obrigatório para op 2(mudar nome da porta). Caso op seja 0 ou 1, não passe esse argumento para o método

        Este método retorna um número inteiro com o código de resposta HTTP da requisição realizada
        {
                200: sucesso
                401: Falha na autenticacação
                500: Equipamento não localizado
        }
    */
    int resp = equipamento.controlTomada(tomada,op,ac_name); 

### Exemplos:
### 2.1. Ligar/Desligar Tomada:
    int resp = equipamento.controlTomada(1,0); //args: tomada 1 e op 0. Inverte o estado da tomada 1 (Liga/Desliga)

### 2.2. Habilitar/Desabilitar Tomada:
     int resp = equipamento.controlTomada(2,1);//args: tomada 2 e op 1. Inverte o estado da tomada 2 (Habilita/Desabilita)

### 2.3. Configurar nome da Tomada:
     int resp = equipamento.controlTomada(3,2,'Tomada 3'); //args: tomada 3 e op 2. Altera o nome da tomada 3 para 'Tomada 3'
     

## 3. Configurar interface Ethernet
     
     /* public int configEthernet(Boolean booldhcp, String newhost, String newip, String newgtw, String newmask, String newdns1, String newdns2)

        À função configEthernet, devem ser passados os argumentos: 
        -boolDhcp: Boolean (True habilita DHCP do equipamento / False desabilita)
        -newhost: String ( Hostname à ser configurado no equipamento)
        -newip: String ( Endereço IP a ser configurado no equipamento)
        -newgtw: String ( Endereço de gateway a ser configurado no equipamento)
        -newmask: String ( Máscara de rede a ser configurada no equipamento)
        -newdns1: String ( Endereço de DNS primário)
        -newdns2: String ( Endereço de DNS secundário)

        Qualquer argumento pode ser passado nulo, e neste caso a configuração anterior dele será mantida

       Este método retorna um número inteiro com o código de resposta HTTP da requisição realizada
        {
                200: Sucesso
                401: Falha na autenticacação
                500: Equipamento não localizado 
        }
    */
    
### Exemplo
        /*
        Configurar a interface de rede com os argumentos:
        Hostname: 'Filtro de Linha'
        IP: '192.168.0.91'
        Gateway: '192.168.0.1'
        Máscara de rede: '255.255.255.0'
        DNS Primário: manter a configuração anterior
        DNS Secundário: manter a configuração anterior

        Qualquer um dos argumentos pode ser passado como null para manter a configuração anterior     
        */
        int resp = equipamento.configEthernet(false, 'Filtro de Linha', '192.168.0.91', '192.168.0.1', '255.255.255.0', null, null);