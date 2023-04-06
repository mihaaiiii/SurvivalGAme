package ro.mihaaiiii.gamesurvival.GameManager.chestSetUp;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import ro.mihaaiiii.gamesurvival.model.ChestType;
import ro.mihaaiiii.gamesurvival.model.LootChest;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChestFactory {
    private GameSurvival plugin;

    public ChestFactory(ChestType chestType, GameSurvival plugin) {
        this.plugin = plugin;
        switch (chestType) {
            case COMMON -> {
                LootChest chest = new LootChest(plugin);
            }
            case EPIC -> {
                LootChest chest = new LootChest(plugin);
                chest.getItems().add(new ItemStack(Material.ACACIA_BUTTON));
            }
            case RARE -> {
                LootChest chest = new LootChest(plugin);
                chest.getItems().add(new ItemStack(Material.ACACIA_LEAVES));
            }
            case LEGENDARY -> {
                LootChest chest = new LootChest(plugin);
                chest.getItems().add(new ItemStack(Material.DIAMOND_ORE));
            }

        }


    }

    public void addItemsInChest() {
        DefaultConfig.getChestLocation().forEach(location -> {
            if (location.getBlock().getState() instanceof Chest) {
                Chest chest = (Chest) location.getBlock().getState();
                List<ItemStack> itemStacks = new ArrayList<>();
                Random r = new Random();
                plugin.getConfig().getConfigurationSection("common_chest").getKeys(false).forEach(key -> {
                    ItemStack[] its = new ItemStack[plugin.getConfig().getStringList("common_chest" + key).size()];
                    ItemStack itemStack = null;
                    for (String i : plugin.getConfig().getStringList("common_chest." + key)) {
                        try {
                            itemStack = new ItemStack(Material.matchMaterial(i), r.nextInt(plugin.getConfig().getInt("random_amount")));
                            itemStacks.add(isArmour(itemStack, i));
                        } catch (Exception e) {
                            itemStack = new ItemStack(Material.STONE);
                        }

                    }
                    chest.getInventory().setContents(itemStacks.toArray(new ItemStack[0]));
                });
            } else {
                location.getBlock().setType(Material.CHEST);
                addItemsInChest();
            }
        });
    }


    private ItemStack isArmour(ItemStack item, String name) {
        List<String> names = List.of("sword");
        System.out.println(item.getType().name().toUpperCase());
        for (String nam : names) {
            if (item.getType().name().contains(nam.toUpperCase())) {
                item = new ItemStack(Material.matchMaterial(name), 1);
            }
        }
        return item;
    }

}
