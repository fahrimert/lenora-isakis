package com.isakis.lenoraisakis.service;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisVersion.IsAkisVersionCreateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IsAkisVersiyonService {
     public ResponseEntity<ApiResponse> getAllIsAkisVersiyonBasedOnIsAkisTanim(String isAkisTanimId);
     public ResponseEntity<ApiResponse> getSingleIsAkisVersiyonBasedOnIsAkisTanim(String isAkisVersionId);
     public ResponseEntity<ApiResponse> createIsAkisiVersion(
            IsAkisVersionCreateDTO i̇sAkisVersionCreateDTO  ,
            String isAkisTanimId
    );
    public ResponseEntity deleteIsAkisiVersion(String isAkisVersionId,
                                     String isAkisTanimId

                                     );

    ResponseEntity<ApiResponse> updateIsAkisVersiyon(String isAkisVersionId, String isAkisTanimId);
}
