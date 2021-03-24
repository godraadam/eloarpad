package dev.godradam.matchball.model;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseModel {

    @Id
    private String id;
    
}
