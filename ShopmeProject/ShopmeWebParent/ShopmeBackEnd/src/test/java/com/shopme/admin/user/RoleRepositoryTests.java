package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	@Autowired
	private RoleRepository repo;
	
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role("Manager", "manage everything");
		Role saveRole = repo.save(roleAdmin);
		
		assertThat(saveRole.getId()).isGreaterThan(0);
	}
	@Test
	public void testCreateRestRoles() {
		Role roleSalesperson = new Role("Salesperson","manage product price, customers, orders");
//		Role roleEditor = new Role("Editor","manage categories, brands, products, articles and menus");
//		Role roleShipper = new Role("Shipper","view product, view orders and update order status");
//		Role roleAssistant = new Role("Assistant","manage question and review");
		
		repo.saveAll(List.of(roleSalesperson));
	}
	@Test 
	public void testDeleteRole() {
		Integer roleId=3;
		repo.deleteById(roleId);
	}
}
