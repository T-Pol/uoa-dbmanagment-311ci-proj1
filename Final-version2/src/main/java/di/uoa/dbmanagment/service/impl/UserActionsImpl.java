package di.uoa.dbmanagment.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import di.uoa.dbmanagment.model.UserActions;
import di.uoa.dbmanagment.repository.UserActionsRepository;
import di.uoa.dbmanagment.service.UserActionService;


@Service
@Transactional
public class UserActionsImpl implements UserActionService{

	@Autowired
	private UserActionsRepository uarepo;
	
	@Override
	public UserActions save(UserActions ua) {
		// TODO Auto-generated method stub
		return uarepo.save(ua);
	}

}