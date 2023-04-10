package ro.mihaaiiii.gamesurvival.GameManager.chestSetUp;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RareChest implements ChestItmes {
    private GameSurvival plugin;

    public RareChest(GameSurvival plugin) {
        this.plugin = plugin;

    }


    @Override
    public void addItems(String name) {

        DefaultConfig.getChestLocation(name).forEach(location -> {
            if (location.getBlock().getState() instanceof Chest) {
                Chest chest = (Chest) location.getBlock().getState();
                List<ItemStack> itemStacks = new ArrayList<>();
                Random r = new Random();
                plugin.getConfig().getConfigurationSection(name + "_chest").getKeys(false).forEach(key -> {
                    ItemStack[] its = new ItemStack[plugin.getConfig().getStringList(name + "_chest" + key).size()];
                    ItemStack itemStack = null;
                    for (String i : plugin.getConfig().getStringList(name + "_chest." + key)) {
                        try {
                            itemStack = new ItemStack(Material.matchMaterial(i), r.nextInt(DefaultConfig.getRandomAmount()));
                            itemStacks.add(isArmour(itemStack, i));
                        } catch (Exception e) {
                            itemStack = new ItemStack(Material.STONE);
                        }

                    }
                    chest.getInventory().setContents(itemStacks.toArray(new ItemStack[0]));
                });
            } else {
                location.getBlock().setType(Material.CHEST);
                addItems(name);
            }
        });

    }

    private ItemStack isArmour(ItemStack item, String name) {
        List<String> names = List.of("sword", "chestplate", "boots", "leggings");
        System.out.println(item.getType().name().toUpperCase());
        for (String nam : names) {
            if (item.getType().name().contains(nam.toUpperCase())) {
                item = new ItemStack(Material.matchMaterial(name), 1);
            }
        }
        return item;
    }


}
