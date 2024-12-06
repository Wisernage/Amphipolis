package model.playerPack;
import java.util.ArrayList;

import model.characterPack.Characters;
import model.characterPack.Characters.*;
import model.tilePack.*;
import model.tilePack.AmphoraTile.amphoraColor;
import model.tilePack.MosaicTile.mosaicColor;

public class Player {
	private String name;
	private int lastMuseumVisit;
	private charColor color;
	private ArrayList<FindingTile> playerTiles = new ArrayList<FindingTile>();
	private ArrayList<Characters> playerCharacters = new ArrayList<Characters>(4);
	/**
	 * <b>Accessor</b>: Returns name of player (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns name of player(part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public String getName() {
		return name;
	}
	/**
	 * <b>Transformer(Mutative)</b>: Sets name of player (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Sets name of player (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public void setName(String name) {
		this.name=name;
	}
	/**
	 * <b>Accessor</b>: Returns color of player (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns color of player(part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public charColor getColor() {
		return color;
	}
	/**
	 * <b>Transformer(Mutative)</b>: Sets color of player (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Sets color of player (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public void setColor(charColor color) {
		this.color=color;
	}
	/**
	 * <b>Accessor</b>: Returns last museum visit of player (used to determine first move) (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns last museum visit of player (used to determine first move)(part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public int getlastMuseumVisit() {
		return lastMuseumVisit;
	}
	/**
	 * <b>Transformer(Mutative)</b>: Sets last museum visit of player (in terms of days ago) (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Sets last museum visit of player (in terms of days ago) (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public void setlastMuseumVisit(int lastMuseumVisit) {
		this.lastMuseumVisit=lastMuseumVisit;
	}
	/**
	 * <b>Accessor</b>: Returns tiles that player owns (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns tiles that player owns (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public ArrayList<FindingTile> getPlayerTiles(){
		return playerTiles;
	}
	/**
	 * <b>Transformer(Applicative)</b>: Adds a tile to the tiles that the player owns<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Adds a tile to the tiles that the player owns<br />
	 * <b>Invariants</b>: -
	 */
	public void addPlayerTiles(FindingTile playerTile){
		playerTiles.add(playerTile);
	}
	/**
	 * <b>Accessor</b>: Returns player's characters (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns player's characters (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public ArrayList<Characters> getPlayerCharacters(){
		return playerCharacters;
	}
	/**
	 * <b>Transformer(Applicative)</b>: Adds a character to the characters that the player owns<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Adds a character to the characters that the player owns<br />
	 * <b>Invariants</b>: -
	 */
	public void addPlayerCharacter(Characters character){
		playerCharacters.add(character);
	}
	/**
	 * <b>Accessor</b>: Calculates and returns player's total points<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Calculates and returns player's total points<br />
	 * <b>Invariants</b>: -
	 */
	public int calcPoints() {
		int points=0, greenMosaic=0, redMosaic=0, yellowMosaic=0, mosaic=0, blueAmphora=0,brownAmphora=0,greenAmphora=0,purpleAmphora=0,redAmphora=0,yellowAmphora=0, differentAmphoras=0,
		skeletonsmallbottom=0,skeletonsmallupper=0,skeletonbigbottom=0,skeletonbigupper=0,skeletonsmall=0,skeletonbig=0;
		
		FindingTile tile;
		for (int i = 0; i < playerTiles.size(); i++) { 	
			tile = playerTiles.get(i);
	        if (tile instanceof MosaicTile) {
	        	if (((MosaicTile)tile).getMosaicColor() == mosaicColor.Green)
	        		greenMosaic++;
	        	else if (((MosaicTile)tile).getMosaicColor() == mosaicColor.Red)
	        		redMosaic++;
	        	else if (((MosaicTile)tile).getMosaicColor() == mosaicColor.Yellow)
	        		yellowMosaic++;
	        }
	        if (tile instanceof AmphoraTile) {
	        	if (((AmphoraTile)tile).getAmphoraColor() == amphoraColor.Blue){
	        		if (blueAmphora == 0) {
	        			differentAmphoras++;
	        		}
	        		blueAmphora++;
	        	}
	        	else if (((AmphoraTile)tile).getAmphoraColor() == amphoraColor.Brown) {
	        		if (brownAmphora == 0) {
	        			differentAmphoras++;
	        		}
	        		brownAmphora++;
	        	}
	        	else if (((AmphoraTile)tile).getAmphoraColor() == amphoraColor.Green) {
	        		if (greenAmphora == 0) {
	        			differentAmphoras++;
	        		}
	        		greenAmphora++;
	        	}
	        	else if (((AmphoraTile)tile).getAmphoraColor() == amphoraColor.Purple) {
	        		if (purpleAmphora == 0) {
	        			differentAmphoras++;
	        		}
	        		purpleAmphora++;
	        	}
	        	else if (((AmphoraTile)tile).getAmphoraColor() == amphoraColor.Red) {
	        		if (redAmphora == 0) {
	        			differentAmphoras++;
	        		}
	        		redAmphora++;
	        	}
	        	else if (((AmphoraTile)tile).getAmphoraColor() == amphoraColor.Yellow) {
	        		if (yellowAmphora == 0) {
	        			differentAmphoras++;
	        		}
	        		yellowAmphora++;
	        	}
	        }
	        if (tile instanceof SkeletonTile) {
	        	if (((SkeletonTile)tile).getSkeletonSize() == SkeletonTile.skeletonSize.Small) {
	        		if (((SkeletonTile)tile).getSkeletonPart() == SkeletonTile.skeletonPart.Bottom) {
		        		skeletonsmallbottom++;
		        	}
	        		else if (((SkeletonTile)tile).getSkeletonPart() == SkeletonTile.skeletonPart.Upper) {
	        			skeletonsmallupper++;
	        		}
	        	}
	        	else if (((SkeletonTile)tile).getSkeletonSize() == SkeletonTile.skeletonSize.Big) {
	        		if (((SkeletonTile)tile).getSkeletonPart() == SkeletonTile.skeletonPart.Bottom) {
		        		skeletonbigbottom++;
		        	}
	        		else if (((SkeletonTile)tile).getSkeletonPart() == SkeletonTile.skeletonPart.Upper) {
	        			skeletonbigupper++;
	        		}
	        	}
	        }
	    }
		while (greenMosaic>=4) {
			points += 6;
			greenMosaic-=4;
		}
		while (redMosaic>=4) {
			points += 6;
			redMosaic-=4;
		}
		while (yellowMosaic>=4) {
			points += 6;
			yellowMosaic-=4;
		}
		mosaic = greenMosaic + redMosaic + yellowMosaic;
		while (mosaic>=4) {
			points += 2;
			mosaic-=4;
		}
		while(differentAmphoras==6) {
			points += 6;
			blueAmphora--;
			if (blueAmphora == 0) {
    			differentAmphoras--;
    		}
			brownAmphora--;
			if (brownAmphora == 0) {
    			differentAmphoras--;
    		}
			greenAmphora--;
			if (greenAmphora == 0) {
    			differentAmphoras--;
    		}
			purpleAmphora--;
			if (purpleAmphora == 0) {
    			differentAmphoras--;
    		}
			redAmphora--;
			if (redAmphora == 0) {
    			differentAmphoras--;
    		}
			yellowAmphora--;
			if (yellowAmphora == 0) {
    			differentAmphoras--;
    		}
		}
		while(differentAmphoras==5) {
			points += 4;
			blueAmphora--;
			if (blueAmphora == 0) {
    			differentAmphoras--;
    		}
			brownAmphora--;
			if (brownAmphora == 0) {
    			differentAmphoras--;
    		}
			greenAmphora--;
			if (greenAmphora == 0) {
    			differentAmphoras--;
    		}
			purpleAmphora--;
			if (purpleAmphora == 0) {
    			differentAmphoras--;
    		}
			redAmphora--;
			if (redAmphora == 0) {
    			differentAmphoras--;
    		}
			yellowAmphora--;
			if (yellowAmphora == 0) {
    			differentAmphoras--;
    		}
		}
		while(differentAmphoras==4) {
			points += 2;
			blueAmphora--;
			if (blueAmphora == 0) {
    			differentAmphoras--;
    		}
			brownAmphora--;
			if (brownAmphora == 0) {
    			differentAmphoras--;
    		}
			greenAmphora--;
			if (greenAmphora == 0) {
    			differentAmphoras--;
    		}
			purpleAmphora--;
			if (purpleAmphora == 0) {
    			differentAmphoras--;
    		}
			redAmphora--;
			if (redAmphora == 0) {
    			differentAmphoras--;
    		}
			yellowAmphora--;
			if (yellowAmphora == 0) {
    			differentAmphoras--;
    		}
		}
		while(differentAmphoras==3) {
			points += 1;
			blueAmphora--;
			if (blueAmphora == 0) {
    			differentAmphoras--;
    		}
			brownAmphora--;
			if (brownAmphora == 0) {
    			differentAmphoras--;
    		}
			greenAmphora--;
			if (greenAmphora == 0) {
    			differentAmphoras--;
    		}
			purpleAmphora--;
			if (purpleAmphora == 0) {
    			differentAmphoras--;
    		}
			redAmphora--;
			if (redAmphora == 0) {
    			differentAmphoras--;
    		}
			yellowAmphora--;
			if (yellowAmphora == 0) {
    			differentAmphoras--;
    		}
		}
		while(skeletonsmallbottom>0 && skeletonsmallupper>0) {
			skeletonsmallbottom--;
			skeletonsmallupper--;
			skeletonsmall++;
		}
		while(skeletonbigbottom>0 && skeletonbigupper>0) {
			skeletonbigbottom--;
			skeletonbigupper--;
			skeletonbig++;
		}
		while(skeletonbig>=2 && skeletonsmall >=1) {
			points+=6;
			skeletonbig-=2;
			skeletonsmall--;
		}
		while(skeletonbig>0 || skeletonsmall >0) {
			points+=1;
			skeletonbig--;
			skeletonsmall--;
		}
		return points;
	}
	/**
	 * <b>Accessor</b>: Returns player's Caryatid tile amount(part of set-get methodology)<br />
	 * <b>Preconditions</b>: When points are to be calculated <br />
	 * <b>Postconditions</b>: Returns player's Caryatid tile amount(part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public int getCaryatids() {
		FindingTile tile;
		int caryatids=0;
		for (int i = 0; i <  playerTiles.size(); i++) { 
			tile = playerTiles.get(i);
			if (tile instanceof CaryatidTile) {
				caryatids++;
			}
		}
		return caryatids;
	}
	/**
	 * <b>Accessor</b>: Returns player's Sphinx tile amount(part of set-get methodology)<br />
	 * <b>Preconditions</b>: When points are to be calculated <br />
	 * <b>Postconditions</b>: Returns player's Sphinx tile amount(part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public int getSphinxes() {
		FindingTile tile;
		int sphinxes=0;
		for (int i = 0; i <  playerTiles.size(); i++) { 
			tile = playerTiles.get(i);
			if (tile instanceof SphinxTile) {
				sphinxes++;
			}
		}
		return sphinxes;
	}
	
	/**
	 * <b>Transformer(Mutative)</b>: Uses a character that the player owns and is not used already<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Uses a character that the player owns and is not used already<br />
	 * <b>Invariants</b>: -
	 */
	public void useCharacter(int character) {
		playerCharacters.get(character).use();
	}
}
