package com.isakis.lenoraisakis.serviceimpl;

import com.isakis.lenoraisakis.dto.ApiResponse;
import com.isakis.lenoraisakis.dto.isAkisTransfer.IsAkisTransferCreateRequestDTO;
import com.isakis.lenoraisakis.dto.isAkisTransfer.IsAkisTransferUpdateDTO;
import com.isakis.lenoraisakis.model.IsAkisAdim;
import com.isakis.lenoraisakis.model.IsAkisTanim;
import com.isakis.lenoraisakis.model.IsAkisTransfer;
import com.isakis.lenoraisakis.repository.IsAkisAdimRepository;
import com.isakis.lenoraisakis.repository.IsAkisTanimRepository;
import com.isakis.lenoraisakis.repository.İsAkisTransferRepository;
import com.isakis.lenoraisakis.service.IsAkisTransferService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class İsAkisTransferServiceImpl extends BaseServiceImpl<IsAkisTransfer, String> implements IsAkisTransferService {
    private İsAkisTransferRepository ısAkisTransferRepository;
        private  final IsAkisTanimRepository i̇sAkisTanimRepository;
        private  final IsAkisAdimRepository isAkisAdimRepository;
        public İsAkisTransferServiceImpl(JpaRepository<IsAkisTransfer, String> repository, İsAkisTransferRepository
                                                 ısAkisTransferRepository
                , IsAkisTanimRepository i̇sAkisTanimRepository, IsAkisTanimRepository i̇sAkisTanimRepository1, IsAkisAdimRepository isAkisAdimRepository) {
        super(repository);
        this.ısAkisTransferRepository = ısAkisTransferRepository;
            this.i̇sAkisTanimRepository = i̇sAkisTanimRepository;
            this.isAkisAdimRepository = isAkisAdimRepository;
        }
    @Override
    public ResponseEntity<ApiResponse> createIsAkisTransfer(IsAkisTransferCreateRequestDTO isAkisTransferCreateRequestDTO, String isAkısTanımId) {

        Optional<IsAkisTanim> isAkisTanim = i̇sAkisTanimRepository.findById(isAkısTanımId);
        Optional<IsAkisAdim> isAkisAdimSource = isAkisAdimRepository.findById(isAkisTransferCreateRequestDTO.getSourceId());
        Optional<IsAkisAdim> isAkisAdimTarget = isAkisAdimRepository.findById(isAkisTransferCreateRequestDTO.getTargetId());

        if (isAkisAdimTarget.get().getBaslangıc() == 1 ) {
            return ResponseEntity.status(409).body(ApiResponse.error(
                    "Başlangıç Eventine Kaynak Atanamaz",null, HttpStatus.NO_CONTENT));

        }
        if (isAkisAdimSource.get().getEnd() == 1 ) {
            return ResponseEntity.status(409).body(ApiResponse.error(
                    "End Eventine Hedef Atanamaz",null, HttpStatus.NO_CONTENT));

        }
        if (isAkisAdimSource.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("Kaynak Adım Bulunamadı",null, HttpStatus.NO_CONTENT));
        }
        IsAkisAdim isAkisAdimExistsSource = isAkisAdimSource.get();

        if (isAkisAdimTarget.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("Hedef Adım Bulunamadı",null, HttpStatus.NO_CONTENT));
        }
        IsAkisAdim isAkisAdimTargetExist = isAkisAdimTarget.get();

        if (ısAkisTransferRepository.existBySourceAndTargetId(isAkisAdimExistsSource.getOid(),isAkisAdimTargetExist.getOid()))  {
            return ResponseEntity.status(409).body(ApiResponse.error("Halihazırd bu iki adım arasında transfer bulunmktadır", Collections.emptyList(), HttpStatus.NO_CONTENT));

        }
        if (isAkisTanim.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanım Listesi Boş", Collections.emptyList(), HttpStatus.NO_CONTENT));
        }
        IsAkisTanim isAkisTanimExists = isAkisTanim.get();

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
        isAkisTransfer.setIsAkisTanim(isAkisTanimExists);
        IsAkisTransfer saved = ısAkisTransferRepository.save(isAkisTransfer);
        List<IsAkisTransfer> isAkisTransferList = new ArrayList<>();
        isAkisTransferList.add(saved);
        isAkisTanimExists.setIsakisTransfers(isAkisTransferList);

        i̇sAkisTanimRepository.save(isAkisTanimExists);

        return  ResponseEntity.ok(ApiResponse.success("Sequence Order Objesi kuruldu"));
    }


    @Override

    public ResponseEntity<ApiResponse> deleteAllIsAkisTransfers(String isAkisTanimOid){

        if (ısAkisTransferRepository.findAll().isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error(
                    "İs akis Transfer Listesi Boş",Collections.emptyList(),HttpStatus.NO_CONTENT
            ));
        }
        Optional<IsAkisTanim> i̇sAkisTanimOptional = i̇sAkisTanimRepository.findById(isAkisTanimOid);

        if (i̇sAkisTanimOptional.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Tanim Optional empty",Collections.emptyList(),HttpStatus.NO_CONTENT));
        }

        System.out.println(i̇sAkisTanimOptional.get());
        System.out.println(i̇sAkisTanimOptional.get().getAdı());
        System.out.println(i̇sAkisTanimOptional.get().getAciklama());
        System.out.println(i̇sAkisTanimOptional.get().getIsakisTransfers());

        System.out.println(i̇sAkisTanimOptional.get());

        try {
            ısAkisTransferRepository.deleteAllByIsAkisTanimOid(isAkisTanimOid);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return  ResponseEntity.status(200).body(ApiResponse.success("İs akis Transferleri successfully deleted"));
    }

    @Override
    public ResponseEntity<ApiResponse> deleteSingleIsAkisTransfer( String isAkisTransferOid) {
            // galiba burada iki durum var bir 0 la 1 arasındaki transferi silinince artık sonraki tüm adımların numar
            Optional<IsAkisTransfer> isAkisTransferOptional = ısAkisTransferRepository.findByOid(isAkisTransferOid);
            if (isAkisTransferOptional.isEmpty()){
                return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Transfer Bulunamadı",Collections.emptyList(),HttpStatus.NO_CONTENT));
            }
            IsAkisTransfer isAkisTransfer = isAkisTransferOptional.get();

            IsAkisAdim isAkisAdimKaynak = isAkisTransfer.getKaynakAdim();
            IsAkisAdim isAkisAdimHedef = isAkisTransfer.getHedefAdim();
            isAkisAdimKaynak.setAdim_no_referans(null);

            isAkisAdimHedef.setAdim_no_geri(null);

            isAkisAdimRepository.save(isAkisAdimKaynak);
            isAkisAdimRepository.save(isAkisAdimHedef);

        ısAkisTransferRepository.delete(isAkisTransfer);

        return  ResponseEntity.ok(ApiResponse.success("İs Akis Transfer Basarıyla Silindi"));
        }



    @Override

    //2 sıra muhabbeti şimdilik kalacak adımlar bunu değiştirince onların da refersanları
    // vesaire değişecek yenilenecek

    //3aynı olamaz zaten sourcesiyle targeti güncellemeye çalışırsan.

    //4 metadata değiştir

    //5 şimdilik sıra muhabbetine ne iş adımını silerken ne transferi silerken güncellerken girmiyorum
    //sıra muhabbeti tamamen çalışma sırası çünkü ve ora ayrı bir mekanizma görsel olarak farklı
    //çalışma olarak farklı isteyebilirsin sonuçta araştırılması gereken bir durum
    public ResponseEntity<ApiResponse> updateSingleAkisTransfer(
       IsAkisTransferUpdateDTO isAkisTransferUpdateDTO,
            String isAkisTransferOid) {
        Optional<IsAkisTransfer> isAkisTransferOptional = ısAkisTransferRepository.findByOid(isAkisTransferOid);
        if (isAkisTransferOptional.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Transfer Bulunamadı",Collections.emptyList(),HttpStatus.NO_CONTENT));
        }
        IsAkisTransfer isAkisTransfer = isAkisTransferOptional.get();


      List<IsAkisTransfer> isAkisTransferOptionalBaslangıc = ısAkisTransferRepository.findAllByKaynakOid(isAkisTransferUpdateDTO.getKaynakAdimOid());
        if (
                isAkisTransfer.getKaynakAdim().getBaslangıc() == 1 &&
                        isAkisTransferOptionalBaslangıc.size() == 1
        ){

            IsAkisAdim isAkisTransferHedefAdim = isAkisAdimRepository.findById(isAkisTransferUpdateDTO.getHedefAdimOid()).get();

            isAkisTransfer.setHedefAdim(isAkisTransferHedefAdim);
            isAkisTransfer.setSonraki_isakis_adim_no(isAkisTransferUpdateDTO.getSonraki_isakis_adim_no());
            ısAkisTransferRepository.save(isAkisTransfer);
            return ResponseEntity.ok(ApiResponse.success("Successfully Update"));
        }
        //başlangıçsa
            //1 tane outbound varsa
            // onun kaynağını güncelleme yapamasın
        IsAkisTransfer isAkisTransferExist = isAkisTransferOptional.get();

        if (isAkisTransferUpdateDTO.getKaynakAdimOid().equals(isAkisTransferUpdateDTO.getHedefAdimOid())){
            return ResponseEntity.status(409).body(ApiResponse.error("İs Akis Transfer Hedefiyle Kaynağı Eşit Olamaz",Collections.emptyList(),HttpStatus.NO_CONTENT));

        }

        isAkisTransferExist.setIsakis_adim_no(isAkisTransferUpdateDTO.getIsakis_adim_no());
        isAkisTransferExist.setSonraki_isakis_adim_no(isAkisTransferUpdateDTO.getSonraki_isakis_adim_no());
        isAkisTransferExist.setKomut(isAkisTransferUpdateDTO.getKomut());
        isAkisTransferExist.setTuru(isAkisTransferUpdateDTO.getTuru());
        isAkisTransferExist.setKosul(isAkisTransferUpdateDTO.getKosul());

        isAkisTransferExist.setEklenti(isAkisTransferUpdateDTO.getEklenti());
        isAkisTransferExist.setAciklama(isAkisTransferUpdateDTO.getAciklama());

        IsAkisAdim isAkisTransferHedefAdim = isAkisAdimRepository.findById(isAkisTransferUpdateDTO.getHedefAdimOid()).get();
        IsAkisAdim isAkisTransferKaynakAdim = isAkisAdimRepository.findById(isAkisTransferUpdateDTO.getKaynakAdimOid()).get();


        isAkisTransferExist.setKaynakAdim(isAkisTransferKaynakAdim);
        isAkisTransferExist.setHedefAdim(isAkisTransferHedefAdim);


        ısAkisTransferRepository.save(isAkisTransferExist);

        return ResponseEntity.ok(ApiResponse.success("Successfully Update"));


        }

    @Override
    public ResponseEntity<ApiResponse> updateKosul(String isTransferOid, String kosul) {
        Optional<IsAkisTransfer> isAkisTransferOptional =  ısAkisTransferRepository.findById(isTransferOid);
        if (isAkisTransferOptional.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("Is akis adim bulunamadı",Collections.emptyList(),HttpStatus.NO_CONTENT));
        }
        IsAkisTransfer existsTransfer = isAkisTransferOptional.get();

        existsTransfer.setKosul(kosul);

        return  ResponseEntity.ok(ApiResponse.success("Kosul Successfully updated"));

    }

    @Override
    public ResponseEntity<ApiResponse> deleteKosul(String isTransferOid) {
        Optional<IsAkisTransfer> isAkisTransferOptional =  ısAkisTransferRepository.findById(isTransferOid);
        if (isAkisTransferOptional.isEmpty()){
            return ResponseEntity.status(409).body(ApiResponse.error("Is akis adim bulunamadı",Collections.emptyList(),HttpStatus.NO_CONTENT));
        }
        IsAkisTransfer existsTransfer = isAkisTransferOptional.get();

        existsTransfer.setKosul(null);

        return  ResponseEntity.ok(ApiResponse.success("Kosul Successfully deleted"));
    }
}
