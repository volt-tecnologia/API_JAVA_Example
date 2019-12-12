package br.volt.filtrosmartweb.model;


public class VoltDevice {

    protected String  user="",
             pass="";

    protected String url = "127.0.0.1";
    protected String modelo = "";

    //Version info
    private String ver_har="Versão Hardware: N/A";
    private String ver_OS="Versão Volt OS: N/A";
    private String ver_MCU="Versão MCU: N/A";
    private String ver_Web="Versão WebPage: N/A ";


    ///ethernet info
    String  devdhcp="",
            devip = "",
            devmac = "",
            devhost = "",
            devgtw = "",
            devmask = "",
            devdns1 = "",
            devdns2 = "";

    public VoltDevice(String user, String pass, String url, String devmac, String modelo, String devhost) {
        this.user = user;
        this.pass = pass;
        this.url = url;
        this.devmac = devmac;
        this.modelo = modelo;
        this.devhost = devhost;
    }
    public VoltDevice(){

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDevdhcp() {
        return devdhcp;
    }

    public void setDevdhcp(String devdhcp) {
        this.devdhcp = devdhcp;
    }

    public String getDevip() {
        return devip;
    }

    public void setDevip(String devip) {
        this.devip = devip;
    }

    public String getDevmac() {
        return devmac;
    }

    public void setDevmac(String devmac) {
        this.devmac = devmac;
    }

    public String getDevhost() {
        return devhost;
    }

    public void setDevhost(String devhost) {
        this.devhost = devhost;
    }

    public String getDevgtw() {
        return devgtw;
    }

    public void setDevgtw(String devgtw) {
        this.devgtw = devgtw;
    }

    public String getDevmask() {
        return devmask;
    }

    public void setDevmask(String devmask) {
        this.devmask = devmask;
    }

    public String getDevdns1() {
        return devdns1;
    }

    public void setDevdns1(String devdns1) {
        this.devdns1 = devdns1;
    }

    public String getDevdns2() {
        return devdns2;
    }

    public void setDevdns2(String devdns2) {
        this.devdns2 = devdns2;
    }
    public String getVer_har() {
        return ver_har;
    }

    public void setVer_har(String ver_har) {
        this.ver_har = ver_har;
    }

    public String getVer_OS() {
        return ver_OS;
    }

    public void setVer_OS(String ver_OS) {
        this.ver_OS = ver_OS;
    }

    public String getVer_MCU() {
        return ver_MCU;
    }

    public void setVer_MCU(String ver_MCU) {
        this.ver_MCU = ver_MCU;
    }

    public String getVer_Web() {
        return ver_Web;
    }

    public void setVer_Web(String ver_Web) {
        this.ver_Web = ver_Web;
    }

    @Override
    public String toString() {
        return "VoltDevice{" +
                "user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", url='" + url + '\'' +
                ", devmac='" + devmac + '\'' +
                ", modelo='" + modelo + '\'' +
                ", devhost='" + devhost + '\'' +
                '}';
    }
}