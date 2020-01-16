package br.volt.filtrosmartweb.control;


import java.io.IOException;

import java.net.HttpURLConnection;

import java.net.URLEncoder;

import java.net.URI;
import java.net.URL;
import java.util.Base64;


/**
 *
 * @author Fernando Souza
 * @version 1.0.0
 */

import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
public class HTTPVolt {
    
    




    /**
     * Realiza uma requisição HTTP GET com autenticação BASIC AUTH e retorna o código de resposta juntamente com o resultado da requisição.
     * @param url
     * @param user
     * @param pass
     * @param timeout
     * @return String[2] - [0]=response code,[1]=response msg
     */
    public static String[] reqGETHTTP(String url, String user, String pass, int timeout){
        
        var resp = new String[2];
        resp[0] = "500";
        resp[1] = "";
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(timeout)).build();
        
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((user+":"+pass).getBytes())) // add request header
                .build();

        HttpResponse<String> response; 
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
             HttpHeaders headers = response.headers();
            headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

            // print status code
            resp[0] = ""+response.statusCode();
            resp[1] = response.body();

        // print response body
        //System.out.println(response.body());
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("/////////");

        return resp;
    }
    
    public static String[] reqPostForm(String url, String user, String pass, Map form){
        String[] resp = new String[2];
        resp[0] = "500";
        resp[1] = "";
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(2000)).build();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(ofFormData(form))
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((user+":"+pass).getBytes()))
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            // print status code
        resp[0] = ""+response.statusCode();
        resp[1] = response.body();
        // print response body
        //System.out.println(response.body());
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        
        return resp;
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
            System.out.println("///////// ERRO AO TENTAR REBOOT: "+endereco);
            ex.printStackTrace();
            return false;


        }
        //System.out.println("Cod erro"+ret[0]);
        //System.out.println("Erro: "+ret[1]);
        System.out.println("--------------------"+endereco);
        return true;

    }

    
    public static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
}


