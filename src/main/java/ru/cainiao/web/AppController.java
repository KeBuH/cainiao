package ru.cainiao.web;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.cainiao.service.DeliveryService;
import ru.cainiao.web.dto.DeliveryInfo;




@RestController
@RequiredArgsConstructor
public class AppController {

    private final DeliveryService deliveryService;

    @GetMapping("/route")
    public DeliveryInfo route(@RequestParam String fromCity,
                              @RequestParam String toCity) {
        return deliveryService.calcDelivery(fromCity, toCity);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String noRouteResult() {
        return "building route is not possible";
    }

}
