package jp.comprehension.api.jpcomprehensionapi.userword.srs.jpdb;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class Jpdb {

    @SerializedName("cards_vocabulary_jp_en")
    private List<JpdbWord> cardsVocabularyJpEn;
}

