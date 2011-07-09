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
 * <b>RosaryUtils.java</b>: acts like a swiss army knife, providing nice
 * features for this plugin.
 */

// package info
package net.sf.nbrosary.utils;

// needed imports
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.openide.util.ImageUtilities;

/**
 * Acts like a swiss army knife, providing nice features for this plugin. This
 * is basically a static class, so there is no need of creating instances.
 * @author Paulo Roberto Massa Cereda
 * @version 1.0
 * @since 1.0
 */
public class RosaryUtils {

    /**
     * Makes a button to act and look like a label, while keeping the action
     * performed events.
     * @param button The button.
     */
    public static void makeButtonActLikeLabel(JButton button) {

        // change the button properties
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
    }

    /**
     * Adds two images to a button: one is the default and the other is while
     * rollover.
     * @param button The button.
     * @param defaultImage The default image.
     * @param rolloverImage The rollover image.
     */
    public static void addIconsToButton(JButton button, String defaultImage, String rolloverImage) {

        // set default icon
        button.setIcon((Icon) ImageUtilities.loadImage("net/sf/nbrosary/images/buttons/" + defaultImage, true));

        // set rollover icon
        button.setRolloverIcon((Icon) ImageUtilities.loadImage("net/sf/nbrosary/images/buttons/" + rolloverImage, true));

        // set rollover enabled
        button.setRolloverEnabled(true);
    }

    /**
     * Adds an image to a label.
     * @param label The label.
     * @param image The image.
     * @param folder The folder.
     */
    public static void addImageToLabel(JLabel label, String image, String folder) {

        // set icon
        label.setIcon((Icon) ImageUtilities.loadImage("net/sf/nbrosary/images/" + folder + "/" + image, true));
    }

}
