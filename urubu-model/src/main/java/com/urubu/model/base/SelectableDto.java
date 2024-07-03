package com.urubu.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SelectableDto {

    private String key;
    private String value;

    public SelectableDto(String key) {
        this.key = key;
    }
}
