package uo.ri.business.impl.admin;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;

public class FindAllMechanics implements Command<List<MechanicDto>>{

	public List<MechanicDto> execute() {

		MecanicoRepository r = Factory.repository.forMechanic();

		List<Mecanico> list = r.findAll();
		return DtoAssembler.toMechanicDtoList(list);
	}

}
