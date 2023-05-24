package com.seegene.mvnpoc.web.api.v1.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seegene.mvnpoc.support.code.RoleCode;
import com.seegene.mvnpoc.support.code.WorkCode;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * class 한글 설명
 * <PRE>
 * </PRE>
 *
 * @author : khhong@mz.co.kr
 * @History <PRE>
 * No  Date           Author            Desc
 * ---- ------------ ---------------- ------------------------------------
 * 1 2023-05-10        khhong        최초작성
 * </PRE>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public record UserInfoRecord(@JsonInclude String id, @JsonProperty("userId") String userId, @JsonProperty("userName") String userName,
                             @JsonProperty("userPassword") String userPassword, String userEmail, String userBirthDay,
                             String userEnterDay, String userResignationDay, @JsonProperty("userOrganizationName") String userOrganizationName,
                             String userMobileNumber, @JsonProperty("userWorkStatus") WorkCode userWorkStatus, @JsonProperty("userRole") String userRole) {

//    public UserInfoRecord(Long userSeq, String userId, String userName, LocalDate userBirthDay, LocalDate userEnterDay, LocalDate userResignationDay, String userOrganizationName, String userMobileNumber, WorkCode userWorkStatus, RoleCode userRole) {
//        this(userSeq, userId, userName, "","", DefaultDateTimeConverter.convertDate(userBirthDay), DefaultDateTimeConverter.convertDate(userEnterDay), DefaultDateTimeConverter.convertDate(userResignationDay), userOrganizationName, userMobileNumber, userWorkStatus, userRole);
//    }
//
    public UserInfoRecord(String id, String userId, String userName, String userEmail, WorkCode workCode, String userRole) {
        this(id, userId, userName, "", userEmail, "","",  "", "", "", workCode, userRole);
    }

//    public static UserInfoRecord fromEntity(UserEntity byUserName) {
//        return new UserInfoRecord(byUserName.getId(), byUserName.getUserId(), byUserName.getUserName(), byUserName.getUserPassword(),
//                DefaultDateTimeConverter.convertDateTime(byUserName.getUserPasswordSettingDate()), DefaultDateTimeConverter.convertDate(byUserName.getUserBirthDay()), DefaultDateTimeConverter.convertDate(byUserName.getUserEnterDay()),
//                DefaultDateTimeConverter.convertDate(byUserName.getUserResignationDay()), byUserName.getUserOrganizationName(), byUserName.getUserMobileNumber(), byUserName.getUserWorkStatus(), byUserName.getUserRole());
//    }

}