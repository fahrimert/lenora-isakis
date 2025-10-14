package com.isakis.lenoraisakis.serviceimpl;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisTanim.İsAkisTanimCreateRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTanim.İsAkisTanimResponseDTO;
import com.isakis.lenoraisakis.dto.isAkisTanim.İsAkisTanimUpdateRequestDTO;
import com.isakis.lenoraisakis.model.İsAkisTanim;
import com.isakis.lenoraisakis.repository.İsAkisTanimRepository;
import com.isakis.lenoraisakis.repository.İsAkisVersiyonRepository;
import com.isakis.lenoraisakis.service.mapper.İsAkisTanimMapper;
import com.isakis.lenoraisakis.service.İsAkisTanimService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class İsAkisTanimServiceImpl extends BaseServiceImpl<İsAkisTanim, String> implements İsAkisTanimService {
    private  final İsAkisTanimRepository i̇sAkisTanimRepository;
    private final İsAkisTanimMapper isAkisTanimMapper;
    private  final İsAkisVersiyonRepository i̇sAkisVersiyonRepository;

    public İsAkisTanimServiceImpl(JpaRepository<İsAkisTanim, String> repository, İsAkisTanimRepository i̇sAkisTanimRepository, İsAkisTanimRepository i̇sAkisTanimRepository1, İsAkisTanimMapper isAkisTanimMapper, İsAkisVersiyonRepository i̇sAkisVersiyonRepository) {
        super(repository);
        this.i̇sAkisTanimRepository = i̇sAkisTanimRepository1;
        this.isAkisTanimMapper = isAkisTanimMapper;
        this.i̇sAkisVersiyonRepository = i̇sAkisVersiyonRepository;
    }


    public  ResponseEntity<ApiResponse>  getAllİsAkis(){
            List<İsAkisTanim> i̇sAkisTanimList = i̇sAkisTanimRepository.findAll();

            if (i̇sAkisTanimList.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanim Listesi Boş", new ArrayList<>(), HttpStatus.NO_CONTENT));
            }

            List<İsAkisTanimResponseDTO> i̇sAkisTanimResponseDTO = i̇sAkisTanimList.stream().map(isAkisTanimMapper::fromİsAkisTanim).toList();

            return  ResponseEntity.status(200).body(ApiResponse.success(i̇sAkisTanimResponseDTO));
    }

    public ResponseEntity<ApiResponse> createİsAkis(İsAkisTanimCreateRequestDTO isAkisTanimCreateRequestDTO,String creatorPerson) {
        İsAkisTanim i̇sAkisTanim = new İsAkisTanim();
        i̇sAkisTanim.setAciklama(isAkisTanimCreateRequestDTO.getAciklama());
        i̇sAkisTanim.setAdı(isAkisTanimCreateRequestDTO.getAdı());
        i̇sAkisTanim.setAktıfversıyon(1);
        //bu kısmın gerçek useri alması gerekiyor sistemden.
        i̇sAkisTanim.setCreatorPerson(creatorPerson);
        i̇sAkisTanim.setCreated(LocalDateTime.now());
        i̇sAkisTanim.setLastUpdated(LocalDateTime.now());
        i̇sAkisTanim.setLastUpdater(creatorPerson);
        i̇sAkisTanim.setLastUpdated(LocalDateTime.now());

        i̇sAkisTanim.setLastUpdated(LocalDateTime.now());

        i̇sAkisTanim.setCreatorPerson(creatorPerson);
        i̇sAkisTanim.setAktif(1);
        i̇sAkisTanim.setAktif_isakis_versiyon_oid(String.valueOf(1));





        İsAkisTanim savedİsAkisTanim = i̇sAkisTanimRepository.save(i̇sAkisTanim);

       İsAkisTanimResponseDTO i̇sAkisTanimResponseDTO = isAkisTanimMapper.fromİsAkisTanim(savedİsAkisTanim);

        return  ResponseEntity.status(200).body(ApiResponse.success(i̇sAkisTanimResponseDTO));
    }
//
//    public ResponseEntity<ApiResponse> findByOid( String isAkisTanimId){
//        Optional<İsAkisTanim> i̇sAkisTanim  = i̇sAkisTanimRepository.findById(oid);
//
//    }

    public ResponseEntity   updateİsAkis(İsAkisTanimUpdateRequestDTO i̇sAkisTanimUpdateRequestDTO, String isAkisTanimId) {
        Optional<İsAkisTanim> i̇sAkisTanim  = i̇sAkisTanimRepository.findById(isAkisTanimId);

        if (isAkisTanimId.isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("İs akisi bulunamadı", Collections.emptyList(),HttpStatus.NO_CONTENT));
        }

        if (i̇sAkisVersiyonRepository.findById(i̇sAkisTanimUpdateRequestDTO.getAktif_isakis_versiyon_oid()).isEmpty()){

            return  ResponseEntity.status(409).body(ApiResponse.error("İs Tanımına Dair akışı versiyonu bulunamadı", Collections.emptyList(),HttpStatus.NO_CONTENT));
    }

        İsAkisTanim existTanim = i̇sAkisTanim.get();

        existTanim.setAdı(i̇sAkisTanimUpdateRequestDTO.getAdı());
        existTanim.setAciklama(i̇sAkisTanimUpdateRequestDTO.getAciklama());
        existTanim.setAktif_isakis_versiyon_oid(i̇sAkisTanimUpdateRequestDTO.getAktif_isakis_versiyon_oid());


        return  ResponseEntity.ok(existTanim);
}

}
