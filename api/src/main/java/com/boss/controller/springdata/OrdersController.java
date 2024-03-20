package com.boss.controller.springdata;


import com.boss.controller.request.orders.OrdersCreatRequest;
import com.boss.domain.hibernate.HibernateOrder;
import com.boss.domain.hibernate.HibernateUser;
import com.boss.repository.user.UserSpringDataRepository;
import com.boss.security.util.PrincipalUtil;
import com.boss.service.ordes.OrdersServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/rest/data/orders")
@Tag(name = "Orders controller")
public class OrdersController {


    private final OrdersServiceImpl ordersService;
    private final ConversionService conversionService;
    private final UserSpringDataRepository userSpringDataRepository;


    @PostMapping()
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)

    @ResponseStatus(HttpStatus.CREATED)
    @RequestBody(
            description = "This method allows create a new orders  in DataBase.",
            required = true,
            content = @Content(schema = @Schema(implementation = OrdersCreatRequest.class)))
    public ResponseEntity<Object> createOrders(
            @Valid @org.springframework.web.bind.annotation.RequestBody
            OrdersCreatRequest ordersCreatRequest,
            Principal principal) {
        String username = PrincipalUtil.getUsername(principal);
        Optional<HibernateUser> result = userSpringDataRepository.findByCredentialsLogin(username);

        if (result.isPresent()) {

            ordersCreatRequest.setIdUser(result.get().getIdUser());

            HibernateOrder hibernateOrder =
                    conversionService.convert(ordersCreatRequest, HibernateOrder.class);

            hibernateOrder = ordersService.create(hibernateOrder);

            Map<String, Object> model = new HashMap<>();
            model.put("orders", ordersService.findById(hibernateOrder.getIdOrder()));

            return new ResponseEntity<>(model, HttpStatus.CREATED);
        } else {
            throw new AuthorizationServiceException("User is not authenticate");
        }

    }

}
