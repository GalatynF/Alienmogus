package com.github.galatynf.alienmogus.component.world;

import com.github.galatynf.alienmogus.component.MyComponents;
import com.github.galatynf.alienmogus.enumRoles.EnumRole;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.Map;

public class WorldGameClassComponent implements WorldGameComponent{
    private GameStates currentState = GameStates.NOT_STARTED;
    @Override
    public void startGame(Map<PlayerEntity, EnumRole> playersAndRoles, BlockPos startPosition) {
        this.currentState = GameStates.RUNNING_DEFAULT;
        playersAndRoles.forEach((key, val) -> {
            MyComponents.ROLE.get(key).setRole(val);
            // TODO Give gear
            key.teleport(startPosition.getX(), startPosition.getY(), startPosition.getZ());
        } );
    }

    @Override
    public void endGame() {

    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.currentState = GameStates.valueOf(tag.getString("state"));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putString("state", this.currentState.toString());
    }
}
