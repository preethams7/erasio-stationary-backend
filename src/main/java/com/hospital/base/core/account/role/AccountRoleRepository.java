package com.hospital.base.core.account.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRoleEntity, Long> {

}
