package io.github.harryjhin.sign.controller

import io.github.harryjhin.sign.request.SignUpRequest
import io.github.harryjhin.sign.service.SignUpService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sign-up")
class SignUpController(
    private val signUpService: SignUpService,
) {

    @PostMapping
    fun signUp(
        @RequestBody request: SignUpRequest,
    ): String {

        signUpService.execute(request)

        return "Sign up success"
    }
}
