package model.boardPack;
import java.util.ArrayList;

import model.characterPack.Archaeologist;
import model.characterPack.Assistant;
import model.characterPack.Digger;
import model.characterPack.Professor;
import model.characterPack.Characters.charColor;
import model.playerPack.*;
import model.tilePack.*;

public class Board {
	public ArrayList<LandslideTile> entryArea = new ArrayList<LandslideTile>(16);
	public ArrayList<StatueTile> statueArea = new ArrayList<StatueTile>(24);
	public ArrayList<MosaicTile> mosaicArea = new ArrayList<MosaicTile>(27);
	public ArrayList<SkeletonTile> skeletonArea = new ArrayList<SkeletonTile>(30);
	public ArrayList<AmphoraTile> amphoraArea = new ArrayList<AmphoraTile>(30);
	
	/**
	 * <b>Accessor</b>: Returns game status (true if over, false if not over)<br />
	 * <b>Preconditions</b>: -<br />
	 * <b>Postconditions</b>: Returns game status<br />
	 * <b>Invariants</b>: -
	 */
	
	public boolean checkGameOver() {
		if (entryArea.size() >= 16)
			return true;
		return false;
	}
	/**
	 * <b>Transformer(Mutative)</b>: Draws a tile from the bag<br />
	 * <b>Preconditions</b>: Every turn<br />
	 * <b>Postconditions</b>: Draws a tile from the bag<br />
	 * <b>Invariants</b>: -
	 */
	public Tile drawTile(Bag bag) {
		Tile tile = bag.takeFromBag();
		if (tile instanceof MosaicTile) {
			mosaicArea.add((MosaicTile)tile);
		}
		else if (tile instanceof CaryatidTile) {
			statueArea.add((CaryatidTile)tile);
		}
		else if (tile instanceof SphinxTile) {
			statueArea.add((SphinxTile)tile);
		}
		else if (tile instanceof AmphoraTile) {
			amphoraArea.add((AmphoraTile)tile);
		}
		else if (tile instanceof SkeletonTile) {
			skeletonArea.add((SkeletonTile) tile);
		}
		else if (tile instanceof LandslideTile) {
			entryArea.add((LandslideTile) tile);
		}
		return tile;
	}
	/**
	 * <b>Transformer(Mutative)</b>: Sets the first player based on last museum visit<br />
	 * <b>Preconditions</b>: beginning of game after players are created<br />
	 * <b>Postconditions</b>: Sets the first player based on last museum visit<br />
	 * <b>Invariants</b>: -
	 */
	public int setFirst(Player[] players) {
		int fewestDays = Integer.MAX_VALUE;
		int firstPlayer=0;
		for (int i=0; i<4; i++) {
			if (players[i].getlastMuseumVisit() < fewestDays) {
				firstPlayer = i;
				fewestDays = players[i].getlastMuseumVisit();
			}
		}
		return firstPlayer;
	}
	/**
	 * <b>Transformer(Mutative)</b>: Initializes players and characters <br />
	 * <b>Preconditions</b>: beginning of game <br />
	 * <b>Postconditions</b>: Initializes necessary components <br />
	 * <b>Invariants</b>: -
	 */
	public Player[] makePlayersAndCharacters() {
		Player player1 = new Player();
		player1.setColor(charColor.Green);
		Player player2 = new Player();
		player2.setColor(charColor.Red);
		Player player3 = new Player();
		player3.setColor(charColor.Blue);
		Player player4 = new Player();
		player4.setColor(charColor.Black);
		Archaeologist archaeologist = new Archaeologist();
		Archaeologist archaeologist2 = new Archaeologist();
		Archaeologist archaeologist3 = new Archaeologist();
		Archaeologist archaeologist4 = new Archaeologist();
		archaeologist.setColor(charColor.Green);
		player1.addPlayerCharacter(archaeologist);
		archaeologist2.setColor(charColor.Red);
		player2.addPlayerCharacter(archaeologist2);
		archaeologist3.setColor(charColor.Blue);
		player3.addPlayerCharacter(archaeologist3);
		archaeologist4.setColor(charColor.Black);
		player4.addPlayerCharacter(archaeologist4);
		Assistant assistant = new Assistant();
		Assistant assistant2 = new Assistant();
		Assistant assistant3 = new Assistant();
		Assistant assistant4 = new Assistant();
		assistant.setColor(charColor.Green);
		player1.addPlayerCharacter(assistant);
		assistant2.setColor(charColor.Red);
		player2.addPlayerCharacter(assistant2);
		assistant3.setColor(charColor.Blue);
		player3.addPlayerCharacter(assistant3);
		assistant4.setColor(charColor.Black);
		player4.addPlayerCharacter(assistant4);
		Digger digger = new Digger();
		Digger digger2 = new Digger();
		Digger digger3 = new Digger();
		Digger digger4 = new Digger();
		digger.setColor(charColor.Green);
		player1.addPlayerCharacter(digger);
		digger2.setColor(charColor.Red);
		player2.addPlayerCharacter(digger2);
		digger3.setColor(charColor.Blue);
		player3.addPlayerCharacter(digger3);
		digger4.setColor(charColor.Black);
		player4.addPlayerCharacter(digger4);
		Professor professor = new Professor();
		Professor professor2 = new Professor();
		Professor professor3 = new Professor();
		Professor professor4 = new Professor();
		professor.setColor(charColor.Green);
		player1.addPlayerCharacter(professor);
		professor2.setColor(charColor.Red);
		player2.addPlayerCharacter(professor2);
		professor3.setColor(charColor.Blue);
		player3.addPlayerCharacter(professor3);
		professor4.setColor(charColor.Black);
		player4.addPlayerCharacter(professor4);
		Player[] playerArray = new Player[4];
		playerArray[0]=player1;
		playerArray[1]=player2;
		playerArray[2]=player3;
		playerArray[3]=player4;
		return playerArray;
	}	
}
