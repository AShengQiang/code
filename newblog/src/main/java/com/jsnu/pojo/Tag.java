package com.jsnu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Serializable {
    private Long id;
    private String name;

    private List<Blog> blogs=new ArrayList<>();
}
