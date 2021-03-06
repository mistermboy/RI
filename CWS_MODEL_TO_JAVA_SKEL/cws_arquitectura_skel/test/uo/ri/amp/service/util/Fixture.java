package uo.ri.amp.service.util;

import alb.util.date.DateUtil;
import alb.util.random.Random;
import uo.ri.business.dto.CardDto;
import uo.ri.business.dto.ClientDto;
import uo.ri.business.dto.VoucherDto;

public class Fixture {

	public static ClientDto newClientDto() {
		ClientDto c = new ClientDto();
		c.dni = "123a";
		c.email = "client@workshop.com";
		c.name = "name";
		c.surname = "surname";
		c.phone = "123-456-789";
		c.addressStreet = "street";
		c.addressCity = "city";
		c.addressZipcode = "zipcode";
		return c;
	}

	public static CardDto newCardDto(Long clientId) {
		CardDto card = new CardDto();
		card.clientId = clientId;
		card.cardType = "VISA";
		card.cardNumber = Random.string(16);
		card.cardExpiration = DateUtil.tomorrow();
		return card;
	}

	public static VoucherDto newVoucherDto(Long clientId) {
		VoucherDto v = new VoucherDto();
		v.clientId = clientId;
		v.accumulated = 0.0;
		v.available = 100.0;
		v.description = "For testing purposes";
		// v.code = is generated by the service
		return v;
	}

}
