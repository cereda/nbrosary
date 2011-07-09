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
 * <b>HolyRosary.java</b>: this class acts like a real rosary, in which handles
 * mysteries, prayers, images and the beads themselves.
 */

// package info
package net.sf.nbrosary;

// needed imports
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.sf.jcarrierpigeon.WindowPosition;
import net.sf.jtelegraph.Telegraph;
import net.sf.jtelegraph.TelegraphQueue;
import net.sf.jtelegraph.TelegraphType;
import net.sf.nbrosary.utils.RosaryUtils;
import net.sf.nbrosary.utils.enumerations.RosaryMysteries;
import net.sf.nbrosary.utils.enumerations.RosaryMystery;
import net.sf.nbrosary.utils.enumerations.RosaryPrayers;
import org.openide.util.NbBundle;

/**
 * Acts like a real rosary, in which handles mysteries, prayers, images and
 * the beads themselves. In order to notify the user on the current prayer,
 * I decided to use another opensource project of mine - JTelegraph - to handle
 * such notifications, instead of the default NetBeans notification system.
 *
 * @author Paulo Roberto Massa Cereda
 * @version 1.0
 * @since 1.0
 */
public class HolyRosary {

    // rosary and mysteries images
    private JLabel imgRosary;
    private JLabel imgMystery;

    // mysteries lables
    private JLabel lblMysteryType;
    private JLabel lblMysteryTitle;
    private JLabel lblMysteryText;

    // rosary buttons
    private JButton btnStart;
    private JButton btnNext;
    private JButton btnPrevious;

    // the notification queue
    private TelegraphQueue queue;

    // the beads counter
    private int counter = 0;

    // mysteries type, according to the day of the week
    private RosaryMysteries mysteriesType;

    /**
     * Setter for the next button.
     * @param btnNext The next button.
     */
    public void setButtonNext(JButton btnNext) {

        // set the attribute
        this.btnNext = btnNext;
    }

    /**
     * Setter for the previous button
     * @param btnPrevious The previous button.
     */
    public void setButtonPrevious(JButton btnPrevious) {

        // set the attribute
        this.btnPrevious = btnPrevious;
    }

    /**
     * Setter for the start button.
     * @param btnStart The start button.
     */
    public void setButtonStart(JButton btnStart) {

        // set the attribute
        this.btnStart = btnStart;
    }

    /**
     * Setter for the mystery image label.
     * @param imgMystery The mystery image label.
     */
    public void setImageMystery(JLabel imgMystery) {

        // set the attribute
        this.imgMystery = imgMystery;
    }

    /**
     * Setter for the rosary image label.
     * @param imgRosary The rosary image label.
     */
    public void setImageRosary(JLabel imgRosary) {

        // set the attribute
        this.imgRosary = imgRosary;
    }

    /**
     * Setter for the mystery description label.
     * @param lblMysteryText The mystery description label.
     */
    public void setLabelMysteryText(JLabel lblMysteryText) {

        // set the attribute
        this.lblMysteryText = lblMysteryText;
    }

    /**
     * Setter for the mystery title label.
     * @param lblMysteryTitle The mystery title label.
     */
    public void setLabelMysteryTitle(JLabel lblMysteryTitle) {

        // set the attribute
        this.lblMysteryTitle = lblMysteryTitle;
    }

    /**
     * Setter for the mystery type label.
     * @param lblMysteryType The mystery type label.
     */
    public void setLabelMysteryType(JLabel lblMysteryType) {

        // set the attribute
        this.lblMysteryType = lblMysteryType;
    }

    /**
     * Constructor method. Nothing new in here.
     */
    public HolyRosary() {

        // get the current date
        GregorianCalendar today = new GregorianCalendar();

        // get the day of the week
        int day = today.get(Calendar.DAY_OF_WEEK);

        // find which day it is
        switch (day) {
            case Calendar.MONDAY:
            case Calendar.SATURDAY:

                // today is Joyful
                mysteriesType = RosaryMysteries.JOYFUL;
                break;

            case Calendar.TUESDAY:
            case Calendar.FRIDAY:

                // today is Sorrowful
                mysteriesType = RosaryMysteries.SORROWFUL;
                break;

            case Calendar.THURSDAY:

                // today is Luminous
                mysteriesType = RosaryMysteries.LUMINOUS;
                break;

            case Calendar.WEDNESDAY:
            case Calendar.SUNDAY:

                // today is Glorious
                mysteriesType = RosaryMysteries.GLORIOUS;
        }

        // create a new queue instance
        queue = new TelegraphQueue();
    }

