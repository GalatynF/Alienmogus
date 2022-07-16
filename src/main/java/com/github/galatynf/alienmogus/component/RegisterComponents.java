package com.github.galatynf.alienmogus.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import net.minecraft.util.Identifier;

public class RegisterComponents {
    public static final ComponentKey<AbilitiesComponent> ROLE_COMPONENT =
            ComponentRegistry.getOrCreate(new Identifier("alienmogus", "role_component"), AbilitiesComponent.class);
}
