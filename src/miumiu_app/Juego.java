package miumiu_app;

import character.MiuMiu;
import data.Acceso;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import java.util.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import static miumiu_app.Menu.clip1;
import static miumiu_app.Menu.clip2;
import static miumiu_app.Menu.pixelMplus;
import org.openide.util.Exceptions;

/**
 * Ventana del juego
 * @author Lourdes Navarro Tocón
 */
public class Juego extends javax.swing.JFrame {
    
    File xml; //Archivo xml donde se guardan los datos del personaje
    ImageIcon img, standing, eating, sleeping, training, cleaning, dirty, lvlup, dead, state; //Imágenes a incluir
    MiuMiu m; //Personaje
    //Timer y timertask
    Timer timer;
    TimerTask tarea;
    //Sonidos para las diferentes acciones del juego
    AudioInputStream audioEat, audioClean, audioTrain, audioSleep, audioDirty, audioLevelUp, audioDead, audioWin; 
    Clip clipEat, clipClean, clipTrain, clipSleep, clipDirty, clipLevelUp, clipDead, clipWin;
    boolean doingAction; //Booleano que controla si el personaje se encuentra realizando una acción

    /**
     * Constructor
     * @throws javax.sound.sampled.UnsupportedAudioFileException UnsupportedAudioFileException
     * @throws java.io.IOException IOException
     * @throws javax.sound.sampled.LineUnavailableException LineUnavailableException
     */
    public Juego() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        xml = new File("src/data/save.xml");
        
        img = new ImageIcon("src/resources/icon.png");
        standing = new ImageIcon("src/resources/standing.gif");
        eating = new ImageIcon("src/resources/eating.gif");
        sleeping = new ImageIcon("src/resources/sleeping.gif");
        training = new ImageIcon("src/resources/training.gif");
        cleaning = new ImageIcon("src/resources/clean.gif");
        dirty = new ImageIcon("src/resources/dirty.gif");
        lvlup = new ImageIcon("src/resources/lvlup.gif");
        dead = new ImageIcon("src/resources/dead.gif");
        
        clipEat = AudioSystem.getClip();
        audioEat = AudioSystem.getAudioInputStream(new File("src/resources/sounds/eat.wav"));
        clipEat.open(audioEat);
        clipClean = AudioSystem.getClip();
        audioClean = AudioSystem.getAudioInputStream(new File("src/resources/sounds/clean.wav"));
        clipClean.open(audioClean);
        clipDirty = AudioSystem.getClip();
        audioDirty = AudioSystem.getAudioInputStream(new File("src/resources/sounds/dirty.wav"));
        clipDirty.open(audioDirty);
        clipTrain = AudioSystem.getClip();
        audioTrain = AudioSystem.getAudioInputStream(new File("src/resources/sounds/training.wav"));
        clipTrain.open(audioTrain);
        clipSleep = AudioSystem.getClip();
        audioSleep = AudioSystem.getAudioInputStream(new File("src/resources/sounds/sleep.wav"));
        clipSleep.open(audioSleep);
        clipLevelUp = AudioSystem.getClip();
        audioLevelUp = AudioSystem.getAudioInputStream(new File("src/resources/sounds/levelup.wav"));
        clipLevelUp.open(audioLevelUp);
        clipDead = AudioSystem.getClip();
        audioDead = AudioSystem.getAudioInputStream(new File("src/resources/sounds/dead.wav"));
        clipDead.open(audioDead);
        
        state = standing;
        
        //Abrimos el archivo xml para obtener los datos y manejarlos
        Acceso.abrirXML(xml);
        String[] datos = Acceso.obtenerDatos();
        m = new MiuMiu(datos[0], Float.parseFloat(datos[1]), Float.parseFloat(datos[2]), Float.parseFloat(datos[3]), Float.parseFloat(datos[4]), Float.parseFloat(datos[5]),Float.parseFloat(datos[6]),Integer.parseInt(datos[7])); //Declaramos el objeto de la clase MiuMiu pasándole los valores guardados en el xml
        
