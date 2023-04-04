package ro.mihaaiiii.gamesurvival.Game;

import lombok.Data;
import org.bukkit.inventory.ItemStack;
import ro.mihaaiiii.gamesurvival.GameSurvival;

import java.util.ArrayList;
import java.util.List;

@Data
public  class LootChest {
    private GameSurvival plugin;
    private List<ItemStack> items;

    public LootChest(GameSurvival plugin) {
        items = new ArrayList<>();
    }

}
