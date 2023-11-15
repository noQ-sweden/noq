package com.noq.backend.models.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CommaSeparatedListConverterTest {

    @Test
    void shouldConvertListToCommaSeparatedString() {
        var converter = new CommaSeparatedListConverter();
        assertThat(converter.convertToDatabaseColumn(List.of("Value1", "Value2"))).isEqualTo("Value1,Value2");
    }

    @Test
    void shouldConvertCommaSeparatedStringToList() {
        var converter = new CommaSeparatedListConverter();
        assertThat(converter.convertToEntityAttribute("AnotherValue1,AnotherValue2")).isEqualTo(List.of("AnotherValue1", "AnotherValue2"));
    }

}