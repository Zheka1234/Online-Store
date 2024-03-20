package com.boss.controller.springdata;


import com.boss.controller.request.user.UserChangeRequest;
import com.boss.controller.request.user.UserCreateRequest;
import com.boss.domain.hibernate.HibernateUser;
import com.boss.repository.user.UserSpringDataRepository;
import com.boss.security.util.PrincipalUtil;
import com.boss.service.user.UserImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@Tag(name = "User controller")
@RequestMapping("/rest/data/users")
public class UserController {

    private final UserSpringDataRepository repository;

    private final UserImpl service;

    private final ConversionService conversionService;

    @GetMapping()
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @ResponseStatus(HttpStatus.OK)
    public HibernateUser showProfile(Principal principal) {
        String username = PrincipalUtil.getUsername(principal);
        Optional<HibernateUser> result = repository.findByCredentialsLogin(username);

        if (result.isPresent()) {
            return service.findById(result.get().getIdUser());
        } else {
            throw new AuthorizationServiceException("User is not authenticate");
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "This method allows create a new user in DataBase.",
            required = true,
            content = @Content(schema = @Schema(implementation = UserCreateRequest.class)))
    public ResponseEntity<Object> createUser(
            @Valid @org.springframework.web.bind.annotation.RequestBody
            UserCreateRequest userCreateRequest) {

        HibernateUser hibernateUser = conversionService.convert(userCreateRequest, HibernateUser.class);

        hibernateUser = service.create(hibernateUser);

        Map<String, Object> model = new HashMap<>();
        model.put("user", service.findById(hibernateUser.getIdUser()));

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PutMapping()
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @ResponseStatus(HttpStatus.OK)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "This method allows update the user in DataBase.",
            required = true,
            content = @Content(schema = @Schema(implementation = UserChangeRequest.class)))
    public ResponseEntity<Object> updateUser(
            @Valid @org.springframework.web.bind.annotation.RequestBody
            UserChangeRequest userChangeRequest,
            Principal principal) {

        String username = PrincipalUtil.getUsername(principal);
        Optional<HibernateUser> result = repository.findByCredentialsLogin(username);

        if (result.isPresent()) {

            userChangeRequest.setId(result.get().getIdUser());
            HibernateUser hibernateUser =
                    conversionService.convert(userChangeRequest, HibernateUser.class);

            hibernateUser = service.update(hibernateUser);

            Map<String, Object> model = new HashMap<>();
            model.put("user", service.findById(hibernateUser.getIdUser()));

            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            throw new AuthorizationServiceException("User is not authenticate");
        }
    }

    @DeleteMapping()
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @Operation(description = "This method allows deactivate the user in DataBase")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteUser(Principal principal) {

        String username = PrincipalUtil.getUsername(principal);
        Optional<HibernateUser> result = repository.findByCredentialsLogin(username);

        if (result.isPresent()) {

            Long userId = result.get().getIdUser();

            HibernateUser hibernateUser = service.delete(userId);

            Map<String, Object> model = new HashMap<>();
            model.put("user", service.findById(hibernateUser.getIdUser()));

            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            throw new AuthorizationServiceException("User is not authenticate");
        }
    }
}
