/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

/**
 *
 * @author Lourdes
 */
public class MiuMiu {
    
    String name;
    float energia, hambre, fuerza, suciedad, felicidad, experiencia;
    int nivel;
    
    public MiuMiu(String name){
        this.name=name;
        this.energia=90;
        this.hambre=10;
        this.fuerza=0;
        this.suciedad=10;
        this.felicidad=10;
        this.experiencia=0;
        this.nivel=1;
    }
    
    public boolean comer(){
        boolean res = addHambre(-10);
        if (res){
            addEnergia(10);
            addSuciedad(10);
            addFelicidad(4);
            setExperiencia(experiencia+1+getFuerza()*0.1f);
        }
        return res;
    }
    
    public boolean entrenar(){
        boolean res = addFuerza(10);
        if (res){
            addEnergia(-10);
            if(getEnergia()>0){
                addHambre(10);
                addSuciedad(10);
                addFelicidad(1);
                setExperiencia(experiencia+4);
            } 
        }
        return res;
    }
    
    public void limpiar(){
        setSuciedad(0);
        addFelicidad(2);
        setExperiencia(experiencia+2);
    }
    
    public void dormir(){
        setEnergia(100);
        addFelicidad(3);
        setExperiencia(experiencia+1);
    }
    
    public void muerto(){
        setEnergia(0);
        setHambre(0);
        setFuerza(0);
        setSuciedad(0);
        setFelicidad(0);
        setExperiencia(0);
    }
    
    public float getEnergia() {
        return energia;
    }

    public boolean setEnergia(float energia) {
        this.energia = (energia>100) ? 100 : energia;
        return true;
    }
    
    public boolean addEnergia(float sumando){
        return setEnergia(energia + sumando);
    }

    public float getHambre() {
        return hambre;
    }

    public boolean setHambre(float hambre) {
        boolean res = true;
        if(hambre<0){
            return false;
        }
        if(hambre>100){
            this.hambre=100;
        } else {
            this.hambre=hambre;
        }
        return res;
    }
    
    public boolean addHambre(float sumando){
        return setHambre(hambre + sumando); 
    }

    public float getFuerza() {
        return fuerza;
    }

    public boolean setFuerza(float fuerza) {
        boolean res = true;
        if(fuerza<0){
            return false;
        }
        this.fuerza = (fuerza>100) ? 100 : fuerza;
        return res;
    }
    
    public boolean addFuerza(float sumando){
        return setFuerza(fuerza + sumando);
    }

    public float getSuciedad() {
        return suciedad;
    }

    public boolean setSuciedad(float suciedad) {
        boolean res = true;
        if(suciedad<0){
            return false;
        }
        if(suciedad>100){
            this.suciedad=100;
        } else {
            this.suciedad=suciedad;
        }
        return res;
    }
    
    public boolean addSuciedad(float sumando){
        return setSuciedad(suciedad + sumando);
    }

    public float getFelicidad() {
        return felicidad;
    }

    public boolean setFelicidad(float felicidad) {
        if(felicidad<0){
           this.felicidad=0;
           return false;
        }
        if(suciedad>=100 && this.felicidad<felicidad){
            return false;
        }
       
        this.felicidad = (felicidad>100) ? 100 : felicidad;
        return true;
    }
    
    public boolean addFelicidad(float sumando){
        return setFelicidad(felicidad + sumando);
    }

    public float getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(float experiencia) {
        if(getFelicidad()>0){
            this.experiencia = experiencia +getFuerza()*0.1f;
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
