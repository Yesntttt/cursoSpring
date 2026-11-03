package io.github.cursospring.libraryapi.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO(@NotBlank(message = "Cliente Id obrigatório")
                        String clientId,

                        @NotBlank(message = "Client Secret obrigatório")
                        String clientSecret,

                        @NotBlank(message = "Redirect URI obrigatório")
                        String redirectURI,

                        @NotBlank(message = "Scope obrigatório")
                        String scope) {

}
