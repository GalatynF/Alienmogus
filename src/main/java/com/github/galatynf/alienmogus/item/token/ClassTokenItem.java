package com.github.galatynf.alienmogus.item.token;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ClassTokenItem extends Item {

    public ClassTokenItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }
}
