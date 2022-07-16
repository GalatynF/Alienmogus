package com.github.galatynf.alienmogus.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;

public class MyComponents implements EntityComponentInitializer {
    public static final ComponentKey<AbilitiesComponent> ROLE =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("alienmogus:role"), AbilitiesComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(ROLE, PlayerAbilitiesComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