    /**
     * Interrupt the rosary.
     */
    public void stop() {

        // no descriptions
        setMysteryTexts(RosaryMystery.NONE, 11);

        // no image
        setMysteryImage(RosaryMystery.NONE);

        // rosary count is back to zero
        RosaryUtils.addImageToLabel(imgRosary, "rosary0.png", "rosary");

        // stop button becomes start button again
        RosaryUtils.addIconsToButton(btnStart, "play.png", "play_hover.png");

        // disable previous button
        btnPrevious.setEnabled(false);

        // disable next button
        btnNext.setEnabled(false);
    }

    /*
     * Starts the rosary.
     */
    public void pray() {

        // now the start button becomes a stop button
        RosaryUtils.addIconsToButton(btnStart, "stop.png", "stop_hover.png");

        // lets enable the next button
        btnNext.setEnabled(true);

        // counter is set to the first state
        counter = 1;

        // set the rosary state according to the counter
        setRosaryState();
    }

    /**
     * Goes to the next step in the rosary.
     */
    public void next() {

        // increase the counter
        counter++;

        // update the rosary state
        setRosaryState();
    }

    /**
     * Goes to the previous step in the rosary.
     */
    public void previous() {

        // increase the counter
        counter--;

        // update the rosary state
        setRosaryState();
    }

    /**
     * Prays the current prayer through the JTelegraph notification system.
     * I decided to use it because NetBeans default notification system was not
     * able to handle long texts in balloon tooltips. It basically fails when
     * a line wrap is needed, and I really need that, since some prayers are
     * really long. Unfortunately, HTML was not supported, so instead of trying
     * to hack it, I decided to give a shot on my own library.
     * @param prayer The current prayer.
     */
    public void sayPrayer(RosaryPrayers prayer) {

        // each prayer has a different timeout
        int timer = 0;

        // the fully qualified localization reference
        String prayerName = "";

        // check which prayer we need to get
        switch (prayer) {

            case GLORYBETOTHEFATHER:

                // set the localization reference
                prayerName = "prayers.glorybetothefather.";

                // set timer
                timer = 3000;

                break;

            case THEAPOSTLESCREED:

                // set the localization reference
                prayerName = "prayers.theapostlescreed.";

                // set timer
                timer = 8000;

                break;

            case THEFATIMAPRAYER:

                // set the localization reference
                prayerName = "prayers.thefatimaprayer.";

                // set timer
                timer = 3000;

                break;

            case THEHAILHOLYQUEEN:

                // set the localization reference
                prayerName = "prayers.thehailholyqueen.";

                // set timer
                timer = 5000;

                break;

            case THEHAILMARY:

                // set the localization reference
                prayerName = "prayers.thehailmary.";

                // set timer
                timer = 4000;

                break;

            case THEOURFATHER:

                // set the localization reference
                prayerName = "prayers.theourfather.";

                // set timer
                timer = 4000;

                break;

            case THESIGNOFTHECROSS:

                // set the localization reference
                prayerName = "prayers.thesignofthecross.";

                // set timer
                timer = 3000;

                break;
        }

        // now we get the texts using the localization features

        // first, the prayer title
        String pTitle = NbBundle.getBundle("net.sf.nbrosary.i18n.Rosary").getString(prayerName + "title");

        // the, the prayer itself
        String pContent = NbBundle.getBundle("net.sf.nbrosary.i18n.Rosary").getString(prayerName + "prayer");

        // now, we create a new Telegraph instance, which will show the
        // prayer as a notification
        Telegraph telegraph = new Telegraph(pTitle, pContent, TelegraphType.MESSAGE, WindowPosition.BOTTOMRIGHT, timer);

        // add the prayer to the queue
        queue.add(telegraph);
        
    }

