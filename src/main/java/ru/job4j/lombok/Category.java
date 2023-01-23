package ru.job4j.lombok;

import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@Getter
@Setter
public class Category {
    @NonNull
    @EqualsAndHashCode.Include
    private int id;

    private String name;

}

