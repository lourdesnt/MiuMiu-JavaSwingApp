/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miumiu_app;

import character.MiuMiu;
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
import static miumiu_app.Menu.pixelMplus;
import org.openide.util.Exceptions;

/**
 *
 * @author Lourdes
 */
public class Juego extends javax.swing.JFrame {
    
    ImageIcon img, standing, eating, sleeping, training, cleaning, dirty, lvlup, dead, state;
    MiuMiu m;
    Timer timer;
    TimerTask tarea;
    AudioInputStream audioEat, audioClean, audioTrain, audioSleep, audioDirty, audioLevelUp, audioDead, audioWin;
    Clip clipEat, clipClean, clipTrain, clipSleep, clipDirty, clipLevelUp, clipDead, clipWin;
    boolean doingAction;

    /**
     * Creates new form Juego
     */
    public Juego() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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
        clipWin = AudioSystem.getClip();
        audioWin = AudioSystem.getAudioInputStream(new File("src/resources/sounds/win.wav"));
        clipWin.open(audioWin);
        
        state = standing;
        
        m = new MiuMiu(Datos.MiuMiuName);
        
        doingAction = false;
        
        initComponents();
        
        lbMiuName.setText(m.getName());
        lbMiuLevel.setText("Nivel "+m.getNivel());
       
