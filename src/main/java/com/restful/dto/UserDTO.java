package com.restful.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String name;
    private String email;

    /**
     * @JsonIgnore - this annotation prevents both serialization and deserialization of the annotated field. To resolve this we have to prevent the access
     *
     * @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) - this ensures that this field will be ignored during serialization and available during deserialization
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
