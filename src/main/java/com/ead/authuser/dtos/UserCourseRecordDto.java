package com.ead.authuser.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserCourseRecordDto(UUID userId,
                                  @NotNull(message = "Course is mandatory") UUID courseId) {
}
