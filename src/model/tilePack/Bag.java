package model.tilePack;
import java.util.ArrayList;
import java.util.Collections;

import model.tilePack.AmphoraTile.amphoraColor;
import model.tilePack.MosaicTile.mosaicColor;
import model.tilePack.SkeletonTile.skeletonPart;
import model.tilePack.SkeletonTile.skeletonSize;
public class Bag {
	private ArrayList<Tile> bagTiles = new ArrayList<Tile>(135);
	/**
	 * <b>Transformer(Applicative)</b>: Fills the bag with tiles<br />
	 * <b>Preconditions</b>:  Game began <br />
	 * <b>Postconditions</b>: Fills the bag with tiles<br />
	 * <b>Invariants</b>: -
	 */
	public void fillBag() {
		for (int i=0; i<4; i++) {
			AmphoraTile newAmphoraTile = new AmphoraTile();
			newAmphoraTile.setamphoraColor(amphoraColor.Blue);
			bagTiles.add(newAmphoraTile);
			SkeletonTile newSkeletonTile = new SkeletonTile();
			newSkeletonTile.setSkeletonPart(skeletonPart.Upper);
			newSkeletonTile.setSkeletonSize(skeletonSize.Big);
			bagTiles.add(newSkeletonTile);
		}
		for (int i=0; i<5; i++) {
			AmphoraTile newAmphoraTile = new AmphoraTile();
			newAmphoraTile.setamphoraColor(amphoraColor.Brown);
			bagTiles.add(newAmphoraTile);
			SkeletonTile newSkeletonTile = new SkeletonTile();
			newSkeletonTile.setSkeletonPart(skeletonPart.Upper);
			newSkeletonTile.setSkeletonSize(skeletonSize.Small);
			bagTiles.add(newSkeletonTile);
		}
		for (int i=0; i<5; i++) {
			AmphoraTile newAmphoraTile = new AmphoraTile();
			newAmphoraTile.setamphoraColor(amphoraColor.Green);
			bagTiles.add(newAmphoraTile);
			SkeletonTile newSkeletonTile = new SkeletonTile();
			newSkeletonTile.setSkeletonPart(skeletonPart.Bottom);
			newSkeletonTile.setSkeletonSize(skeletonSize.Big);
			bagTiles.add(newSkeletonTile);
		}
		for (int i=0; i<5; i++) {
			AmphoraTile newAmphoraTile = new AmphoraTile();
			newAmphoraTile.setamphoraColor(amphoraColor.Purple);
			bagTiles.add(newAmphoraTile);
			SkeletonTile newSkeletonTile = new SkeletonTile();
			newSkeletonTile.setSkeletonPart(skeletonPart.Bottom);
			newSkeletonTile.setSkeletonSize(skeletonSize.Big);
			bagTiles.add(newSkeletonTile);
		}
		for (int i=0; i<5; i++) {
			AmphoraTile newAmphoraTile = new AmphoraTile();
			newAmphoraTile.setamphoraColor(amphoraColor.Red);
			bagTiles.add(newAmphoraTile);
			SkeletonTile newSkeletonTile = new SkeletonTile();
			newSkeletonTile.setSkeletonPart(skeletonPart.Bottom);
			newSkeletonTile.setSkeletonSize(skeletonSize.Small);
			bagTiles.add(newSkeletonTile);
		}
		for (int i=0; i<5; i++) {
			AmphoraTile newAmphoraTile = new AmphoraTile();
			newAmphoraTile.setamphoraColor(amphoraColor.Yellow);
			bagTiles.add(newAmphoraTile);
			SkeletonTile newSkeletonTile = new SkeletonTile();
			newSkeletonTile.setSkeletonPart(skeletonPart.Upper);
			newSkeletonTile.setSkeletonSize(skeletonSize.Big);
			bagTiles.add(newSkeletonTile);
		}
		for (int i=0; i<24; i++) {
			LandslideTile newLandslideTile = new LandslideTile();
			bagTiles.add(newLandslideTile);
		}
		for (int i=0; i<8; i++) {
			MosaicTile newMosaicTile = new MosaicTile();
			newMosaicTile.setMosaicColor(mosaicColor.Green);
			bagTiles.add(newMosaicTile);
		}
		for (int i=0; i<9; i++) {
			MosaicTile newMosaicTile = new MosaicTile();
			newMosaicTile.setMosaicColor(mosaicColor.Red);
			bagTiles.add(newMosaicTile);
		}
		for (int i=0; i<9; i++) {
			MosaicTile newMosaicTile = new MosaicTile();
			newMosaicTile.setMosaicColor(mosaicColor.Yellow);
			bagTiles.add(newMosaicTile);
		}
		for (int i=0; i<11; i++) {
			SphinxTile newSphinxTile = new SphinxTile();
			bagTiles.add(newSphinxTile);
		}
		for (int i=0; i<11; i++) {
			CaryatidTile newCaryatidTile = new CaryatidTile();
			bagTiles.add(newCaryatidTile);
		}
		Collections.shuffle(bagTiles);
	}
	/**
	 * <b>Transformer(Mutative)</b>: Removes a tile from the bag and returns it to caller<br />
	 * <b>Preconditions</b>:  Tiles are drawn <br />
	 * <b>Postconditions</b>: Removes a tile from the bag and returns it to caller<br />
	 * <b>Invariants</b>: -
	 */
	public Tile takeFromBag() {
		return bagTiles.remove(0);
	}
	/**
	 * <b>Accessor</b>: Returns all of the bag's tiles (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns all of the bag's tiles (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public ArrayList<Tile> getbagTiles(){
		return bagTiles;
	}
}
