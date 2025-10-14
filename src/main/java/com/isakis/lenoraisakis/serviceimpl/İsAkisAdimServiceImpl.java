package com.isakis.lenoraisakis.serviceimpl;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisAdim.İsAkisAdimCreateDTO;
import com.isakis.lenoraisakis.dto.isAkisAdim.İsAkisAdimInitializeRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisAdim.İsAkisAdimResponseDTO;
import com.isakis.lenoraisakis.dto.isAkisTanim.İsAkisTanimCreateRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTanim.İsAkisTanimResponseDTO;
import com.isakis.lenoraisakis.model.IsAkisAdim;
import com.isakis.lenoraisakis.model.IsAkisTransfer;
import com.isakis.lenoraisakis.model.İsAkisTanim;
import com.isakis.lenoraisakis.repository.IsAkisAdimRepository;
import com.isakis.lenoraisakis.repository.İsAkisTanimRepository;
import com.isakis.lenoraisakis.repository.İsAkisTransferRepository;
import com.isakis.lenoraisakis.service.mapper.İsAkisAdimMapper;
import com.isakis.lenoraisakis.service.mapper.İsAkisTanimMapper;
import com.isakis.lenoraisakis.service.İsAkisAdimService;
import com.isakis.lenoraisakis.service.İsAkisTanimService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class İsAkisAdimServiceImpl implements İsAkisAdimService {
    private  final İsAkisTanimRepository i̇sAkisTanimRepository;
    private  final  IsAkisAdimRepository isAkisAdimRepository;
    private final İsAkisAdimMapper i̇sAkisAdimMapper;
    private final İsAkisTransferRepository isAkisTransferRepository;

    public İsAkisAdimServiceImpl(IsAkisAdimRepository isAkisAdimRepository, İsAkisTanimRepository i̇sAkisTanimRepository, İsAkisAdimMapper i̇sAkisAdimMapper, İsAkisTransferRepository isAkisTransferRepository) {
        this.isAkisAdimRepository = isAkisAdimRepository;
        this.i̇sAkisTanimRepository = i̇sAkisTanimRepository;
        this.i̇sAkisAdimMapper = i̇sAkisAdimMapper;
        this.isAkisTransferRepository = isAkisTransferRepository;
    }
    public ResponseEntity<ApiResponse> initalizeIsAdım(İsAkisAdimInitializeRequestDTO i̇sAkisAdimInitializeRequestDTO, String kullaniciOid, String isAkisTanimOid){
        Optional<İsAkisTanim> isAkisTanim = i̇sAkisTanimRepository.findById(isAkisTanimOid);

        if (isAkisTanim.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Boş", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }
        İsAkisTanim isAkisTanimExists = isAkisTanim.get();

        IsAkisAdim isAkisAdim = new IsAkisAdim();
        isAkisAdim.setAdim_no(1); //baslangıc sırası 1
        isAkisAdim.setIsakistanim(isAkisTanimExists);
        isAkisAdim.setBirim_oid(i̇sAkisAdimInitializeRequestDTO.getBirim_oid());
        isAkisAdim.setBirim_tipi_oid(i̇sAkisAdimInitializeRequestDTO.getBirim_tipi_oid());
        isAkisAdim.setIsakıs_versıon_oid(isAkisTanimExists.getAktif_isakis_versiyon_oid());
        isAkisAdim.setBaslangıc(1);
        isAkisAdim.setTuru(0); // entityde tanımını yaptım
        // isakis_grup_oid yi setlemedim ne bilmiyorum çünkü
        isAkisAdim.setKullanıcı_oid(kullaniciOid);
        isAkisAdim.setAcıklama(i̇sAkisAdimInitializeRequestDTO.getAciklama());
        isAkisAdim.setAkıs_acıklama(isAkisTanimExists.getAciklama());
        //versiyon eklemedim şimdilik iş akış adımlarına özel versiyon var mı bilmiyorum çünkü.

        IsAkisAdim savedIsAkisAdim = isAkisAdimRepository.save(isAkisAdim);

        return  ResponseEntity.status(200).body(ApiResponse.success("Adım Successfully Initialized"));
    }

    @Override
    public ResponseEntity<ApiResponse> createIsAdim(İsAkisAdimCreateDTO isAkisAdimCreateRequestDTO, String kullaniciOid, String isAkisTanimOid) {
        Optional<İsAkisTanim> isAkisTanim = i̇sAkisTanimRepository.findById(isAkisTanimOid);

        if (isAkisTanim.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Boş", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }
        İsAkisTanim isAkisTanimExists = isAkisTanim.get();

        IsAkisAdim isAkisAdim = new IsAkisAdim();
        isAkisAdim.setIsakistanim(isAkisTanimExists);
        isAkisAdim.setBirim_oid(isAkisAdimCreateRequestDTO.getBirim_oid());
        isAkisAdim.setBirim_tipi_oid(isAkisAdimCreateRequestDTO.getBirim_tipi_oid());
        isAkisAdim.setIsakıs_versıon_oid(isAkisTanimExists.getAktif_isakis_versiyon_oid());
        isAkisAdim.setBaslangıc(0);
        isAkisAdim.setTuru(isAkisAdim.getTuru()); // entityde tanımını yaptım
        // isakis_grup_oid yi setlemedim ne bilmiyorum çünkü
        isAkisAdim.setKullanıcı_oid(kullaniciOid);
        isAkisAdim.setAcıklama(isAkisAdimCreateRequestDTO.getAciklama());
        isAkisAdim.setAkıs_acıklama(isAkisTanimExists.getAciklama());
        //versiyon eklemedim şimdilik iş akış adımlarına özel versiyon var mı bilmiyorum çünkü.

        IsAkisAdim savedIsAkisAdim = isAkisAdimRepository.save(isAkisAdim);

        return  ResponseEntity.status(200).body(ApiResponse.success("Adım Successfully Created"));
    }

    @Override
    public ResponseEntity<ApiResponse> getIsAdımsOfIsAkısTanım(String isAkisTanimOid) {
        Optional<İsAkisTanim> isAkisTanim = i̇sAkisTanimRepository.findById(isAkisTanimOid);

        if (isAkisTanim.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Boş", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }
        İsAkisTanim isAkisTanimExists = isAkisTanim.get();


        List<İsAkisAdimResponseDTO> i̇sAkisAdimResponseDTO = isAkisTanimExists.getİsAkisAdims().stream()
                .map(i̇sAkisAdimMapper::fromİsAdim).toList();

        return ResponseEntity.ok(ApiResponse.success(i̇sAkisAdimResponseDTO));
    }


    @Override
    public ResponseEntity<ApiResponse> getIsAdımOfIsAkısTanım(String isAdimOid) {
        Optional<IsAkisAdim> isAkisAdim = isAkisAdimRepository.findById(isAdimOid);

        if (isAkisAdim.isEmpty()){
            return ResponseEntity.status(409)
                    .body(ApiResponse.error("İs Akis Adım Listesi Boş"
                            , Collections.emptyList(), HttpStatus.NO_CONTENT));
        }
        IsAkisAdim isAkisAdimExists = isAkisAdim.get();


        İsAkisAdimResponseDTO i̇sAkisAdimResponseDTO = i̇sAkisAdimMapper.fromİsAdim(isAkisAdimExists);

        return ResponseEntity.ok(ApiResponse.success(i̇sAkisAdimResponseDTO));
    }

    @Override
    public ResponseEntity<ApiResponse> deleteIsAkisAdim(String isAdimOid) {
        Optional<IsAkisAdim> isAkisAdim = isAkisAdimRepository.findById(isAdimOid);

        if (isAkisAdim.isEmpty()){
   return ResponseEntity.status(409).body(ApiResponse.error(
           "İs Akis Adimi Bulunmamakta",
           null,
           HttpStatus.NO_CONTENT));
        }
        IsAkisAdim isAkisAdimExists = isAkisAdim.get();
        if (isAkisAdimExists.getAdim_no().equals(null)  ){
            isAkisAdimRepository.delete(isAkisAdimExists);
        }
        else{
            //3-4-5 dimi
            //4 -5 arası transferi silsem
            //3- 4 arası transferi artık 3- 5 şeklidne yapsma
            //zaten 1. sıra olacağı için 3-4 arası sıra 1 olarak kalmaya adevam eder
            //sonra da adımı silerim sırayla oynamamış olurum

            //eğer 3 -4 5 ve ayrıca 4 e 2 de geliyorsa  4 ü silince



            if (isAkisAdimExists.getGelenAkış().size() > 0  || isAkisAdimExists.getGidenAkış().size() > 0 ){
            isAkisTransferRepository.deleteAllByKaynakAdimOidOrHedefAdimOid(isAkisAdimExists.getOid()); //bu yaklaşım mantıklı mı bilmiyorum
            }

            IsAkisTransfer isAkisTransferFindedByKaynakOidToReferans = isAkisTransferRepository.
                    findByKaynakOidAndReferansNo(
                            isAkisAdimExists.getAdim_no_geri() ,
                            isAkisAdimExists.getAdim_no_referans());

            isAkisTransferRepository.delete(isAkisTransferFindedByKaynakOidToReferans);

            IsAkisTransfer isAkisTransfer = isAkisTransferRepository.
                    findBySıra(
                            isAkisTransferFindedByKaynakOidToReferans.getSıra() -1 );
            isAkisTransfer.setSonraki_isakis_adim_no(isAkisAdimExists.getAdim_no_referans());
            IsAkisAdim isAkisAdimReferansNew = isAkisAdimRepository.findByAdimNo(isAkisAdimExists.getAdim_no_referans());
            isAkisTransfer.setHedefAdim(isAkisAdimReferansNew);


            isAkisAdimRepository.delete(isAkisAdimExists);


        //bu kaynak oid ile yani benim oid ile benim referansımın transferini alıp onu kaldırıyor

        }


    }


//    public ResponseEntity<ApiResponse> getAllİsAdims(){
//        List<IsAkisAdim> isAkisAdimList = i̇sAkisAdimRepository.findAll();
//
//        if (isAkisAdimList.isEmpty()){
//            return ResponseEntity.noContent(ApiResponse.error("İs Akis Adim Listesi Boş", Optional.empty(), HttpStatus.NO_CONTENT));
//        }
//
//        List<İsAkisTanimResponseDTO> i̇sAkisTanimResponseDTO = isAkisAdimList.stream().map(isAkisTanimMapper::fromİsAkisTanim).toList();
//
//        return  ResponseEntity.status(200).body(ApiResponse.success(i̇sAkisTanimResponseDTO));
//    }
//

}
