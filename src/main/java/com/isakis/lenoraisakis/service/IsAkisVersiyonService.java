package com.isakis.lenoraisakis.service;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisVersion.İsAkisVersionCreateDTO;
import com.isakis.lenoraisakis.model.IsAkisVersion;
import com.isakis.lenoraisakis.model.core.BaseModel;
import com.isakis.lenoraisakis.model.İsAkisTanim;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IsAkisVersiyonService {
     public ResponseEntity<ApiResponse> getAllIsAkisVersiyonBasedOnIsAkisTanim(String isAkisTanimId);
     public ResponseEntity<ApiResponse> getSingleIsAkisVersiyonBasedOnIsAkisTanim(String isAkisVersionId);
     public ResponseEntity<ApiResponse> createIsAkisiVersion(
            @RequestBody İsAkisVersionCreateDTO i̇sAkisVersionCreateDTO  ,
            String isAkisTanimId
    );
    public ResponseEntity deleteIsAkisiVersion(String isAkisVersionId,
                                     String isAkisTanimId

                                     );

}
