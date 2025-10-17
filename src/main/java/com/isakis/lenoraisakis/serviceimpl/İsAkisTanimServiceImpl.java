package com.isakis.lenoraisakis.serviceimpl;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisTanim.IsAkisTanimCreateRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTanim.IsAkisTanimResponseDTO;
import com.isakis.lenoraisakis.dto.isAkisTanim.IsAkisTanimUpdateRequestDTO;
import com.isakis.lenoraisakis.model.IsAkisAdim;
import com.isakis.lenoraisakis.model.IsAkisTanim;
import com.isakis.lenoraisakis.model.IsAkisVersion;
import com.isakis.lenoraisakis.repository.IsAkisTanimRepository;
import com.isakis.lenoraisakis.repository.İsAkisVersiyonRepository;
import com.isakis.lenoraisakis.service.mapper.İsAkisTanimMapper;
import com.isakis.lenoraisakis.service.İsAkisTanimService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class İsAkisTanimServiceImpl extends BaseServiceImpl<IsAkisTanim, String> implements İsAkisTanimService {
    private  final IsAkisTanimRepository i̇sAkisTanimRepository;
    private final İsAkisTanimMapper isAkisTanimMapper;
    private  final İsAkisVersiyonRepository i̇sAkisVersiyonRepository;

    public İsAkisTanimServiceImpl(JpaRepository<IsAkisTanim, String> repository, IsAkisTanimRepository i̇sAkisTanimRepository, IsAkisTanimRepository i̇sAkisTanimRepository1, İsAkisTanimMapper IsAkisTanimMapper, İsAkisVersiyonRepository i̇sAkisVersiyonRepository) {
        super(repository);
        this.i̇sAkisTanimRepository = i̇sAkisTanimRepository1;
        this.isAkisTanimMapper = IsAkisTanimMapper;
        this.i̇sAkisVersiyonRepository = i̇sAkisVersiyonRepository;
    }

    @Override
    public ResponseEntity<ApiResponse> deployIsAkis(String isAkisTanimOid) {

        Optional<IsAkisVersion> ısAkisVersionOptional = i̇sAkisVersiyonRepository.findByIsAkisTanimOidAndAktıf(isAkisTanimOid);
        if ( ısAkisVersionOptional.isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanim Bulunamamakta", Collections.emptyList(), HttpStatus.CONFLICT));
        }
        IsAkisTanim i̇sAkisTanimExists = ısAkisVersionOptional.get().getIsakistanim();

        List<IsAkisAdim> isakisTanimAdimList = i̇sAkisTanimExists.getİsAkisAdims();

        if (isakisTanimAdimList.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanımı adımlara sahip değil.", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }
        if (isakisTanimAdimList.stream().anyMatch( a -> a.getBaslangıc() == 0)){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Adımları  Start Evente Sahip Değil,deploy edilemez", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }

        if (isakisTanimAdimList.stream().anyMatch( a -> a.getEnd() == 0)){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Adımları  End Evente Sahip Değil,deploy edilemez", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }

        //bpmn modelinin birçok türde elementi var eğer atıyorum bu bi message elementi falan olsa
        //bu durum mantıksız olurdu öyle di durum lenoranın pratiğinde yok gibi.
        if (isakisTanimAdimList.stream().anyMatch( a -> a.getAdim_no_geri() == null || a.getAdim_no_referans() == null)){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Adımlarının  Bağlantıları Olması gerekir deploy edilemez", Collections.emptyList(), HttpStatus.NO_CONTENT));

        }

        if (isakisTanimAdimList.stream().anyMatch( a -> Objects.equals(a.getAdim_no_geri(), a.getAdim_no_referans()))){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Adımlarının  Bağlantıları aynı kaynaktan aynı hedefe doğru olamaz.", Collections.emptyList(), HttpStatus.NO_CONTENT));

        }

        if (isakisTanimAdimList.stream().anyMatch( a -> a.getKosul() == null )){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Adımlarının Koşulu Bulunmadan Deploy edilemez", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }

        //validateleme lazım

        return ResponseEntity.ok(ApiResponse.success("Is Akis Tanim " + i̇sAkisTanimExists.getAdı() +  "Successfully Deployed"));

    }

    @Override
    public ResponseEntity<ApiResponse>  getAllİsAkis(){
            List<IsAkisTanim> i̇sAkisTanimList = i̇sAkisTanimRepository.findAll();

            if (i̇sAkisTanimList.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanim Listesi Boş", new ArrayList<>(), HttpStatus.NO_CONTENT));
            }

            List<IsAkisTanimResponseDTO> i̇sAkisTanimResponseDTO = i̇sAkisTanimList.stream().map(isAkisTanimMapper::fromİsAkisTanim).toList();

            return  ResponseEntity.status(200).body(ApiResponse.success(i̇sAkisTanimResponseDTO));
    }
    @Override
    public ResponseEntity<ApiResponse> createİsAkis(IsAkisTanimCreateRequestDTO isAkisTanimCreateRequestDTO, String creatorPerson) {
        IsAkisTanim i̇sAkisTanim = new IsAkisTanim();
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





        IsAkisTanim savedİsAkisTanim = i̇sAkisTanimRepository.save(i̇sAkisTanim);

       IsAkisTanimResponseDTO i̇sAkisTanimResponseDTO = isAkisTanimMapper.fromİsAkisTanim(savedİsAkisTanim);

        return  ResponseEntity.status(200).body(ApiResponse.success(i̇sAkisTanimResponseDTO));
    }
//
//    public ResponseEntity<ApiResponse> findByOid( String isAkisTanimId){
//        Optional<İsAkisTanim> i̇sAkisTanim  = i̇sAkisTanimRepository.findById(oid);
//
//    }
@Override
public ResponseEntity   updateİsAkis(IsAkisTanimUpdateRequestDTO i̇sAkisTanimUpdateRequestDTO, String isAkisTanimId) {
        Optional<IsAkisTanim> i̇sAkisTanim  = i̇sAkisTanimRepository.findById(isAkisTanimId);

        if (isAkisTanimId.isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("İs akisi bulunamadı", Collections.emptyList(),HttpStatus.NO_CONTENT));
        }

        if (i̇sAkisVersiyonRepository.findById(i̇sAkisTanimUpdateRequestDTO.getAktif_isakis_versiyon_oid()).isEmpty()){

            return  ResponseEntity.status(409).body(ApiResponse.error("İs Tanımına Dair akışı versiyonu bulunamadı", Collections.emptyList(),HttpStatus.NO_CONTENT));
    }

        IsAkisTanim existTanim = i̇sAkisTanim.get();

        existTanim.setAdı(i̇sAkisTanimUpdateRequestDTO.getAdı());
        existTanim.setAciklama(i̇sAkisTanimUpdateRequestDTO.getAciklama());
        existTanim.setAktif_isakis_versiyon_oid(i̇sAkisTanimUpdateRequestDTO.getAktif_isakis_versiyon_oid());


        return  ResponseEntity.ok(existTanim);
}

    public ResponseEntity<ApiResponse> runIsAkis(String isAkisTanimOid) {
        //runlamada ilk instanceyi oluşturup sonrasında  ilk görevi de oluşturmak lazım
        Optional<IsAkisVersion> ısAkisVersionOptional = i̇sAkisVersiyonRepository.findByIsAkisTanimOidAndAktıf(isAkisTanimOid);
        if ( ısAkisVersionOptional.isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanim Bulunamamakta", Collections.emptyList(), HttpStatus.CONFLICT));
        }
        IsAkisTanim i̇sAkisTanimExists = ısAkisVersionOptional.get().getIsakistanim();




    }
}
