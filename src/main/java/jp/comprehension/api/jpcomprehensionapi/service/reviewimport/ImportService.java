package jp.comprehension.api.jpcomprehensionapi.service.reviewimport;

import jp.comprehension.api.jpcomprehensionapi.dto.reviewimport.jpdb.ImportStat;
import org.springframework.web.multipart.MultipartFile;

public interface ImportService {
    ImportStat importReviews(String username, MultipartFile importFile);
}
