
/**
 * @author 
 * @version 
 */
public enum Schwierigkeitsstufe
{
    LEICHT(5,5,5);

    private final int reihen;
    private final int spalten;
    private final int bomben;

    Schwierigkeitsstufe(int reihen, int spalten, int bomben){
        this.reihen = reihen;
        this.spalten = spalten;
        this.bomben = bomben;
    }

    public int getReihen(){
        return reihen;
    }

    public int getSpalten(){
        return spalten;
    }

    public int getBomben(){
        return bomben;
    }

}
