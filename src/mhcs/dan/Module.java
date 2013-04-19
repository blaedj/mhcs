package mhcs.dan;

public class Module {
    
    private String code;
    private String damage;
    private String xcoor;
    private String ycoor;
    private String turns;
    
    
    
    public Module(String code, String damage, String xcoor, String ycoor, String turns) {
        this.code = code;
        this.damage = damage;
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.turns = turns;
    }
    
    public String toString() {
        return code + " " + damage + " " + xcoor + " " + ycoor + " " + turns;
    }
    /**
     * getCode returns a string of the module type
     * @return code 
     */
    public String getCode(){
    	return code;
    	/**
    	 * Code strings are as follows:
    	 * "storage"
    	 * "med"
    	 * "air"
    	 * "control"
    	 * "gym"
    	 * "canteen"
    	 * "power"
    	 * "sanitation"
    	 * "dorm"
    	 * "plain"
    	 */
    }
    public String getDamage(){
    	return damage;
    }
    public String getXCoor(){
    	return xcoor;
    }
    public String getYCoor(){
    	return ycoor;
    }
    public String getTurns(){
    	return turns;
    }
}
