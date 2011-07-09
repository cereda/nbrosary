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
 * <b>RosaryAboutPanel.java</b>: provides an about panel to be included as
 * a component for a NetBeans notification.
 */

// package info
package net.sf.nbrosary.about;

// needed imports
import org.openide.util.NbBundle;

/**
 * Provides an about panel to be included as a component for a NetBeans
 * notification. This class is used to show credits on this plugin.
 *
 * @author Paulo Roberto Massa Cereda
 * @version 1.0
 * @since 1.0
 */
public class RosaryAboutPanel extends javax.swing.JPanel {

    /**
     * Constructor method. Nothing new in here.
     */
    public RosaryAboutPanel() {

        // init the panel components
        initComponents();

        // set the specific texts about languages and localizations
        lblLanguage.setText("The current language is " + NbBundle.getBundle("net.sf.nbrosary.i18n.Rosary").getString("rosary.language.name"));
        lblLanguageAuthor.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 250, "This language localization is brought to you by " + NbBundle.getBundle("net.sf.nbrosary.i18n.Rosary").getString("rosary.language.author")));
        lblCredits.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 250, "The Rosary images are provided by The Holy Rosary website (http://www.theholyrosary.org)"));
    }

    /** 
     * This method is called from within the constructor to initialize the
     * form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgFlower = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        lblAuthorName = new javax.swing.JLabel();
        lblLicenseInfo = new javax.swing.JLabel();
        lblLanguage = new javax.swing.JLabel();
        lblLanguageAuthor = new javax.swing.JLabel();
        lblHomepageInfo = new javax.swing.JLabel();
        lblCredits = new javax.swing.JLabel();

        imgFlower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/icons/rose32.png"))); // NOI18N
        imgFlower.setText(org.openide.util.NbBundle.getMessage(RosaryAboutPanel.class, "RosaryAboutPanel.imgFlower.text")); // NOI18N

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTitle.setText(org.openide.util.NbBundle.getMessage(RosaryAboutPanel.class, "RosaryAboutPanel.lblTitle.text")); // NOI18N

        lblSubtitle.setText(org.openide.util.NbBundle.getMessage(RosaryAboutPanel.class, "RosaryAboutPanel.lblSubtitle.text")); // NOI18N

        lblAuthorName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/icons/user.png"))); // NOI18N
        lblAuthorName.setText(org.openide.util.NbBundle.getMessage(RosaryAboutPanel.class, "RosaryAboutPanel.lblAuthorName.text")); // NOI18N

        lblLicenseInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/icons/license.png"))); // NOI18N
        lblLicenseInfo.setText(org.openide.util.NbBundle.getMessage(RosaryAboutPanel.class, "RosaryAboutPanel.lblLicenseInfo.text")); // NOI18N

        lblLanguage.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblLanguage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/icons/language.png"))); // NOI18N
        lblLanguage.setText(org.openide.util.NbBundle.getMessage(RosaryAboutPanel.class, "RosaryAboutPanel.lblLanguage.text")); // NOI18N

        lblLanguageAuthor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/icons/author.png"))); // NOI18N
        lblLanguageAuthor.setText(org.openide.util.NbBundle.getMessage(RosaryAboutPanel.class, "RosaryAboutPanel.lblLanguageAuthor.text")); // NOI18N

        lblHomepageInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/icons/web.png"))); // NOI18N
        lblHomepageInfo.setText(org.openide.util.NbBundle.getMessage(RosaryAboutPanel.class, "RosaryAboutPanel.lblHomepageInfo.text")); // NOI18N

        lblCredits.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/nbrosary/images/icons/thanks.png"))); // NOI18N
        lblCredits.setText(org.openide.util.NbBundle.getMessage(RosaryAboutPanel.class, "RosaryAboutPanel.lblCredits.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblCredits, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHomepageInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLicenseInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAuthorName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLanguageAuthor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLanguage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(imgFlower, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSubtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(imgFlower, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSubtitle)))
                .addGap(13, 13, 13)
                .addComponent(lblAuthorName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLicenseInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHomepageInfo)
                .addGap(33, 33, 33)
                .addComponent(lblCredits)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(lblLanguage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLanguageAuthor)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgFlower;
    private javax.swing.JLabel lblAuthorName;
    private javax.swing.JLabel lblCredits;
    private javax.swing.JLabel lblHomepageInfo;
    private javax.swing.JLabel lblLanguage;
    private javax.swing.JLabel lblLanguageAuthor;
    private javax.swing.JLabel lblLicenseInfo;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables

}
