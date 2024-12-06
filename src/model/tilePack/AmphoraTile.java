package model.tilePack;

public class AmphoraTile extends FindingTile{	
	private amphoraColor color;
	public enum amphoraColor{
		Blue,
		Brown,
		Red,
		Green,
		Yellow,
		Purple
	}
	/**
	 * <b>Accessor</b>: Returns amphora's color (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns amphora's color (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public amphoraColor getAmphoraColor() {
		return color;
	}
	/**
	 * <b>Transformer(Mutative)</b>: Sets amphora's color (part of set-get methodology)<br />
	 * <b>Preconditions</b>:  <br />
	 * <b>Postconditions</b>: Sets amphora's color (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public void setamphoraColor(amphoraColor color) {
		this.color = color;
	}
}
