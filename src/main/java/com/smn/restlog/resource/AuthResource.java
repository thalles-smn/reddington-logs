package com.smn.restlog.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthResource extends BaseResource {

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> index(){
         return this.buildResponse(HttpStatus.OK, Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()));
    }

}
