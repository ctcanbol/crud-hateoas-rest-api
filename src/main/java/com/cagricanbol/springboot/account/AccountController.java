package com.cagricanbol.springboot.account;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountServiceImpl accService;

	public AccountController(AccountServiceImpl accService) {
		this.accService = accService;
	}

	@GetMapping
	public CollectionModel<EntityModel<Account>> getAllAccounts() {
		return accService.getAllAccounts();
	}

	@GetMapping("/{id}")
	public EntityModel<Account> getAccountById(@PathVariable("id") long acccountId) {
		return accService.getAccountById(acccountId);
	}

	@PostMapping
	public ResponseEntity<?> createAccount(@RequestBody Account account) {
		return accService.createAccount(account);
	}

	@PutMapping("/{id}")
	public EntityModel<Account> updatAccount(@RequestBody Account account, @PathVariable("id") long acccountId) {
		return accService.updatAccount(account, acccountId);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable("id") long acccountId) {
		return accService.deleteAccount(acccountId);
	}
}