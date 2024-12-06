package controller;
import model.boardPack.*;
import model.playerPack.*;
import model.tilePack.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.*;

public class Controller {
	private int tileTakenFromArea, tileTakenAmount=0, outOfAreaAllowed=0, anyAreaAllowed=0, sameAreaAllowed=0, otherAreaAllowed=0, firstPlayer, currentPlayer, charHasBeenUsed=0, otherArea1=-1, otherArea2=-1;
	private Player[] playerArray;
	private int[] totalPoints;
	private Board board;
	private Bag bag;
	private GUI GraphicUI;
	
	/**
	 * <b>Constructor</b>: Runs the initialization functions for the several objects<br />
	 * <b>Preconditions</b>: Game start <br />
	 * <b>Postconditions</b>: Runs the initialization functions for the several objects<br />
	 * <b>Invariants</b>: -
	 */
	public Controller() {
		initialize();
		GUIinitialize();
		StartingMenu();
		initiateActionListeners();
	}
	/**
	 * <b>Transformer(Mutative)</b>: Initializes some variables and sets the game board, players and bag<br />
	 * <b>Preconditions</b>: Game start <br />
	 * <b>Postconditions</b>: Initializes some variables and sets the game board, players and bag<br />
	 * <b>Invariants</b>: -
	 */
	
	public void initialize() {
		totalPoints = new int[4];
		board = new Board();
		bag = new Bag();
		bag.fillBag();
		playerArray = board.makePlayersAndCharacters();
	}
	
	/**
	 * <b>Transformer(Mutative)</b>: Initializes the GUI<br />
	 * <b>Preconditions</b>: Game start after other initializations <br />
	 * <b>Postconditions</b>: Initializes the GUI<br />
	 * <b>Invariants</b>: -
	 */
	
	public void GUIinitialize() {
		GraphicUI = new GUI();
		GraphicUI.initializeCharacter();
		GraphicUI.getDrawButton().setEnabled(false);
		GraphicUI.getEndTurnButton().setEnabled(false);
		GraphicUI.setVisible(true);
	}
	
