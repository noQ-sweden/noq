package com.noq.backend.services.cosmos.utils;

import static io.micrometer.common.util.StringUtils.isBlank;

public class InputValidator {
    public enum IdField {
        HOST_ID("Host id"),
        BED_ID("Bed id"),
        USER_ID("User id");

        private final String fieldName;

        IdField(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }
    }

    public static void validateId(IdField field, String value) {
        if (isBlank(value)) {
            throw new IllegalArgumentException(field.getFieldName() + " is required.");
        }
    }
}
