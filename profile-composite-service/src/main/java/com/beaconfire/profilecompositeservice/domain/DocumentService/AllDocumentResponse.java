package com.beaconfire.profilecompositeservice.domain.DocumentService;

import com.beaconfire.profilecompositeservice.entity.DocumentService.Document;
import lombok.*;

import java.util.List;

/**
 * @author Richard Zhang
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllDocumentResponse {
    private List<Document> documentList;
}
