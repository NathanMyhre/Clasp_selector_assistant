package ClaspSelectorAssistant;

//Notes to self: Key words to search in development:
    //Bug: Something that needs to be fixed later, or a feature that could be added later.
    //Javadoc: Something to note for Javadoc
    //Note: logic reminders

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.HashMap;

//Bug: Prevent multiple alert windows from popping up

/** Bug: Javadoc for fields
 * ClaspGUI is a Clasp selecting assistance application.
 *
 */
public class ClaspGUI extends JPanel{
    public AbutmentToothPanel leftPanel;
    public JPanel middlePanel, rightPanel;
    public static HashMap<String, String> definitions = ClaspGUI.makeDefinitions();
    public static Insets insets = new Insets(5,5,5,5 );
    public static Insets defaultInset = new Insets(0,0,0,0);
    public ImageIcon mandibularArchPic;
    public ImageIcon maxillaryArchPic;

    //buttons that go in middle pane
    public InfoButton stressReleaseInfo;
    public InfoButton surveyLineInfo;
    public InfoButton retentiveUndercutInfo;
    public InfoButton occlusionInfo;
    public InfoButton softTissueUndercutInfo;
    public InfoButton buccalVestib2mmInfo;
    public InfoButton estheticInfo;
    public InfoButton toothTypeInfo;

    public ClaspRadioButton stressReleaseYes, stressReleaseNo;
    public ClaspRadioButton surveyLineClass1, surveyLineClass2, surveyLineClass3;
    public ClaspRadioButton retentiveUndercut1, retentiveUndercut2;
    public ClaspRadioButton occlusionMesial, occlusionDistal;
    public ClaspRadioButton softTissueUndercutYes, softTissueUndercutNo;
    public ClaspRadioButton buccalVestib2mmYes, buccalVestib2mmNo;
    public ClaspRadioButton estheticYes, estheticNo;
    public ClaspRadioButton toothTypeAnterior, toothTypePremolar, toothTypeMolar;

    public ButtonGroup stressReleaseGroup;
    public ButtonGroup surveyLineGroup;
    public ButtonGroup retentiveUndercutGroup;
    public ButtonGroup occlusionGroup;
    public ButtonGroup softTissueUndercutGroup;
    public ButtonGroup buccalVestib2mmGroup;
    public ButtonGroup estheticGroup;
    public ButtonGroup toothTypeGroup;

    //buttons that go in right panel
    public ClaspButton iBarMesial;
    public ClaspButton iBarDistal;
    public ClaspButton iBarCingulum;
    public ClaspButton modTBarMesial;
    public ClaspButton modTBarDistal;
    public ClaspButton modTBarCingulum;
    public ClaspButton wwMesial;
    public ClaspButton wwDistal;
    public ClaspButton wwCingulum;
    public ClaspButton ccMesial;
    public ClaspButton ccDistal;
    public ClaspButton ccCingulum;
    public ClaspButton ring;
    public ClaspButton reset;

    //Data structure to track the active radio buttons
    public HashMap<String, String> activeCriteria;

