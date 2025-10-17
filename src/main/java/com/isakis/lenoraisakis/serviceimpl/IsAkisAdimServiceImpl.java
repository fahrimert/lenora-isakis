package com.isakis.lenoraisakis.serviceimpl;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisAdim.IsAkisAdimCreateDTO;
import com.isakis.lenoraisakis.dto.isAkisAdim.IsAkisAdimInitializeRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisAdim.IsAkisAdimResponseDTO;
import com.isakis.lenoraisakis.dto.isAkisAdim.IsAkisAdimUpdateRequestDTO;
import com.isakis.lenoraisakis.model.IsAkisAdim;
import com.isakis.lenoraisakis.model.IsAkisTanim;
import com.isakis.lenoraisakis.model.IsAkisTransfer;
import com.isakis.lenoraisakis.repository.IsAkisAdimRepository;
import com.isakis.lenoraisakis.repository.IsAkisTanimRepository;
import com.isakis.lenoraisakis.repository.İsAkisTransferRepository;
import com.isakis.lenoraisakis.service.IsAkisAdimService;
import com.isakis.lenoraisakis.service.mapper.İsAkisAdimMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class IsAkisAdimServiceImpl implements IsAkisAdimService {
    private  final IsAkisTanimRepository i̇sAkisTanimRepository;
    private  final  IsAkisAdimRepository isAkisAdimRepository;
    private final İsAkisAdimMapper i̇sAkisAdimMapper;
    private final İsAkisTransferRepository isAkisTransferRepository;

    public IsAkisAdimServiceImpl(IsAkisAdimRepository isAkisAdimRepository,  IsAkisTanimRepository i̇sAkisTanimRepository, İsAkisAdimMapper i̇sAkisAdimMapper, İsAkisTransferRepository isAkisTransferRepository) {
        this.isAkisAdimRepository = isAkisAdimRepository;
        this.i̇sAkisTanimRepository = i̇sAkisTanimRepository;
        this.i̇sAkisAdimMapper = i̇sAkisAdimMapper;
        this.isAkisTransferRepository = isAkisTransferRepository;
    }
    @Override
    public ResponseEntity<ApiResponse> initalizeIsAdım(IsAkisAdimInitializeRequestDTO i̇sAkisAdimInitializeRequestDTO, String kullaniciOid, String isAkisTanimOid){
        Optional<IsAkisTanim> isAkisTanim= i̇sAkisTanimRepository.findById(isAkisTanimOid);

        if (isAkisTanim.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Boş", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }

        IsAkisTanim isAkisTanimExists = isAkisTanim.get();

        if (isAkisTanimExists.getİsAkisAdims().stream().anyMatch( a -> a.getBaslangıc() == 1)){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Adımları Zaten Start Evente Sahip", Collections.emptyList(), HttpStatus.NO_CONTENT));

        }

        IsAkisAdim isAkisAdim = new IsAkisAdim();
//        isAkisAdim.setAdim_no(1);
        isAkisAdim.setIsakistanim(isAkisTanimExists);
        isAkisAdim.setBirim_oid(i̇sAkisAdimInitializeRequestDTO.getBirim_oid());
        isAkisAdim.setBirim_tipi_oid(i̇sAkisAdimInitializeRequestDTO.getBirim_tipi_oid());
        isAkisAdim.setIsakıs_versıon_oid(isAkisTanimExists.getAktif_isakis_versiyon_oid());
        isAkisAdim.setBaslangıc(1);
        isAkisAdim.setTuru(0); // entityde tanımını yaptım

        isAkisAdim.setX(i̇sAkisAdimInitializeRequestDTO.getX());
        isAkisAdim.setY(i̇sAkisAdimInitializeRequestDTO.getY());


        // isakis_grup_oid yi setlemedim ne bilmiyorum çünkü
        isAkisAdim.setKullanıcı_oid(kullaniciOid);
        isAkisAdim.setAcıklama(i̇sAkisAdimInitializeRequestDTO.getAciklama());
        isAkisAdim.setAkıs_acıklama(isAkisTanimExists.getAciklama());
        //versiyon eklemedim şimdilik iş akış adımlarına özel versiyon var mı bilmiyorum çünkü.

        IsAkisAdim savedIsAkisAdim = isAkisAdimRepository.save(isAkisAdim);

        return  ResponseEntity.status(200).body(ApiResponse.success("Adım Successfully Initialized"));
    }

    @Override
    public ResponseEntity<ApiResponse> createIsAdim(IsAkisAdimCreateDTO isAkisAdimCreateRequestDTO, String kullaniciOid, String isAkisTanimOid) {
        Optional<IsAkisTanim> isAkisTanim= i̇sAkisTanimRepository.findById(isAkisTanimOid);

        if (isAkisTanim.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Boş", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }
        IsAkisTanim isAkisTanimExists = isAkisTanim.get();

        IsAkisAdim isAkisAdim = new IsAkisAdim();
        isAkisAdim.setIsakistanim(isAkisTanimExists);
        isAkisAdim.setBirim_oid(isAkisAdimCreateRequestDTO.getBirim_oid());
        isAkisAdim.setBirim_tipi_oid(isAkisAdimCreateRequestDTO.getBirim_tipi_oid());
        isAkisAdim.setIsakıs_versıon_oid(isAkisTanimExists.getAktif_isakis_versiyon_oid());
        isAkisAdim.setBaslangıc(0);
        isAkisAdim.setEnd(0);
        isAkisAdim.setTuru(isAkisAdim.getTuru()); // entityde tanımını yaptım
        // isakis_grup_oid yi setlemedim ne bilmiyorum çünkü
        isAkisAdim.setKullanıcı_oid(kullaniciOid);
        isAkisAdim.setAcıklama(isAkisAdimCreateRequestDTO.getAciklama());
        isAkisAdim.setAkıs_acıklama(isAkisTanimExists.getAciklama());
        //x ve y window x ve y leri bunlara da validasyon gerekiyor.
        isAkisAdim.setX(isAkisAdimCreateRequestDTO.getX());
        isAkisAdim.setY(isAkisAdimCreateRequestDTO.getY());

        //versiyon eklemedim şimdilik iş akış adımlarına özel versiyon var mı bilmiyorum çünkü.

        IsAkisAdim savedIsAkisAdim = isAkisAdimRepository.save(isAkisAdim);

        return  ResponseEntity.status(200).body(ApiResponse.success("Adım Successfully Created"));
    }

    @Override
    public ResponseEntity<ApiResponse> endIsAdim(IsAkisAdimInitializeRequestDTO isAkisAdimInitializeRequestDTO, String kullaniciOid, String isAkisTanimOid) {
        Optional<IsAkisTanim> isAkisTanim= i̇sAkisTanimRepository.findById(isAkisTanimOid);

        if (isAkisTanim.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Boş", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }

        IsAkisTanim isAkisTanimExists = isAkisTanim.get();

        if (isAkisTanimExists.getİsAkisAdims().stream().anyMatch( a -> a.getEnd() == 1)){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Adımları Zaten End Evente Sahip", Collections.emptyList(), HttpStatus.NO_CONTENT));

        }

        IsAkisAdim isAkisAdim = new IsAkisAdim();
//        isAkisAdim.setAdim_no(1);
        isAkisAdim.setIsakistanim(isAkisTanimExists);
        isAkisAdim.setBirim_oid(isAkisAdimInitializeRequestDTO.getBirim_oid());
        isAkisAdim.setBirim_tipi_oid(isAkisAdimInitializeRequestDTO.getBirim_tipi_oid());
        isAkisAdim.setIsakıs_versıon_oid(isAkisTanimExists.getAktif_isakis_versiyon_oid());
        isAkisAdim.setEnd(1);
        isAkisAdim.setTuru(0); // entityde tanımını yaptım

        isAkisAdim.setX(isAkisAdimInitializeRequestDTO.getX());
        isAkisAdim.setY(isAkisAdimInitializeRequestDTO.getY());


        // isakis_grup_oid yi setlemedim ne bilmiyorum çünkü
        isAkisAdim.setAcıklama(isAkisAdimInitializeRequestDTO.getAciklama());
        isAkisAdim.setAkıs_acıklama(isAkisTanimExists.getAciklama());
        //versiyon eklemedim şimdilik iş akış adımlarına özel versiyon var mı bilmiyorum çünkü.

        IsAkisAdim savedIsAkisAdim = isAkisAdimRepository.save(isAkisAdim);

        return  ResponseEntity.status(200).body(ApiResponse.success("End Adım Successfully Created"));

    }

    @Override
    public ResponseEntity<ApiResponse> updateIsAdim(IsAkisAdimUpdateRequestDTO isAkisAdimUpdateRequestDTO, String kullaniciOid, String isAkisAdimOid) {
        Optional<IsAkisAdim> isAkisAdim = isAkisAdimRepository.findById(isAkisAdimOid);

        if (isAkisAdim.isEmpty()){
            return ResponseEntity.status(409)
                    .body(ApiResponse.error("İs Akis Adım Listesi Boş"
                            , Collections.emptyList(), HttpStatus.NO_CONTENT));
        }
        IsAkisAdim isAkisAdimExists = isAkisAdim.get();

        isAkisAdimExists.setBirim_tipi_oid(isAkisAdimExists.getBirim_tipi_oid());
        isAkisAdimExists.setBirim_oid(isAkisAdimExists.getBirim_oid());
        isAkisAdimExists.setAcıklama(isAkisAdimExists.getAcıklama());
        isAkisAdimExists.setIsakis_grup_oid(isAkisAdimExists.getIsakis_grup_oid());

        isAkisAdimExists.setX(isAkisAdimExists.getX());
        isAkisAdimExists.setY(isAkisAdimExists.getY());
        isAkisAdimExists.setTuru(isAkisAdimExists.getTuru());
        isAkisAdimExists.setKosul(isAkisAdimExists.getKosul());
        isAkisAdimExists.setEk(isAkisAdimExists.getEk());

    return ResponseEntity.ok(ApiResponse.success("Is adimi başarıyla güncellendi."));
    }

    @Override
    public ResponseEntity<ApiResponse> getIsAdımsOfIsAkısTanım(String isAkisTanimOid) {
        Optional<IsAkisTanim> IsAkisTanim= i̇sAkisTanimRepository.findById(isAkisTanimOid);

        if (IsAkisTanim.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Boş", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }
        IsAkisTanim isAkisTanimExists = IsAkisTanim.get();


        List<IsAkisAdimResponseDTO> i̇sAkisAdimResponseDTO = isAkisTanimExists.getİsAkisAdims().stream()
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


        IsAkisAdimResponseDTO i̇sAkisAdimResponseDTO = i̇sAkisAdimMapper.fromİsAdim(isAkisAdimExists);

        return ResponseEntity.ok(ApiResponse.success(i̇sAkisAdimResponseDTO));
    }

    @Override

    public ResponseEntity<ApiResponse> deleteIsAkisAdim(String isAdimOid)   {
        Optional<IsAkisAdim> isAkisAdim = isAkisAdimRepository.findById(isAdimOid);

        if (isAkisAdim.isEmpty()){
   return ResponseEntity.status(409).body(ApiResponse.error(
           "İs Akis Adimi Bulunmamakta",
           null,
           HttpStatus.NO_CONTENT));
        }
        IsAkisAdim isAkisAdimExists = isAkisAdim.get();

        if (isAkisAdimExists.getAdim_no_referans() == null &&  isAkisAdimExists.getAdim_no_geri()== null){
            isAkisAdimRepository.delete(isAkisAdimExists);
            return  ResponseEntity.ok(ApiResponse.success("Successfuly Deleted"));
        }


        if (isAkisAdimExists.getAdim_no_referans() == null){
            IsAkisTransfer isAkisTransferFindedByKaynakOidAndGeri = isAkisTransferRepository.
                    findByKaynakOidAndReferansNo(
                            isAkisAdimExists.getAdim_no_geri(),
                            isAkisAdimExists.getAdim_no());
            isAkisTransferRepository.delete(isAkisTransferFindedByKaynakOidAndGeri);
            isAkisAdimRepository.delete(isAkisAdimExists);
            return  ResponseEntity.ok(ApiResponse.success("Successfuly Deleted"));

        }
        else{
            //start eventi sildiğin zaman bu işlemleri yapamıyorsun aslında direkt olarak
            // sonraki transferi silmiş oluyorsun sadece
            if (isAkisAdimExists.getBaslangıc() == 1 ){
                //sadece transferi ve bunu sil
                isAkisTransferRepository.deleteAllByIsKaynakAdimOid(isAkisAdimExists.getOid());
                isAkisAdimRepository.delete(isAkisAdimExists);
                return  ResponseEntity.ok(ApiResponse.success("Successfuly Start Event Deleted"));

            }
            if (isAkisAdimExists.getEnd() == 1 ){
                //sadece transferi ve bunu sil
                isAkisTransferRepository.deleteAllByIsHedefAdimOid(isAkisAdimExists.getOid());
                isAkisAdimRepository.delete(isAkisAdimExists);
                return  ResponseEntity.ok(ApiResponse.success("Successfuly End Event Deleted"));

            }


            else {
                //3-4-5 dimi
                //4 -5 arası transferi silsem
                //3- 4 arası transferi artık 3- 5 şeklidne yapsma

                //eğer 3 -4 5 ve ayrıca 4 e 2 de geliyorsa  4 ü silince

                //bi servis görev yapma gibi servisler de olacak yani.

                System.out.println("GelenAkısSize" + isAkisAdimExists.getGelenAkış().size());
                System.out.println("GidenAkışSize" +isAkisAdimExists.getGidenAkış().size());

//                            if (isAkisAdimExists.getGelenAkış().size() > 1  || isAkisAdimExists.getGidenAkış().size() > 1 ){
//            isAkisTransferRepository.deleteAllByKaynakAdimOidOrHedefAdimOid(isAkisAdimExists.getOid()); //bu yaklaşım mantıklı mı bilmiyorum
//            }

                System.out.println(isAkisAdimExists.getAdim_no());
                System.out.println(isAkisAdimExists.getAdim_no_referans());

                IsAkisTransfer isAkisTransferFindedByKaynakOidToReferans = isAkisTransferRepository.
                        findByKaynakOidAndReferansNo(
                                isAkisAdimExists.getAdim_no(),
                                isAkisAdimExists.getAdim_no_referans());
                System.out.println("isAkisTransferFindedByKaynakOidToReferans" + isAkisTransferFindedByKaynakOidToReferans);

                isAkisTransferRepository.delete(isAkisTransferFindedByKaynakOidToReferans);


                IsAkisTransfer isAkisTransfer = isAkisTransferRepository.
                        findByKaynakOidAndReferansNo(
                                isAkisAdimExists.getAdim_no_geri(),
                                isAkisAdimExists.getAdim_no());
                isAkisTransfer.setSonraki_isakis_adim_no(isAkisAdimExists.getAdim_no_referans());
                IsAkisAdim isAkisAdimReferansNew = isAkisAdimRepository.findByAdimNo(isAkisAdimExists.getAdim_no_referans());
                isAkisTransfer.setHedefAdim(isAkisAdimReferansNew);

                isAkisTransferRepository.save(isAkisTransfer);

                //burası mesela 2-3 -4 diyelim adımlar 3 ü sildim artık 2 nin referansını 4 e verme kısmını kaydetme iş adım modeli manasında
                IsAkisAdim isAkisAdimBefore = isAkisTransfer.getKaynakAdim();
                isAkisAdimBefore.setAdim_no_referans(isAkisAdimExists.getAdim_no_referans());
                isAkisAdimRepository.save(isAkisAdimBefore);

                IsAkisAdim isAkisAdimAfter = isAkisTransfer.getHedefAdim();
                isAkisAdimAfter.setAdim_no_geri(isAkisAdimExists.getAdim_no_geri());
                isAkisAdimRepository.save(isAkisAdimBefore);

                isAkisAdimRepository.delete(isAkisAdimExists);


                //bu kaynak oid ile yani benim oid ile benim referansımın transferini alıp onu kaldırıyor

            }  }
        return ResponseEntity.ok(ApiResponse.success("İs Akis Adimi Basariyla Silindi"));


    }

    @Override
    public ResponseEntity<ApiResponse> getAllİsAdims(){
        List<IsAkisAdim> isAkisAdimList = isAkisAdimRepository.findAll();

        if (isAkisAdimList.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Adim Listesi Boş", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }
        List<IsAkisAdimResponseDTO> isAdiimDto = isAkisAdimList.stream().map(i̇sAkisAdimMapper::fromİsAdim).toList();

        return  ResponseEntity.status(200).body(ApiResponse.success(isAdiimDto));
    }

    @Override
    public ResponseEntity<ApiResponse> deleteAllByIsAkisTanimOid(String isAkisTanimOid){

        if (isAkisAdimRepository.findAll().isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error(
                    "İs akis adim listesi boş",Collections.emptyList(),HttpStatus.NO_CONTENT
            ));
        }
        Optional<IsAkisTanim> i̇sAkisTanimOptional = i̇sAkisTanimRepository.findById(isAkisTanimOid);

        if (i̇sAkisTanimOptional.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanim Optional empty",Collections.emptyList(),HttpStatus.NO_CONTENT));
        }

        System.out.println(i̇sAkisTanimOptional.get());
        try {
            isAkisAdimRepository.deleteAllByIsAkisTanimOid(isAkisTanimOid);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return  ResponseEntity.status(200).body(ApiResponse.success("İs akis adimlari successfully deleted"));
    }

    @Override
    public ResponseEntity<ApiResponse> updateKosul(String isAkisAdimOid ,String kosul) {
        Optional<IsAkisAdim> isAkisAdimOptional =  isAkisAdimRepository.findById(isAkisAdimOid);
        if (isAkisAdimOptional.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("Is akis adim bulunamadı",Collections.emptyList(),HttpStatus.NO_CONTENT));
        }
        IsAkisAdim existsAdim = isAkisAdimOptional.get();

        existsAdim.setKosul(kosul);

        return  ResponseEntity.ok(ApiResponse.success("Kosul Successfully updated"));

    }

    @Override
    public ResponseEntity<ApiResponse> deleteKosul(String isAdimOid) {
        Optional<IsAkisAdim> isAkisAdimOptional =  isAkisAdimRepository.findById(isAdimOid);
        if (isAkisAdimOptional.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("Is akis adim bulunamadı",Collections.emptyList(),HttpStatus.NO_CONTENT));
        }
        IsAkisAdim existsAdim = isAkisAdimOptional.get();

        existsAdim.setKosul(null);

        return  ResponseEntity.ok(ApiResponse.success("Kosul Successfully deleted"));
    }


}
