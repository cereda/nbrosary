/**
 * \cond LICENSE
 * ********************************************************************
 * This is a conditional block for preventing the DoxyGen documentation
 * tool to include this license header within the description of each
 * source code file. If you want to include this block, please define
 * the LICENSE parameter into the provided DoxyFile.
 * ********************************************************************
 *
 * nbRosary - A Rosary plugin for NetBeans IDE
 * Copyright (c) 2011, Paulo Roberto Massa Cereda
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. Neither the name of the project's author nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * ********************************************************************
 * End of the LICENSE conditional block
 * ********************************************************************
 * \endcond
 *
 * <b>RosaryUITopComponent.java</b>: creates the plugin main form. This class
 * contains the visual components.
 */

// package info
package net.sf.nbrosary.ui;

// needed imports
import java.util.logging.Logger;
import net.sf.nbrosary.HolyRosary;
import net.sf.nbrosary.about.RosaryAboutPanel;
import net.sf.nbrosary.utils.RosaryUtils;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 * Creates the plugin main form. This class contains the visual components.
 * It basically calls methods from the <code>net.sf.nbrosary.HolyRosary</code>
 * class, which effectively handles events.
 *
 * @author Paulo Roberto Massa Cereda
 * @version 1.0
 * @since 1.0
 */
@ConvertAsProperties(dtd = "-//net.sf.nbrosary.ui//RosaryUI//EN",
autostore = false)
public final class RosaryUITopComponent extends TopComponent {

    // singleton
    private static RosaryUITopComponent instance;

    // icon path
    static final String ICON_PATH = "net/sf/nbrosary/images/icons/rose16.png";

    // window id
    private static final String PREFERRED_ID = "RosaryUITopComponent";

    // start button flag
    private boolean startButton = true;

    // the holy rosary object
    private HolyRosary rosary;

    // rosary counter
    private int counter;

