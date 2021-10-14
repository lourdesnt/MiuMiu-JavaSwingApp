package character;

/**
 * Clase con los datos del personaje MiuMiu
 * @author Lourdes
 */
public class MiuMiu {
    
    String name; //Nombre del personaje
    //Valores de energia, hambre, fuerza, suciedad, felicidad, experiencia y nivel
    float energia, hambre, fuerza, suciedad, felicidad, experiencia;
    int nivel;
    
    /**
     * Constructor que admite 8 parámetros
     * @param name Nombre del personaje
     * @param hambre Hambre del personaje
     * @param suciedad Suciedad del personaje
     * @param fuerza Fuerza del personaje
     * @param energia Energia del personaje
     * @param felicidad Felicidad del personaje
     * @param experiencia Experiencia del personaje
     * @param nivel Nivel del personaje
     */
    public MiuMiu(String name, float hambre, float suciedad, float fuerza, float energia, float felicidad, float experiencia, int nivel) {
        this.name = name;
        this.hambre = hambre;
        this.suciedad = suciedad;
        this.fuerza = fuerza;
        this.energia = energia;
        this.felicidad = felicidad;
        this.experiencia = experiencia;
        this.nivel = nivel;
    }
    
    /**
     * Método que suma valores o resta a los stats del personaje cuando come
     * @return true si ha podido comer, false si no
     */
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
    
    /**
     * Método que suma valores o resta a los stats del personaje cuando entrena
     * @return true si ha podido entrenar, false si no
     */
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
    
    /**
     * Método que suma valores o resta a los stats del personaje cuando se baña
     */
    public void limpiar(){
        setSuciedad(0);
        addFelicidad(2);
        setExperiencia(experiencia+2);
    }
    
    /**
     * Método que suma valores o resta a los stats del personaje cuando duerme
     */
    public void dormir(){
        setEnergia(100);
        addFelicidad(3);
        setExperiencia(experiencia+1);
    }
    
    /**
     * Método que pone todos los valores de los stats a cero, significando eso que se ha muerto
     */
    public void muerto(){
        setEnergia(0);
        setHambre(0);
        setFuerza(0);
        setSuciedad(0);
        setFelicidad(0);
        setExperiencia(0);
    }
    
    //Getters y setters
    
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
        if(hambre<0){ //Controlamos que no sea negativo
            return false;
        }
        if(hambre>100){ //Controlamos que el máx sea 100
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
        if(suciedad>=100 && this.felicidad<felicidad){ //Si la suciedad está a 100 o si la felicidad a añadir/restar es mayor que la actual no se añade felicidad
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
            this.experiencia = experiencia +getFuerza()*0.1f; //La experiencia es proporcional a la fuerza del personaje
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
