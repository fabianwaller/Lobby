package de.rewex.lobby.manager.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    ItemStack itemStack = null;
    Material material = null;
    int amount = 1;
    int subid = 0;
    int durability = 0;
    String displayname = "";
    Enchantment enchantment = null;
    int enchantmentlevel = 1;
    boolean enchantmentBoolean = false;
    String[] loreLines = null;
    boolean loreBoolean = false;

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder setSubId(int subId) {
        this.subid = subId;
        return this;
    }

    public ItemBuilder setDurability(int durability) {
        this.durability = durability;
        return this;
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.displayname = displayName;
        return this;
    }

    public ItemBuilder setEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
        return this;
    }

    public ItemBuilder setLore(String[] lore) {
        this.loreLines = lore;
        return this;
    }

    public ItemBuilder setEnchantmentLevel(int level) {
        this.enchantmentlevel = level;
        return this;
    }

    public ItemBuilder setEnchantmentBool(boolean enchantmentBoolean) {
        this.enchantmentBoolean = enchantmentBoolean;
        return this;
    }

    public ItemBuilder setLoreBool(boolean loreBoolean) {
        this.loreBoolean = loreBoolean;
        return this;
    }

    public ItemStack build() {
        itemStack = new ItemStack(material, amount, (short)subid);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemStack.setDurability((short) durability);
        itemMeta.setDisplayName(displayname);
        if(enchantment != null) {
            itemMeta.addEnchant(enchantment, enchantmentlevel, enchantmentBoolean);
        }
        if(loreBoolean) {
            List<String> lores = new ArrayList<>();
            for(String lore : loreLines) {
                lores.add(lore);
            }
            itemMeta.setLore(lores);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


}
