package com.isakis.lenoraisakis.serviceimpl;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisVersion.IsAkisVersionCreateDTO;
import com.isakis.lenoraisakis.dto.isAkisVersion.IsAkisVersionResponseDTO;
import com.isakis.lenoraisakis.model.IsAkisTanim;
import com.isakis.lenoraisakis.model.IsAkisVersion;
import com.isakis.lenoraisakis.repository.IsAkisDurumRepository;
import com.isakis.lenoraisakis.repository.IsAkisTanimRepository;
import com.isakis.lenoraisakis.repository.İsAkisVersiyonRepository;
import com.isakis.lenoraisakis.service.IsAkisVersiyonService;
import com.isakis.lenoraisakis.service.IsAkısDurumService;
import com.isakis.lenoraisakis.service.mapper.İsAkisVersionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class IsAkisDurumServiceImpl implements IsAkısDurumService {
    private IsAkisDurumRepository isAkisDurumRepository;
    private İsAkisVersionMapper i̇sAkisVersionMapper;
    private IsAkisTanimRepository i̇sAkisTanimRepository;
    public IsAkisDurumServiceImpl(İsAkisVersiyonRepository i̇sAkisVersiyonRepository, İsAkisVersionMapper i̇sAkisVersionMapper, IsAkisTanimRepository i̇sAkisTanimRepository) {
        this.i̇sAkisVersiyonRepository = i̇sAkisVersiyonRepository;
        this.i̇sAkisVersionMapper = i̇sAkisVersionMapper;
        this.i̇sAkisTanimRepository = i̇sAkisTanimRepository;
    }


    public ResponseEntity<ApiResponse> getAllIsAkisVersiyonBasedOnIsAkisTanim(String isAkisTanimId) {
        Optional<IsAkisTanim> ısAkisTanimOptional = i̇sAkisTanimRepository.findById(isAkisTanimId);
        if ( ısAkisTanimOptional.isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanim Bulunamamakta", Collections.emptyList(), HttpStatus.CONFLICT));
        }
        IsAkisTanim i̇sAkisTanim = ısAkisTanimOptional.get();

        if (i̇sAkisTanim.getİsAkisVersions().isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanımına dair versiyon listesi boş.", Collections.emptyList(), HttpStatus.CONFLICT));
        }

        List<IsAkisVersionResponseDTO> isAkisVersionDTOList = i̇sAkisTanim.getİsAkisVersions().stream().map(i̇sAkisVersionMapper::fromİsAkisVersion).toList();

        return ResponseEntity.status(200).body(ApiResponse.success(isAkisVersionDTOList));
    }

    @Override
    public ResponseEntity<ApiResponse> getSingleIsAkisVersiyonBasedOnIsAkisTanim(String isAkisVersionId) {
        Optional<IsAkisVersion> isAkisVersion = i̇sAkisVersiyonRepository.findById(isAkisVersionId);

        if(isAkisVersion.isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("İs Akis Versiyonu bulunamadı.", Collections.emptyList(), HttpStatus.CONFLICT));

        }
        IsAkisVersion existİsAkisVersion = isAkisVersion.get();

        if (existİsAkisVersion.getIsakistanim().equals(Optional.empty())){
            return  ResponseEntity.status(409).body(ApiResponse.error("İs Akis Versiyonuna dair is akis tanim bulunamadı.", Collections.emptyList(), HttpStatus.CONFLICT));
        }
    return  ResponseEntity.ok(ApiResponse.success(existİsAkisVersion.getIsakistanim()));

}




    public ResponseEntity<ApiResponse> createIsAkisiVersion(IsAkisVersionCreateDTO i̇sAkisVersionCreateDTO  , String isAkisTanimId) {
        Optional<IsAkisTanim> ısAkisTanimOptional = i̇sAkisTanimRepository.findById(isAkisTanimId);
        if ( ısAkisTanimOptional.isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanim Bulunamamakta", Collections.emptyList(), HttpStatus.CONFLICT));
        }

        IsAkisTanim akisTanim = ısAkisTanimOptional.get();

        IsAkisVersion i̇sAkisVersion = new IsAkisVersion();
        i̇sAkisVersion.setVersion(i̇sAkisVersionCreateDTO.getVersiyon());
        i̇sAkisVersion.setAciklama(i̇sAkisVersionCreateDTO.getAciklama());
        i̇sAkisVersion.setOlusturma_zamanı(LocalDateTime.now().toString());
        i̇sAkisVersion.setOlusturan_oid(i̇sAkisVersionCreateDTO.getOlusturan_oid());
        i̇sAkisVersion.setAktif(1);
        i̇sAkisVersion.setIsakistanim(akisTanim);

        ArrayList<IsAkisVersion> isAkisVersionArrayList = new ArrayList<>();
        isAkisVersionArrayList.add(i̇sAkisVersion);

        akisTanim.setİsAkisVersions(isAkisVersionArrayList);
        i̇sAkisTanimRepository.save(akisTanim);

        IsAkisVersionResponseDTO ısAkisVersionResponseDTO = new IsAkisVersionResponseDTO();
        ısAkisVersionResponseDTO.setIsakis_tanim_adi(akisTanim.getAdı());
        ısAkisVersionResponseDTO.setVersiyon(i̇sAkisVersion.getVersiyon());
        ısAkisVersionResponseDTO.setAciklama(i̇sAkisVersion.getAciklama());
        ısAkisVersionResponseDTO.setOlusturma_zamanı(LocalDateTime.now().toString());
        ısAkisVersionResponseDTO.setCreatorPerson(i̇sAkisVersion.getCreatorPerson());
        ısAkisVersionResponseDTO.setAktif(i̇sAkisVersion.getAktif());


        return  ResponseEntity.ok(ApiResponse.success(ısAkisVersionResponseDTO));
    }

    @Override
    public ResponseEntity deleteIsAkisiVersion(String isAkisVersionId, String isAkisTanimId) {
        Optional<IsAkisTanim> isAkisTanimOptional= i̇sAkisTanimRepository.findById(isAkisTanimId);

        if ( isAkisTanimOptional.isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanim Bulunamamakta", Collections.emptyList(), HttpStatus.CONFLICT));
        }

        Optional<IsAkisVersion> isAkisVersionOptional= i̇sAkisVersiyonRepository.findById(isAkisVersionId);
        if ( isAkisVersionOptional.isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("İs Akis Versiyonu Bulunamamakta", Collections.emptyList(), HttpStatus.CONFLICT));
        }

        i̇sAkisVersiyonRepository.deleteById(isAkisVersionId);

        return  ResponseEntity.ok("Successfully versiyon Deleted");
    }


}
