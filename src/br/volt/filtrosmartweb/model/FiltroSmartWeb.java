package br.volt.filtrosmartweb.model;

import br.volt.filtrosmartweb.control.HTTPRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;


public class FiltroSmartWeb extends VoltDevice{


    private String nportas="";
    private int temp = 0;
    
    
    //Status das portas
    private int ac1 = 0, ac2 = 0, ac3 = 0, ac4 = 0, ac5 = 0, ac6 = 0, ac7 = 0, ac8 = 0, ac9 = 0, ac10 = 0;

    //Nomes das portas
    private String nt1 = "Tomada 1",
                    nt2 = "Tomada 2",
                    nt3 = "Tomada 3",
                    nt4 = "Tomada 4",
                    nt5 = "Tomada 5",
                    nt6 = "Tomada 6",
                    nt7 = "Tomada 7",
                    nt8 = "Tomada 8",
                    nt9 = "Tomada 9",
                    nt10 = "Tomada 10";


    //WatchDog info
    Boolean wdten=false;
    private String latencia,
            mlatencia,
            rmatual="NA",
            ipatual="0.0.0.0",
            varredura="PAUSA",
            cntping="0",

            nresetr1="0",
            nresetr2="0",
            nresetr3="0",
            nresetr4="0",
            nresetr5="0",
            nresetr6="0",
            nresetr7="0",
            nresetr8="0",
            nresetr9="0",
            nresetr10="0";

    String wdtint="0",
            wdtms="0",
            wdtrearme="0",
            wdtmaxrearme="0",
            wdtip1 = "0.0.0.0",
            wdtip2 = "0.0.0.0",
            wdtip3 = "0.0.0.0",
            wdtip4 = "0.0.0.0",
            wdtip5 = "0.0.0.0",
            wdtip6 = "0.0.0.0",
            wdtip7 = "0.0.0.0",
            wdtip8 = "0.0.0.0",
            wdtip9 = "0.0.0.0",
            wdtip10 = "0.0.0.0";
    Boolean wdtcheck1 = false,
            wdtcheck2 = false,
            wdtcheck3 = false,
            wdtcheck4 = false,
            wdtcheck5 = false,
            wdtcheck6 = false,
            wdtcheck7 = false,
            wdtcheck8 = false,
            wdtcheck9 = false,
            wdtcheck10 = false;


    //SNMP info
    private Boolean snmpen = false;
    private String snmpmr1="",
            snmpmr2="",
            snmpmr3="",
            snmpmw1="",
            snmpmw2="",
            snmpmw3="";

    //Uptime/Date info
    private String uptime="Uptime: 0d - 0h:0m",
            date = "RTC Data: na/na/na",
            time="RTC Time: na:na:na";

    //Timer info
    private Boolean entimer1 = false,
            entimer2 = false,
            entimer3 = false,
            entimer4 = false,
            entimer5 = false,
            entimer6 = false,
            entimer7 = false,
            entimer8 = false,
            entimer9 = false,
            entimer10 = false;

    private String l_on1 = "",
            l_on2 = "",
            l_on3 = "",
            l_on4 = "",
            l_on5 = "",
            l_on6 = "",
            l_on7 = "",
            l_on8 = "",
            l_on9 = "",
            l_on10 = "",
            al_off1 = "",
            al_off2 = "",
            al_off3 = "",
            al_off4 = "",
            al_off5 = "",
            al_off6 = "",
            al_off7 = "",
            al_off8 = "",
            al_off9 = "",
            al_off10 = "",
            dias1 = "0000000",
            dias2 = "0000000",
            dias3 = "0000000",
            dias4 = "0000000",
            dias5 = "0000000",
            dias6 = "0000000",
            dias7 = "0000000",
            dias8 = "0000000",
            dias9 = "0000000",
            dias10 = "0000000";

    //private String url = "127.0.0.1";
    
    
    
    public FiltroSmartWeb(String user, String pass, String url, String devmac, String modelo, String devhost) {
        super(user, pass, url, devmac, modelo, devhost);
    }
    public FiltroSmartWeb(String user, String pass, String url){
        this.user = user;
        this.pass = pass;
        this.url = url;

    }
    
