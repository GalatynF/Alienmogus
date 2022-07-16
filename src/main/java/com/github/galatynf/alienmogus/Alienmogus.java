package com.github.galatynf.alienmogus;

import com.github.galatynf.alienmogus.block.RegisterBlocks;
import com.github.galatynf.alienmogus.item.RegisterItems;
import net.fabricmc.api.ModInitializer;

public class Alienmogus implements ModInitializer {
    public static final String MOD_ID = "alienmogus";


    @Override
    public void onInitialize() {
        RegisterBlocks.registerBlocks();
        RegisterItems.registerItems();
    }
}
