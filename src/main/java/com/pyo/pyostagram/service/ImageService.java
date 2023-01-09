package com.pyo.pyostagram.service;


import com.pyo.pyostagram.config.auth.PrincipalDetails;
import com.pyo.pyostagram.domain.image.Image;
import com.pyo.pyostagram.domain.image.ImageRepository;
import com.pyo.pyostagram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}") // yml에 적힌값 가져오는거
    private String uploadFolder;

    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
       UUID uuid = UUID.randomUUID(); //uuid 하는 이유 = 만약 같은이름의 파일이 uuid 없이 올라온다면 덮어씌워지기 때문에
        String imageFileName= uuid+"_"+ imageUploadDto.getFile().getOriginalFilename(); //1.jpg
        System.out.println("이미지 파일 이름  =" + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName); //실제 경로 적어야함

        //통신 , I/O 일어날때는 예외가 발생할 수 있기때문에 예외처리 해야함
        try{
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
                        //첫번째로 경로,2. 실제 파일을 byte화 해서 넣어야함, 3.옵션값인데 생략가능
        }catch(Exception e){
                e.printStackTrace();
        }
        Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
        Image imageEntity = imageRepository.save(image);

        System.out.println(imageEntity);
    }
}
