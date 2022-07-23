package com.github.galatynf.alienmogus.component.world;

import com.github.galatynf.alienmogus.enumRoles.EnumRole;
import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Map;

public interface WorldGameComponent extends Component {
    void startGame(Map<PlayerEntity, EnumRole> playersAndRoles, BlockPos startPosition);
    void endGame();

}
