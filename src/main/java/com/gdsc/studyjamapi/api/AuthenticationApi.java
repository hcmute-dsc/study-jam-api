package com.gdsc.studyjamapi.api;

import com.gdsc.studyjamapi.dto.request.SignInRequest;
import com.gdsc.studyjamapi.dto.response.ErrorResponse;
import com.gdsc.studyjamapi.dto.response.SignInResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Tag(name = "Authentication")
public interface AuthenticationApi {
    @Operation(summary = "Authenticate a user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SignInResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "Bad credentials", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @PostMapping("/sign-in")
    ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest request);
}
