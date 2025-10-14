package com.isakis.lenoraisakis.serviceimpl;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisTransfer.IsAkisTransferCreateRequestDTO;
import com.isakis.lenoraisakis.model.IsAkisAdim;
import com.isakis.lenoraisakis.model.IsAkisTransfer;
import com.isakis.lenoraisakis.model.İsAkisTanim;
import com.isakis.lenoraisakis.repository.IsAkisAdimRepository;
import com.isakis.lenoraisakis.repository.İsAkisTanimRepository;
import com.isakis.lenoraisakis.repository.İsAkisTransferRepository;
import com.isakis.lenoraisakis.service.IsAkisTransferService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class İsAkisTransferServiceImpl extends BaseServiceImpl<IsAkisTransfer, String> implements IsAkisTransferService {
        private İsAkisTransferRepository i̇sAkisTransferRepository;
        private  final İsAkisTanimRepository i̇sAkisTanimRepository;
        private  final IsAkisAdimRepository isAkisAdimRepository;
        public İsAkisTransferServiceImpl(JpaRepository<IsAkisTransfer, String> repository, İsAkisTransferRepository i̇sAkisTransferRepository, İsAkisTanimRepository i̇sAkisTanimRepository, İsAkisTanimRepository i̇sAkisTanimRepository1, IsAkisAdimRepository isAkisAdimRepository) {
        super(repository);
        this.i̇sAkisTransferRepository = i̇sAkisTransferRepository;
            this.i̇sAkisTanimRepository = i̇sAkisTanimRepository1;
            this.isAkisAdimRepository = isAkisAdimRepository;
        }
    @Override
    public ResponseEntity<ApiResponse> createIsAkisTransfer(IsAkisTransferCreateRequestDTO isAkisTransferCreateRequestDTO, String isAkısTanımId) {

        Optional<İsAkisTanim> isAkisTanim = i̇sAkisTanimRepository.findById(isAkısTanımId);
        Optional<IsAkisAdim> isAkisAdimSource = isAkisAdimRepository.findById(isAkisTransferCreateRequestDTO.getSourceId());
        Optional<IsAkisAdim> isAkisAdimTarget = isAkisAdimRepository.findById(isAkisTransferCreateRequestDTO.getTargetId());

        if (isAkisAdimSource.get().getBaslangıc() == 1 ) {
            return ResponseEntity.status(409).body(ApiResponse.error(
                    "Başlangıç Eventine Kaynak Atanamaz",null, HttpStatus.NO_CONTENT));

        }
        if (isAkisAdimSource.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("Kaynak Adım Bulunamadı",null, HttpStatus.NO_CONTENT));
        }
        IsAkisAdim isAkisAdimExistsSource = isAkisAdimSource.get();

        if (isAkisAdimTarget.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("Hedef Adım Bulunamadı",null, HttpStatus.NO_CONTENT));
        }
        IsAkisAdim isAkisAdimTargetExist = isAkisAdimTarget.get();

        if (i̇sAkisTransferRepository.existBySourceAndTargetId(isAkisAdimExistsSource.getOid(),isAkisAdimTargetExist.getOid()))  {
            return ResponseEntity.status(409).body(ApiResponse.error("Halihazırd bu iki adım arasında transfer bulunmktadır", Collections.emptyList(), HttpStatus.NO_CONTENT));

        }
        if (isAkisTanim.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Boş", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }
        İsAkisTanim isAkisTanimExists = isAkisTanim.get();

        IsAkisTransfer isAkisTransfer = new IsAkisTransfer();

        if (isAkisTransferCreateRequestDTO.getSourceId().isEmpty() || isAkisTransferCreateRequestDTO.getTargetId().isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("Hedef Ve Kaynak Id girilmelidir",Collections.emptyList(), HttpStatus.NO_CONTENT));
        }

        if (isAkisAdimRepository.findById(isAkisTransferCreateRequestDTO.getSourceId()).isEmpty() || isAkisAdimRepository.findById(isAkisTransferCreateRequestDTO.getTargetId()).isEmpty() ){
            return  ResponseEntity.status(409).body(ApiResponse.error("Hedef Ve Kaynak Id Bir İş Adımına Ait Olmalıdır",Collections.emptyList(), HttpStatus.NO_CONTENT));
        }

        if (isAkisTransferCreateRequestDTO.getSourceId().equals(isAkisTransferCreateRequestDTO.getTargetId())) {
            return  ResponseEntity.status(409).body(
                    ApiResponse.error(
                            "Kaynak iş adımı hedef iş adımına eşit olamaz.",
                            Collections.emptyList(),
                            HttpStatus.NO_CONTENT));

        }

        isAkisTransfer.setAciklama(isAkisTransferCreateRequestDTO.getAciklama());
          isAkisTransfer.setKomut(isAkisTransferCreateRequestDTO.getKomut());
        isAkisTransfer.setTuru(isAkisTransferCreateRequestDTO.getTuru());
        isAkisTransfer.setIsakis_versiyon_oid(isAkisTanimExists.getAktif_isakis_versiyon_oid());
        isAkisTransfer.setKosul(isAkisTransferCreateRequestDTO.getKosul());
        isAkisTransfer.setAciklama(isAkisTransferCreateRequestDTO.getAciklama());

        isAkisTransfer.setHedef_ad_aciklama(isAkisAdimTargetExist.getAcıklama());
        isAkisTransfer.setKosul_aciklama(isAkisTransferCreateRequestDTO.getKosul_aciklama());

        List<IsAkisAdim> isAkisAdims = isAkisTanimExists.getİsAkisAdims();
        if (isAkisAdims.isEmpty()){
            return  ResponseEntity.status(409).body(ApiResponse.error("İş Akışı Adım Listesi Boş    ",Collections.emptyList(),HttpStatus.NO_CONTENT));
        }
        Integer maxAdimNo = Collections.max(isAkisAdims.stream().filter(Objects::nonNull).map(a -> a.getAdim_no()).filter(Objects::nonNull)

                .toList());


        if (isAkisAdimExistsSource.getBaslangıc() == 1){
            isAkisTransfer.setSıra(1);
        }
        else{

        Optional<IsAkisTransfer> isAkisTransferOptional = i̇sAkisTransferRepository.getMaxSiraİsAkisTransfer();
        if (isAkisTransferOptional.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İş Akışı Daha başlatılamadı",Collections.emptyList(),HttpStatus.NO_CONTENT));
        }

        isAkisTransfer.setSıra(isAkisTransferOptional.get().getSıra() + 1);
        }

        //adim numarasını işakışının adımlarında en büyük sıraya sahibin bi sonrakisi yapılabilir mi acaba tam emin değilim bu kısımda
        if (isAkisAdimExistsSource.getAdim_no() == null || isAkisAdimExistsSource.getAdim_no() == 1) {
            isAkisAdimExistsSource.setAdim_no(maxAdimNo + 1);
        }




        //adım no kısmını da yapabilmek lazım
        //adim numarasını işakışının adımlarında en büyük sıraya sahibin bi sonrakisi yapılabilir mi acaba tam emin değilim bu kısımda
        if (isAkisAdimTargetExist.getAdim_no() == null || isAkisAdimTargetExist.getAdim_no() == 0){
            isAkisAdimTargetExist.setAdim_no(isAkisAdimExistsSource.getAdim_no() + 1);
        }
        isAkisTransfer.setIsakis_adim_no(isAkisAdimExistsSource.getAdim_no());
        isAkisTransfer.setSonraki_isakis_adim_no(isAkisAdimTargetExist.getAdim_no());


        isAkisAdimExistsSource.setAdim_no_referans(isAkisAdimTargetExist.getAdim_no());

        isAkisAdimTargetExist.setAdim_no_geri(isAkisAdimExistsSource.getAdim_no());


        isAkisTransfer.setKaynakAdim(isAkisAdimExistsSource);
        isAkisTransfer.setHedefAdim(isAkisAdimTargetExist);


        IsAkisTransfer saved = i̇sAkisTransferRepository.save(isAkisTransfer);


        return  ResponseEntity.ok(ApiResponse.success("Sequence Order Objesi kuruldu"));
    }
}
