package com.cagricanbol.springboot.account;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface AccountService {

	public CollectionModel<EntityModel<Account>> getAllAccounts();

	public EntityModel<Account> getAccountById(long acccountId);

	public ResponseEntity<?> createAccount(Account account);

	public EntityModel<Account> updatAccount(Account account, long acccountId);

	public ResponseEntity<?> deleteAccount(long acccountId);
}