    /***
    * Requisita, ao equipamento, a atualização das informações do mesmo via requisição HTTP GET e o mesmo retorna um onjeto JSON com todas suas informações
    * @return código HTTP de resposta do equipamento(200 - OK ; 400,401,403 - Falha Auth; 500 - Equip não encontrado)
    */ 
    public int HTTPGETEquipInfo(){
                    String resp[] = new String[2];
                    resp[0] = "0";
                    JSONObject jo = null;
                    resp = HTTPRequest.reqGETHTTP("http://"+this.getUrl()+"/status.json", this.getUser(), this.getPass(), 2000);
                    //System.out.println(resp[0]);
                    try{
                        jo = new JSONObject(resp[1]);
                        this.setParams(jo);
                    }catch(JSONException e){
                        System.out.println("Erro ao adquirir info do equipamento: "+e.getMessage());
                        e.printStackTrace();
                    }
                    
                    
                    return Integer.parseInt(resp[0]);
    }
    
    
    /***
     *  Controla tomadas com comando Liga/Desliga, Habilita/Desabilita e alterar nome da tomada
     * @param tomada - 1~10 Tomada a ser controlada/alterada
     * @param op - 0:desliga/liga tomada; 1: habilita/desabilita tomada; 2 - Altera nome da tomada
     * @param ac_name - Novo nome para a tomada(op 2) ou null (op 0 e 1)
     */
    public void controlTomada(int tomada, int op, String ac_name){
        
        switch(op){
            case 0:
                if(this.getAc(tomada) != 2){
                    String[] resp = HTTPRequest.reqGETHTTP("http://" + this.getUrl() + "/outpoe.cgi?poe=" + tomada + "&sts="+(this.getAc(tomada) == 1 ? '0':'1')+"&pr=0", this.getUser(), this.getPass(), 1000);
                        System.out.println("http://" + this.getUrl() + "/outpoe.cgi?poe=" + tomada + "&sts="+(this.getAc(tomada) == 1 ? '0':'1')+"&pr=0");
                }
                else JOptionPane.showMessageDialog(null, "Porta desabilitada!!");
                break;
               case 1:
                   String nomePorta = this.getAc_nome(tomada);
                   
                    HTTPRequest.reqGETHTTP("http://" + this.getUrl() + "/output.htm?porta=" + tomada + "&rmac=" + (this.getAc(tomada) == 2 ? "true" : "false") + "&nt=" + nomePorta, this.getUser(), this.getPass(), 1000);
            
                break;
                
               case 2:
                   if(ac_name != null)                   
                    HTTPRequest.reqGETHTTP("http://" + this.getUrl() + "/output.htm?porta=" + tomada + "&rmac=" + (this.getAc(tomada) == 2 ? "false" : "true") + "&nt=" + ac_name, this.getUser(), this.getPass(), 1000);
                   else JOptionPane.showMessageDialog(null, "Digite um nome válido para a tomada!!");
                   break;
                   
        }
        
        
        
        

        
    }
    
    
    /***
     * Realiza a atualização dos parâmetros de rede do equipamento através de uma requisição HTTP POST
     * @param vd
     * @return - boolean: true = sucesso / false = falha
     */
    public boolean configEthernet(VoltDevice vd)
    {
        String request="dhcp="+(vd.getDevdhcp())+"&mac="+URLEncoder.encode("11:22:33:44:55:66")+"&host="+URLEncoder.encode(vd.getDevhost())+
                    "&ip="+vd.getDevip()+"&gw="+vd.getDevgtw()+"&sub="+vd.getDevmask()+"&dns1="+vd.getDevdns1()+
                    "&dns2="+vd.getDevdns2();
        
        try {
            URL url = new URL("http://"+this.devip+"/config.htm?");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);
            conn.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((user+":"+pass).getBytes()));
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            System.out.println(conn.getURL().toString()+request);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");

            writer.write(request);//Envia as informações de rede para o equipamento
            writer.flush();
            writer.close();



            try{//É necessário receber a página retornada pelo equipamento "reboot.htm", porém a mesma não autoriza o acesso. Então, sabendo do retorno HTTP 401, deixo entrar em erro e passar direto
                String response="";
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    response+=line;
                }

