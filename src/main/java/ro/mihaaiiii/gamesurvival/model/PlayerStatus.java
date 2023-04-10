package ro.mihaaiiii.gamesurvival.model;

public class PlayerStatus {
    private String owner;
    private int kills;
    private int deaths;
    private int loss;
    private int wins;

    public PlayerStatus(String owner, int kills, int deaths, int loss, int wins) {
        this.owner = owner;
        this.wins = wins;
        this.loss = loss;
        this.kills = kills;
        this.deaths = deaths;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

}

