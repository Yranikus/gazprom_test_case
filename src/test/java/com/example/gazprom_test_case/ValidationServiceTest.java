package com.example.gazprom_test_case;

import com.example.gazprom_test_case.service.ValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



//Информацию о формате адресса профиля и группы я взял с офф документации вк https://vk.com/faq18038
class ValidationServiceTest {

	private ValidationService validationService = new ValidationService();

	//Минимальная длина 5 максимальная 32
	@Test
	void maxLenOfIdTEst() {
		String id = "jjj";
		String second_id = "jjjjjdjdjdjdjdjdjdjkdkdfkfirvojbtuitgfrnujrewww";
		Assertions.assertFalse(validationService.validateCurrentId(id));
		Assertions.assertFalse(validationService.validateCurrentId(second_id));
	}

	//Id не должен начинаться 3х цифр
	@Test
	void idStartWithThreeAndMoreNumbers() {
		String id = "333hyfg";
		String second_id = "3334tfhg";
		String third_id = "22thylok";
		Assertions.assertFalse(validationService.validateCurrentId(id));
		Assertions.assertFalse(validationService.validateCurrentId(second_id));
		Assertions.assertTrue(validationService.validateCurrentId(third_id));
	}

	//ID не должен содержать служебных слов
	@Test
	void containsReservedWords() {
		String id = "samiikrutoiadmin";
		String second_id = "topphotograph";
		String third_id = "eventlistner";
		Assertions.assertFalse(validationService.validateCurrentId(id));
		Assertions.assertFalse(validationService.validateCurrentId(second_id));
		Assertions.assertFalse(validationService.validateCurrentId(third_id));
	}


	//ID не должен одновременно начинаться и заканчиваться знаком _
	@Test
	void starAndEndWithUnderline() {
		String id = "_megaman";
		String second_id = "megawoman_";
		String third_id = "_lolka_";
		Assertions.assertTrue(validationService.validateCurrentId(id));
		Assertions.assertTrue(validationService.validateCurrentId(second_id));
		Assertions.assertFalse(validationService.validateCurrentId(third_id));
	}

	//ID не должен содержать точку после кооторой менее 4х знаков начинающихся с буквы
	@Test
	void containsDotAndLessThanThreeSigns() {
		String id = "krytoi.1chel";
		String second_id = "krytoi.ch";
		String third_id = "krytoiq.chel";
		Assertions.assertFalse(validationService.validateCurrentId(id));
		Assertions.assertFalse(validationService.validateCurrentId(second_id));
		Assertions.assertTrue(validationService.validateCurrentId(third_id));
	}

	//ID не должен начинаться с точки
	@Test
	void startWithDot() {
		String id = ".1chel";
		String second_id = "yranikus";
		Assertions.assertFalse(validationService.validateCurrentId(id));
		Assertions.assertTrue(validationService.validateCurrentId(second_id));
	}

	//ID не должен содержать пробелов между символов
	@Test
	void containsSpaces() {
		String id = "1ch el";
		Assertions.assertFalse(validationService.validateCurrentId(id));
	}


}
