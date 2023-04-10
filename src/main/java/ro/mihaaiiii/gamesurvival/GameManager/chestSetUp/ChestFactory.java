package ro.mihaaiiii.gamesurvival.GameManager.chestSetUp;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChestFactory {
    private GameSurvival plugin;
    private ChestItmes chest;

    public ChestFactory(GameSurvival plugin) {
        this.plugin = plugin;
        this.chest = chest;
//        setTipeChest(chestType);


    }

//    public void setTipeChest(String chestType) {
//        switch (chestType) {
//            case "epic" -> {
//                chest = new RareChest(plugin);
//                chest.addItems("epic");
//
//            }
//            case "rare" -> {
//                chest = new RareChest(plugin);
//                chest.addItems("rare");
//            }
//            case "legendary" -> {
//                chest = new RareChest(plugin);
//                chest.addItems("legendary");
//            }
//            default -> {
//                chest = new RareChest(plugin);
//                chest.addItems("common");
//            }
//
//        }
//    }


    public void addItemsInChest(String tipe) {

        DefaultConfig.getChestLocation(tipe).forEach(location -> {
            if (location.getBlock().getState() instanceof Chest) {
                Chest chest = (Chest) location.getBlock().getState();
                List<ItemStack> itemStacks = new ArrayList<>();
                Random r = new Random();
                DefaultConfig.getConfig().getConfigurationSection(tipe + "_chest").getKeys(false).forEach(key -> {
                    ItemStack[] its = new ItemStack[DefaultConfig.getConfig().getStringList(tipe + "_chest" + key).size()];
                    ItemStack itemStack = null;
                    for (String i : DefaultConfig.getConfig().getStringList(tipe + "_chest." + key)) {
                        try {
                            itemStack = new ItemStack(Material.matchMaterial(i), r.nextInt(DefaultConfig.getConfig().getInt("random_amount")));
                            itemStacks.add(isArmour(itemStack, i));
                        } catch (Exception e) {
                            itemStack = new ItemStack(Material.STONE);
                        }

                    }
                    // chest.getInventory().setContents(itemStacks.toArray(new ItemStack[0]));
                    for (ItemStack item : itemStacks) {
                        if (chest.getInventory().contains(item)) {
                            continue;
                        }

                        chest.getInventory().setItem(r.nextInt(0, chest.getInventory().getSize()), item);

                    }
                });
            } else {
                location.getBlock().setType(Material.CHEST);
                addItemsInChest(tipe);
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