    //Data structures to hold buttons
    //Bug:Abutment tooth selection does not select radio buttons. Might have to make HashMaps with button data structures.
    public LinkedList<ClaspButton> buttons;
    public HashMap<String, ClaspRadioButton> radioButtons;
    public LinkedList<ButtonGroup> buttonGroups;



//Javadoc: 3 columns, 1 row. 1 JPanel per column
    //Javadoc: Jpanels: 1 cell = 20 height
    public ClaspGUI() {
        this.setLayout(new GridBagLayout());
        leftPanel = new AbutmentToothPanel(new GridBagLayout(), this);
        middlePanel = new JPanel(new GridBagLayout());
        rightPanel = new JPanel(new GridBagLayout());

        //create images of mandibular arches
        mandibularArchPic = createImageIcon("images\\mandibular.PNG", "Mandibular Arch US numbering system");
        maxillaryArchPic = createImageIcon("images\\maxillary.PNG", "Maxillary Arch US numbering system");

        //Note: create GBC now, and only change within static methods - scope keeps safe.
        GridBagConstraints c = new GridBagConstraints();

        //Map of active criteria
        activeCriteria = new HashMap<String, String>();
        //List of all clasp buttons for checking criteria
        buttons = new LinkedList<ClaspButton>();
        radioButtons = new HashMap<String, ClaspRadioButton>();
        buttonGroups = new LinkedList<ButtonGroup>();

        //Add Left most buttons to GUI
        //addLeftHandButtons(this, c);

        //initialize buttons and add them to the button List
        iBarMesial = ClaspButton.factory("I-Bar Clasp Mesial Rest", this); buttons.add(iBarMesial);
        iBarDistal = ClaspButton.factory("I-Bar Clasp Distal Rest", this); buttons.add(iBarDistal);
        iBarCingulum = ClaspButton.factory("I-Bar Clasp Cingulum Rest", this); buttons.add(iBarCingulum);
        modTBarMesial = ClaspButton.factory("Mod T-Bar Clasp Mesial Rest", this); buttons.add(modTBarMesial);
        modTBarDistal = ClaspButton.factory("Mod T-Bar Clasp Distal Rest", this); buttons.add(modTBarDistal);
        modTBarCingulum = ClaspButton.factory("Mod T-Bar Clasp Cingulum Rest", this); buttons.add(modTBarCingulum);
        wwMesial = ClaspButton.factory("WW Clasp Mesial Rest", this); buttons.add(wwMesial);
        wwDistal = ClaspButton.factory("WW Clasp Distal Rest", this); buttons.add(wwDistal);
        wwCingulum =ClaspButton.factory("WW Clasp Cingulum Rest", this); buttons.add(wwCingulum);
        ccMesial = ClaspButton.factory("CC Clasp Mesial Rest", this); buttons.add(ccMesial);
        ccDistal = ClaspButton.factory("CC Clasp Distal Rest", this); buttons.add(ccDistal);
        ccCingulum = ClaspButton.factory("CC Clasp Cingulum Rest", this); buttons.add(ccCingulum);
        ring = ClaspButton.factory("Ring Clasp", this); buttons.add(ring);
        reset = ClaspButton.factory("RESET", this);

        //Add and place clasp buttons to GUI
        ClaspGUI.addClaspButtons(this, c);

        //Add and place radio buttons for patient selection criteria
        //Bug: soon, I want to refactor this for consistency and make a hierarchy of buttons.
        //Bug: ActonListener will get very complicated if main Panel is the only listener.
        ClaspGUI.addStressReleaseButtons(this, c);
        ClaspGUI.addSurveyLineButtons(this, c);
        ClaspGUI.addRetentiveUndercutButtons(this, c);
        ClaspGUI.addOcclusionButtons(this, c);
        ClaspGUI.addsoftTissueUndercutButtons(this, c);
        ClaspGUI.addBuccalVestibButtons(this, c);
        ClaspGUI.addEstheticConcernButtons(this, c);
        ClaspGUI.addToothTypeButtons(this, c);

        //Add Radio buttons and button groups to lists for use later if needed.
        radioButtons.put("Stress Release Needed ; Yes", stressReleaseYes); radioButtons.put("Stress Release Needed ; No", stressReleaseNo);
        radioButtons.put("Survey Line Classification ; I", surveyLineClass1); radioButtons.put("Survey Line Classification ; II", surveyLineClass2);
        radioButtons.put("Survey Line Classification ; II", surveyLineClass3); radioButtons.put("Retentive Undercut ; 0.01\"", retentiveUndercut1);
        radioButtons.put("Retentive Undercut ; 0.02\"", retentiveUndercut2); radioButtons.put("Occlusion ; Distal", occlusionDistal);
        radioButtons.put("Occlusion ; Medial", occlusionMesial); radioButtons.put("Soft Tissue Undercut ; Yes", softTissueUndercutYes);
        radioButtons.put("Soft Tissue Undercut ; No",softTissueUndercutNo); radioButtons.put("2mm or More Buccal Vestibule ; Yes", buccalVestib2mmYes);
        radioButtons.put("2mm or More Buccal Vestibule ; No", buccalVestib2mmNo); radioButtons.put("Esthetically Concerned Patient ; Yes", estheticYes);
        radioButtons.put("Esthetically Concerned Patient ; No", estheticNo); radioButtons.put("Tooth Type ; Anterior",toothTypeAnterior);
        radioButtons.put("Tooth Type ; Molar", toothTypeMolar); radioButtons.put("Tooth Type ; PreMolar", toothTypePremolar);

        buttonGroups.add(stressReleaseGroup); buttonGroups.add(surveyLineGroup); buttonGroups.add(retentiveUndercutGroup);
        buttonGroups.add(occlusionGroup); buttonGroups.add(softTissueUndercutGroup); buttonGroups.add(buccalVestib2mmGroup);
        buttonGroups.add(estheticGroup); buttonGroups.add(toothTypeGroup);

        //Add the three thirds of the GUI.
        this.add(leftPanel);
        this.add(middlePanel);
        this.add(rightPanel);

    }


