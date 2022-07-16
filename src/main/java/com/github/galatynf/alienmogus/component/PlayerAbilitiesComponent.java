package com.github.galatynf.alienmogus.component;

import com.github.galatynf.alienmogus.enumRoles.EnumRole;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class PlayerAbilitiesComponent implements AbilitiesComponent, AutoSyncedComponent {
    private final PlayerEntity provider;
    private EnumRole role;

    public PlayerAbilitiesComponent(PlayerEntity provider) {
        this.provider = provider;
        this.role = EnumRole.CREWMATE;
    }

    @Override
    public EnumRole getRole() {
        return this.role;
    }

    @Override
    public void setRole(EnumRole newRole) {
        this.role = newRole;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.role = EnumRole.valueOf(tag.getString("role"));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putString("role", this.role.toString());
    }
}
