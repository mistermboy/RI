package uo.ri.business.impl;

import uo.ri.util.exception.BusinessException;

public interface Command<T> {

	T execute() throws BusinessException; 
}
