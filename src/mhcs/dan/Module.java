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

<<<<<<< HEAD
        /**
         * constructor for the type.
         * @param str string representation of the type.
         */
        ModuleType(final String astr) {this.str = astr;}
=======
        ModuleType(String str) {this.str = str;}
>>>>>>> a482befedbd41c5e6958bfc92e886edfef8c0cca

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
<<<<<<< HEAD
    public final boolean equals(final Object aThat) {
        // if same object
        if (this == aThat) { return true; }
        // if not a module object
        if (!(aThat instanceof Module)) { return false; }
        Module that = (Module) aThat;
        // checks for equality in every variable
        return  this.getCode() == that.getCode() &&
                this.getDamage() == that.getDamage() &&
                this.getXCoor() == that.getXCoor() &&
                this.getYCoor() == that.getYCoor() &&
                this.getTurns() == that.getTurns();
    }
    
    /**
     * because checkstyle told me to.
     */
    public int hashCode() {
        return code.hashCode();
=======

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
>>>>>>> a482befedbd41c5e6958bfc92e886edfef8c0cca
    }

    /**
     *
     * @param code the code of the module
     * @param damage the amount of damage sustained by the module
     * @param xcoor the x coordinate of where the module is located
     * @param ycoor the y coordinate of where the module is located
     * @param turns the numbers of turns to orient the module upright
     */
<<<<<<< HEAD
    public Module(final String acode, final String adamage, final String axcoor,
            final String aycoor, final String aturns) {
        this.code = acode;
        this.damage = adamage;
        this.xcoor = axcoor;
        this.ycoor = aycoor;
        this.turns = aturns;
=======
    public Module(final String code, final String damage, final String xcoor,
		  final String ycoor, final String turns) {
        this.code = code;
        this.damage = damage;
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.turns = turns;
>>>>>>> a482befedbd41c5e6958bfc92e886edfef8c0cca
        int codeInt = Integer.parseInt(code);
        // assign module type based on code
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
     * Returns the type of the module.
     * @return type the type of module
     */
    public final ModuleType getType() {
        return type;
    }

    /**
     * Returns a string representation of the module.
     * @return a string representation of the module
     */
    public final String toString() {
        return code + " " + damage + " " + xcoor + " " + ycoor + " " + turns;
    }
}