    /**
     * Set the current mystery texts according to the day, and bead count.
     * @param mystery The current mystery.
     * @param value The bead count.
     */
    public void setMysteryTexts(RosaryMystery mystery, int value) {

        // if it's a high value
        if (value == 11) {

            // erase all texts
            lblMysteryText.setText("");
            lblMysteryTitle.setText("");
            lblMysteryType.setText("");

            // then return
            return;
        }

        // path will hold the fully qualified localization strings path.
        String path = "";

        // get the mysteries type
        switch (mysteriesType) {

            case GLORIOUS:

                // set the path
                path = "glorious.";

                break;

            case JOYFUL:

                // set the path
                path = "joyful.";

                break;

            case LUMINOUS:

                // set the path
                path = "luminous.";

                break;

            case SORROWFUL:

                // set the path
                path = "sorrowful.";

                break;
        }

        // get the order
        String order = "";

        switch (mystery) {

            case NONE:
            case FIRST:

                // set the order
                order = "first.";

                break;

            case SECOND:

                // set the order
                order = "second.";

                break;

            case THIRD:

                // set the order
                order = "third.";

                break;

            case FOURTH:

                // set the order
                order = "fourth.";

                break;

            case FIFTH:

                // set the order
                order = "fifth.";
                
                break;
        }

        // now we set the texts according to the localization
        lblMysteryType.setText(NbBundle.getBundle("net.sf.nbrosary.i18n.Rosary").getString(path + "title"));
        lblMysteryTitle.setText(NbBundle.getBundle("net.sf.nbrosary.i18n.Rosary").getString(path + order + "title"));

        // if it is not an Our Father
        if (value != 0) {

            // display the current bead text
            lblMysteryText.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 250, NbBundle.getBundle("net.sf.nbrosary.i18n.Rosary").getString(path + order + String.valueOf(value))));
            
        }
        else {

            // erase the text
            lblMysteryText.setText("");
        }
        
    }

    /**
     * Set the mystery image according to the current mystery and type.
     * @param mystery The current mystery.
     */
    public void setMysteryImage(RosaryMystery mystery) {

        // the filename
        String filename = "";

        switch (mysteriesType) {

            case GLORIOUS:

                // set the filename
                filename = "glorious";

                break;

            case JOYFUL:

                // set the filename
                filename = "joy";

                break;

            case LUMINOUS:

                // set the filename
                filename = "light";

                break;

            case SORROWFUL:

                // set the filename
                filename = "sorrow";

                break;
        }

        // now we must check the order
        switch (mystery) {
            case NONE:

                // set the filename
                filename = "none.png";

                break;

            case FIRST:

                // set the filename
                filename = filename + "1.png";

                break;

            case SECOND:

                // set the filename
                filename = filename + "2.png";

                break;

            case THIRD:

                // set the filename
                filename = filename + "3.png";

                break;

            case FOURTH:

                // set the filename
                filename = filename + "4.png";

                break;

            case FIFTH:

                // set the filename
                filename = filename + "5.png";

                break;
        }

        // we now update the image to the label
        RosaryUtils.addImageToLabel(imgMystery, filename, "mysteries");
    }

    /**
     * Sets the rosary current state. This is by far the biggest method in
     * the whole project, as it handles every single rosary step. I decided to
     * work in here as a big integer switch, since the rosary has a well
     * defined order of praying. At least, I have full control over the whole
     * rosary at every single step, forward or even backwards.
     */
    private void setRosaryState() {

        // choose the right setting according to the rosary counter
        switch (counter) {

            case 1:

                // disable the previous button
                btnPrevious.setEnabled(false);

                // set the mystery texts
                setMysteryTexts(RosaryMystery.NONE, 11);

                // set mystery image
                setMysteryImage(RosaryMystery.NONE);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary1.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THESIGNOFTHECROSS);
                
                break;

            case 2:

                // enable the previous button
                btnPrevious.setEnabled(true);

                // set the mystery texts
                setMysteryTexts(RosaryMystery.NONE, 11);

                // set mystery image
                setMysteryImage(RosaryMystery.NONE);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary1.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEAPOSTLESCREED);

                break;

            case 3:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.NONE, 11);

                // set mystery image
                setMysteryImage(RosaryMystery.NONE);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary2.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEOURFATHER);

                break;

            case 4:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.NONE, 11);

                // set mystery image
                setMysteryImage(RosaryMystery.NONE);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary3.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 5:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.NONE, 11);

                // set mystery image
                setMysteryImage(RosaryMystery.NONE);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary4.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 6:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.NONE, 11);

                // set mystery image
                setMysteryImage(RosaryMystery.NONE);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary5.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 7:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.NONE, 11);

                // set mystery image
                setMysteryImage(RosaryMystery.NONE);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary5.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.GLORYBETOTHEFATHER);

                break;

            case 8:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary6.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEOURFATHER);

                break;

            case 9:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 1);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary7.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 10:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 2);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary8.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 11:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 3);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary9.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 12:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 4);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary10.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 13:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 5);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary11.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 14:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 6);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary12.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 15:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 7);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary13.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 16:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 8);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary14.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 17:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 9);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary15.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 18:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 10);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary16.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 19:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary16.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.GLORYBETOTHEFATHER);

                break;

            case 20:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIRST, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.FIRST);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary16.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEFATIMAPRAYER);

                break;

            case 21:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary17.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEOURFATHER);

                break;

            case 22:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 1);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary18.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 23:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 2);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary19.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 24:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 3);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary20.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 25:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 4);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary21.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 26:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 5);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary22.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 27:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 6);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary23.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 28:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 7);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary24.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 29:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 8);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary25.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 30:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 9);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary26.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 31:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 10);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary27.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 32:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary27.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.GLORYBETOTHEFATHER);

                break;

            case 33:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.SECOND, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.SECOND);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary27.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEFATIMAPRAYER);

                break;

            case 34:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary28.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEOURFATHER);

                break;

            case 35:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 1);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary29.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 36:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 2);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary30.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 37:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 3);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary31.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 38:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 4);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary32.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 39:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 5);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary33.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 40:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 6);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary34.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 41:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 7);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary35.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 42:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 8);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary36.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 43:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 9);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary37.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 44:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 10);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary38.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 45:

                // set the mystery texts
                setMysteryImage(RosaryMystery.THIRD);

                // set mystery image
                setMysteryTexts(RosaryMystery.THIRD, 0);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary38.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.GLORYBETOTHEFATHER);

                break;

            case 46:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.THIRD, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.THIRD);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary38.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEFATIMAPRAYER);

                break;

            case 47:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary39.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEOURFATHER);

                break;

            case 48:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 1);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary40.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 49:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 2);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary41.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 50:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 3);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary42.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 51:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 4);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary43.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 52:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 5);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary44.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 53:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 6);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary45.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 54:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 7);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary46.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 55:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 8);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary47.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 56:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 9);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary48.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 57:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 10);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary49.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 58:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary49.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.GLORYBETOTHEFATHER);

                break;

            case 59:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FOURTH, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.FOURTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary49.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEFATIMAPRAYER);

                break;

            case 60:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary50.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEOURFATHER);

                break;

            case 61:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 1);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary51.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 62:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 2);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary52.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 63:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 3);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary53.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 64:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 4);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary54.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 65:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 5);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary55.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 66:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 6);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary56.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 67:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 7);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary57.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 68:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 8);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary58.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 69:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 9);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary59.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 70:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 10);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary60.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILMARY);

                break;

            case 71:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary60.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.GLORYBETOTHEFATHER);

                break;

            case 72:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.FIFTH, 0);

                // set mystery image
                setMysteryImage(RosaryMystery.FIFTH);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary60.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEFATIMAPRAYER);

                break;

            case 73:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.NONE, 11);

                // set mystery image
                setMysteryImage(RosaryMystery.NONE);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary60.png", "rosary");

                // say prayer
                sayPrayer(RosaryPrayers.THEHAILHOLYQUEEN);

                // this is the last state, so the stop button
                // needs to become the start button again
                RosaryUtils.addIconsToButton(btnStart, "play.png", "play_hover.png");

                // disable the next button
                btnNext.setEnabled(false);

                // disable the previous button
                btnPrevious.setEnabled(false);

                break;

            default:

                // set the mystery texts
                setMysteryTexts(RosaryMystery.NONE, 11);

                // set mystery image
                setMysteryImage(RosaryMystery.NONE);

                // update the rosary image
                RosaryUtils.addImageToLabel(imgRosary, "rosary0.png", "rosary");
        }
    }

}
