package com.hospital.base.business.auth.login;

import com.hospital.base.core.account.accounts.AccountsEntity;

public interface UserService {
    void save(AccountsEntity user);

    AccountsEntity findByUsername(String username);
}