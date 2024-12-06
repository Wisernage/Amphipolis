package view;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLayeredPane layeredPane;
	private JPanel backgroundPanel, mosaicArea, statueArea, amphoraArea, skeletonArea, landslideArea, playerTiles, characterPanel, optionsPanel;
	private JButton drawButton, endTurnButton, startGameButton, tileButton;
	private ArrayList<JButton> mosaicTiles, statueTiles, amphoraTiles, skeletonTiles, landslideTiles;
	private JButton[] characters, Archaeologist, Assistant, Digger, Professor;
	private ArrayList<JButton>[] allPlayersTiles;
	private JLabel background, useCharacterLabel;
	private JLabel[] playerNames;
	/**
	 * <b>Constructor</b>: Runs the initialization function for the GUI objects<br />
	 * <b>Preconditions</b>: new GUI at game beginning <br />
	 * <b>Postconditions</b>: Runs the initialization function for the GUI objects<br />
	 * <b>Invariants</b>: -
	 */
    public GUI() {
    	initialize();
    }
    /**
	 * <b>Transformer(Applicative)</b>: Initializes GUI objects<br />
	 * <b>Preconditions</b>: new GUI at game beginning <br />
	 * <b>Postconditions</b>: Initializes GUI objects<br />
	 * <b>Invariants</b>: -
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
    	allPlayersTiles = new ArrayList[4];
    	for (int i = 0; i<4; i++) {
    		allPlayersTiles[i] = new ArrayList<JButton>();
    	}
    	playerNames = new JLabel[4];
    	setMosaicTiles(new ArrayList<JButton>(27));
    	setStatueTiles(new ArrayList<JButton>(24));
    	setAmphoraTiles(new ArrayList<JButton>(30));
    	setSkeletonTiles(new ArrayList<JButton>(30));
    	setLandslideTiles(new ArrayList<JButton>(16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/img/tile_back.png")));
		setTitle("Amphipolis");
		setBounds(100, 100, 750, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 550, 550);
		getContentPane().add(layeredPane);
		layeredPane.setLayout(null);
		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(0, 0, 550, 550);
		layeredPane.add(backgroundPanel);
		background = new JLabel("");
		background.setBounds(0, 0, 550, 550);
		background.setIcon(new ImageIcon(scale("/resources/img/background.png", 550, 550)));
		backgroundPanel.setLayout(null);
		backgroundPanel.add(background);
		Color back = new Color(236, 208, 160);
		setMosaicArea(new JPanel());
		getMosaicArea().setBackground(back);
		layeredPane.setLayer(getMosaicArea(), 1);
		getMosaicArea().setBounds(16, 14, 160, 140);
		layeredPane.add(getMosaicArea());
		getMosaicArea().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		setStatueArea(new JPanel());
		getStatueArea().setBackground(back);
		layeredPane.setLayer(getStatueArea(), 1);
		getStatueArea().setBounds(376, 14, 160, 140);
		layeredPane.add(getStatueArea());
		
		setAmphoraArea(new JPanel());
		getAmphoraArea().setBackground(back);
		layeredPane.setLayer(getAmphoraArea(), 1);
		getAmphoraArea().setBounds(16, 395, 160, 140);
		layeredPane.add(getAmphoraArea());
		
		setSkeletonArea(new JPanel());
		getSkeletonArea().setBackground(back);
		layeredPane.setLayer(getSkeletonArea(), 1);
		getSkeletonArea().setBounds(376, 395, 158, 140);
		layeredPane.add(getSkeletonArea());
		
		setLandslideArea(new JPanel());
		getLandslideArea().setBackground(back);
		layeredPane.setLayer(getLandslideArea(), 1);
		getLandslideArea().setBounds(198, 240, 160, 160);
		layeredPane.add(getLandslideArea());
		
		setPlayerTiles(new JPanel());
		getPlayerTiles().setBounds(0, 550, 734, 161);
		getContentPane().add(getPlayerTiles());
		
		setCharacterPanel(new JPanel());
		getCharacterPanel().setBounds(550, 0, 184, 400);
		getContentPane().add(getCharacterPanel());
		getCharacterPanel().setLayout(null);
		
		optionsPanel = new JPanel();
		optionsPanel.setBounds(550, 400, 184, 150);
		getContentPane().add(optionsPanel);
		optionsPanel.setLayout(null);
		
		useCharacterLabel = new JLabel("Use Character");
		useCharacterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		useCharacterLabel.setBounds(0, 80, 184, 20);
		getCharacterPanel().add(useCharacterLabel);
		
		setDrawButton(new JButton("Draw Tiles"));
		getDrawButton().setBounds(0, 0, 184, 75);
		optionsPanel.add(getDrawButton());
		
		setEndTurnButton(new JButton("End Turn"));
		getEndTurnButton().setBounds(0, 75, 184, 75);
		optionsPanel.add(getEndTurnButton());
		
		
		setStartGameButton(new JButton("Start Game"));
		getStartGameButton().setBounds(0, 0, 184, 75);
		getStartGameButton().setEnabled(false);
		getPlayerTiles().add(getStartGameButton());
    }
	/**
	 * <b>Transformer(Mutative)</b>: Scales an image to given dimensions<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns scaled image<br />
	 * <b>Invariants</b>: -
	 */
    public Image scale(String URL, int dimension1, int dimension2) {
		Image img = new ImageIcon(GUI.class.getResource(URL)).getImage();
		Image newimg = img.getScaledInstance(dimension1, dimension2,  java.awt.Image.SCALE_SMOOTH);
		return newimg;
	}
    /**
	 * <b>Transformer(Mutative)</b>: Scales an image to given dimensions and greys it out, disabling it<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns scaled and greyed image <br />
	 * <b>Invariants</b>: -
	 */
	public Image grey(String URL, int dimension1, int dimension2) {
		Image img = new ImageIcon(GUI.class.getResource(URL)).getImage();
		Image newimg = img.getScaledInstance(dimension1, dimension2,  java.awt.Image.SCALE_SMOOTH);
		Image newimg2 = GrayFilter.createDisabledImage(newimg);
		return newimg2;
	}
	/**
	 * <b>Transformer(Applicative)</b>: Puts specified tile to specified area<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Puts specified tile to specified area<br />
	 * <b>Invariants</b>: -
	 */
	public JButton placeTileToArea(String tileType, JPanel tileArea, ArrayList<JButton> tileList) {
		setTileButton(makeNewTileButton(tileType,18));
		tileList.add(getTileButton());
		tileArea.add(getTileButton());
		setVisible(true);
		return getTileButton();
	}
	/**
	 * <b>Transformer(Applicative)</b>: Removes specified tile from specified area<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Removes specified tile from specified area<br />
	 * <b>Invariants</b>: -
	 */
	public void removeTileFromArea(JPanel tileArea, ArrayList<JButton> tileList, JButton tileType, int tileCount) {
		tileArea.remove(tileList.get(tileCount));
		tileList.remove(tileCount);
		tileArea.revalidate();
		tileArea.repaint();
		setVisible(true);
	}
	/**
	 * <b>Transformer(Applicative)</b>: Puts specified tile to specified player's tiles<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Puts specified tile to specified player's tiles<br />
	 * <b>Invariants</b>: -
	 */
	public void addPlayerTile(String tileType, int player) {
		makeNewTileButton(tileType, 50);
		getPlayerTiles().add(getTileButton());
		getPlayerTiles().repaint();
		getPlayerTiles().revalidate();
		allPlayersTiles[player].add(getTileButton());
		setVisible(true);
	}
	/**
	 * <b>Transformer(Applicative)</b>: Makes a new tile button with specified icon URL and dimensions<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Makes a new tile button with specified icon URL and dimensions<br />
	 * <b>Invariants</b>: -
	 */
	public JButton makeNewTileButton(String tileType, int dimensions) {
		String path = "/resources/img/"+tileType+".png";
		setTileButton(new JButton());
		getTileButton().setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		getTileButton().setContentAreaFilled(false);
		getTileButton().setFocusable(false);
		getTileButton().setIcon(new ImageIcon(scale(path, dimensions, dimensions)));
		getTileButton().setVisible(true);
		getTileButton().setEnabled(true);
		return getTileButton();
	}
	/**
	 * <b>Transformer(Applicative)(Mutative)</b>: Sets previous player's tiles to not visible and current player's tiles to visible<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Sets previous player's tiles to not visible and current player's tiles to visible<br />
	 * <b>Invariants</b>: -
	 */
	public void showNextPlayerTiles(int player) {
		int playerPrev;
		if (player == 0) {
			playerPrev = 3;
		}
		else {
			playerPrev = player-1;
		}
		for (int i=0; i < allPlayersTiles[playerPrev].size(); i++) {
			allPlayersTiles[playerPrev].get(i).setVisible(false);
			allPlayersTiles[playerPrev].get(i).setEnabled(false);
		}
		for (int i=0; i < allPlayersTiles[player].size(); i++) {
			allPlayersTiles[player].get(i).setVisible(true);
			allPlayersTiles[player].get(i).setEnabled(true);
		}
    }
	/**
	 * <b>Transformer(Applicative)</b>: Initializes all character buttons<br />
	 * <b>Preconditions</b>: GUI beginning <br />
	 * <b>Postconditions</b>: Initializes all character buttons<br />
	 * <b>Invariants</b>: -
	 */
	public void initializeCharacter() {
		Archaeologist = new JButton[4];
		Assistant = new JButton[4];
		Digger = new JButton[4];
		Professor = new JButton[4];
		setCharacters(new JButton[16]);
		for (int i=0; i<4; i++) {
			Archaeologist[i] = new JButton("");
			Archaeologist[i].setBorder(BorderFactory.createEmptyBorder());
			Archaeologist[i].setContentAreaFilled(false);
			Archaeologist[i].setBounds(0, 140, 90, 128);
			Archaeologist[i].setIcon(new ImageIcon(scale("/resources/img/archaeologist.png", 90, 128)));
			Assistant[i] = new JButton("");
			Assistant[i].setBorder(BorderFactory.createEmptyBorder());
			Assistant[i].setContentAreaFilled(false);
			Assistant[i].setBounds(94, 140, 90, 128);
			Assistant[i].setIcon(new ImageIcon(scale("/resources/img/assistant.png", 90, 128)));
			Digger[i] = new JButton("");
			Digger[i].setBorder(BorderFactory.createEmptyBorder());
			Digger[i].setContentAreaFilled(false);
			Digger[i].setBounds(0, 272, 90, 128);
			Digger[i].setIcon(new ImageIcon(scale("/resources/img/digger.png", 90, 128)));
			Professor[i] = new JButton("");
			Professor[i].setBorder(BorderFactory.createEmptyBorder());
			Professor[i].setContentAreaFilled(false);
			Professor[i].setBounds(94, 272, 90, 128);
			Professor[i].setIcon(new ImageIcon(scale("/resources/img/professor.png", 90, 128)));
			getCharacters()[4*i]=Archaeologist[i];
			getCharacters()[4*i+1]=Assistant[i];
			getCharacters()[4*i+2]=Digger[i];
			getCharacters()[4*i+3]=Professor[i];
			getCharacters()[4*i].setEnabled(false);
			getCharacters()[4*i+1].setEnabled(false);
			getCharacters()[4*i+2].setEnabled(false);
			getCharacters()[4*i+3].setEnabled(false);
			characterPanel.add(Archaeologist[i]);
			characterPanel.add(Assistant[i]);
			characterPanel.add(Digger[i]);
			characterPanel.add(Professor[i]);
			Archaeologist[i].setVisible(false);
			Assistant[i].setVisible(false);
			Digger[i].setVisible(false);
			Professor[i].setVisible(false);
		}
    }
	/**
	 * <b>Transformer(Applicative)</b>: Greys out specified character<br />
	 * <b>Preconditions</b>: Character has been used <br />
	 * <b>Postconditions</b>: Greys out specified character<br />
	 * <b>Invariants</b>: -
	 */
	public void greyCharacter(int count) {
		if (count % 4 == 0) {
			getCharacters()[count].setIcon(new ImageIcon(grey("/resources/img/archaeologist.png", 90, 128)));
		}
		if (count % 4 == 1) {
			getCharacters()[count].setIcon(new ImageIcon(grey("/resources/img/assistant.png", 90, 128)));
		}
		if (count % 4 == 2) {
			getCharacters()[count].setIcon(new ImageIcon(grey("/resources/img/digger.png", 90, 128)));
		}
		if (count % 4 == 3) {
			getCharacters()[count].setIcon(new ImageIcon(grey("/resources/img/professor.png", 90, 128)));
		}
	}
	/**
	 * <b>Transformer(Applicative)(Mutative)</b>: Sets previous player's characters to not visible and current player's characters to visible<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Sets previous player's characters to not visible and current player's characters to visible<br />
	 * <b>Invariants</b>: -
	 */
	public void showNextCharacters(int player) {
		switch(player) {
			case 0:
				for (int i=12; i<15; i++) {
					getCharacters()[i].setVisible(false);
					getCharacters()[i].setEnabled(false);
					getCharacters()[i].revalidate();
					getCharacters()[i].repaint();
				}
				for (int i=0; i<4; i++) {
					getCharacters()[i].setVisible(true);
					getCharacters()[i].setEnabled(true);
					getCharacters()[i].revalidate();
					getCharacters()[i].repaint();
				}
				
			break;
			case 1:
				for (int i=0; i<4; i++) {
					getCharacters()[i].setVisible(false);
					getCharacters()[i].setEnabled(false);
					getCharacters()[i].revalidate();
					getCharacters()[i].repaint();
				}
				for (int i=4; i<8; i++) {
					getCharacters()[i].setVisible(true);
					getCharacters()[i].setEnabled(true);
					getCharacters()[i].revalidate();
					getCharacters()[i].repaint();
				}
				
			break;
			case 2:
				for (int i=4; i<8; i++) {
					getCharacters()[i].setVisible(false);
					getCharacters()[i].setEnabled(false);
					getCharacters()[i].revalidate();
					getCharacters()[i].repaint();
				}
				for (int i=8; i<12; i++) {
					getCharacters()[i].setVisible(true);
					getCharacters()[i].setEnabled(true);
					getCharacters()[i].revalidate();
					getCharacters()[i].repaint();
				}
			break;
			case 3:
				for (int i=8; i<12; i++) {
					getCharacters()[i].setVisible(false);
					getCharacters()[i].setEnabled(false);
					getCharacters()[i].revalidate();
					getCharacters()[i].repaint();
				}
				for (int i=12; i<16; i++) {
					getCharacters()[i].setVisible(true);
					getCharacters()[i].setEnabled(true);
					getCharacters()[i].revalidate();
					getCharacters()[i].repaint();
				}
			break;
		}
	}
	/**
	 * <b>Transformer(Applicative)(Mutative)</b>: Sets the player names<br />
	 * <b>Preconditions</b>: GUI beginning <br />
	 * <b>Postconditions</b>: Sets the player names<br />
	 * <b>Invariants</b>: -
	 */
	public void setPlayerNames(String name1, String name2, String name3, String name4) {
		playerNames[0] = new JLabel();
		playerNames[0].setText(name1);
		playerNames[0].setHorizontalAlignment(SwingConstants.CENTER);
		playerNames[0].setBounds(0, 0, 184, 50);
		playerNames[0].setVisible(false);
		getCharacterPanel().add(playerNames[0]);
		playerNames[1] = new JLabel();
		playerNames[1].setText(name2);
		playerNames[1].setHorizontalAlignment(SwingConstants.CENTER);
		playerNames[1].setBounds(0, 0, 184, 50);
		playerNames[1].setVisible(false);
		getCharacterPanel().add(playerNames[1]);
		playerNames[2] = new JLabel();
		playerNames[2].setText(name3);
		playerNames[2].setHorizontalAlignment(SwingConstants.CENTER);
		playerNames[2].setBounds(0, 0, 184, 50);
		playerNames[2].setVisible(false);
		getCharacterPanel().add(playerNames[2]);
		playerNames[3] = new JLabel();
		playerNames[3].setText(name4);
		playerNames[3].setHorizontalAlignment(SwingConstants.CENTER);
		playerNames[3].setBounds(0, 0, 184, 50);
		playerNames[3].setVisible(false);
		getCharacterPanel().add(playerNames[3]);
	}
	/**
	 * <b>Transformer(Mutative)</b>: Shows specified player's name<br />
	 * <b>Preconditions</b>: Game beginning and every new turn <br />
	 * <b>Postconditions</b>: Shows specified player's name<br />
	 * <b>Invariants</b>: -
	 */
	public void showPlayerName(int playercount) {
		playerNames[playercount].setVisible(true);
		setVisible(true);
	}
	/**
	 * <b>Transformer(Mutative)</b>: Hides specified player's name<br />
	 * <b>Preconditions</b>: Every new turn <br />
	 * <b>Postconditions</b>: Hides specified player's name<br />
	 * <b>Invariants</b>: -
	 */
	public void hidePlayerName(int playercount) {
		playerNames[playercount].setVisible(false);
		setVisible(true);
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JButton getDrawButton() {
		return drawButton;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setDrawButton(JButton drawButton) {
		this.drawButton = drawButton;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JButton getEndTurnButton() {
		return endTurnButton;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setEndTurnButton(JButton endTurnButton) {
		this.endTurnButton = endTurnButton;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JPanel getCharacterPanel() {
		return characterPanel;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setCharacterPanel(JPanel characterPanel) {
		this.characterPanel = characterPanel;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JButton getStartGameButton() {
		return startGameButton;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setStartGameButton(JButton startGameButton) {
		this.startGameButton = startGameButton;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JPanel getPlayerTiles() {
		return playerTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setPlayerTiles(JPanel playerTiles) {
		this.playerTiles = playerTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JPanel getMosaicArea() {
		return mosaicArea;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setMosaicArea(JPanel mosaicArea) {
		this.mosaicArea = mosaicArea;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JPanel getStatueArea() {
		return statueArea;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setStatueArea(JPanel statueArea) {
		this.statueArea = statueArea;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JPanel getAmphoraArea() {
		return amphoraArea;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setAmphoraArea(JPanel amphoraArea) {
		this.amphoraArea = amphoraArea;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JPanel getSkeletonArea() {
		return skeletonArea;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setSkeletonArea(JPanel skeletonArea) {
		this.skeletonArea = skeletonArea;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JPanel getLandslideArea() {
		return landslideArea;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setLandslideArea(JPanel landslideArea) {
		this.landslideArea = landslideArea;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public ArrayList<JButton> getMosaicTiles() {
		return mosaicTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setMosaicTiles(ArrayList<JButton> mosaicTiles) {
		this.mosaicTiles = mosaicTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public ArrayList<JButton> getStatueTiles() {
		return statueTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setStatueTiles(ArrayList<JButton> statueTiles) {
		this.statueTiles = statueTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public ArrayList<JButton> getAmphoraTiles() {
		return amphoraTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setAmphoraTiles(ArrayList<JButton> amphoraTiles) {
		this.amphoraTiles = amphoraTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public ArrayList<JButton> getSkeletonTiles() {
		return skeletonTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setSkeletonTiles(ArrayList<JButton> skeletonTiles) {
		this.skeletonTiles = skeletonTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public ArrayList<JButton> getLandslideTiles() {
		return landslideTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setLandslideTiles(ArrayList<JButton> landslideTiles) {
		this.landslideTiles = landslideTiles;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public JButton getTileButton() {
		return tileButton;
	}
	/**
	 * <b>Accessor(get), Transformer(Mutative)(set)</b>: Part of set/get methodology (same JavaDoc comment for all set-gets)
	 */
	public void setTileButton(JButton tileButton) {
		this.tileButton = tileButton;
	}
	public JButton[] getCharacters() {
		return characters;
	}
	public void setCharacters(JButton[] characters) {
		this.characters = characters;
	}
}

