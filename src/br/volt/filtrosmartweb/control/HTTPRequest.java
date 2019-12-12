package br.volt.filtrosmartweb.control;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author Fernando Souza
 * @version 1.0.0
 */
public class HTTPRequest {




    /**
     * Realiza uma requisição HTTP GET com autenticação BASIC AUTH e retorna o código de resposta juntamente com o resultado da requisição.
     * @param endereco
     * @param user
     * @param pass
     * @return String[2] - [0]=response code,[1]=response msg
     */
    public static String[] reqGETHTTP(String endereco, String user, String pass, int timeout){
        //metodo.toUpperCase();
        String resp="";
        String ret[] = new String[2];
        try{
            //System.out.println("Testando socketstate ip: "+endereco);
            URL url = new URL(endereco);
            //String aut = Base64.encode((user+":"+pass).getBytes());
            String aut = Base64.getEncoder().encodeToString((user+":"+pass).getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + aut);
            // the SAX way:

            //try{
                ret[0] = ""+connection.getResponseCode();
            /*}catch(IOException e){
                System.out.println("Erro: "+e.getMessage());
            }*/
            InputStream in = (InputStream) connection.getInputStream();
            for (int b; (b = in.read()) != -1;) {
                resp+=(char)b;
            }


            ret[1] = resp;
            in.close();
            connection.disconnect();


            //System.out.println(xml.toString());

        } catch (IOException ex) {
            System.out.println("///////// ERRO AO TENTAR SOCKET COM IP: "+endereco);
            //System.out.println(ret[0]);
            resp = "Erro.\n";
            if(ex.getMessage() != null && ex.getMessage().length()>35 && ex.getMessage().substring(0, 35).equals("Server returned HTTP response code:"))
            {

                ret[0]=ex.getMessage().substring(36,39);



            }else if(ret[0] != null && ret[0].length()<1)
            {
                ret[0] = "0";
                
                ex.printStackTrace();
            }else ret[0] = "500";

            ret[1]  = ex.getMessage();

        }
        //System.out.println("Cod erro"+ret[0]);
        //System.out.println("Erro: "+ret[1]);
        return ret;

    }

    public static Boolean reqGETHTTP_no_response(String endereco, String user, String pass, int timeout){
        //metodo.toUpperCase();
        String resp="";
        String ret[] = new String[2];
        try{
            //System.out.println("Testando socketstate ip: "+endereco);
            URL url = new URL(endereco);
            //String aut = Base64.encode((user+":"+pass).getBytes());
            String aut = Base64.getEncoder().encodeToString((user+":"+pass).getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + aut);
            // the SAX way:

            connection.disconnect();


            //System.out.println(xml.toString());

        } catch (IOException ex) {
            System.out.println("///////// ERRO AO TENTA REBOOT: "+endereco);
            ex.printStackTrace();
            return false;


        }
        //System.out.println("Cod erro"+ret[0]);
        //System.out.println("Erro: "+ret[1]);
        System.out.println("--------------------"+endereco);
        return true;

    }
/*
    public static String configRede(String dhcp, String end, String user, String pass, String mac, String host, String ip, String sub, String gw, String dns1, String dns2)
    {
        try {
            String request="dhcp="+dhcp+"&mac="+URLEncoder.encode(mac)+"&host="+URLEncoder.encode(host)+
                    "&ip="+ip+"&gw="+gw+"&sub="+sub+"&dns1="+dns1+
                    "&dns2="+dns2;
            URL url = new URL("http://"+end+"/config.htm?");

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

            }

            //Como a página reboot.htm, retornada pelo equipamento não foi tratada e o AJAX, contido na mesma, não foi executado eu chamo diretamente o script de reboot "reboot.cgi"
            conn = (HttpURLConnection) new URL("http://"+end+"/reboot.cgi").openConnection();
            conn.setReadTimeout(1000);//quando o script for executado, a conexão será perdida. Timeout para o software não ficar preso esperando resposta
            conn.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((user+":"+pass).getBytes()));
            try{
                conn.getResponseCode();//preciso realizar essa leitura
            }catch(SocketTimeoutException ex){

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
*//*
    public static boolean configRele(String end, String user, String pass, int trigger, int trele, int ttemp, int ttempm){
        try {

            URL url = new URL(end);
            String aut = Base64.getEncoder().encodeToString((user+":"+pass).getBytes());


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);
            conn.setRequestProperty("Authorization", "Basic " + aut);
            conn.setDoOutput(true);
            System.out.println(conn.getURL().toString());

            try{
                String resp="";
                System.out.println("Response code: "+conn.getResponseCode());
                InputStream in = (InputStream) conn.getInputStream();
                for (int b; (b = in.read()) != -1;) {
                    resp+=(char)b;
                }
            }catch(IOException ex){
                ex.printStackTrace();
                System.out.println("ERRO ++++++");
            }


            conn.disconnect();
            return true;



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

*/
}


