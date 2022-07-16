package com.github.galatynf.alienmogus.block;

import com.github.galatynf.alienmogus.Alienmogus;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterBlocks {
    public static TrapdoorBlock INVERTED_TRAPDOOR;
    public static BadAirBlock BAD_AIR;

    public static void registerBlocks() {
        INVERTED_TRAPDOOR = registerTrapdoorBlock("inverted_trapdoor", new InvertedTrapdoorBlock(), ItemGroup.REDSTONE);
        BAD_AIR = registerBlock("bad_air", new BadAirBlock(AbstractBlock.Settings.of(Material.AIR)), ItemGroup.MISC);
    }

    public static <T extends TrapdoorBlock> T registerTrapdoorBlock(Identifier id, T block, ItemGroup group) {
        T registered = Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, new BlockItem(registered, new Item.Settings().group(group)));
        return registered;
    }

    private static <T extends TrapdoorBlock> T registerTrapdoorBlock(String name, T block, ItemGroup group) {
        Identifier id = new Identifier(Alienmogus.MOD_ID, name);
        return registerTrapdoorBlock(id, block, group);
    }

    public static <T extends Block> T registerBlock(Identifier id, T block, ItemGroup group) {
        T registered = Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, new BlockItem(registered, new Item.Settings().group(group)));
        return registered;
    }

    private static <T extends Block> T registerBlock(String name, T block, ItemGroup group) {
        Identifier id = new Identifier(Alienmogus.MOD_ID, name);
        return registerBlock(id, block, group);
    }
}
