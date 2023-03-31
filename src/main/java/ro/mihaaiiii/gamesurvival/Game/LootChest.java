package ro.mihaaiiii.gamesurvival.Game;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Data
public class LootChest {


    private Long idLootChest;
    private List<ItemStack> items;
    private ChestType chestType;

    public LootChest() {
        items = new ArrayList<>();
        chestType = ChestType.COMMON;
    }
}
