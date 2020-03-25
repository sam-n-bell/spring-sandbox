package com.togglr.testapp.entities.projections;

import com.togglr.testapp.entities.ApplicationUserEntity;
import com.togglr.testapp.entities.TaskEntity;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;

@Projection(name = "includeSubObjects", types = {ApplicationUserEntity.class})
public interface AppUserProjection {

    int getId();

    String getEmail();

    String getEid();

    boolean getDeleted();

    String getName();

}
