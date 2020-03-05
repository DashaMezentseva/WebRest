package com.nixsolutions.dto;

import com.nixsolutions.domain.Role;
import com.nixsolutions.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.util.Locale;

public class RoleFormatter implements Formatter<Role> {

    @Autowired
    private RoleService roleService;

    private static final String PREFIX = "ROLE_";

    public String print(Role role, Locale arg1) {
        return role.getName().substring(PREFIX.length());
    }

    public Role parse(String str, Locale arg1) {
        if (!str.startsWith(PREFIX)) {
            str = PREFIX + str;
        }
        return roleService.findByName(str);
    }
}
