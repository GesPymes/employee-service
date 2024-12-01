package com.gespyme.domain.facade;

import com.gespyme.commons.model.user.UserModelApi;

public interface UserFacade {
    UserModelApi getUserModelApi(String email);
}
