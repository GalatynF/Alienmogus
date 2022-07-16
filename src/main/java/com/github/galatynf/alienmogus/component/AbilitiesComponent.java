package com.github.galatynf.alienmogus.component;

import com.github.galatynf.alienmogus.enumRoles.EnumRole;
import dev.onyxstudios.cca.api.v3.component.Component;

public interface AbilitiesComponent extends Component {
    EnumRole getRole();
    void setRole(EnumRole role);
}