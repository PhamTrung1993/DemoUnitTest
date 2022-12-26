package com.demounittest.service.coach;

import com.demounittest.model.Coach;
import com.demounittest.service.GeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ICoachService extends GeneralService<Coach>, UserDetailsService {
    Coach findByEmail(String email);
}
