package model.tilePack;

public class SkeletonTile extends FindingTile{
	private skeletonSize size;
	private skeletonPart part;
	public enum skeletonSize{
		Big,
		Small
	}
	public enum skeletonPart{
		Bottom,
		Upper
	}
	
	/**
	 * <b>Transformer(Mutative)</b>: Sets skeleton's size (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>:Sets skeleton's size (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public void setSkeletonSize(skeletonSize size) {
		this.size = size;
	};
	/**
	 * <b>Transformer(Mutative)</b>: Sets skeleton's part (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Sets skeleton's part (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public void setSkeletonPart(skeletonPart part) {
		this.part = part;
	};
	/**
	 * <b>Accessor</b>: Returns skeleton's size (part of set-get methodology)<br />
	 * <b>Preconditions</b>: - <br />
	 * <b>Postconditions</b>: Returns skeleton's size (part of set-get methodology)<br />
	 * <b>Invariants</b>: -
	 */
	public skeletonSize getSkeletonSize() {
		return size;
		/**
		 * <b>Accessor</b>: Returns skeleton's part (part of set-get methodology)<br />
		 * <b>Preconditions</b>: - <br />
		 * <b>Postconditions</b>: Returns skeleton's part (part of set-get methodology)<br />
		 * <b>Invariants</b>: -
		 */
	}
	public skeletonPart getSkeletonPart() {
		return part;
	}
}
