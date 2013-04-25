package mhcs.blaed;

/**
 * @deprecated, use mhcs.dan.Module instead
 */
public class HabitatModule {

	private final int xCoordinate;
	private final int yCoordinate;
	private final int idCode;
	//private final String moduleType;

	public HabitatModule(int x, int y, int code){
		xCoordinate = x;
		yCoordinate = y;
		idCode = code;
	}

	public String getType() {
		return "Module Type Here";
	}

	public int getY() {
		return yCoordinate;
	}

	public int getX() {
		return xCoordinate;
	}

	public int getCode() {
		return idCode;
	}


}
