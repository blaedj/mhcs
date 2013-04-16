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
}
