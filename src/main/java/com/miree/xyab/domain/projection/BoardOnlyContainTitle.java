package com.miree.xyab.domain.projection;

import com.miree.xyab.domain.Board;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "getOnlyTitle", types = {Board.class})
public interface BoardOnlyContainTitle {
    String getTitle();
}
