package com.ead.authuser.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record InstructorRecordDto(@NotNull(message = "User is mandatory")UUID userId) {
}
