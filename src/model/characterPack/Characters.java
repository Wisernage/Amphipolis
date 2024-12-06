package model.characterPack;

public abstract class Characters {
	private boolean hasBeenUsed;
	private charColor color;
	public enum charColor{
		Green,
		Red,
		Blue,
		Black
	}
	public enum chars{
		Archaeologist,
		Assistant,
		Digger,
		Professor
	}
	/**
	 * <b>Accessor</b>: Returns if character has been used or not (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns if character has been used or not (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public boolean gethasBeenUsed() {
		return hasBeenUsed;
	}
	/**
	 * <b>Accessor</b>: Returns character owner (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns character owner (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public charColor getColor() {
		return color;
	}
	/**
	 * <b>Transformer(Mutative)</b>: Sets color of character (part of set-get methodology)<br />
	 * <b>Preconditions</b>: character is assigned to a player of same color <br />
	 * <b>Postconditions</b>: Sets color of character (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public void setColor(charColor color) {
		this.color = color;
	}
	/**
	 * <b>Transformer(Mutative)</b>: Sets hasBeenUsed to true (part of set-get methodology) <br />
	 * <b>Preconditions</b>: character is not used (part of set-get methodology) <br />
	 * <b>Postconditions</b>: Sets hasBeenUsed to true (part of set-get methodology) <br />
	 * <b>Invariants</b>: - <br />
	 * I am not planning to override use inside each child class. Instead, the indirect implementation will happen inside the Controller package as it is more convenient.
	 */
	
	public void use() {
		hasBeenUsed=true;
	}
}
