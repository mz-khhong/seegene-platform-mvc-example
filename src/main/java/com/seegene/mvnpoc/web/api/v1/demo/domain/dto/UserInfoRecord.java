package com.seegene.mvnpoc.web.api.v1.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seegene.mvnpoc.support.code.RoleCode;
import com.seegene.mvnpoc.support.code.WorkCode;
import jakarta.validation.constraints.Pattern;
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
                             @JsonProperty("userPassword") String userPassword, String userPasswordSettingDate, String userEmail, @Pattern(regexp = DATE_FORMAT) String userBirthDay,
                             @Pattern(regexp = DATE_FORMAT) String userEnterDay, String userResignationDay, @JsonProperty("userOrganizationName") String userOrganizationName,
                             @Pattern(regexp = PHONE_NUMBER_REGEX) String userMobileNumber, @JsonProperty("userWorkStatus") WorkCode userWorkStatus, @JsonProperty("userRole") RoleCode userRole) {

    public static final String DATE_FORMAT = "^(\\d{4})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
//    public static final String DATE_TIME_FORMAT = "^(\\d{4})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) (\\d{2}):(\\d{2}):(\\d{2})$";
    public static final String PHONE_NUMBER_REGEX = "^01(\\d{1})-(\\d{3,4})-(\\d{4})$";

//    public UserInfoRecord(Long userSeq, String userId, String userName, LocalDate userBirthDay, LocalDate userEnterDay, LocalDate userResignationDay, String userOrganizationName, String userMobileNumber, WorkCode userWorkStatus, RoleCode userRole) {
//        this(userSeq, userId, userName, "","", DefaultDateTimeConverter.convertDate(userBirthDay), DefaultDateTimeConverter.convertDate(userEnterDay), DefaultDateTimeConverter.convertDate(userResignationDay), userOrganizationName, userMobileNumber, userWorkStatus, userRole);
//    }
//
    public UserInfoRecord(String id, String userId, String userName, String userEmail, WorkCode workCode, RoleCode userRole) {
        this(id, userId, userName, "", "", userEmail, "","",  "", "", "", workCode, userRole);
    }

//    public static UserInfoRecord fromEntity(UserEntity byUserName) {
//        return new UserInfoRecord(byUserName.getId(), byUserName.getUserId(), byUserName.getUserName(), byUserName.getUserPassword(),
//                DefaultDateTimeConverter.convertDateTime(byUserName.getUserPasswordSettingDate()), DefaultDateTimeConverter.convertDate(byUserName.getUserBirthDay()), DefaultDateTimeConverter.convertDate(byUserName.getUserEnterDay()),
//                DefaultDateTimeConverter.convertDate(byUserName.getUserResignationDay()), byUserName.getUserOrganizationName(), byUserName.getUserMobileNumber(), byUserName.getUserWorkStatus(), byUserName.getUserRole());
//    }

}