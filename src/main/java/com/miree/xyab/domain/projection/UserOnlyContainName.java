package com.miree.xyab.domain.projection;

import com.miree.xyab.domain.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "getOnlyName", types = {User.class})
public interface UserOnlyContainName {
    String getName();
}