        doingAction = false; //El personaje no está realizando ninguna acción por defecto
        
        initComponents();
        
        //El sonido del menú se pausa
        clip1.setFramePosition(0);
        clip1.stop();
        
        //Colocamos las etiquetas del nombre del MiuMiu y su nivel
        lbMiuName.setText(m.getName()); 
        lbMiuLevel.setText("Nivel "+m.getNivel());
        
        //Este botón y etiqueta se habilitan cuando el personaje muere
        btnRenacer.setVisible(false);
        lbEnd.setVisible(false);
        lbEnd1.setVisible(false);
       
        updateProgress();
    }
    
    /**
     * Método que actualiza los valores de los stats del personaje y los refleja en las barras de progreso
     */
    private void updateProgress(){
       progrHambre.setValue((int) m.getHambre());
       progrSuciedad.setValue((int)m.getSuciedad());
       progrFuerza.setValue((int)m.getFuerza());
       progrEnergia.setValue((int)m.getEnergia());
       if(m.getEnergia()<20){
           lbEnergia.setForeground(Color.red); //Para avisar de que la energía del personaje es muy baja y puede morir
       } else {
           float[] gris = new float[3];
           Color.RGBtoHSB(153, 153, 153, gris);
           lbEnergia.setForeground(Color.getHSBColor(gris[0], gris[1], gris[2]));
       }
       progrFelicidad.setValue((int)m.getFelicidad());
       progrExp.setValue((int)m.getExperiencia());
    }
    
    /**
     * Método para que el personaje suba de nivel
     */
    private void levelUp(){
        if(m.getExperiencia()>=100 && !doingAction){ //Para que suba de nivel se debe alcanzar 100 de experiencia
            doingAction = true;
            int nivel = m.getNivel();
            m.setNivel(nivel+1);
            m.setExperiencia(0);
            m.setFuerza(0);
            lbMiuLevel.setText("Nivel "+m.getNivel());
            
            MiuCh.setIcon(lvlup);
            clipLevelUp.start();
            clipLevelUp.setFramePosition(0);

            tarea = new TimerTask(){ //Utilizamos el timertask y el timer para que la imagen de la acción dure determinado tiempo
            @Override
                public void run() {
                    MiuCh.setIcon(state);
                    doingAction = false;
                }
            };
        
            timer = new Timer();
            timer.schedule(tarea, 1000);
            
        }
    }
    
    /**
     * Método para que el personaje muera
     */
    private void muerto(){
        if(m.getEnergia()<0 && !doingAction){ //Para que muera la energía debe ser menor que cero
            doingAction = true;
            MiuCh.setIcon(dead);
            btnRenacer.setVisible(true);
            lbEnd.setVisible(true);
            lbEnd1.setVisible(true);
            m.muerto();
            btnEat.setEnabled(false);
            btnBath.setEnabled(false);
            btnTrain.setEnabled(false);
            btnSleep.setEnabled(false);
            btnPause.setEnabled(false);
            clipDead.start();
            clipDead.setFramePosition(0);
        }
    }
    
    /**
     * Método para que el personaje se ensucie
     */
    private void sucio(){
        if(m.getSuciedad()==100 && !doingAction){ //Para que el personaje esté sucio su suciedad debe llegar a 100
            state = dirty;
            clipDirty.start();
            clipDirty.setFramePosition(0);
            m.addFelicidad(-50);
        }
    }
    
    /**
     * Método para que el personaje esté hambriento
     */
    private void hambriento(){
        if(m.getHambre()>=100){ //Si su hambre es igual o mayor a 100, la energía irá disminuyendo en 10 por cada acción hasta que su hambre baje
            m.addEnergia(-10);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogPause = new javax.swing.JDialog();
        panelPause = new javax.swing.JPanel();
        btnCont = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        panelGame = new javax.swing.JPanel();
        lbMiuLevel = new javax.swing.JLabel();
        MiuCh = new javax.swing.JLabel();
        btnEat = new javax.swing.JButton();
        btnBath = new javax.swing.JButton();
        btnTrain = new javax.swing.JButton();
        btnSleep = new javax.swing.JButton();
        lbHambre = new javax.swing.JLabel();
        lbSuciedad = new javax.swing.JLabel();
        lbFuerza = new javax.swing.JLabel();
        lbEnergia = new javax.swing.JLabel();
        lbFelicidad = new javax.swing.JLabel();
        lbExp = new javax.swing.JLabel();
        progrHambre = new javax.swing.JProgressBar();
        progrSuciedad = new javax.swing.JProgressBar();
        progrFuerza = new javax.swing.JProgressBar();
        progrEnergia = new javax.swing.JProgressBar();
        progrFelicidad = new javax.swing.JProgressBar();
        progrExp = new javax.swing.JProgressBar();
        lbMiuName = new javax.swing.JLabel();
        lbEnd = new javax.swing.JLabel();
        lbEnd1 = new javax.swing.JLabel();
        btnRenacer = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();

        dialogPause.setTitle("MiuMiu");
        dialogPause.setIconImage(img.getImage());
        dialogPause.setPreferredSize(new java.awt.Dimension(500, 400));
        dialogPause.setResizable(false);
        dialogPause.setSize(new java.awt.Dimension(500, 400));

        panelPause.setBackground(new java.awt.Color(254, 236, 214));
        panelPause.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCont.setBackground(new java.awt.Color(255, 255, 255));
        btnCont.setFont(pixelMplus);
        btnCont.setForeground(new java.awt.Color(105, 171, 99));
        btnCont.setText("Continuar");
        btnCont.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(105, 171, 99), 1, true));
        btnCont.setContentAreaFilled(false);
        btnCont.setOpaque(true);
        btnCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContActionPerformed(evt);
            }
        });
        panelPause.add(btnCont, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 390, 48));

        btnExit.setBackground(new java.awt.Color(255, 255, 255));
        btnExit.setFont(pixelMplus);
        btnExit.setForeground(new java.awt.Color(155, 184, 237));
        btnExit.setText("Guardar y salir");
        btnExit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(155, 184, 237), 1, true));
        btnExit.setContentAreaFilled(false);
        btnExit.setOpaque(true);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        panelPause.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 390, 48));

        javax.swing.GroupLayout dialogPauseLayout = new javax.swing.GroupLayout(dialogPause.getContentPane());
        dialogPause.getContentPane().setLayout(dialogPauseLayout);
        dialogPauseLayout.setHorizontalGroup(
            dialogPauseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPause, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        dialogPauseLayout.setVerticalGroup(
            dialogPauseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPause, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MiuMiu");
        setIconImage(img.getImage());
        setResizable(false);
        setSize(new java.awt.Dimension(700, 600));

        panelGame.setBackground(new java.awt.Color(254, 236, 214));
        panelGame.setMinimumSize(new java.awt.Dimension(700, 600));
        panelGame.setName(""); // NOI18N
        panelGame.setPreferredSize(new java.awt.Dimension(700, 600));
        panelGame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbMiuLevel.setFont(pixelMplus);
        lbMiuLevel.setForeground(new java.awt.Color(155, 184, 237));
        lbMiuLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelGame.add(lbMiuLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 146, 256, 30));

        MiuCh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MiuCh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/standing.gif"))); // NOI18N
        panelGame.add(MiuCh, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 194, 312, -1));

        btnEat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/food.png"))); // NOI18N
        btnEat.setBorderPainted(false);
        btnEat.setContentAreaFilled(false);
        btnEat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEatActionPerformed(evt);
            }
        });
        panelGame.add(btnEat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        btnBath.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bath.png"))); // NOI18N
        btnBath.setBorderPainted(false);
        btnBath.setContentAreaFilled(false);
        btnBath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBathActionPerformed(evt);
            }
        });
        panelGame.add(btnBath, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 460, -1, -1));

        btnTrain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sword.png"))); // NOI18N
        btnTrain.setBorderPainted(false);
        btnTrain.setContentAreaFilled(false);
        btnTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrainActionPerformed(evt);
            }
        });
        panelGame.add(btnTrain, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 480, -1, -1));

        btnSleep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bed.png"))); // NOI18N
        btnSleep.setBorderPainted(false);
        btnSleep.setContentAreaFilled(false);
        btnSleep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSleepActionPerformed(evt);
            }
        });
        panelGame.add(btnSleep, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 470, -1, -1));

        lbHambre.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbHambre.setForeground(new java.awt.Color(153, 153, 153));
        lbHambre.setText("Hambre");
        panelGame.add(lbHambre, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 23, -1, -1));

        lbSuciedad.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbSuciedad.setForeground(new java.awt.Color(153, 153, 153));
        lbSuciedad.setText("Suciedad");
        panelGame.add(lbSuciedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 56, -1, -1));

        lbFuerza.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbFuerza.setForeground(new java.awt.Color(153, 153, 153));
        lbFuerza.setText("Fuerza");
        panelGame.add(lbFuerza, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 89, -1, -1));

        lbEnergia.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbEnergia.setForeground(new java.awt.Color(153, 153, 153));
        lbEnergia.setText("Energia");
        panelGame.add(lbEnergia, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 23, -1, -1));

        lbFelicidad.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbFelicidad.setForeground(new java.awt.Color(153, 153, 153));
        lbFelicidad.setText("Felicidad");
        panelGame.add(lbFelicidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 56, -1, -1));

        lbExp.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbExp.setForeground(new java.awt.Color(153, 153, 153));
        lbExp.setText("EXP");
        panelGame.add(lbExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(387, 89, -1, -1));

        progrHambre.setForeground(new java.awt.Color(105, 171, 99));
        panelGame.add(progrHambre, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 23, 100, -1));

        progrSuciedad.setForeground(new java.awt.Color(105, 171, 99));
        panelGame.add(progrSuciedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 56, 100, -1));

        progrFuerza.setForeground(new java.awt.Color(105, 171, 99));
        panelGame.add(progrFuerza, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 89, 100, -1));

        progrEnergia.setForeground(new java.awt.Color(105, 171, 99));
        panelGame.add(progrEnergia, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 23, 100, -1));

        progrFelicidad.setForeground(new java.awt.Color(105, 171, 99));
        panelGame.add(progrFelicidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 56, 100, -1));

        progrExp.setForeground(new java.awt.Color(105, 171, 99));
        panelGame.add(progrExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 89, 100, -1));

        lbMiuName.setFont(pixelMplus);
        lbMiuName.setForeground(new java.awt.Color(51, 51, 51));
        lbMiuName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelGame.add(lbMiuName, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 405, 246, 37));

        lbEnd.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbEnd.setForeground(new java.awt.Color(153, 153, 153));
        lbEnd.setText("¡Oh no!");
        panelGame.add(lbEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, -1, -1));

        lbEnd1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbEnd1.setForeground(new java.awt.Color(153, 153, 153));
        lbEnd1.setText("Se ha agotado su energía :(");
        panelGame.add(lbEnd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, -1, -1));

        btnRenacer.setBackground(new java.awt.Color(255, 255, 255));
        btnRenacer.setFont(pixelMplus);
        btnRenacer.setForeground(new java.awt.Color(105, 171, 99));
        btnRenacer.setText("Renacer");
        btnRenacer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(105, 171, 99), 1, true));
        btnRenacer.setContentAreaFilled(false);
        btnRenacer.setOpaque(true);
        btnRenacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenacerActionPerformed(evt);
            }
        });
        panelGame.add(btnRenacer, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 140, 50));

        btnPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pause.png"))); // NOI18N
        btnPause.setBorderPainted(false);
        btnPause.setContentAreaFilled(false);
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });
        panelGame.add(btnPause, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 70, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que permite alimentar al personaje al pulsar el botón de comida
     * @param evt Action Event
     */
    private void btnEatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEatActionPerformed
        // TODO add your handling code here:
        if(!doingAction){
            boolean res = m.comer();
            if(res){ //Comprobamos que pueda comer
                doingAction = true;
                MiuCh.setIcon(eating);
                clipEat.start();
                clipEat.setFramePosition(0);

                tarea = new TimerTask(){ //Utilizamos el timertask y el timer para que la imagen de la acción dure determinado tiempo
                @Override
                    public void run() {
                        doingAction = false;
                        sucio(); //Comprobamos si se ensucia
                        MiuCh.setIcon(state);
                        levelUp(); //Comprobamos si sube de nivel
                        muerto(); //Comprobamos si se muere
                        updateProgress(); //Actualizamos los valores
                    }
                };
        
                timer = new Timer();
                timer.schedule(tarea, 1000);
            }
        }
    }//GEN-LAST:event_btnEatActionPerformed

    /**
     * Método que permite limpiar al personaje al pulsar el botón de baño
     * @param evt Action Event
     */
    private void btnBathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBathActionPerformed
        // TODO add your handling code here:
        if(m.getSuciedad()>0 && !doingAction){ //Si el personaje tiene suciedad y no está realizando ninguna acción, se procede a limpiarlo
            doingAction = true;
            m.limpiar();
            state = standing;
            MiuCh.setIcon(cleaning);
            clipClean.start();
            clipClean.setFramePosition(0);

            tarea = new TimerTask(){ //Utilizamos el timertask y el timer para que la imagen de la acción dure determinado tiempo
            @Override
                public void run() {
                    MiuCh.setIcon(state);
                    doingAction = false;
                    hambriento(); //Comprobamos si está hambriento
                    levelUp(); //Comprobamos si sube de nivel
                    updateProgress(); //Actualizamos los valores
                }
            };
        
            timer = new Timer();
            timer.schedule(tarea, 1300);
        }
    }//GEN-LAST:event_btnBathActionPerformed

    /**
     * Método que permite entrenar al personaje al pulsar el botón de entrenar
     * @param evt Action Event
     */
    private void btnTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrainActionPerformed
        // TODO add your handling code here:
        if(!doingAction){
            boolean res = m.entrenar();
            if(res){ //Comprobamos que pueda entrenar
                doingAction = true;
                MiuCh.setIcon(training);
                clipTrain.start();
                clipTrain.setFramePosition(0);

                tarea = new TimerTask(){ //Utilizamos el timertask y el timer para que la imagen de la acción dure determinado tiempo
                    @Override
                    public void run() {
                        doingAction = false;
                        hambriento();
                        sucio();
                        MiuCh.setIcon(state);
                        levelUp();
                        muerto();
                        updateProgress();
                    }
                };
        
                timer = new Timer();
                timer.schedule(tarea, 1000);
            }
        }
    }//GEN-LAST:event_btnTrainActionPerformed

    /**
     * Método que permite que el personaje duerma al pulsar el botón de dormir
     * @param evt Action Event
     */
    private void btnSleepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSleepActionPerformed
        // TODO add your handling code here:
        if(m.getEnergia()<50 && !doingAction){ //Si su energia es menor de 50 y no está realizando ninguna acción, el personaje puede dormir
           doingAction = true;
           m.dormir();
           MiuCh.setIcon(sleeping);
           clipSleep.start();
           clipSleep.setFramePosition(0);

            tarea = new TimerTask(){ //Utilizamos el timertask y el timer para que la imagen de la acción dure determinado tiempo
            @Override
                public void run() {
                    doingAction = false;
                    hambriento(); //Comprobamos si está hambriento
                    sucio(); //Comprobamos si está sucio
                    MiuCh.setIcon(state);
                    levelUp(); //Comprobamos si sube de nivel
                    updateProgress(); //Actualizamos los valores
                }
            };
        
            timer = new Timer();
            timer.schedule(tarea, 1800);
        }
    }//GEN-LAST:event_btnSleepActionPerformed

    /**
     * Método que permite salir al menú y guardar la partida
     * @param evt Action Event
     */
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        clip2.start(); //Sonido de botón
        clip2.setFramePosition(0);
        Acceso.guardarDatos(m.getName(), m.getHambre(), m.getSuciedad(), m.getFuerza(), m.getEnergia(), m.getFelicidad(), m.getExperiencia(), m.getNivel()); //Guardamos los datos antes de salir
        Acceso.sobreescribir();
        Menu m;
        try {
            m = new Menu(); 
            dialogPause.setVisible(false); //Se cierra la ventana de pausa y de la partida y se abre la del menú
            this.dispose();
            m.setVisible(true);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * Método que permite volver a empezar la partida una vez que el personaje se ha muerto al darle al botón de renacer
     * @param evt Action Event
     */
    private void btnRenacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenacerActionPerformed
        // TODO add your handling code here:
        clip2.start(); //Sonido del botón
        clip2.setFramePosition(0);
        Datos d;
        try {
            d = new Datos(); 
            Acceso.guardarDatos("none", 0, 0, 0, 0, 0, 0, 0); //Establecemos los stats del personaje por defecto
            Acceso.sobreescribir();
            this.dispose();
            d.setVisible(true); //Se cierra la ventana de la partida y se abre la del formulario
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
            Exceptions.printStackTrace(ex);
        }
        clip1.start(); //Música del menú
        clip1.loop(-1);
    }//GEN-LAST:event_btnRenacerActionPerformed

    /**
     * Método que permite pausar el juego al darle al botón de pausa
     * @param evt Action Event
     */
    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        // TODO add your handling code here:
        clip2.start(); //Sonido del botón
        clip2.setFramePosition(0);
        dialogPause.setVisible(true); //Se abre la ventana de pausa
        dialogPause.isModal();
    }//GEN-LAST:event_btnPauseActionPerformed

    /**
     * Método que permite continuar la partida al pulsar el botón de continuar
     * @param evt Action Event
     */
    private void btnContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContActionPerformed
        // TODO add your handling code here:
        clip2.start(); //Sonido del botón
        clip2.setFramePosition(0);
        dialogPause.setVisible(false); //Se cierra la ventana de pausa
    }//GEN-LAST:event_btnContActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Juego().setVisible(true);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        });
        
        //Definimos la fuente que importamos
        try{
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/PixelMplus10-Regular.ttf")).deriveFont(30f);	
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/PixelMplus10-Regular.ttf")));			
	} catch(IOException | FontFormatException e){
            e.printStackTrace();
	}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MiuCh;
    private javax.swing.JButton btnBath;
    private javax.swing.JButton btnCont;
    private javax.swing.JButton btnEat;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnRenacer;
    private javax.swing.JButton btnSleep;
    private javax.swing.JButton btnTrain;
    private javax.swing.JDialog dialogPause;
    private javax.swing.JLabel lbEnd;
    private javax.swing.JLabel lbEnd1;
    private javax.swing.JLabel lbEnergia;
    private javax.swing.JLabel lbExp;
    private javax.swing.JLabel lbFelicidad;
    private javax.swing.JLabel lbFuerza;
    private javax.swing.JLabel lbHambre;
    private javax.swing.JLabel lbMiuLevel;
    private javax.swing.JLabel lbMiuName;
    private javax.swing.JLabel lbSuciedad;
    private javax.swing.JPanel panelGame;
    private javax.swing.JPanel panelPause;
    private javax.swing.JProgressBar progrEnergia;
    private javax.swing.JProgressBar progrExp;
    private javax.swing.JProgressBar progrFelicidad;
    private javax.swing.JProgressBar progrFuerza;
    private javax.swing.JProgressBar progrHambre;
    private javax.swing.JProgressBar progrSuciedad;
    // End of variables declaration//GEN-END:variables
}
