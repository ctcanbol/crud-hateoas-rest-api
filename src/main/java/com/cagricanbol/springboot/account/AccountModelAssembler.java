package com.cagricanbol.springboot.account;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>> {

	@Override
	public EntityModel<Account> toModel(Account account) {

		return EntityModel.of(account, //
				linkTo(methodOn(AccountController.class).getAccountById(account.getId())).withSelfRel(),
				linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("accounts"));
	}
}