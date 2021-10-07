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
    int energia, hambre, fuerza, suciedad, felicidad, experiencia, nivel;
    
    public MiuMiu(String name){
        this.name=name;
        this.energia=50;
        this.hambre=20;
        this.fuerza=20;
        this.suciedad=20;
        this.felicidad=50;
        this.experiencia=0;
        this.nivel=1;
    }
    
    public void comer(){
        energia+=20;
        hambre-=50;
        suciedad+=10;
        felicidad+=20;
        experiencia+=10;
    }
    
    public void entrenar(){
        energia-=30;
        hambre+=20;
        fuerza+=30;
        suciedad+=30;
        felicidad+=20;
        experiencia+=10;
    }
    
    public void limpiar(){
        suciedad=0;
        felicidad+=20;
        experiencia+=10;
    }
    
    public void dormir(){
        energia=100;
        felicidad+=20;
        experiencia+=10;
    }
    
    public void muerto(){
        energia=0;
        hambre=0;
        fuerza=0;
        suciedad=0;
        felicidad=0;
        experiencia=0;
    }
    
    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getSuciedad() {
        return suciedad;
    }

    public void setSuciedad(int suciedad) {
        this.suciedad = suciedad;
    }

    public int getFelicidad() {
        return felicidad;
    }

    public void setFelicidad(int felicidad) {
        this.felicidad = felicidad;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
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
