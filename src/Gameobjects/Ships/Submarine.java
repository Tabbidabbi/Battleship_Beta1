package Gameobjects.Ships;

import java.io.Serializable;

public class Submarine extends Ship implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8570994728399446892L;
	String name = "U-Boot";
        boolean isPlaced;

  //U-Boote positionieren
    public Submarine(int number) {
        super("U", 2, false, number, false, 1, 0, 1, "U-Boot");
        this.isPlaced = false;
    }

    //// Getter und Setter Methoden
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
