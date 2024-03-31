package com.jagmi.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/method")
@PreAuthorize("denyAll()")
public class TestAuthController {

    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello(){
        return "Hola Mundo sin Securizar";
    }

    @GetMapping("/hello-secured")
    @PreAuthorize("hasAuthority('READ')")
    public String helloSecured(){
        return "Hola Mundo Securizado";
    }

    @GetMapping("/hello-secured2")
    @PreAuthorize("hasAuthority('CREATE')")
    public String helloSecured2(){
        return "Hola Mundo Securizado 2";
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public String helloGet(){
        return "Hola Mundo GET";
    }
    @PostMapping("/post")
    @PreAuthorize("hasAuthority('CREATE') or hasAuthority('READ')")
    public String helloPost(){
        return "Hola Mundo POST";
    }
    @PutMapping("/put")
    @PreAuthorize("hasRole('DEVELOPER')")
    public String helloPut(){
        return "Hola Mundo PUT";
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('DEVELOPER') or hasRole('ADMIN')")
    public String helloDelete(){
        return "Hola Mundo DELETE";
    }
    @PatchMapping("/patch")
    @PreAuthorize("hasAuthority('REFACTOR')")
    public String helloPatch(){
        return "Hola Mundo PATCH";
    }
}
