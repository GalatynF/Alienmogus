package com.github.galatynf.alienmogus.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class BadAirBlock extends AirBlock {

    protected BadAirBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if(block instanceof BadAirBlock) {

        }
    }
}
