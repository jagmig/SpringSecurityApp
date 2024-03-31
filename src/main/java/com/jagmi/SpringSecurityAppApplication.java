package com.jagmi;

import com.jagmi.persistence.entity.PermissionEntity;
import com.jagmi.persistence.entity.RoleEntity;
import com.jagmi.persistence.entity.RoleEnum;
import com.jagmi.persistence.entity.UserEntity;
import com.jagmi.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			//Paso 1 Crear permisos
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();


			//PASO 2: Crear los roles
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionsList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionsList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionsList(Set.of(readPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionsList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			//3 Create Users

			UserEntity userJorge = UserEntity.builder()
					.username("Jorge")
					.password("$2a$10$08o0LsB/NBCz3oTz7eADieSyTo3qIPCFxDorDJ9X.vwPG0JgLvCKa")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			UserEntity userCarlos = UserEntity.builder()
					.username("Carlos")
					.password("$2a$10$08o0LsB/NBCz3oTz7eADieSyTo3qIPCFxDorDJ9X.vwPG0JgLvCKa")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userHerminia = UserEntity.builder()
					.username("Herminia")
					.password("$2a$10$08o0LsB/NBCz3oTz7eADieSyTo3qIPCFxDorDJ9X.vwPG0JgLvCKa")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userAngela = UserEntity.builder()
					.username("Angela")
					.password("$2a$10$08o0LsB/NBCz3oTz7eADieSyTo3qIPCFxDorDJ9X.vwPG0JgLvCKa")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			userRepository.saveAll(List.of(userJorge, userCarlos, userHerminia, userAngela));
        };
	}
}
