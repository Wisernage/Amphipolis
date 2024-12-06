package view;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.*;

public class PlayerNamesAndVisits extends JFrame {
	private static final long serialVersionUID = 1L;
	private String[] NamesAndVisits = new String[8];
    private JTextField[] PlayersNames = new JTextField[4];
    private JTextField[] PlayersLastMuseumVisit = new JTextField[4];
    private JButton submit = new JButton("Submit");
    /**
	 * <b>Constructor</b>: Creates a new menu for submitting player names and days since last museum visit <br />
	 * <b>Preconditions</b>: Start of game<br />
	 * <b>Constructor</b>: Creates a new menu for submitting player names and days since last museum visit <br />
	 * <b>Invariants</b>: -
	 */
    public PlayerNamesAndVisits() {
        super("Player Menu");
        JLabel instructions = new JLabel("Enter the names of the players and the number of days since they last visited a museum.", JLabel.CENTER);
        instructions.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        JPanel visitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 70, 0));
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/img/sphinx.png")));
        setSize(720, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        PlayersNames[0] = new JTextField(10);
        namePanel.add(PlayersNames[0]);
        PlayersLastMuseumVisit[0] = new JTextField(5);
        visitPanel.add(PlayersLastMuseumVisit[0]);
        PlayersNames[1] = new JTextField(10);
        namePanel.add(PlayersNames[1]);
        PlayersLastMuseumVisit[1] = new JTextField(5);
        visitPanel.add(PlayersLastMuseumVisit[1]);
        PlayersNames[2] = new JTextField(10);
        namePanel.add(PlayersNames[2]);
        PlayersLastMuseumVisit[2] = new JTextField(5);
        visitPanel.add(PlayersLastMuseumVisit[2]);
        PlayersNames[3] = new JTextField(10);
        namePanel.add(PlayersNames[3]);
        PlayersLastMuseumVisit[3] = new JTextField(5);
        visitPanel.add(PlayersLastMuseumVisit[3]);
        southPanel.add(getSubmit());
        Box theBox = Box.createVerticalBox();
        theBox.add(Box.createVerticalStrut(50));
        theBox.add(instructions);
        theBox.add(Box.createVerticalStrut(100));	
        theBox.add(namePanel);
        theBox.add(visitPanel);
        theBox.add(Box.createVerticalStrut(100));
        theBox.add(southPanel);
        add(theBox);
        this.setVisible(true);
    }
    /**
	 * <b>Transformer(Applicative)</b>: Fills the NamesAndVisits String array after checking if values are integers for LastMuseumVisits<br />
	 * <b>Preconditions</b>: Submit button is pressed <br />
	 * <b>Postconditions</b>: Fills the NamesAndVisits String array after checking if values are integers for LastMuseumVisits<br />
	 * <b>Invariants</b>: -
	 */
    public void submitAction() {
    	for(int i=0; i<4; i++) {
    		NamesAndVisits[i] = new String();
    		NamesAndVisits[i]= PlayersNames[i].getText();
	    	try {
	    		Integer.parseInt(PlayersLastMuseumVisit[i].getText());
	    	}
	    	catch(Exception NumberFormatException) {
	    		new ErrorDialog();
	    		break;
	    	}
	    	NamesAndVisits[4+i] = new String();
	    	NamesAndVisits[4+i] = PlayersLastMuseumVisit[i].getText();
    	}
    }
    /**
	 * <b>Accessor</b>: Returns names and visits of players (part of set-get methodology)<br />
	 * <b>Preconditions</b>: After submit has ran <br />
	 * <b>Postconditions</b>: Returns names and visits of players(part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
    public String[] getNamesAndVisits() {
    	return NamesAndVisits;
    }
    /**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JButton getSubmit() {
		return submit;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setSubmit(JButton submit) {
		this.submit = submit;
	}
}