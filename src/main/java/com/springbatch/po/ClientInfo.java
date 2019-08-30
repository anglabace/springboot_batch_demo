package com.springbatch.po;


import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name = "client_info")
public class ClientInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
    @Column
    private long id;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //客户号
     @Column(name = "client_no", unique = true, nullable = false, length = 11, updatable = false)
    private String clientNo;

    //客户姓名
     @Column(name = "client_name")
    private String clientName;

    //性别
    
     @Column(name = "sex")
    private String sex;

    //证件类型
    
     @Column(name = "id_type")
    private String idType;

    //证件号码
    
     @Column(name = "id_no")
    private String idNo;

    //出生日期
    
    @JsonFormat(pattern = "yyyy-MM-dd")
     @Column(name = "birth_date")
    private Date birthDate;

    //国籍
     @Column(name = "country")
    private String country;

    //民族
     @Column
    private String nationality;

    //婚姻状况
    @Column
    private String marriage;

    //工作是否在本地 Y：有  N：无
    @Column(name = "local_house_flag")
    private Boolean localHouseFlag;

    //居住地是否在本地（客户评级需要）
    @Column(name = "native_place_flag")
    private Boolean nativePlaceFlag;

    //证件到期日
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "idno_valid_date")
    private Date idnoValidDate;

    //身份证发证机关所在地
    @Column(name = "native_address")
    private String nativeAddress;

    //学历
    @Column
    private String education;

    //学位
    @Column
    private String degree;

    //毕业学校代码
    @Column(name = "school_code")
    private String schoolCode;

    //毕业时间
//    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "graduation_time")
    private Date graduationTime;

    //雇佣类型(客户类型)
    @Column(name = "employee_type")
    private String employeeType;

    //定价类型
    @Column(name = "pricing_type")
    private String pricingType;

    //公务员类型
    @Column(name = "civil_servant_type")
    private String civilServantType;

    //优良职业类型
    @Column(name = "occupation_type")
    private String occupationType;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public Boolean getLocalHouseFlag() {
        return localHouseFlag;
    }

    public void setLocalHouseFlag(Boolean localHouseFlag) {
        this.localHouseFlag = localHouseFlag;
    }

    public Boolean getNativePlaceFlag() {
        return nativePlaceFlag;
    }

    public void setNativePlaceFlag(Boolean nativePlaceFlag) {
        this.nativePlaceFlag = nativePlaceFlag;
    }

    public Date getIdnoValidDate() {
        return idnoValidDate;
    }

    public void setIdnoValidDate(Date idnoValidDate) {
        this.idnoValidDate = idnoValidDate;
    }

    public String getNativeAddress() {
        return nativeAddress;
    }

    public void setNativeAddress(String nativeAddress) {
        this.nativeAddress = nativeAddress;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getPricingType() {
        return pricingType;
    }

    public void setPricingType(String pricingType) {
        this.pricingType = pricingType;
    }

    public String getCivilServantType() {
        return civilServantType;
    }

    public void setCivilServantType(String civilServantType) {
        this.civilServantType = civilServantType;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public ClientInfo() {
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "clientNo='" + clientNo + '\'' +
                ", clientName='" + clientName + '\'' +
                ", sex='" + sex + '\'' +
                ", idType='" + idType + '\'' +
                ", idNo='" + idNo + '\'' +
                ", birthDate=" + birthDate +
                ", country='" + country + '\'' +
                ", nationality='" + nationality + '\'' +
                ", marriage='" + marriage + '\'' +
                ", localHouseFlag=" + localHouseFlag +
                ", nativePlaceFlag=" + nativePlaceFlag +
                ", idnoValidDate=" + idnoValidDate +
                ", nativeAddress='" + nativeAddress + '\'' +
                ", education='" + education + '\'' +
                ", degree='" + degree + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                ", graduationTime=" + graduationTime +
                ", employeeType='" + employeeType + '\'' +
                ", pricingType='" + pricingType + '\'' +
                ", civilServantType='" + civilServantType + '\'' +
                ", occupationType='" + occupationType + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedDate=" + updatedDate +
                '}';
    }

    public ClientInfo(String clientNo, String clientName, String sex, String idType, String idNo, Date birthDate, String country, String nationality, String marriage, Boolean localHouseFlag, Boolean nativePlaceFlag, Date idnoValidDate, String nativeAddress, String education, String degree, String schoolCode, Date graduationTime, String employeeType, String pricingType, String civilServantType, String occupationType) {
        this.clientNo = clientNo;
        this.clientName = clientName;
        this.sex = sex;
        this.idType = idType;
        this.idNo = idNo;
        this.birthDate = birthDate;
        this.country = country;
        this.nationality = nationality;
        this.marriage = marriage;
        this.localHouseFlag = localHouseFlag;
        this.nativePlaceFlag = nativePlaceFlag;
        this.idnoValidDate = idnoValidDate;
        this.nativeAddress = nativeAddress;
        this.education = education;
        this.degree = degree;
        this.schoolCode = schoolCode;
        this.graduationTime = graduationTime;
        this.employeeType = employeeType;
        this.pricingType = pricingType;
        this.civilServantType = civilServantType;
        this.occupationType = occupationType;
    }

    public ClientInfo(String clientNo, String clientName) {
        this.clientNo = clientNo;
        this.clientName = clientName;
    }

}
