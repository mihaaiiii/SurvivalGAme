package ro.mihaaiiii.gamesurvival.model;

public class PlayerStatusBuilder {
    private String owner;
    private int kills;
    private int deaths;
    private int loss;
    private int wins;

    public PlayerStatusBuilder getOwner(String owner) {
        this.owner = owner;
        return this;

    }

    public PlayerStatusBuilder getKills(int kills) {
        this.kills = kills;
        return this;

    }

    public PlayerStatusBuilder getdDeaths(int deaths) {
        this.deaths = deaths;
        return this;

    }

    public PlayerStatusBuilder getLoss(int loss) {
        this.loss = loss;
        return this;

    }

    public PlayerStatusBuilder getWins(int wins) {
        this.wins = wins;
        return this;

    }

    public PlayerStatus build() {
        return new PlayerStatus(owner, kills, deaths, loss, wins);
    }


}
