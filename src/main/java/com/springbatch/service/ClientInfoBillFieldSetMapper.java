package com.springbatch.service;

import com.springbatch.po.ClientInfo;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

/**
 * @author xiongchenyang
 * @Date 2019/8/27
 **/
@Component
public class ClientInfoBillFieldSetMapper implements FieldSetMapper<ClientInfo> {
    @Override
    public ClientInfo mapFieldSet(FieldSet fieldSet) throws BindException {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setClientName(fieldSet.readString("clientName"));
        clientInfo.setClientNo(fieldSet.readString("clientNo"));
        clientInfo.setIdType(fieldSet.readString("idType"));
        clientInfo.setIdNo(fieldSet.readString("idNo"));
        clientInfo.setSex(fieldSet.readString("sex"));
        clientInfo.setBirthDate(StringUtils.isBlank(fieldSet.readString("birthDate"))? null: fieldSet.readDate("birthDate","yyyy-MM-dd"));
        clientInfo.setCivilServantType(fieldSet.readString("civilServantType"));
        clientInfo.setCountry(fieldSet.readString("country"));
        clientInfo.setDegree(fieldSet.readString("degree"));
        clientInfo.setEducation(fieldSet.readString("education"));
        clientInfo.setEmployeeType(fieldSet.readString("employeeType"));
        clientInfo.setGraduationTime(StringUtils.isBlank(fieldSet.readString("graduationTime"))? null:fieldSet.readDate("graduationTime","yyyy-MM-dd"));
        clientInfo.setIdnoValidDate(StringUtils.isBlank(fieldSet.readString("idnoValidDate"))? null:fieldSet.readDate("idnoValidDate","yyyy-MM-dd"));
        clientInfo.setLocalHouseFlag(fieldSet.readBoolean("localHouseFlag"));
        clientInfo.setMarriage(fieldSet.readString("marriage"));
        clientInfo.setNationality(fieldSet.readString("nationality"));
        clientInfo.setNativeAddress(fieldSet.readString("nativeAddress"));
        clientInfo.setNativePlaceFlag(fieldSet.readBoolean("nativePlaceFlag"));
        clientInfo.setOccupationType(fieldSet.readString("occupationType"));
        clientInfo.setPricingType(fieldSet.readString("pricingType"));
        clientInfo.setSchoolCode(fieldSet.readString("schoolCode"));
        clientInfo.setCreatedBy(fieldSet.readString("createdBy"));
        clientInfo.setUpdatedBy(fieldSet.readString("updatedBy"));
        clientInfo.setCreatedDate(StringUtils.isBlank(fieldSet.readString("createdDate"))? null:fieldSet.readDate("createdDate","yyyy-MM-dd HH:mm:ss"));
        clientInfo.setUpdatedDate(StringUtils.isBlank(fieldSet.readString("updatedDate"))? null:fieldSet.readDate("updatedDate","yyyy-MM-dd HH:mm:ss"));
        return clientInfo;
    }
}
