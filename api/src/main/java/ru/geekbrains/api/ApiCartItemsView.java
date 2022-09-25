package ru.geekbrains.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCartItemsView {

    @JsonProperty
    private Long id;
    @JsonProperty
    private List<ApiCartItem> items;

    public void addCartToList(ApiCartItem view){
        items.add(view);
    }
}
