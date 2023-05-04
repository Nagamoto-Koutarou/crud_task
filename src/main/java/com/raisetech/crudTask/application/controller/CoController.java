package com.raisetech.crudTask.application.controller;

import com.raisetech.crudTask.application.form.CoForm;
import com.raisetech.crudTask.domain.exception.BadRequestException;
import com.raisetech.crudTask.domain.service.CoService;
import com.raisetech.crudTask.infrastructure.entity.Coffee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequestMapping("/coffees")
@RequiredArgsConstructor
@RestController
public class CoController {

    private final CoService coService;

    @GetMapping
    public List<CoResponse> findAll() {
        return coService.findAll().stream().map(CoResponse::new).toList();
    }

    @GetMapping("/{id}")
    public CoResponse findById(@PathVariable int id) {
        return new CoResponse(coService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> create(@RequestBody @Validated CoForm coForm, BindingResult result, UriComponentsBuilder uriComponentsBuilder) {
        if (result.hasErrors()) {
            throw new BadRequestException("bad request");
        }
        ModelMapper modelMapper1 = new ModelMapper();
        Coffee conversionCoffee = modelMapper1.map(coForm, Coffee.class);
        Coffee coffee = coService.register(conversionCoffee);
        URI url = uriComponentsBuilder.path("/coffee/" + coffee.getId())
                .build()
                .toUri();
        return ResponseEntity.created(url).body(Map.of("message", "coffee successfully create"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String,String>> patch(@PathVariable("id")int id, @RequestBody @Validated CoForm coForm, BindingResult result, UriComponentsBuilder uriComponentsBuilder) {
        if (result.hasErrors()) {
            throw new BadRequestException("bad request");
        }
        ModelMapper modelMapper = new ModelMapper();
        Coffee conversionCoffee = modelMapper.map(coForm, Coffee.class);
        coService.update(id, conversionCoffee);
        URI url = uriComponentsBuilder.path("/coffees/" + id)
                .build()
                .toUri();
        return ResponseEntity.created(url).body(Map.of("message", "coffee successfully update"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable("id")int id) {
        coService.delete(id);
        return ResponseEntity.ok(Map.of("message", "coffee successfully delete"));
    }
}