                System.out.println(new String(response.getBytes(),"UTF8"));
                reader.close();
            }catch(IOException ex){
                System.out.println("TRY 2");
            }

            //Como a página reboot.htm, retornada pelo equipamento não foi tratada e o AJAX, contido na mesma, não foi executado eu chamo diretamente o script de reboot "reboot.cgi"
            conn = (HttpURLConnection) new URL("http://"+this.devip+"/reset.cgi?timeout=1").openConnection();
            conn.setReadTimeout(1000);//quando o script for executado, a conexão será perdida. Timeout para o software não ficar preso esperando resposta
            conn.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((user+":"+pass).getBytes()));
            try{
                conn.getResponseCode();//preciso realizar essa leitura
            }catch(SocketTimeoutException ex){
                System.out.println("TRY 3");
            }
            
            this.devip = vd.getDevip();
            return true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("TRY 1");
        } catch (IOException e) {
            System.out.println("TRY 4");
            e.printStackTrace();
        }
        
        return false;
        

    }
    
    public boolean setParams(JSONObject jo){
        try{
            setNportas(jo.getString("nportas"));
            setTemp(Integer.parseInt(jo.getString("temp")));
            
            setAc(1, jo.getString("rmac1").equals("true") ? jo.getString("ac0").equals("0") ? 1 : 0 : 2);
            setAc(2, jo.getString("rmac2").equals("true") ? jo.getString("ac1").equals("0") ? 1 : 0 : 2);
            setAc(3, jo.getString("rmac3").equals("true") ? jo.getString("ac2").equals("0") ? 1 : 0 : 2);
            setAc(4, jo.getString("rmac4").equals("true") ? jo.getString("ac3").equals("0") ? 1 : 0 : 2);
            setAc(5, jo.getString("rmac5").equals("true") ? jo.getString("ac4").equals("0") ? 1 : 0 : 2);
            setAc(6, jo.getString("rmac6").equals("true") ? jo.getString("ac5").equals("0") ? 1 : 0 : 2);
            setAc(7, jo.getString("rmac7").equals("true") ? jo.getString("ac6").equals("0") ? 1 : 0 : 2);
            setAc(8, jo.getString("rmac8").equals("true") ? jo.getString("ac7").equals("0") ? 1 : 0 : 2);
            setAc(9, jo.getString("rmac9").equals("true") ? jo.getString("ac8").equals("0") ? 1 : 0 : 2);
            setAc(10, jo.getString("rmac10").equals("true") ? jo.getString("ac9").equals("0") ? 1 : 0 : 2);

            setAc_nome(1,jo.getString("nt1"));
            setAc_nome(2,jo.getString("nt2"));
            setAc_nome(3,jo.getString("nt3"));
            setAc_nome(4,jo.getString("nt4"));
            setAc_nome(5,jo.getString("nt5"));
            setAc_nome(6,jo.getString("nt6"));
            setAc_nome(7,jo.getString("nt7"));
            setAc_nome(8,jo.getString("nt8"));
            setAc_nome(9,jo.getString("nt9"));
            setAc_nome(10,jo.getString("nt10"));
            
            setUptime(jo.getString("updia")+"d "+jo.getString("uphora")+"h "+jo.get("upmin"));
            setDate(jo.getString("rtc_days")+"/"+jo.getString("rtc_months")+"/"+jo.get("rtc_years"));
            setTime(jo.getString("rtc_hours")+":"+jo.getString("rtc_minutes")+":"+jo.getString("rtc_seconds"));
            
            setVer_har("Versão de Hardware: "+jo.get("verhw"));
            setVer_OS("Versão de OS: "+jo.getString("veros"));
            
            setWdten(jo.get("wdten").equals("true"));
            setWdtint(jo.getString("wdtint"));
            setWdtms(jo.getString("wdtms"));
            setWdtrearme(jo.getString("wdtrearme"));
            setWdtmaxrearme(jo.getString("wdtmaxrearme"));
            setCntping(jo.getString("cntping"));
            setVarredura(jo.getString("varredura").equals("0")?"PAUSA":"EXCUTANDO..");
            setRmatual(jo.getString("rmatual"));
            setIpatual(jo.getString("ipatual"));
            setLatencia(jo.getString("latencia"));
            setMlatencia(jo.getString("mlatencia"));

            setNresetr(1, jo.getString("nresetr1"));
            setNresetr(2, jo.getString("nresetr2"));
            setNresetr(3, jo.getString("nresetr3"));
            setNresetr(4, jo.getString("nresetr4"));
            setNresetr(5, jo.getString("nresetr5"));
            setNresetr(6, jo.getString("nresetr6"));
            setNresetr(7, jo.getString("nresetr7"));
            setNresetr(8, jo.getString("nresetr8"));
            setNresetr(9, jo.getString("nresetr9"));
            setNresetr(10, jo.getString("nresetr10"));
            
            setSnmpen(jo.getString("snmpen").equals("true"));
            setSnmpmr1(jo.getString("snmpcmr1"));
            setSnmpmr2(jo.getString("snmpcmr2"));
            setSnmpmr3(jo.getString("snmpcmr3"));
            setSnmpmw1(jo.getString("snmpcmw1"));
            setSnmpmw2(jo.getString("snmpcmw2"));
            setSnmpmw3(jo.getString("snmpcmw3"));
            
            setDevip(jo.getString("devip"));
            setDevmask(jo.getString("devmask"));
            setDevgtw(jo.getString("devgtw"));
            setDevmac(jo.getString("devmac"));
            setDevhost(jo.getString("devhost"));
            setDevdns1(jo.getString("devdns1"));
            setDevdns2(jo.getString("devdns2"));
            setDevdhcp(jo.getString("devdhcp"));
            
            setWdtcheck(1,jo.getString("wdtcheck1").equals("true"));
            setWdtip(1,jo.getString("wdtip1"));
            setWdtcheck(2,jo.getString("wdtcheck2").equals("true"));
            setWdtip(2,jo.getString("wdtip2"));
            setWdtcheck(3,jo.getString("wdtcheck3").equals("true"));
            setWdtip(3,jo.getString("wdtip3"));
            setWdtcheck(4,jo.getString("wdtcheck4").equals("true"));
            setWdtip(4,jo.getString("wdtip4"));
            setWdtcheck(5,jo.getString("wdtcheck5").equals("true"));
            setWdtip(5,jo.getString("wdtip5"));
            setWdtcheck(6,jo.getString("wdtcheck6").equals("true"));
            setWdtip(6,jo.getString("wdtip6"));
            setWdtcheck(7,jo.getString("wdtcheck7").equals("true"));
            setWdtip(7,jo.getString("wdtip7"));
            setWdtcheck(8,jo.getString("wdtcheck8").equals("true"));
            setWdtip(8,jo.getString("wdtip8"));
            setWdtcheck(9,jo.getString("wdtcheck9").equals("true"));
            setWdtip(9,jo.getString("wdtip9"));
            setWdtcheck(10,jo.getString("wdtcheck10").equals("true"));
            setWdtip(10,jo.getString("wdtip10"));
            
             for(int i = 0; i < (Integer.parseInt(getNportas())); i++){
                setEnTimer(i+1, jo.getString(("entimer"+(i+1))).equals("true"));
            }

            for(int i = 0; i < (Integer.parseInt(getNportas())); i++){
                setL_on(i+1,jo.getString(("l_on"+(i+1))));
            }

            for(int i = 0; i < (Integer.parseInt(getNportas())); i++){
                setAl_off(i+1,jo.getString(("al_off"+(i+1))));
            }

            for(int i = 0; i < (Integer.parseInt(getNportas())); i++){
                setDias(i+1, jo.getString(("dias"+(i+1))));
            }
        }catch(JSONException e){
            e.printStackTrace();
            
            return false;
        }
        
        
        return true;
    }

    public String getNportas() {
        return nportas;
    }

    public void setNportas(String nportas) {
        this.nportas = nportas;
    }
    
    
    public int getTemp(){
        return this.temp;
    }
    
    public void setTemp(int temp){
        this.temp = temp;
    }
   

    public String getWdtint() {
        return wdtint;
    }

    public void setWdtint(String wdtint) {
        this.wdtint = wdtint;
    }

    public String getWdtms() {
        return wdtms;
    }

    public void setWdtms(String wdtms) {
        this.wdtms = wdtms;
    }

    public String getWdtrearme() {
        return wdtrearme;
    }

    public void setWdtrearme(String wdtrearme) {
        this.wdtrearme = wdtrearme;
    }

    public String getWdtmaxrearme() {
        return wdtmaxrearme;
    }

    public void setWdtmaxrearme(String wdtmaxrearme) {
        this.wdtmaxrearme = wdtmaxrearme;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getWdtip(int tomada) {
        String ret = "";
        switch(tomada){
            case 1:
                ret = this.wdtip1;
                break;

            case 2:
                ret = this.wdtip2;
                break;

            case 3:
                ret = this.wdtip3;
                break;

            case 4:
                ret = this.wdtip4;
                break;

            case 5:
                ret = this.wdtip5;
                break;

            case 6:
                ret = this.wdtip6;
                break;

            case 7:
                ret = this.wdtip7;
                break;

            case 8:
                ret = this.wdtip8;
                break;

            case 9:
                ret = this.wdtip9;
                break;

            case 10:
                ret = this.wdtip10;
                break;
        }

        return ret;
    }

    public void setWdtip(int tomada, String wdtip){

        switch(tomada){
            case 1:
                    this.wdtip1 = wdtip;
                break;

            case 2:
                    this.wdtip2 = wdtip;
                break;

            case 3:
                    this.wdtip3 = wdtip;
                break;

            case 4:
                     this.wdtip4 = wdtip;
                break;

            case 5:
                    this.wdtip5 = wdtip;
                break;

            case 6:
                    this.wdtip6 = wdtip;
                break;

            case 7:
                    this.wdtip7 = wdtip;
                break;

            case 8:
                    this.wdtip8 = wdtip;
                break;

            case 9:
                    this.wdtip9 = wdtip;
                break;

            case 10:
                    this.wdtip10 = wdtip;
                break;
        }


    }



    public Boolean getWdtcheck(int tomada) {
        Boolean ret = false;

        switch(tomada){
            case 1:
                    ret = this.wdtcheck1;
                break;

            case 2:
                    ret = this.wdtcheck2;
                break;

            case 3:
                    ret = this.wdtcheck3;
                break;

            case 4:
                    ret = this.wdtcheck4;
                break;

            case 5:
                    ret = this.wdtcheck5;
                break;

            case 6:
                    ret = this.wdtcheck6;
                break;

            case 7:
                    ret = this.wdtcheck7;
                break;

            case 8:
                    ret = this.wdtcheck8;
                break;

            case 9:
                    ret = this.wdtcheck9;
                break;

            case 10:
                    ret = this.wdtcheck10;
                break;
        }



        return ret;
    }

    public void setWdtcheck(int tomada, Boolean wdtcheck) {

        switch(tomada){
            case 1:
                    this.wdtcheck1 = wdtcheck;
                break;

            case 2:
                    this.wdtcheck2 = wdtcheck;
                break;

            case 3:
                    this.wdtcheck3 = wdtcheck;
                break;

            case 4:
                    this.wdtcheck4 = wdtcheck;
                break;

            case 5:
                    this.wdtcheck5 = wdtcheck;
                break;

            case 6:
                    this.wdtcheck6 = wdtcheck;
                break;

            case 7:
                    this.wdtcheck7 = wdtcheck;
                break;

            case 8:
                    this.wdtcheck8 = wdtcheck;
                break;

            case 9:
                    this.wdtcheck9 = wdtcheck;
                break;

            case 10:
                    this.wdtcheck10 = wdtcheck;
                break;
        }
    }

    public Boolean getSnmpen() {
        return snmpen;
    }

    public void setSnmpen(Boolean snmpen) {
        this.snmpen = snmpen;
    }

    public String getSnmpmr1() {
        return snmpmr1;
    }

    public void setSnmpmr1(String snmpmr1) {
        this.snmpmr1 = snmpmr1;
    }

    public String getSnmpmr2() {
        return snmpmr2;
    }

    public void setSnmpmr2(String snmpmr2) {
        this.snmpmr2 = snmpmr2;
    }

    public String getSnmpmr3() {
        return snmpmr3;
    }

    public void setSnmpmr3(String snmpmr3) {
        this.snmpmr3 = snmpmr3;
    }

    public String getSnmpmw1() {
        return snmpmw1;
    }

    public void setSnmpmw1(String snmpmw1) {
        this.snmpmw1 = snmpmw1;
    }

    public String getSnmpmw2() {
        return snmpmw2;
    }

    public void setSnmpmw2(String snmpmw2) {
        this.snmpmw2 = snmpmw2;
    }

    public String getSnmpmw3() {
        return snmpmw3;
    }

    public void setSnmpmw3(String snmpmw3) {
        this.snmpmw3 = snmpmw3;
    }

    public String getLatencia() {
        return latencia;
    }

    public void setLatencia(String latencia) {
        this.latencia = latencia;
    }

    public String getMlatencia() {
        return mlatencia;
    }

    public void setMlatencia(String mlatencia) {
        this.mlatencia = mlatencia;
    }

    public String getRmatual() {
        return rmatual;
    }

    public void setRmatual(String rmatual) {
        this.rmatual = rmatual;
    }

    public String getIpatual() {
        return ipatual;
    }

    public void setIpatual(String ipatual) {
        this.ipatual = ipatual;
    }

    public String getVarredura() {
        return varredura;
    }

    public void setVarredura(String varredura) {
        this.varredura = varredura;
    }

    public String getCntping() {
        return cntping;
    }

    public void setCntping(String cntping) {
        this.cntping = cntping;
    }

    ////////////////

    public String getNresetr(int porta)
    {
        String nresetr = "";

        switch(porta){
            case 1:
                    nresetr = nresetr1;
                break;

            case 2:
                    nresetr = nresetr2;
                break;

            case 3:
                    nresetr = nresetr3;
                break;

            case 4:
                    nresetr = nresetr4;
                break;

            case 5:
                    nresetr = nresetr5;
                break;

            case 6:
                    nresetr = nresetr6;
                break;

            case 7:
                    nresetr = nresetr7;
                break;

            case 8:
                    nresetr = nresetr8;
                break;

            case 9:
                    nresetr = nresetr9;
                break;

            case 10:
                    nresetr = nresetr10;
                break;

        }

        return nresetr;
    }

    public void setNresetr(int porta, String value)
    {
        switch(porta){
            case 1:
                 nresetr1 = value;
                break;

            case 2:
                    nresetr2 = value;
                break;

            case 3:
                    nresetr3 = value;
                break;

            case 4:
                    nresetr4 = value;
                break;

            case 5:
                    nresetr5 = value;
                break;

            case 6:
                    nresetr6 = value;
                break;

            case 7:
                nresetr7 = value;
                break;

            case 8:
                    nresetr8 = value;
                break;

            case 9:
                    nresetr9 = value;
                break;

            case 10:
                    nresetr10 = value;
                break;

        }

    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getWdten() {
        return wdten;
    }

    public void setWdten(Boolean wdten) {
        this.wdten = wdten;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAc(int porta){
        int state=2;
        switch(porta){
            case 1:
                state = this.ac1;
                break;

            case 2:
                state = this.ac2;
                break;

            case 3:
                state = this.ac3;
                break;

            case 4:
                state = this.ac4;
                break;

            case 5:
                state = this.ac5;
                break;

            case 6:
                state = this.ac6;
                break;

            case 7:
                state = this.ac7;
                break;

            case 8:
                state = this.ac8;
                break;

            case 9:
                state = this.ac9;
                break;

            case 10:
                state = this.ac10;
                break;

        }

        return state;
    }

    public void setAc(int porta, int state){
        switch(porta){
            case 1:
                this.ac1 = state;
                break;

            case 2:
                this.ac2 = state;
                break;

            case 3:
                    this.ac3 = state;
                break;

            case 4:
                    this.ac4 = state;
                  break;

            case 5:
                    this.ac5 = state;
                break;

            case 6:
                    this.ac6 = state;
                break;

            case 7:
                    this.ac7 = state;
                break;

            case 8:
                    this.ac8 = state;
                break;

            case 9:
                    this.ac9 = state;
                break;

            case 10:
                this.ac10 = state;
                break;

        }
    }

    public String getAc_nome(int porta){
        String nome_porta = "Porta inexistente";
        switch(porta){
            case 1:
                nome_porta = this.nt1;
                break;

            case 2:
                nome_porta = this.nt2;
                break;

            case 3:
                nome_porta = this.nt3;
                break;

            case 4:
                nome_porta = this.nt4;
                break;

            case 5:
                nome_porta = this.nt5;
                break;

            case 6:
                nome_porta = this.nt6;
                break;

            case 7:
                nome_porta = this.nt7;
                break;

            case 8:
                nome_porta = this.nt8;
                break;

            case 9:
                nome_porta = this.nt9;
                break;

            case 10:
                nome_porta = this.nt10;
                break;
        }

        return nome_porta;
    }

    public void setAc_nome(int porta, String nome)
    {

        switch(porta){
            case 1:
                    this.nt1 = nome;
                break;

            case 2:
                    this.nt2 = nome;
                break;

            case 3:
                    this.nt3 = nome;
                break;

            case 4:
                    this.nt4 = nome;
                break;

            case 5:
                    this.nt5 = nome;
                break;

            case 6:
                    this.nt6 = nome;
                break;

            case 7:
                    this.nt7 = nome;
                break;

            case 8:
                    this.nt8 = nome;
                break;

            case 9:
                    this.nt9 = nome;
                break;

            case 10:
                    this.nt10 = nome;
                break;
        }
    }

    public boolean getEnTimer(int tomada){
        boolean resp = false;
        switch(tomada){
            case 1:
                    resp = this.entimer1;
                break;

            case 2:
                    resp = this.entimer2;
                break;

            case 3:
                    resp = this.entimer3;
                break;

            case 4:
                    resp = this.entimer4;
                break;

            case 5:
                    resp = this.entimer5;
                break;

            case 6:
                    resp = this.entimer6;
                break;

            case 7:
                    resp = this.entimer7;
                break;

            case 8:
                    resp = this.entimer8;
                break;

            case 9:
                    resp = this.entimer9;
                break;

            case 10:
                    resp = this.entimer10;
                break;
        }

        return resp;
    }

    public void setEnTimer(int tomada, boolean value){
        switch(tomada){
            case 1:
                    this.entimer1 = value;
                break;

            case 2:
                    this.entimer2 = value;
                break;

            case 3:
                    this.entimer3 = value;
                break;

            case 4:
                    this.entimer4 = value;
                break;

            case 5:
                    this.entimer5 = value;
                break;

            case 6:
                    this.entimer6 = value;
                break;

            case 7:
                    this.entimer7 = value;
                break;

            case 8:
                    this.entimer8 = value;
                break;

            case 9:
                    this.entimer9 = value;
                break;

            case 10:
                    this.entimer10 = value;
                break;
        }
    }

    public String getL_on(int tomada){

        String resp = "00:00";
        switch(tomada){
            case 1:
                    resp = this.l_on1;
                break;

            case 2:
                    resp = this.l_on2;
                break;

            case 3:
                    resp = this.l_on3;
                break;

            case 4:
                    resp = this.l_on4;
                break;

            case 5:
                    resp = this.l_on5;
                break;

            case 6:
                    resp = this.l_on6;
                break;

            case 7:
                    resp = this.l_on7;
                break;

            case 8:
                    resp = this.l_on8;
                break;

            case 9:
                    resp = this.l_on9;
                break;

            case 10:
                    resp = this.l_on10;
                break;
        }
        System.out.println(resp);
        System.out.println(resp.substring(0,2)+":"+resp.substring(2,4));
        return (resp.substring(0,2)+":"+resp.substring(2,4));
    }

    public void setL_on(int tomada, String value){
        switch(tomada){
            case 1:
                    this.l_on1 = value;
                break;

            case 2:
                    this.l_on2 = value;
                break;

            case 3:
                    this.l_on3 = value;
                break;

            case 4:
                    this.l_on4 = value;
                break;

            case 5:
                    this.l_on5 = value;
                break;

            case 6:
                    this.l_on6 = value;
                break;

            case 7:
                    this.l_on7 = value;
                break;

            case 8:
                    this.l_on8 = value;
                break;

            case 9:
                    this.l_on9 = value;
                break;

            case 10:
                    this.l_on10 = value;
                break;
        }
    }

    public String getAl_off(int tomada){
        String resp = "00:00";
        switch(tomada){
            case 1:
                    resp = this.al_off1;
                break;

            case 2:
                    resp = this.al_off2;
                break;

            case 3:
                    resp = this.al_off3;
                break;

            case 4:
                    resp = this.al_off4;
                break;

            case 5:
                    resp = this.al_off5;
                break;

            case 6:
                    resp = this.al_off6;
                break;

            case 7:
                    resp = this.al_off7;
                break;

            case 8:
                    resp = this.al_off8;
                break;

            case 9:
                    resp = this.al_off9;
                break;

            case 10:
                    resp = this.al_off10;
                break;
        }

        System.out.println(resp);
        System.out.println(resp.substring(0,2)+":"+resp.substring(2,4));

        return (resp.substring(0,2)+":"+resp.substring(2,4));
    }

    public void setAl_off(int tomada, String value){
        switch(tomada){
            case 1:
                    this.al_off1 = value;
                break;

            case 2:
                    this.al_off2 = value;
                break;

            case 3:
                    this.al_off3 = value;
                break;

            case 4:
                    this.al_off4 = value;
                break;

            case 5:
                    this.al_off5 = value;
                break;

            case 6:
                    this.al_off6 = value;
                break;

            case 7:
                    this.al_off7 = value;
                break;

            case 8:
                    this.al_off8 = value;
                break;

            case 9:
                    this.al_off9 = value;
                break;

            case 10:
                    this.al_off10 = value;
                break;
        }
    }

    public String getDias(int tomada){
        String resp = "";
        switch(tomada){
            case 1:
                resp = this.dias1;
                break;

            case 2:
                resp = this.dias2;
                break;

            case 3:
                resp = this.dias3;
                break;

            case 4:
                resp = this.dias4;
                break;

            case 5:
                resp = this.dias5;
                break;

            case 6:
                resp = this.dias6;
                break;

            case 7:
                resp = this.dias7;
                break;

            case 8:
                resp = this.dias8;
                break;

            case 9:
                resp = this.dias9;
                break;

            case 10:
                resp = this.dias10;
                break;
        }

        return resp;
    }

    public void setDias(int tomada, String value){

        switch(tomada){
            case 1:
                    this.dias1 = value;
                break;

            case 2:
                    this.dias2 = value;
                break;

            case 3:
                    this.dias3 = value;
                break;

            case 4:
                    this.dias4 = value;
                break;

            case 5:
                    this.dias5 = value;
                break;

            case 6:
                    this.dias6 = value;
                break;

            case 7:
                    this.dias7 = value;
                break;

            case 8:
                    this.dias8 = value;
                break;

            case 9:
                    this.dias9 = value;
                break;

            case 10:
                    this.dias10 = value;
                break;
        }

    }
    
   
   /*
    
    Métodos principais abaixo
    
    */ 
    
    
    
   

    @Override
    public String toString() {
        return "FiltroSmartWeb{" + "nportas=" + nportas + ", ac1=" + ac1 + ", ac2=" + ac2 + ", ac3=" + ac3 + ", ac4=" + ac4 + ", ac5=" + ac5 + ", ac6=" + ac6 + ", ac7=" + ac7 + ", ac8=" + ac8 + ", ac9=" + ac9 + ", ac10=" + ac10 + ", nt1=" + nt1 + ", nt2=" + nt2 + ", nt3=" + nt3 + ", nt4=" + nt4 + ", nt5=" + nt5 + ", nt6=" + nt6 + ", nt7=" + nt7 + ", nt8=" + nt8 + ", nt9=" + nt9 + ", nt10=" + nt10 + ", wdten=" + wdten + ", latencia=" + latencia + ", mlatencia=" + mlatencia + ", rmatual=" + rmatual + ", ipatual=" + ipatual + ", varredura=" + varredura + ", cntping=" + cntping + ", nresetr1=" + nresetr1 + ", nresetr2=" + nresetr2 + ", nresetr3=" + nresetr3 + ", nresetr4=" + nresetr4 + ", nresetr5=" + nresetr5 + ", nresetr6=" + nresetr6 + ", nresetr7=" + nresetr7 + ", nresetr8=" + nresetr8 + ", nresetr9=" + nresetr9 + ", nresetr10=" + nresetr10 + ", wdtint=" + wdtint + ", wdtms=" + wdtms + ", wdtrearme=" + wdtrearme + ", wdtmaxrearme=" + wdtmaxrearme + ", wdtip1=" + wdtip1 + ", wdtip2=" + wdtip2 + ", wdtip3=" + wdtip3 + ", wdtip4=" + wdtip4 + ", wdtip5=" + wdtip5 + ", wdtip6=" + wdtip6 + ", wdtip7=" + wdtip7 + ", wdtip8=" + wdtip8 + ", wdtip9=" + wdtip9 + ", wdtip10=" + wdtip10 + ", wdtcheck1=" + wdtcheck1 + ", wdtcheck2=" + wdtcheck2 + ", wdtcheck3=" + wdtcheck3 + ", wdtcheck4=" + wdtcheck4 + ", wdtcheck5=" + wdtcheck5 + ", wdtcheck6=" + wdtcheck6 + ", wdtcheck7=" + wdtcheck7 + ", wdtcheck8=" + wdtcheck8 + ", wdtcheck9=" + wdtcheck9 + ", wdtcheck10=" + wdtcheck10 + ", snmpen=" + snmpen + ", snmpmr1=" + snmpmr1 + ", snmpmr2=" + snmpmr2 + ", snmpmr3=" + snmpmr3 + ", snmpmw1=" + snmpmw1 + ", snmpmw2=" + snmpmw2 + ", snmpmw3=" + snmpmw3 + ", uptime=" + uptime + ", date=" + date + ", time=" + time + ", entimer1=" + entimer1 + ", entimer2=" + entimer2 + ", entimer3=" + entimer3 + ", entimer4=" + entimer4 + ", entimer5=" + entimer5 + ", entimer6=" + entimer6 + ", entimer7=" + entimer7 + ", entimer8=" + entimer8 + ", entimer9=" + entimer9 + ", entimer10=" + entimer10 + ", l_on1=" + l_on1 + ", l_on2=" + l_on2 + ", l_on3=" + l_on3 + ", l_on4=" + l_on4 + ", l_on5=" + l_on5 + ", l_on6=" + l_on6 + ", l_on7=" + l_on7 + ", l_on8=" + l_on8 + ", l_on9=" + l_on9 + ", l_on10=" + l_on10 + ", al_off1=" + al_off1 + ", al_off2=" + al_off2 + ", al_off3=" + al_off3 + ", al_off4=" + al_off4 + ", al_off5=" + al_off5 + ", al_off6=" + al_off6 + ", al_off7=" + al_off7 + ", al_off8=" + al_off8 + ", al_off9=" + al_off9 + ", al_off10=" + al_off10 + ", dias1=" + dias1 + ", dias2=" + dias2 + ", dias3=" + dias3 + ", dias4=" + dias4 + ", dias5=" + dias5 + ", dias6=" + dias6 + ", dias7=" + dias7 + ", dias8=" + dias8 + ", dias9=" + dias9 + ", dias10=" + dias10 + '}';
    }

    
    






}