	/**
	 * <b>Transformer(Mutative)</b>: Initializes the Starting Menu<br />
	 * <b>Preconditions</b>: Game start <br />
	 * <b>Postconditions</b>:Initializes the Starting Menu<br />
	 * <b>Invariants</b>: -
	 */
	public void StartingMenu() {
		PlayerNamesAndVisits players = new PlayerNamesAndVisits();
		players.getSubmit().addActionListener( (e)-> {
            players.submitAction();
            String[] namevisits = players.getNamesAndVisits();
            playerArray[0].setName(namevisits[0]);
            playerArray[1].setName(namevisits[1]);
            playerArray[2].setName(namevisits[2]);
            playerArray[3].setName(namevisits[3]);
            playerArray[0].setlastMuseumVisit(Integer.parseInt(namevisits[4]));
            playerArray[1].setlastMuseumVisit(Integer.parseInt(namevisits[5]));
            playerArray[2].setlastMuseumVisit(Integer.parseInt(namevisits[6]));
            playerArray[3].setlastMuseumVisit(Integer.parseInt(namevisits[7]));
            firstPlayer = board.setFirst(playerArray);
            currentPlayer = firstPlayer;
            GraphicUI.getStartGameButton().setEnabled(true);
    		players.dispose(); 
        });
	}
	/**
	 * <b>Transformer(Mutative)</b>: Initializes the action listeners<br />
	 * <b>Preconditions</b>: Game start after GUI initialization <br />
	 * <b>Postconditions</b>: Initializes the action listeners<br />
	 * <b>Invariants</b>: -
	 */
	
	
	public void initiateActionListeners() {
		GraphicUI.getStartGameButton().addActionListener( (e)-> {
			GraphicUI.getDrawButton().setEnabled(true);
			GraphicUI.setPlayerNames(playerArray[0].getName(), playerArray[1].getName(), playerArray[2].getName(), playerArray[3].getName());
			GraphicUI.showPlayerName(firstPlayer);
			GraphicUI.showNextCharacters(currentPlayer);
			GraphicUI.getStartGameButton().setVisible(false);
			GraphicUI.getPlayerTiles().remove(GraphicUI.getStartGameButton());
		});
		GraphicUI.getDrawButton().addActionListener( (e)-> {
			if (GraphicUI.getDrawButton().isEnabled()) {
				drawTiles();
				GraphicUI.getEndTurnButton().setEnabled(true);
				if (board.checkGameOver()) {
					gameOver();
				}
			}
		});
		GraphicUI.getEndTurnButton().addActionListener( (e)-> {
			endTurn();
		});
		for (int i=0; i<16; i++) {
			int charCount = i%4;
			GraphicUI.getCharacters()[i].addActionListener( (e)-> {
				useChar(currentPlayer, charCount);
			});
		}
	}
	/**
	 * <b>Transformer(Mutative)</b>: Gets next player, adjusts the GUI player name field and the player tile field<br />
	 * <b>Preconditions</b>: Every new turn <br />
	 * <b>Postconditions</b>: Gets next player, adjusts the GUI player name field and the player tile field <br />
	 * <b>Invariants</b>: -
	 */
	public void nextPlayer() {
		GraphicUI.hidePlayerName(currentPlayer);
		if (currentPlayer==3) {
			currentPlayer = 0;
		}
		else {
			currentPlayer++;
		}
		GraphicUI.showPlayerName(currentPlayer);
		GraphicUI.showNextCharacters(currentPlayer);
		GraphicUI.showNextPlayerTiles(currentPlayer);
	}
	/**
	 * <b>Transformer(Mutative)</b>: Removes a tile from the board and adds it to a player's tiles (both GUI and Model components)<br />
	 * <b>Preconditions</b>: A tile has been chosen by the current player (need to have the rights to take it) <br />
	 * <b>Postconditions</b>: Tile is added to player and removed from the board<br />
	 * <b>Invariants</b>: -
	 */
	public void takeTileFromBoard(JPanel tileArea, ArrayList<JButton> tileList, JButton tileType, JButton tileButton, Tile tile, int area) {
		if (!GraphicUI.getDrawButton().isEnabled()) {
			if (tileTakenAmount==0) {
				addTileToPlayer(currentPlayer, (FindingTile) tile);
				switch (area) {
				case 1:
					board.mosaicArea.remove(tileList.indexOf(tileButton));
					break;
				case 2:
					board.statueArea.remove(tileList.indexOf(tileButton));
					break;
				case 3:
					board.amphoraArea.remove(tileList.indexOf(tileButton));
					break;
				case 4:
					board.skeletonArea.remove(tileList.indexOf(tileButton));
					break;
				}
				GraphicUI.removeTileFromArea(tileArea, tileList, tileType, tileList.indexOf(tileButton));
				tileTakenAmount++;
				tileTakenFromArea = area;
			}
			else if (tileTakenAmount==1 && area == tileTakenFromArea) {
				addTileToPlayer(currentPlayer, (FindingTile) tile);
				switch (area) {
				case 1:
					board.mosaicArea.remove(tileList.indexOf(tileButton));
					break;
				case 2:
					board.statueArea.remove(tileList.indexOf(tileButton));
					break;
				case 3:
					board.amphoraArea.remove(tileList.indexOf(tileButton));
					break;
				case 4:
					board.skeletonArea.remove(tileList.indexOf(tileButton));
					break;
				}
				GraphicUI.removeTileFromArea(tileArea, tileList, tileType, tileList.indexOf(tileButton));
				tileTakenAmount++;
			}
			else if (outOfAreaAllowed > 0 && area != tileTakenFromArea) {
				addTileToPlayer(currentPlayer, (FindingTile) tile);
				switch (area) {
				case 1:
					board.mosaicArea.remove(tileList.indexOf(tileButton));
					break;
				case 2:
					board.statueArea.remove(tileList.indexOf(tileButton));
					break;
				case 3:
					board.amphoraArea.remove(tileList.indexOf(tileButton));
					break;
				case 4:
					board.skeletonArea.remove(tileList.indexOf(tileButton));
					break;
				}
				GraphicUI.removeTileFromArea(tileArea, tileList, tileType, tileList.indexOf(tileButton));
				outOfAreaAllowed--;
			}
			else if (anyAreaAllowed > 0) {
				addTileToPlayer(currentPlayer, (FindingTile) tile);
				switch (area) {
				case 1:
					board.mosaicArea.remove(tileList.indexOf(tileButton));
					break;
				case 2:
					board.statueArea.remove(tileList.indexOf(tileButton));
					break;
				case 3:
					board.amphoraArea.remove(tileList.indexOf(tileButton));
					break;
				case 4:
					board.skeletonArea.remove(tileList.indexOf(tileButton));
					break;
				}
				GraphicUI.removeTileFromArea(tileArea, tileList, tileType, tileList.indexOf(tileButton));
				anyAreaAllowed--;
			}
			else if (sameAreaAllowed > 0 && area == tileTakenFromArea) {
				addTileToPlayer(currentPlayer, (FindingTile) tile);
				switch (area) {
				case 1:
					board.mosaicArea.remove(tileList.indexOf(tileButton));
					break;
				case 2:
					board.statueArea.remove(tileList.indexOf(tileButton));
					break;
				case 3:
					board.amphoraArea.remove(tileList.indexOf(tileButton));
					break;
				case 4:
					board.skeletonArea.remove(tileList.indexOf(tileButton));
					break;
				}
				GraphicUI.removeTileFromArea(tileArea, tileList, tileType, tileList.indexOf(tileButton));
				sameAreaAllowed--;
			}
			else if (otherAreaAllowed == 3 && area != tileTakenFromArea) {
				addTileToPlayer(currentPlayer, (FindingTile) tile);
				switch (area) {
				case 1:
					board.mosaicArea.remove(tileList.indexOf(tileButton));
					break;
				case 2:
					board.statueArea.remove(tileList.indexOf(tileButton));
					break;
				case 3:
					board.amphoraArea.remove(tileList.indexOf(tileButton));
					break;
				case 4:
					board.skeletonArea.remove(tileList.indexOf(tileButton));
					break;
				}
				GraphicUI.removeTileFromArea(tileArea, tileList, tileType, tileList.indexOf(tileButton));
				otherArea1 = area;
				otherAreaAllowed--;
			}
			else if (otherAreaAllowed == 2 && area != tileTakenFromArea && area != otherArea1) {
				addTileToPlayer(currentPlayer, (FindingTile) tile);
				switch (area) {
				case 1:
					board.mosaicArea.remove(tileList.indexOf(tileButton));
					break;
				case 2:
					board.statueArea.remove(tileList.indexOf(tileButton));
					break;
				case 3:
					board.amphoraArea.remove(tileList.indexOf(tileButton));
					break;
				case 4:
					board.skeletonArea.remove(tileList.indexOf(tileButton));
					break;
				}
				GraphicUI.removeTileFromArea(tileArea, tileList, tileType, tileList.indexOf(tileButton));
				otherArea2 = area;
				otherAreaAllowed--;
			}
			else if (otherAreaAllowed == 1 && area != tileTakenFromArea && area != otherArea1 && area != otherArea2) {
				addTileToPlayer(currentPlayer, (FindingTile) tile);
				switch (area) {
				case 1:
					board.mosaicArea.remove(tileList.indexOf(tileButton));
					break;
				case 2:
					board.statueArea.remove(tileList.indexOf(tileButton));
					break;
				case 3:
					board.amphoraArea.remove(tileList.indexOf(tileButton));
					break;
				case 4:
					board.skeletonArea.remove(tileList.indexOf(tileButton));
					break;
				}
				GraphicUI.removeTileFromArea(tileArea, tileList, tileType, tileList.indexOf(tileButton));
				otherAreaAllowed--;
			}
		}
	}
	/**
	 * <b>Transformer(Mutative) </b>: Adds a specified tile to the current player's tiles (GUI) <br />
	 * <b>Preconditions</b>: When tiles are taken from the board and placed to a player<br />
	 * <b>Postconditions</b>: Adds a specified tile to the current player's tiles (GUI) <br />
	 * <b>Invariants</b>: -
	 */
	public void addTileToPlayer(int player, FindingTile tile) {
		playerArray[currentPlayer].addPlayerTiles(tile);
		if (tile instanceof MosaicTile) {
			MosaicTile.mosaicColor color = ((MosaicTile) tile).getMosaicColor();
			switch (color) {
			case Green:
				GraphicUI.addPlayerTile("mosaic_green", player);
				break;
			case Red:
				GraphicUI.addPlayerTile("mosaic_red", player);
				break;
			case Yellow:
				GraphicUI.addPlayerTile("mosaic_yellow", player);
				break;
			}
		}
		else if (tile instanceof CaryatidTile) {
			GraphicUI.addPlayerTile("caryatid", player);
		}
		else if (tile instanceof SphinxTile) {
			GraphicUI.addPlayerTile("sphinx", player);
		}
		else if (tile instanceof AmphoraTile) {
			AmphoraTile.amphoraColor color = ((AmphoraTile) tile).getAmphoraColor();
			switch (color) {
			case Blue:
				GraphicUI.addPlayerTile("amphora_blue", player);
				break;
			case Brown:
				GraphicUI.addPlayerTile("amphora_brown", player);
				break;
			case Red:
				GraphicUI.addPlayerTile("amphora_red", player);
				break;
			case Green:
				GraphicUI.addPlayerTile("amphora_green", player);
				break;
			case Yellow:
				GraphicUI.addPlayerTile("amphora_yellow", player);
				break;
			case Purple:
				GraphicUI.addPlayerTile("amphora_purple", player);
				break;
			}
		}
		else if (tile instanceof SkeletonTile) {
			SkeletonTile.skeletonPart part = ((SkeletonTile) tile).getSkeletonPart();
			SkeletonTile.skeletonSize size = ((SkeletonTile) tile).getSkeletonSize();
			switch(part) {
				case Upper:
					switch (size) {
						case Big:
							GraphicUI.addPlayerTile("skeleton_big_top", player);
							break;
						case Small:
							GraphicUI.addPlayerTile("skeleton_small_top", player);
							break;
					}
				break;
				case Bottom:
					switch (size) {
						case Big:
							GraphicUI.addPlayerTile("skeleton_big_bottom", player);
							break;
						case Small:
							GraphicUI.addPlayerTile("skeleton_small_bottom", player);
							break;
					}
				break;
			}
		}
		
	}
	/**
	 * <b>Transformer(Mutative) </b>: Is used to place a tile to the board and add the appropriate action listener to it<br />
	 * <b>Preconditions</b>: every turn when tiles are drawn <br />
	 * <b>Postconditions</b>: Is used to place a tile to the board and add the appropriate action listener to it<br />
	 * <b>Invariants</b>: -
	 */
	public void placeTilesToBoard(Tile tile) {
		int currentTile;
		JButton tileButton;
		if (tile instanceof MosaicTile) {
			MosaicTile.mosaicColor color = ((MosaicTile) tile).getMosaicColor();
			switch (color) {
			case Green:
				tileButton = GraphicUI.placeTileToArea("mosaic_green", GraphicUI.getMosaicArea(), GraphicUI.getMosaicTiles());
				currentTile = GraphicUI.getMosaicTiles().size() - 1;
				GraphicUI.getMosaicTiles().get(currentTile).addActionListener( (e)-> {
					takeTileFromBoard(GraphicUI.getMosaicArea(), GraphicUI.getMosaicTiles(), GraphicUI.getTileButton(), tileButton, tile, 1);
				});
				break;
			case Red:
				tileButton = GraphicUI.placeTileToArea("mosaic_red", GraphicUI.getMosaicArea(), GraphicUI.getMosaicTiles());
				currentTile = GraphicUI.getMosaicTiles().size() - 1;
				GraphicUI.getMosaicTiles().get(currentTile).addActionListener( (e)-> {
					takeTileFromBoard(GraphicUI.getMosaicArea(), GraphicUI.getMosaicTiles(), GraphicUI.getTileButton(), tileButton, tile, 1);
				});
				break;
			case Yellow:
				tileButton = GraphicUI.placeTileToArea("mosaic_yellow", GraphicUI.getMosaicArea(), GraphicUI.getMosaicTiles());
				currentTile = GraphicUI.getMosaicTiles().size() - 1;
				GraphicUI.getMosaicTiles().get(currentTile).addActionListener( (e)-> {
					takeTileFromBoard(GraphicUI.getMosaicArea(), GraphicUI.getMosaicTiles(), GraphicUI.getTileButton(), tileButton, tile, 1);
				});
				break;
			}
		}
		else if (tile instanceof CaryatidTile) {
			tileButton = GraphicUI.placeTileToArea("caryatid", GraphicUI.getStatueArea(), GraphicUI.getStatueTiles());
			currentTile = GraphicUI.getStatueTiles().size() - 1;
			GraphicUI.getStatueTiles().get(currentTile).addActionListener( (e)-> {
				takeTileFromBoard(GraphicUI.getStatueArea(), GraphicUI.getStatueTiles(), GraphicUI.getTileButton(), tileButton, tile, 2);
			});
		}
		else if (tile instanceof SphinxTile) {
			tileButton = GraphicUI.placeTileToArea("sphinx", GraphicUI.getStatueArea(), GraphicUI.getStatueTiles());
			currentTile = GraphicUI.getStatueTiles().size() - 1;
			GraphicUI.getStatueTiles().get(currentTile).addActionListener( (e)-> {
				takeTileFromBoard(GraphicUI.getStatueArea(), GraphicUI.getStatueTiles(), GraphicUI.getTileButton(), tileButton, tile, 2);
			});
		}
		else if (tile instanceof AmphoraTile) {
			AmphoraTile.amphoraColor color = ((AmphoraTile) tile).getAmphoraColor();
			switch (color) {
			case Blue:
				tileButton = GraphicUI.placeTileToArea("amphora_blue", GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles());
				currentTile = GraphicUI.getAmphoraTiles().size() - 1;
				GraphicUI.getAmphoraTiles().get(currentTile).addActionListener( (e)-> {
					takeTileFromBoard(GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles(), GraphicUI.getTileButton(), tileButton, tile, 3);
				});
				break;
			case Brown:
				tileButton = GraphicUI.placeTileToArea("amphora_brown", GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles());
				currentTile = GraphicUI.getAmphoraTiles().size() - 1;
				GraphicUI.getAmphoraTiles().get(currentTile).addActionListener( (e)-> {
					takeTileFromBoard(GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles(), GraphicUI.getTileButton(), tileButton, tile, 3);
				});
				break;
			case Red:
				tileButton = GraphicUI.placeTileToArea("amphora_red", GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles());
				currentTile = GraphicUI.getAmphoraTiles().size() - 1;
				GraphicUI.getAmphoraTiles().get(currentTile).addActionListener( (e)-> {
					takeTileFromBoard(GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles(), GraphicUI.getTileButton(), tileButton, tile, 3);
				});
				break;
			case Green:
				tileButton = GraphicUI.placeTileToArea("amphora_green", GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles());
				currentTile = GraphicUI.getAmphoraTiles().size() - 1;
				GraphicUI.getAmphoraTiles().get(currentTile).addActionListener( (e)-> {
					takeTileFromBoard(GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles(), GraphicUI.getTileButton(), tileButton, tile, 3);
				});
				break;
			case Yellow:
				tileButton = GraphicUI.placeTileToArea("amphora_yellow", GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles());
				currentTile = GraphicUI.getAmphoraTiles().size() - 1;
				GraphicUI.getAmphoraTiles().get(currentTile).addActionListener( (e)-> {
					takeTileFromBoard(GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles(), GraphicUI.getTileButton(), tileButton, tile, 3);
				});
				break;
			case Purple:
				tileButton = GraphicUI.placeTileToArea("amphora_purple", GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles());
				currentTile = GraphicUI.getAmphoraTiles().size() - 1;
				GraphicUI.getAmphoraTiles().get(currentTile).addActionListener( (e)-> {
					takeTileFromBoard(GraphicUI.getAmphoraArea(), GraphicUI.getAmphoraTiles(), GraphicUI.getTileButton(), tileButton, tile, 3);
				});
				break;
			}
		}
		else if (tile instanceof SkeletonTile) {
			SkeletonTile.skeletonPart part = ((SkeletonTile) tile).getSkeletonPart();
			SkeletonTile.skeletonSize size = ((SkeletonTile) tile).getSkeletonSize();
			switch(part) {
				case Upper:
					switch (size) {
						case Big:
							tileButton = GraphicUI.placeTileToArea("skeleton_big_top", GraphicUI.getSkeletonArea(), GraphicUI.getSkeletonTiles());
							currentTile = GraphicUI.getSkeletonTiles().size() - 1;
							GraphicUI.getSkeletonTiles().get(currentTile).addActionListener( (e)-> {
								takeTileFromBoard(GraphicUI.getSkeletonArea(), GraphicUI.getSkeletonTiles(), GraphicUI.getTileButton(), tileButton, tile, 4);
							});
							break;
						case Small:
							tileButton = GraphicUI.placeTileToArea("skeleton_small_top", GraphicUI.getSkeletonArea(), GraphicUI.getSkeletonTiles());
							currentTile = GraphicUI.getSkeletonTiles().size() - 1;
							GraphicUI.getSkeletonTiles().get(currentTile).addActionListener( (e)-> {
								takeTileFromBoard(GraphicUI.getSkeletonArea(), GraphicUI.getSkeletonTiles(), GraphicUI.getTileButton(), tileButton, tile, 4);
							});
							break;
					}
				break;
				case Bottom:
					switch (size) {
						case Big:
							tileButton = GraphicUI.placeTileToArea("skeleton_big_bottom", GraphicUI.getSkeletonArea(), GraphicUI.getSkeletonTiles());
							currentTile = GraphicUI.getSkeletonTiles().size() - 1;
							GraphicUI.getSkeletonTiles().get(currentTile).addActionListener( (e)-> {
								takeTileFromBoard(GraphicUI.getSkeletonArea(), GraphicUI.getSkeletonTiles(), GraphicUI.getTileButton(), tileButton, tile, 4);
							});
							break;
						case Small:
							tileButton = GraphicUI.placeTileToArea("skeleton_small_bottom", GraphicUI.getSkeletonArea(), GraphicUI.getSkeletonTiles());
							currentTile = GraphicUI.getSkeletonTiles().size() - 1;
							GraphicUI.getSkeletonTiles().get(currentTile).addActionListener( (e)-> {
								takeTileFromBoard(GraphicUI.getSkeletonArea(), GraphicUI.getSkeletonTiles(), GraphicUI.getTileButton(), tileButton, tile, 4);
							});
							break;
					}
				break;
			}
		}
		else if (tile instanceof LandslideTile) {
			GraphicUI.placeTileToArea("landslide", GraphicUI.getLandslideArea(), GraphicUI.getLandslideTiles());
		}
		GraphicUI.getEndTurnButton().addActionListener( (e)-> {
			endTurn();
		});
		
	}
	/**
	 * <b>Transformer(Mutative)</b>: Draws tiles from the bag and places them to the board.<br />
	 * <b>Preconditions</b>: every turn <br />
	 * <b>Postconditions</b>: Draws tiles from the bag and places them to the board<br />
	 * <b>Invariants</b>: -
	 */
	public void drawTiles() {
		placeTilesToBoard(board.drawTile(bag)); 
		placeTilesToBoard(board.drawTile(bag)); 
		placeTilesToBoard(board.drawTile(bag)); 
		placeTilesToBoard(board.drawTile(bag));
		GraphicUI.getDrawButton().setEnabled(false);
	}
	/**
	 * <b>Transformer(Mutative)</b>: Calculates the total points of each player and returns an int array with all the values<br />
	 * <b>Preconditions</b>: end of game <br />
	 * <b>Postconditions</b>: Calculates the total points of each player and returns an int array with all the values<br />
	 * <b>Invariants</b>: -
	 */
	public int[] calcTotalPoints() {
		int mincaryatids=Integer.MAX_VALUE, maxcaryatids=0, minsphinxes=Integer.MAX_VALUE, maxsphinxes=0;
		int[] points = new int[4];
		int[] caryatids = new int[4];
		int[] sphinxes = new int[4];
		for (int i=0; i<4; i++) {
			caryatids[i] = playerArray[i].getCaryatids();
			sphinxes[i] = playerArray[i].getSphinxes();
			points[i] = playerArray[i].calcPoints();
		}
		for(int i=0; i<4; i++) {
			if (caryatids[i] < mincaryatids){
				mincaryatids = caryatids[i];
			}
			if (caryatids[i] > maxcaryatids){
				maxcaryatids = caryatids[i];
			}
			if (sphinxes[i] < minsphinxes){
				minsphinxes = sphinxes[i];
			}
			if (sphinxes[i] > maxsphinxes){
				maxsphinxes = sphinxes[i];
			}
		}
		
		for(int i =0; i<4; i++) {
			if (caryatids[i] == maxcaryatids){
				if (maxcaryatids!=0)
					points[i]+=6;
			}
			else if(caryatids[i] > mincaryatids) {
				points[i]+=3;
			}
			if (sphinxes[i] == maxsphinxes){
				if (maxsphinxes!=0)
					points[i]+=6;
			}
			else if(sphinxes[i] > minsphinxes) {
				points[i]+=3;
			}
		}
		return points;
	}
	/**
	 * <b>Transformer(Mutative)</b>: Uses a character if he has not already been used and passes the appropriate information <br />
	 * <b>Preconditions</b>: An unused Character has been used after a tile has been drawn from the board and no other character is being used currently<br />
	 * <b>Postconditions</b>:Uses a character if he has not already been used and passes the appropriate information <br />
	 * <b>Invariants</b>: -
	 */
	public void useChar(int player,int character) {
		if (!playerArray[player].getPlayerCharacters().get(character).gethasBeenUsed() && tileTakenAmount >= 1 && charHasBeenUsed == 0) {
			playerArray[player].useCharacter(character);
			GraphicUI.greyCharacter(4*player+character);
			charHasBeenUsed=1;
			switch (character) {
				case 0:
					outOfAreaAllowed=2;
					break;
				case 1:
					anyAreaAllowed=1;
					break;
				case 2:
					sameAreaAllowed=2;
					break;
				case 3:
					otherAreaAllowed=3;
					break;
			}
		}
	}
	/**
	 * <b>Transformer(Mutative)</b>: Ends the current turn, setting the EndTurn button to disabled and the DrawButton to enabled, clearing all variables related to tiles taken of previous player, and using the nextPlayer function.<br />
	 * <b>Preconditions</b>: Player has clicked the end turn button <br />
	 * <b>Postconditions</b>: Ends the current turn, setting the EndTurn button to disabled and the DrawButton to enabled, clearing all variables related to tiles taken of previous player, and using the nextPlayer function. <br />
	 * <b>Invariants</b>: -
	 */
	public void endTurn() {
		GraphicUI.getEndTurnButton().setEnabled(false);
		GraphicUI.getDrawButton().setEnabled(true);
		tileTakenFromArea=0;
		tileTakenAmount=0;
		outOfAreaAllowed=0;
		anyAreaAllowed=0;
		sameAreaAllowed=0;
		otherAreaAllowed=0;
		charHasBeenUsed=0;
		otherArea1=-1;
		otherArea2=-1;
		nextPlayer();
	}
	/**
	 * <b>Transformer(Mutative)</b>: Does the necessary actions upon game ending (calculates points, disposes GUI and initiates the score dialog<br />
	 * <b>Preconditions</b>: 16 Landslide tiles have filled the entry area <br />
	 * <b>Postconditions</b>:  Does the necessary actions upon game ending (calculates points, disposes GUI and initiates the score dialog <br />
	 * <b>Invariants</b>: -
	 */
	public void gameOver() {
		totalPoints = calcTotalPoints();
		GraphicUI.dispose();
		EndDialog lastWindow = new EndDialog();
		lastWindow.getScore1().setText(playerArray[0].getName() + "'s score is: " + totalPoints[0]);
		lastWindow.getScore2().setText(playerArray[1].getName() + "'s score is: " + totalPoints[1]);
		lastWindow.getScore3().setText(playerArray[2].getName() + "'s score is: " + totalPoints[2]);
		lastWindow.getScore4().setText(playerArray[3].getName() + "'s score is: " + totalPoints[3]);
	}
}
