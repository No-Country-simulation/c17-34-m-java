package com.c174.models.event;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventRequest {

    private Long id;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid event name")
    @NotEmpty
    @Size(min = 1, max = 50)
    private String name;

}
