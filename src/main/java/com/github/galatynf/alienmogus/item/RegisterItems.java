package com.github.galatynf.alienmogus.item;

import com.github.galatynf.alienmogus.item.token.ClassTokenItem;
import com.github.galatynf.alienmogus.item.token.TokenAlienItem;
import com.github.galatynf.alienmogus.item.token.TokenCrewmateItem;
import com.github.galatynf.alienmogus.item.token.TokenImpostorItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterItems {
    public static final ItemGroup alienmogusTokens = FabricItemGroupBuilder.create(
                    new Identifier("alienmogus", "tokens"))
            .icon(() -> new ItemStack(Items.HONEYCOMB))
            .build();
    public static final Item.Settings settings = new Item.Settings().group(alienmogusTokens);
    public static ClassTokenItem CLASS_TOKEN_ITEM = new ClassTokenItem(settings);
    public static TokenAlienItem TOKEN_ALIEN_ITEM = new TokenAlienItem(settings);
    public static TokenCrewmateItem TOKEN_CREWMATE_ITEM = new TokenCrewmateItem(settings);
    public static TokenImpostorItem TOKEN_IMPOSTOR_ITEM = new TokenImpostorItem(settings);

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("alienmogus", "class_token"), CLASS_TOKEN_ITEM);
        Registry.register(Registry.ITEM, new Identifier("alienmogus", "alien_token"), TOKEN_ALIEN_ITEM);
        Registry.register(Registry.ITEM, new Identifier("alienmogus", "crewmate_token"), TOKEN_CREWMATE_ITEM);
        Registry.register(Registry.ITEM, new Identifier("alienmogus", "impostor_token"), TOKEN_IMPOSTOR_ITEM);

    }
}