    private static HashMap<String, String> makeDefinitions() {
        HashMap<String, String> defs = new HashMap<String, String>();
        defs.put("Stress Release Needed", "Stress Release Definition");
        defs.put("Survey Line Classification", "Survey Line Classification Definition");
        defs.put("Retentive Undercut", "Retentive Undercut Definition");
        defs.put("Occlusion", "Occlusion Definition");
        defs.put("Soft Tissue Undercut", "Soft Tissue Undercut Definition");
        defs.put("2mm or More Buccal Vestibule", "2mm or More Buccal Vestibule Definition");
        defs.put("Esthetically Concerned Patient", "Esthetically Concerned Patient definition");
        defs.put("Tooth Type", "Tooth Type Definition");
        return defs;
    }

    //THIS METHOD IS DEPRECATED. REPLACED WITH ABUTMENTTOOTHPANEL
    /*private static void addLeftHandButtons(ClaspGUI gui, GridBagConstraints c) {
        JButton tCase = new InfoButton("Case");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 75;
        c.ipadx = 75;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = insets;
        gui.leftPanel.add(tCase, c);

        AbutmentToothButton abutmentTooth = new AbutmentToothButton("Abutment Tooth", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 75;
        c.ipadx = 75;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = insets;
        gui.leftPanel.add(abutmentTooth, c);
    }*/

    //Creates info button and radio buttons for Stress Release choices
    private static void addStressReleaseButtons(ClaspGUI gui, GridBagConstraints c){
        //add info button for Stress Release criteria
        gui.stressReleaseInfo = new InfoButton("Stress Release Needed");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 2;
        c.ipady = 30;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = insets;
        gui.middlePanel.add(gui.stressReleaseInfo, c);

        //reset insets and add the radio button manager
        c.insets = defaultInset;
        c.ipadx = 50;
        gui.stressReleaseGroup = new ButtonGroup();

        //add item listener to stress release info to open a new text window.

        gui.stressReleaseYes = new ClaspRadioButton("Stress Release Needed", "Yes", gui);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 0;
        gui.stressReleaseGroup.add(gui.stressReleaseYes);
        gui.middlePanel.add(gui.stressReleaseYes, c);

        //Bug: add item listener to StressRelease radio buttons to deactivate accordingly.
        //Bug: add insets to stress release items

        gui.stressReleaseNo = new ClaspRadioButton("Stress Release Needed", "No", gui);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 1;
        gui.stressReleaseGroup.add(gui.stressReleaseNo);
        gui.middlePanel.add(gui.stressReleaseNo, c);

    }

