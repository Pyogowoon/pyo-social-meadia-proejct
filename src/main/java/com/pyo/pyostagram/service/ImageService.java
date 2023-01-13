package com.pyo.pyostagram.service;


import com.pyo.pyostagram.config.auth.PrincipalDetails;
import com.pyo.pyostagram.domain.image.Image;
import com.pyo.pyostagram.domain.image.ImageRepository;
import com.pyo.pyostagram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;


    @Value("${file.path}") // yml에 적힌값 가져오는거
    private String uploadFolder;


    @Transactional
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID(); //uuid 하는 이유 = 만약 같은이름의 파일이 uuid 없이 올라온다면 덮어씌워지기 때문에
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename(); //1.jpg
        System.out.println("이미지 파일 이름  =" + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder + imageFileName); //실제 경로 적어야함

        //통신 , I/O 일어날때는 예외가 발생할 수 있기때문에 예외처리 해야함
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
            //첫번째로 경로,2. 실제 파일을 byte화 해서 넣어야함, 3.옵션값인데 생략가능
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);

//        System.out.println(imageEntity);
    }

    @Transactional(readOnly = true)
    public Page<Image> 이미지스토리(int principalId , Pageable pageable) {
        Page<Image> images = imageRepository.mStroy(principalId,pageable);

        // 2번으로 로그인 -> 2번이 구독한 이미지를 foreach로 쫙 뽑은다음 그 뽑은 이미지
        //의 좋아요 정보를 2중 for문으로 가져와서 그 좋아요가 2번(cos)가 좋아요했는지 비교하면된다
        //images에 좋아요 담아야함
        images.forEach((image)-> {

            image.setLikeCount(image.getLikes().size());

            image.getLikes().forEach((like) ->{
                if(like.getUser().getId()== principalId){
                    // 해당 이미지에 좋아요 한 사람들을 찾아서 현재 로그인한 사람이 좋아요 한건지비교
                    image.setLikeState(true);
                }


            });
        });
        return images;
    }

    @Transactional(readOnly = true)
    public List<Image> 인기사진(){
        return imageRepository.mPopular();

    }
}
