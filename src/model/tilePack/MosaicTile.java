package model.tilePack;

public class MosaicTile extends FindingTile{
	private mosaicColor color;
	public enum mosaicColor{
		Green,
		Red,
		Yellow
	}
	/**
	 * <b>Transformer(Mutative)</b>: Sets mosaic's color (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Sets mosaic's color (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public void setMosaicColor(mosaicColor color) {
		this.color = color;
	}
	/**
	 * <b>Accessor</b>: Returns mosaic's color (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns mosaic's color (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public mosaicColor getMosaicColor() {
		return color;
	}
}