    //Creates info button and radio buttons for Survey Line choices
    private static void addSurveyLineButtons(ClaspGUI gui, GridBagConstraints c){
        //Bug: make sure font for info buttons works.
        gui.surveyLineInfo = new InfoButton("Survey Line Classification");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 3;   //next gridy = 5
        c.ipady = 45;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = insets;
        gui.middlePanel.add(gui.surveyLineInfo, c);
        //Bug:  add item listener to survey line info to open new text window.
        //Bug: add insets to survey line buttons.

        //reset insets and add the radio button manager
        c.insets = defaultInset;
        c.ipadx = 50;
        gui.surveyLineGroup = new ButtonGroup();

        //add Survey Line Classification Button 1
        gui.surveyLineClass1 = new ClaspRadioButton("Survey Line Classification", "I", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 2;
        gui.surveyLineGroup.add(gui.surveyLineClass1);
        gui.middlePanel.add(gui.surveyLineClass1, c);

        //add Survey Line Classification Button 2
        gui.surveyLineClass2 = new ClaspRadioButton("Survey Line Classification", "II", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 3;
        gui.surveyLineGroup.add(gui.surveyLineClass2);
        gui.middlePanel.add(gui.surveyLineClass2, c);

        //add Survey Line Classification Button 3
        gui.surveyLineClass3 = new ClaspRadioButton("Survey Line Classification", "III", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 4;
        gui.surveyLineGroup.add(gui.surveyLineClass3);
        gui.middlePanel.add(gui.surveyLineClass3, c);
    }

    //Creates info button and radio buttons for Retentive Undercut choices
    private static void addRetentiveUndercutButtons(ClaspGUI gui, GridBagConstraints c){
        //Bug: make sure font for info buttons works.
        gui.retentiveUndercutInfo = new InfoButton("Retentive Undercut");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 2;   //next gridy = 7
        c.ipady = 30;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = insets;
        gui.middlePanel.add(gui.retentiveUndercutInfo, c);

        //reset insets and add the radio button manager
        c.insets = defaultInset;
        c.ipadx = 50;
        gui.retentiveUndercutGroup = new ButtonGroup();

        //add Retentive Undercut button 0.01"
        gui.retentiveUndercut1 = new ClaspRadioButton("Retentive Undercut", "0.01\"", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 5;
        gui.retentiveUndercutGroup.add(gui.retentiveUndercut1);
        gui.middlePanel.add(gui.retentiveUndercut1, c);

        //add Retentive Undercut button 0.02"
        gui.retentiveUndercut2 = new ClaspRadioButton("Retentive Undercut", "0.02\"", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 6;
        gui.retentiveUndercutGroup.add(gui.retentiveUndercut2);
        gui.middlePanel.add(gui.retentiveUndercut2, c);
    }

    //Creates info button and radio buttons for Occlusion choices
    private static void addOcclusionButtons(ClaspGUI gui, GridBagConstraints c) {
        //Bug: make sure font for info buttons works.
        gui.occlusionInfo = new InfoButton("Occlusion");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 2;   //next gridy = 9
        c.ipady = 30;
        c.gridx = 0;
        c.gridy = 7;
        c.insets = insets;
        gui.middlePanel.add(gui.occlusionInfo, c);

        //reset insets and add the radio button manager
        c.insets = defaultInset;
        c.ipadx = 50;
        gui.occlusionGroup = new ButtonGroup();

        //Add Occlusion = mesial radio button
        gui.occlusionMesial = new ClaspRadioButton("Occlusion", "Mesial", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 7;
        gui.occlusionGroup.add(gui.occlusionMesial);
        gui.middlePanel.add(gui.occlusionMesial, c);

        //Add Occlusion= Distal radio button
        gui.occlusionDistal = new ClaspRadioButton("Occlusion", "Distal", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 8;
        gui.occlusionGroup.add(gui.occlusionDistal);
        gui.middlePanel.add(gui.occlusionDistal, c);
    }

    //Creates info button and radio buttons for soft tissue undercut choices
    private static void addsoftTissueUndercutButtons(ClaspGUI gui, GridBagConstraints c) {
        gui.softTissueUndercutInfo = new InfoButton("Soft Tissue Undercut");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 2;
        c.ipady = 30;
        c.gridx = 0;
        c.gridy = 9;
        c.insets = insets;
        gui.middlePanel.add(gui.softTissueUndercutInfo, c);

        //reset insets and add the radio button manager
        c.insets = defaultInset;
        c.ipadx = 50;
        gui.softTissueUndercutGroup = new ButtonGroup();

        //Add Soft Tissue Undercut = Yes button
        gui.softTissueUndercutYes = new ClaspRadioButton("Soft Tissue Undercut", "Yes", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 9;
        gui.softTissueUndercutGroup.add(gui.softTissueUndercutYes);
        gui.middlePanel.add(gui.softTissueUndercutYes, c);

        //Add Soft Tissue Undercut = No button
        gui.softTissueUndercutNo = new ClaspRadioButton("Soft Tissue Undercut", "No", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 10;
        gui.softTissueUndercutGroup.add(gui.softTissueUndercutNo);
        gui.middlePanel.add(gui.softTissueUndercutNo, c);
    }

    //Creates info button and radio buttons for 2mm or more Buccal Vestibule choices
    private static void addBuccalVestibButtons(ClaspGUI gui, GridBagConstraints c) {
        gui.buccalVestib2mmInfo = new InfoButton("2mm or More Buccal Vestibule");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 2;   //next gridy = 13
        c.ipady = 30;
        c.gridx = 0;
        c.gridy = 11;
        c.insets = insets;
        gui.middlePanel.add(gui.buccalVestib2mmInfo, c);

        //reset insets and add the radio button manager
        c.insets = defaultInset;
        c.ipadx = 50;
        gui.buccalVestib2mmGroup = new ButtonGroup();

        //Add 2mm or more Buccal Vestibule = Yes button
        gui.buccalVestib2mmYes = new ClaspRadioButton("2mm or More Buccal Vestibule", "Yes", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 11;
        gui.buccalVestib2mmGroup.add(gui.buccalVestib2mmYes);
        gui.middlePanel.add(gui.buccalVestib2mmYes, c);

        //Add 2mm or more Buccal Vestibule = No button
        gui.buccalVestib2mmNo = new ClaspRadioButton("2mm or More Buccal Vestibule", "No", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 12;
        gui.buccalVestib2mmGroup.add(gui.buccalVestib2mmNo);
        gui.middlePanel.add(gui.buccalVestib2mmNo, c);
    }

    //Creates info button and radio buttons for Esthetically concerned choices
    private static void addEstheticConcernButtons(ClaspGUI gui, GridBagConstraints c) {
        gui.estheticInfo = new InfoButton("Esthetically Concerned Patient");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 2;   //next gridy = 15
        c.ipady = 30;
        c.gridx = 0;
        c.gridy = 13;
        c.insets = insets;
        gui.middlePanel.add(gui.estheticInfo, c);

        //reset insets and add the radio button manager
        c.insets = defaultInset;
        c.ipadx = 50;
        gui.estheticGroup = new ButtonGroup();

        //add Esthetically concerned = yes button
        gui.estheticYes = new ClaspRadioButton("Esthetically Concerned Patient", "Yes", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 13;
        gui.estheticGroup.add(gui.estheticYes);
        gui.middlePanel.add(gui.estheticYes, c);

        //add Esthetically concerned = No button
        gui.estheticNo = new ClaspRadioButton("Esthetically Concerned Patient", "No", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 14;
        gui.estheticGroup.add(gui.estheticNo);
        gui.middlePanel.add(gui.estheticNo, c);
    }

    //Creates info button and radio buttons for Tooth Type choices
    private static void addToothTypeButtons(ClaspGUI gui, GridBagConstraints c) {
        gui.toothTypeInfo = new InfoButton("Tooth Type");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 3;
        c.ipady = 45;
        c.gridx = 0;
        c.gridy = 15;
        c.insets = insets;
        gui.middlePanel.add(gui.toothTypeInfo, c);

        //reset insets and add the radio button manager
        c.insets = defaultInset;
        c.ipadx = 50;
        gui.toothTypeGroup = new ButtonGroup();

        //add Esthetically concerned = yes button
        gui.toothTypeAnterior = new ClaspRadioButton("Tooth Type", "Anterior", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 15;
        gui.toothTypeGroup.add(gui.toothTypeAnterior);
        gui.middlePanel.add(gui.toothTypeAnterior, c);

        //add Esthetically concerned = No button
        gui.toothTypePremolar = new ClaspRadioButton("Tooth Type", "PreMolar", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 16;
        gui.toothTypeGroup.add(gui.toothTypePremolar);
        gui.middlePanel.add(gui.toothTypePremolar, c);

        //add Esthetically concerned = No button
        gui.toothTypeMolar = new ClaspRadioButton("Tooth Type", "Molar", gui);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 17;
        gui.toothTypeGroup.add(gui.toothTypeMolar);
        gui.middlePanel.add(gui.toothTypeMolar, c);

    }

    //adds and places ClaspButtons in the GUI
    private static void addClaspButtons(ClaspGUI gui, GridBagConstraints c) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 175;
        c.ipady = 15;
        c.insets = insets;
        c.gridy = 0;
        c.gridheight = 1;

        //Add buttons to right panel in vertical order
        gui.rightPanel.add(gui.iBarMesial, c);
        c.gridy = 1;
        gui.rightPanel.add(gui.iBarDistal, c);
        c.gridy = 2;
        gui.rightPanel.add(gui.iBarCingulum, c);
        c.gridy = 3;
        gui.rightPanel.add(gui.modTBarMesial, c);
        c.gridy = 4;
        gui.rightPanel.add(gui.modTBarDistal, c);
        c.gridy = 5;
        gui.rightPanel.add(gui.modTBarCingulum, c);
        c.gridy = 6;
        gui.rightPanel.add(gui.wwMesial, c);
        c.gridy = 7;
        gui.rightPanel.add(gui.wwDistal, c);
        c.gridy = 8;
        gui.rightPanel.add(gui.wwCingulum, c);
        c.gridy = 9;
        gui.rightPanel.add(gui.ccMesial, c);
        c.gridy = 10;
        gui.rightPanel.add(gui.ccDistal, c);
        c.gridy = 11;
        gui.rightPanel.add(gui.ccCingulum, c);
        c.gridy = 12;
        gui.rightPanel.add(gui.ring, c);

        //Add the reset button
        c.fill = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridx = 0;
        c.ipady = 5;
        c.ipadx = 40;

        c.anchor = GridBagConstraints.PAGE_END;
        c.weightx = 1.0;
        c.weighty = 1.0;
        gui.rightPanel.add(gui.reset, c);

    }


    /**
     * Listens and acts the clicking of radio button. Updates the valid clasps based on selected radio buttons.
     * @param input String input from the action event performed.
     */

    public void radioButtonClicked(String input) {
        String[] splitString = input.split(" ; ");
        String crit = splitString[0];   //Get the current criterion
        String val = splitString[1];    //Get the current value
        activeCriteria.put(crit, val);  //remember criteria, value pairing
        int invalidTally = 0;
        for (ClaspButton button : buttons) {
            button.updateStatus(activeCriteria);    //Update activity status of the ClaspButtons
            if(!button.isEnabled()) {
                invalidTally++;
            }
        }
        // Make a new window to alert users that there are no valid clasps available.
        if (invalidTally >= 13) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("Alert: No Valid Clasps");
                    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    JTextArea textArea = new JTextArea(15, 50);
                    textArea.setWrapStyleWord(true);
                    textArea.setEditable(false);
                    textArea.append(" No viable clasps exist with the current configuration.\n" +
                            "\n" +
                            "Please consider the following:" +
                            "\n" +

                            "    1: Surveyed crowns: surveyed crowns can be made to provide appropriate depth and location of undercuts.  \n" +
                            "\n" +
                            "    2: Composite addition: composite can be added to the tooth to provide appropriate depth and location of undercuts \n " +
                            "       (composite added to the tooth should be based on a surveyed wax pattern made on a surveyed diagnostic cast)\n" +
                            "\n" +
                            "    3: Preparation of an enamel dimple: a dimple can be prepared in the enamel of the abutment tooth to provide an  \n " +
                            "       appropriate undercut in an appropriate location.\n" +
                            "\n" +
                            "    4: Choose a different abutment");
                    JPanel infoPanel = new JPanel();
                    infoPanel.add(textArea);
                    frame.add(infoPanel);
                    frame.pack();
                    frame.setLocationByPlatform(true);
                    frame.setVisible(true);
                    frame.setResizable(false);

                }
            });
        }
        //Bug: what are the error values we expect?
        //Bug: Special character parsing is flimsy, is there a better way we can do this?
    }

    /**
     * Opens a new window when a ClaspButton is clicked
     * @param claspButton The clasp button clicked.
     * @param claspImage The image related to clasp.
     */
    public static void makeClaspWindow(ClaspButton claspButton, ImageIcon claspImage) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame(claspButton.name);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                JPanel infoPanel = new JPanel();
                JLabel label = new JLabel(claspImage);
                infoPanel.add(label);
                frame.add(infoPanel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);

            }
        });

    }

    /**
     * Produces an information window by clicking on one of the Patient Criteria buttons
     * @param infoButton The specific criterion which the window describes.
     */
    public static void makeInfoWindow(InfoButton infoButton) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame(infoButton.name);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                JTextArea textArea = new JTextArea(15, 50);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.append(infoButton.description);
                JPanel infoPanel = new JPanel();
                infoPanel.add(textArea);
                frame.add(infoPanel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);

            }
        });
    }

    /**
     * Opens the window containing pictures of arches
     * @param button
     * @param imageOne
     * @param imageTwo
     */
    public static void openArchWindow(AbutmentToothButton button, ImageIcon imageOne, ImageIcon imageTwo) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame(button.name);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                JPanel panel = new JPanel();
                JLabel maxilla = new JLabel(imageOne);
                JLabel mandible = new JLabel(imageTwo);
                //add logic for abutment teeth
                panel.add(maxilla);
                panel.add(mandible);
                frame.add(panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(true);

            }
        });
    }

    /**
     * Resets all buttons to default state. Only called when RESET button is clicked.
     * @param g the GUI from which the buttons will be cleared.
     */
    public static void resetButtons(ClaspGUI g) {
        g.activeCriteria.clear();
        for (ClaspButton b : g.buttons) {
            b.updateStatus(g.activeCriteria);
        }
        for(ButtonGroup b : g.buttonGroups) {
            b.clearSelection();
        }
    }

    /**
     * Creates an ImageIcon if the path is valid.
     * @param path - resource path
     * @param description - description of the file
     */
    public ImageIcon createImageIcon(String path,
                                      String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    //Builds and shows the GUI.
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Clasp Selector Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(new ClaspGUI());

        //frame.setLocationByPlatform(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}