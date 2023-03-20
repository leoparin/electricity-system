package com.leo.electricitysystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName:RegistrationDto
 * PackageName:com.leo.electricitysystem.DTO
 * Description:
 *
 * @Date 2023/3/18 09:35
 * @Author leo
 **/
@Data
@AllArgsConstructor
public class RegistrationDTO {

    private String userName;

    private String passwd;

    private String region;

    private String userType;
}
