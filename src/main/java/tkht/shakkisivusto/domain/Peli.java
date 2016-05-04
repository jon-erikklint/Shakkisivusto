package tkht.shakkisivusto.domain;

import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.tietokanta.luojat.Yhdistettava;

public class Peli implements Yhdistettava{
    
    private int id;
    private String nimi;
    private String status;
    
    private PelinPelaaja pelaaja1;
    private PelinPelaaja pelaaja2;
    
    private List<Vuoro> vuorot;
    
    public Peli(int id, String nimi, String status){
        this();
        this.id = id;
        this.nimi = nimi;
        this.status = status;
    }
    
    public Peli(String nimi){
        this();
        this.nimi = nimi;
        this.status = "HAETAAN VASTAPELAAJAA";
    }
    
    public Peli(){
        vuorot = new ArrayList<>();
    }
    
    public String getPelisivu(){
        return "/peli/"+id;
    }
    
    public String getLiittymislinkki(){
        return "/peli/"+id+"/liity";
    }
    
    public String getKatselusivu(){
        int vuoro = getUusinVuoronumero();
        return "/peli/"+id+"/katsele/"+vuoro;
    }
    
    public String getVoittosivu(){
        return "/peli/"+id+"/voitit";
    }
    
    public int getVuoroja(){
        return vuorot.size();
    }
    
    public String getViimeisintekoaika(){
        Vuoro viimeisin = getUusinVuoro();
        
        return viimeisin.getTekoaika().toString();
    }
    
    public int getUusinVuoronumero(){
        Vuoro vuoro = getUusinVuoro();
        
        if(vuoro == null){
            return 0;
        }
        
        return vuoro.getVuoro();
    }
    
    public Vuoro getUusinVuoro(){
        if(vuorot == null){
            return null;
        }
        
        if(vuorot.isEmpty()){
            return null;
        }
        
        int indeksi = 0;
        int suurin = vuorot.get(0).getVuoro();
        
        for(int i = 1 ; i < vuorot.size() ; i++){
            Vuoro vuoro = vuorot.get(i);
            
            if(vuoro.getVuoro() > suurin){
                indeksi = i;
                suurin = vuoro.getVuoro();
            }
        }
        
        return vuorot.get(indeksi);
    }
    
    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PelinPelaaja getPelaaja1() {
        return pelaaja1;
    }

    public void setPelaaja1(PelinPelaaja pelaaja1) {
        this.pelaaja1 = pelaaja1;
    }

    public PelinPelaaja getPelaaja2() {
        return pelaaja2;
    }

    public void setPelaaja2(PelinPelaaja pelaaja2) {
        this.pelaaja2 = pelaaja2;
    }
    
    public double getPelaaja1voittoratio(){
        return pelaaja1.getPelaaja().getVoittoratio();
    }
    
    public String getPelaaja1nimi(){
        return pelaaja1.getPelaaja().getNimi();
    }

    @Override
    public boolean yhdista(Object o) {
        if(o == null){
            return false;
        }
        
        if(o.getClass() == PelinPelaaja.class){
            PelinPelaaja pp = (PelinPelaaja) o;
            
            if(pp.getPeliid() == this.id){
                if(pp.isValkoinen()){
                    pelaaja1 = pp;
                }else{
                    pelaaja2 = pp;
                }
                
                return true;
            }
            
            return false;
        }
        
        if(o.getClass() == Vuoro.class){
            Vuoro v = (Vuoro) o;
            
            if(v.getPeliid() == this.id){
                vuorot.add(v);
                
                return true;
            }
            
            return false;
        }
        
        return false;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Peli other = (Peli) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        return hash;
    }
}