    /**
     * Constructor method.
     */
    public RosaryUITopComponent() {

        // init the class components
        initComponents();

        // set form title according to the localization file
        setName(NbBundle.getBundle("net.sf.nbrosary.i18n.Rosary").getString("rosary.title"));

        // set tooltip based on the localization file
        setToolTipText(NbBundle.getBundle("net.sf.nbrosary.i18n.Rosary").getString("rosary.tooltip"));

        // sets an icon
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));

        // make all form buttons to act as labels
        RosaryUtils.makeButtonActLikeLabel(btnStart);
        RosaryUtils.makeButtonActLikeLabel(btnPrevious);
        RosaryUtils.makeButtonActLikeLabel(btnNext);
        RosaryUtils.makeButtonActLikeLabel(btnAbout);

        // create a new rosary object
        rosary = new HolyRosary();

        // set all visual components to the rosary
        rosary.setButtonNext(btnNext);
        rosary.setButtonPrevious(btnPrevious);
        rosary.setButtonStart(btnStart);
        rosary.setImageMystery(imgMystery);
        rosary.setImageRosary(imgRosary);
        rosary.setLabelMysteryType(lblMysteries);
        rosary.setLabelMysteryText(lblMysteryDescription);
        rosary.setLabelMysteryTitle(lblMysteryTitle);

        // it didn't started yet, but we should stop it
        rosary.stop();

        // counter is zero at first
        counter = 0;

    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgMystery = new javax.swing.JLabel();
        imgRosary = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblMysteries = new javax.swing.JLabel();
        lblMysteryTitle = new javax.swing.JLabel();
        lblMysteryDescription = new javax.swing.JLabel();
        btnAbout = new javax.swing.JButton();

        imgMystery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/mysteries/none.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(imgMystery, org.openide.util.NbBundle.getMessage(RosaryUITopComponent.class, "RosaryUITopComponent.imgMystery.text")); // NOI18N

        imgRosary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/rosary/rosary0.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(imgRosary, org.openide.util.NbBundle.getMessage(RosaryUITopComponent.class, "RosaryUITopComponent.imgRosary.text")); // NOI18N

        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/buttons/play.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnStart, org.openide.util.NbBundle.getMessage(RosaryUITopComponent.class, "RosaryUITopComponent.btnStart.text")); // NOI18N
        btnStart.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/buttons/play_hover.png"))); // NOI18N
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/buttons/previous.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnPrevious, org.openide.util.NbBundle.getMessage(RosaryUITopComponent.class, "RosaryUITopComponent.btnPrevious.text")); // NOI18N
        btnPrevious.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/buttons/previous_hover.png"))); // NOI18N
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/buttons/next.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnNext, org.openide.util.NbBundle.getMessage(RosaryUITopComponent.class, "RosaryUITopComponent.btnNext.text")); // NOI18N
        btnNext.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/buttons/next_hover.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lblMysteries.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(lblMysteries, org.openide.util.NbBundle.getMessage(RosaryUITopComponent.class, "RosaryUITopComponent.lblMysteries.text")); // NOI18N

        lblMysteryTitle.setFont(new java.awt.Font("Tahoma", 2, 11));
        org.openide.awt.Mnemonics.setLocalizedText(lblMysteryTitle, org.openide.util.NbBundle.getMessage(RosaryUITopComponent.class, "RosaryUITopComponent.lblMysteryTitle.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblMysteryDescription, org.openide.util.NbBundle.getMessage(RosaryUITopComponent.class, "RosaryUITopComponent.lblMysteryDescription.text")); // NOI18N

        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/buttons/about.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnAbout, org.openide.util.NbBundle.getMessage(RosaryUITopComponent.class, "RosaryUITopComponent.btnAbout.text")); // NOI18N
        btnAbout.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/buttons/about_hover.png"))); // NOI18N
        btnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgRosary)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(imgMystery, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMysteryDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(lblMysteryTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(lblMysteries, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                .addGap(135, 135, 135)
                .addComponent(btnAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMysteries))
                        .addGap(18, 18, 18)
                        .addComponent(lblMysteryTitle)
                        .addGap(18, 18, 18)
                        .addComponent(lblMysteryDescription)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(imgMystery, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(imgRosary, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41))))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles events when clicking the start button.
     * @param evt The event.
     */
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        
        // if it is a start button
        if (startButton == true) {

            // start counter
            counter = 1;

            // and start rosary
            rosary.pray();

            // this is now a stop button
            startButton = false;
        }
        else {

            // reset counter
            counter = 0;

            // stop rosary
            rosary.stop();

            // and now this is a start button
            startButton = true;
        }
    }//GEN-LAST:event_btnStartActionPerformed

    /**
     * Handles events when clicking the previous button.
     * @param evt The event.
     */
    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        
        // call the previous method from the rosary object
        rosary.previous();

        // decrease counter
        counter--;
    }//GEN-LAST:event_btnPreviousActionPerformed

    /**
     * Handles events when clicking the next button.
     * @param evt The event.
     */
    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed

        // call the next method from the rosary object
        rosary.next();

        // increase counter
        counter++;

        // if this is the end of the rosary
        if (counter == 73) {

            // turn the stop button into a start button
            startButton = true;
        }
    }//GEN-LAST:event_btnNextActionPerformed

    /**
     * Handles events when clicking the about button.
     * @param evt The event.
     */
    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed

        // create a new about panel
        RosaryAboutPanel aboutPanel = new RosaryAboutPanel();

        // then create a new notify descriptor with the
        // newly created about panel
        NotifyDescriptor nd = new NotifyDescriptor(aboutPanel, "About nbRosary", NotifyDescriptor.PLAIN_MESSAGE, NotifyDescriptor.INFORMATION_MESSAGE, new Object[] { NotifyDescriptor.OK_OPTION }, null);

        // display the panel through NetBeans dialog system
        DialogDisplayer.getDefault().notify(nd);
    }//GEN-LAST:event_btnAboutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbout;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel imgMystery;
    private javax.swing.JLabel imgRosary;
    private javax.swing.JLabel lblMysteries;
    private javax.swing.JLabel lblMysteryDescription;
    private javax.swing.JLabel lblMysteryTitle;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized RosaryUITopComponent getDefault() {
        if (instance == null) {
            instance = new RosaryUITopComponent();
        }
        return instance;
    }

    /**
     * Obtain the RosaryUITopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized RosaryUITopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(RosaryUITopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof RosaryUITopComponent) {
            return (RosaryUITopComponent) win;
        }
        Logger.getLogger(RosaryUITopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
}
