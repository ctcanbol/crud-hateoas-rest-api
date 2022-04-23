package com.cagricanbol.springboot.account;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cagricanbol.springboot.exception.ResourceNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final AccountModelAssembler assembler;

	public AccountServiceImpl(AccountRepository accountRepository, AccountModelAssembler assembler) {
		this.accountRepository = accountRepository;
		this.assembler = assembler;
	}

	@Override
	public CollectionModel<EntityModel<Account>> getAllAccounts() {
		List<EntityModel<Account>> accounts = accountRepository.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(accounts, //
				linkTo(methodOn(AccountController.class).getAllAccounts()).withSelfRel());
	}

	@Override
	public EntityModel<Account> getAccountById(long acccountId) {
		Account account = accountRepository.findById(acccountId) //
				.orElseThrow(() -> new ResourceNotFoundException("Account id not found:" + acccountId));

		return assembler.toModel(account);
	}

	@Override
	public ResponseEntity<?> createAccount(Account account) {
		EntityModel<Account> entityModel = assembler.toModel(accountRepository.save(account));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@Override
	public EntityModel<Account> updatAccount(Account account, long acccountId) {
		Account existingAccount = accountRepository.findById(acccountId) //
				.map(exAccount -> {
					exAccount.setBranchCode(account.getBranchCode());
					exAccount.setAccountNumber(account.getAccountNumber());
					exAccount.setCustomerNumber(account.getCustomerNumber());
					return accountRepository.save(exAccount);
				}) //
				.orElseThrow(() -> new ResourceNotFoundException("Account id not found:" + acccountId));

		return assembler.toModel(existingAccount);
	}

	@Override
	public ResponseEntity<?> deleteAccount(long acccountId) {
		Account existingAccount = accountRepository.findById(acccountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account id not found:" + acccountId));
		accountRepository.delete(existingAccount);

		return ResponseEntity.noContent().build();
	}

}
