package com.github.galatynf.alienmogus.component.world;

import com.github.galatynf.alienmogus.component.AbilitiesComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.minecraft.util.Identifier;

public class MyWorldComponents implements WorldComponentInitializer {
    public static final ComponentKey<AbilitiesComponent> ROLE =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("alienmogus:role"), AbilitiesComponent.class);

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {

    }
}
