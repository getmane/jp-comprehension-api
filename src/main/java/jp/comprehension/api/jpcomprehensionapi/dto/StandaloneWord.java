package jp.comprehension.api.jpcomprehensionapi.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class StandaloneWord {
    private String spelling;
    private String reading;
    private String meaning;
}
