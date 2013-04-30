package mhcs.dan;

/**
 *
 * @author Daniel Hammond
 *
 */
public class Module {

    /**
     *
     * @author Daniel Hammond
     *
     */
    public enum ModuleType {
        /**
         * plain module.
         */
        PLAIN("plain"),
        /**
         * dormitory module.
         */
        DORMITORY("dormitory"),
        /**
         * sanitation module.
         */
        SANITATION("sanitation"),
        /**
         * food and water module.
         */
        FOOD_AND_WATER("food and water"),
        /**
         * gym and relaxation module.
         */
        GYM_AND_RELAXATION("gym and relaxation"),
        /**
         * canteen module.
         */
        CANTEEN("canteen"),
        /**
         * power module.
         */
        POWER("power"),
        /**
         * control module.
         */
        CONTROL("control"),
        /**
         * airlock module.
         */
        AIRLOCK("airlock"),
        /**
         * medical module.
         */
        MEDICAL("medical");

        private String str;

        ModuleType(String str) {this.str = str;}

        /**
         * @return str string representation of the type.
         */
        public String toString() {
            return str;
        }
    }

    /**
     * type of module.
     */
    private ModuleType type;
    /**
     * code of module.
     */
    private String code;
    /**
     * damage of module.
     */
    private String damage;
    /**
     * x coordinate of module.
     */
    private String xcoor;
    /**
     * y coordinate of module.
     */
    private String ycoor;
    /**
     * turns to correct orientation of module.
     */
    private String turns;

    @Override

    public boolean equals(Object aThat){
    	if (this == aThat){ return true; }
    	if(! (aThat instanceof Module)) { return false; }
    	Module that = (Module)aThat;
    	return (
		this.getCode().equals(that.getCode()) &&
		this.getDamage().equals(that.getDamage()) &&
		this.getXCoor().equals(that.getXCoor()) &&
		this.getYCoor().equals(that.getYCoor()) &&
		this.getTurns().equals(that.getTurns())
		);
    }

    /**
     *
     * @param code the code of the module
     * @param damage the amount of damage sustained by the module
     * @param xcoor the x coordinate of where the module is located
     * @param ycoor the y coordinate of where the module is located
     * @param turns the numbers of turns to orient the module upright
     */
    public Module(final String code, final String damage, final String xcoor,
		  final String ycoor, final String turns) {
        this.code = code;
        this.damage = damage;
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.turns = turns;
        int codeInt = Integer.parseInt(code);
        if (codeInt >= 1 && codeInt <= 40) {
            type = ModuleType.PLAIN;
        } else if (codeInt >= 61 && codeInt <= 80) {
            type = ModuleType.DORMITORY;
        } else if (codeInt >= 91 && codeInt <= 100) {
            type = ModuleType.SANITATION;
        } else if (codeInt >= 111 && codeInt <= 120) {
            type = ModuleType.FOOD_AND_WATER;
        } else if (codeInt >= 131 && codeInt <= 134) {
            type = ModuleType.GYM_AND_RELAXATION;
        } else if (codeInt >= 141 && codeInt <= 144) {
            type = ModuleType.CANTEEN;
        } else if (codeInt >= 151 && codeInt <= 154) {
            type = ModuleType.POWER;
        } else if (codeInt >= 161 && codeInt <= 164) {
            type = ModuleType.CONTROL;
        } else if (codeInt >= 171 && codeInt <= 174) {
            type = ModuleType.AIRLOCK;
        } else if (codeInt >= 181 && codeInt <= 184) {
            type = ModuleType.MEDICAL;
        }
    }

    public String getCode() {
        return code;
    }

    public String getXCoor() {
        return xcoor;
    }

    public String getYCoor() {
        return ycoor;
    }

    public String getDamage() {
        return damage;
    }

    public String getTurns() {
        return turns;
    }

    /**
     *
     * @return type the type of module
     */
    public final ModuleType getType() {
        return type;
    }

    /**
     *
     * @return a string representation of the module
     */
    public final String toString() {
        return code + " " + damage + " " + xcoor + " " + ycoor + " " + turns;
    }
}