        updateProgress();
    }
    
    private void updateProgress(){
       progrHambre.setValue((int) m.getHambre());
       progrSuciedad.setValue((int)m.getSuciedad());
       progrFuerza.setValue((int)m.getFuerza());
       progrEnergia.setValue((int)m.getEnergia());
       progrFelicidad.setValue((int)m.getFelicidad());
       progrExp.setValue((int)m.getExperiencia());
    }
    
    private void levelUp(){
        if(m.getExperiencia()>=100 && !doingAction){
            doingAction = true;
            int nivel = m.getNivel();
            m.setNivel(nivel+1);
            m.setExperiencia(0);
            m.setFuerza(0);
            lbMiuLevel.setText("Nivel "+m.getNivel());
            
            MiuCh.setIcon(lvlup);
            clipLevelUp.start();
            clipLevelUp.setFramePosition(0);

            tarea = new TimerTask(){
            @Override
                public void run() {
                    MiuCh.setIcon(state);
                    doingAction = false;
                }
            };
        
            timer = new Timer();
            timer.schedule(tarea, 1000);
            
            if(m.getNivel()==10){
                dialogWin.setVisible(true);
            }
        }
    }
    
    private void muerto(){
        if(m.getEnergia()<0 && !doingAction){
            doingAction = true;
            MiuCh.setIcon(dead);
            clipDead.start();
            clipDead.setFramePosition(0);
            m.muerto();
            dialogGameOver.setVisible(true);
        }
    }
    
    private void sucio(){
        if(m.getSuciedad()==100 && !doingAction){
            state = dirty;
            clipDirty.start();
            clipDirty.setFramePosition(0);
            m.addFelicidad(-50);
        }
    }
    
    private void hambriento(){
        if(m.getHambre()>=100){
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

        dialogWin = new javax.swing.JDialog();
        panelPpal = new javax.swing.JPanel();
        lbWin = new javax.swing.JLabel();
        lbWin2 = new javax.swing.JLabel();
        lbWin3 = new javax.swing.JLabel();
        lbMiuWin = new javax.swing.JLabel();
        dialogGameOver = new javax.swing.JDialog();
        panelPpal2 = new javax.swing.JPanel();
        lbGOver = new javax.swing.JLabel();
        lbGOver2 = new javax.swing.JLabel();
        lbGOver3 = new javax.swing.JLabel();
        btnAgain = new javax.swing.JButton();
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

        dialogWin.setTitle("MiuMiu");
        dialogWin.setIconImage(img.getImage());
        dialogWin.setPreferredSize(new java.awt.Dimension(500, 400));
        dialogWin.setSize(new java.awt.Dimension(500, 400));

        panelPpal.setBackground(new java.awt.Color(254, 236, 214));

        lbWin.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lbWin.setForeground(new java.awt.Color(89, 173, 208));
        lbWin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbWin.setText("¡ENHORABUENA!");

        lbWin2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbWin2.setForeground(new java.awt.Color(255, 102, 255));
        lbWin2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbWin2.setText("¡Tu MiuMiu ha crecido mucho!");

        lbWin3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbWin3.setForeground(new java.awt.Color(105, 171, 99));
        lbWin3.setText("Ahora es muy sano, fuerte y feliz gracias a ti :)");

        lbMiuWin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/victory.gif"))); // NOI18N

        javax.swing.GroupLayout panelPpalLayout = new javax.swing.GroupLayout(panelPpal);
        panelPpal.setLayout(panelPpalLayout);
        panelPpalLayout.setHorizontalGroup(
            panelPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPpalLayout.createSequentialGroup()
                .addGroup(panelPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPpalLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lbMiuWin))
                    .addGroup(panelPpalLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(lbWin2))
                    .addGroup(panelPpalLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(lbWin))
                    .addGroup(panelPpalLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(lbWin3)))
                .addContainerGap(223, Short.MAX_VALUE))
        );
        panelPpalLayout.setVerticalGroup(
            panelPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPpalLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(lbWin)
                .addGap(18, 18, 18)
                .addComponent(lbWin2)
                .addGap(18, 18, 18)
                .addComponent(lbWin3)
                .addGap(39, 39, 39)
                .addComponent(lbMiuWin)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout dialogWinLayout = new javax.swing.GroupLayout(dialogWin.getContentPane());
        dialogWin.getContentPane().setLayout(dialogWinLayout);
        dialogWinLayout.setHorizontalGroup(
            dialogWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPpal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogWinLayout.setVerticalGroup(
            dialogWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPpal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dialogGameOver.setTitle("MiuMiu");
        dialogGameOver.setIconImage(img.getImage());
        dialogGameOver.setPreferredSize(new java.awt.Dimension(600, 337));
        dialogGameOver.setResizable(false);
        dialogGameOver.setSize(new java.awt.Dimension(600, 337));

        panelPpal2.setBackground(new java.awt.Color(254, 236, 214));
        panelPpal2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbGOver.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lbGOver.setForeground(new java.awt.Color(153, 153, 153));
        lbGOver.setText("¡Oh no!");
        panelPpal2.add(lbGOver, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        lbGOver2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbGOver2.setForeground(new java.awt.Color(112, 149, 225));
        lbGOver2.setText("No has cuidado bien a tu MiuMiu y se ha quedado sin energía :(");
        panelPpal2.add(lbGOver2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        lbGOver3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbGOver3.setForeground(new java.awt.Color(153, 153, 255));
        lbGOver3.setText("¿Quieres volver a intentarlo?");
        panelPpal2.add(lbGOver3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

        btnAgain.setBackground(new java.awt.Color(255, 255, 255));
        btnAgain.setFont(pixelMplus);
        btnAgain.setForeground(new java.awt.Color(105, 171, 99));
        btnAgain.setText("Volver a intentar");
        btnAgain.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(105, 171, 99), 1, true));
        btnAgain.setContentAreaFilled(false);
        btnAgain.setOpaque(true);
        btnAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgainActionPerformed(evt);
            }
        });
        panelPpal2.add(btnAgain, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 260, 48));

        javax.swing.GroupLayout dialogGameOverLayout = new javax.swing.GroupLayout(dialogGameOver.getContentPane());
        dialogGameOver.getContentPane().setLayout(dialogGameOverLayout);
        dialogGameOverLayout.setHorizontalGroup(
            dialogGameOverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPpal2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        dialogGameOverLayout.setVerticalGroup(
            dialogGameOverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPpal2, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MiuMiu");
        setIconImage(img.getImage());
        setResizable(false);
        setSize(new java.awt.Dimension(700, 600));

        panelGame.setBackground(new java.awt.Color(254, 236, 214));

        lbMiuLevel.setFont(pixelMplus);
        lbMiuLevel.setForeground(new java.awt.Color(155, 184, 237));
        lbMiuLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        MiuCh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MiuCh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/standing.gif"))); // NOI18N

        btnEat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/food.png"))); // NOI18N
        btnEat.setBorderPainted(false);
        btnEat.setContentAreaFilled(false);
        btnEat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEatActionPerformed(evt);
            }
        });

        btnBath.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bath.png"))); // NOI18N
        btnBath.setBorderPainted(false);
        btnBath.setContentAreaFilled(false);
        btnBath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBathActionPerformed(evt);
            }
        });

        btnTrain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sword.png"))); // NOI18N
        btnTrain.setBorderPainted(false);
        btnTrain.setContentAreaFilled(false);
        btnTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrainActionPerformed(evt);
            }
        });

        btnSleep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bed.png"))); // NOI18N
        btnSleep.setBorderPainted(false);
        btnSleep.setContentAreaFilled(false);
        btnSleep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSleepActionPerformed(evt);
            }
        });

        lbHambre.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbHambre.setForeground(new java.awt.Color(153, 153, 153));
        lbHambre.setText("Hambre");

        lbSuciedad.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbSuciedad.setForeground(new java.awt.Color(153, 153, 153));
        lbSuciedad.setText("Suciedad");

        lbFuerza.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbFuerza.setForeground(new java.awt.Color(153, 153, 153));
        lbFuerza.setText("Fuerza");

        lbEnergia.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbEnergia.setForeground(new java.awt.Color(153, 153, 153));
        lbEnergia.setText("Energia");

        lbFelicidad.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbFelicidad.setForeground(new java.awt.Color(153, 153, 153));
        lbFelicidad.setText("Felicidad");

        lbExp.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        lbExp.setForeground(new java.awt.Color(153, 153, 153));
        lbExp.setText("EXP");

        progrHambre.setForeground(new java.awt.Color(105, 171, 99));

        progrSuciedad.setForeground(new java.awt.Color(105, 171, 99));

        progrFuerza.setForeground(new java.awt.Color(105, 171, 99));

        progrEnergia.setForeground(new java.awt.Color(105, 171, 99));

        progrFelicidad.setForeground(new java.awt.Color(105, 171, 99));

        progrExp.setForeground(new java.awt.Color(105, 171, 99));

        lbMiuName.setFont(pixelMplus);
        lbMiuName.setForeground(new java.awt.Color(51, 51, 51));
        lbMiuName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelGameLayout = new javax.swing.GroupLayout(panelGame);
        panelGame.setLayout(panelGameLayout);
        panelGameLayout.setHorizontalGroup(
            panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGameLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSuciedad)
                    .addComponent(lbHambre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbFuerza, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(53, 53, 53)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(progrHambre, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(progrSuciedad, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(progrFuerza, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbExp)
                    .addComponent(lbFelicidad)
                    .addComponent(lbEnergia))
                .addGap(53, 53, 53)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(progrFelicidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(progrEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(progrExp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
            .addGroup(panelGameLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnEat)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGameLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelGameLayout.createSequentialGroup()
                                .addComponent(btnBath)
                                .addGap(20, 20, 20)
                                .addComponent(btnTrain))
                            .addComponent(lbMiuLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGameLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lbMiuName, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(btnSleep)
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MiuCh, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );
        panelGameLayout.setVerticalGroup(
            panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progrHambre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHambre)
                    .addComponent(lbEnergia)
                    .addComponent(progrEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progrSuciedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSuciedad)
                    .addComponent(lbFelicidad)
                    .addComponent(progrFelicidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progrExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelGameLayout.createSequentialGroup()
                        .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(progrFuerza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFuerza)
                            .addComponent(lbExp))
                        .addGap(42, 42, 42)
                        .addComponent(lbMiuLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(MiuCh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSleep, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGameLayout.createSequentialGroup()
                        .addComponent(lbMiuName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBath, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnTrain, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(btnEat, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(31, 31, 31))
        );

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

    private void btnEatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEatActionPerformed
        // TODO add your handling code here:
        if(!doingAction){
            boolean res = m.comer();
            if(res){
                doingAction = true;
                MiuCh.setIcon(eating);
                clipEat.start();
                clipEat.setFramePosition(0);

                tarea = new TimerTask(){
                @Override
                    public void run() {
                        doingAction = false;
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
    }//GEN-LAST:event_btnEatActionPerformed

    private void btnBathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBathActionPerformed
        // TODO add your handling code here:
        if(m.getSuciedad()>0 && !doingAction){
            doingAction = true;
            m.limpiar();
            state = standing;
            MiuCh.setIcon(cleaning);
            clipClean.start();
            clipClean.setFramePosition(0);

            tarea = new TimerTask(){
            @Override
                public void run() {
                    MiuCh.setIcon(state);
                    doingAction = false;
                    hambriento();
                    levelUp();
                    updateProgress();
                }
            };
        
            timer = new Timer();
            timer.schedule(tarea, 1300);
        }
    }//GEN-LAST:event_btnBathActionPerformed

    private void btnTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrainActionPerformed
        // TODO add your handling code here:
        if(!doingAction){
            boolean res = m.entrenar();
            if(res){
                doingAction = true;
                MiuCh.setIcon(training);
                clipTrain.start();
                clipTrain.setFramePosition(0);

                tarea = new TimerTask(){
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

    private void btnSleepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSleepActionPerformed
        // TODO add your handling code here:
        if(m.getEnergia()<50 && !doingAction){
           doingAction = true;
           m.dormir();
           MiuCh.setIcon(sleeping);
           clipSleep.start();
           clipSleep.setFramePosition(0);

            tarea = new TimerTask(){
            @Override
                public void run() {
                    doingAction = false;
                    hambriento();
                    sucio();
                    MiuCh.setIcon(state);
                    levelUp();
                    updateProgress();
                }
            };
        
            timer = new Timer();
            timer.schedule(tarea, 1800);
        }
    }//GEN-LAST:event_btnSleepActionPerformed

    private void btnAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgainActionPerformed
        // TODO add your handling code here:
        Datos d;
        try {
            d = new Datos();
            this.dispose();
            dialogGameOver.dispose();
            d.setVisible(true);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
            Exceptions.printStackTrace(ex);
        }
        clip1.start();
        clip1.loop(-1);
    }//GEN-LAST:event_btnAgainActionPerformed

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
    private javax.swing.JButton btnAgain;
    private javax.swing.JButton btnBath;
    private javax.swing.JButton btnEat;
    private javax.swing.JButton btnSleep;
    private javax.swing.JButton btnTrain;
    private javax.swing.JDialog dialogGameOver;
    private javax.swing.JDialog dialogWin;
    private javax.swing.JLabel lbEnergia;
    private javax.swing.JLabel lbExp;
    private javax.swing.JLabel lbFelicidad;
    private javax.swing.JLabel lbFuerza;
    private javax.swing.JLabel lbGOver;
    private javax.swing.JLabel lbGOver2;
    private javax.swing.JLabel lbGOver3;
    private javax.swing.JLabel lbHambre;
    private javax.swing.JLabel lbMiuLevel;
    private javax.swing.JLabel lbMiuName;
    private javax.swing.JLabel lbMiuWin;
    private javax.swing.JLabel lbSuciedad;
    private javax.swing.JLabel lbWin;
    private javax.swing.JLabel lbWin2;
    private javax.swing.JLabel lbWin3;
    private javax.swing.JPanel panelGame;
    private javax.swing.JPanel panelPpal;
    private javax.swing.JPanel panelPpal2;
    private javax.swing.JProgressBar progrEnergia;
    private javax.swing.JProgressBar progrExp;
    private javax.swing.JProgressBar progrFelicidad;
    private javax.swing.JProgressBar progrFuerza;
    private javax.swing.JProgressBar progrHambre;
    private javax.swing.JProgressBar progrSuciedad;
    // End of variables declaration//GEN-END:variables
}
