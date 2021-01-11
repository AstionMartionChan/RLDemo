package com.rili.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerEntity {
    // 自增id
    private Long id;
    // 客户代理店简称
    private String customerOrgName;
    // 客户类型 1.矿山客户 2.营业客户
    private Integer customerType;
    // 客户id
    private String customerId;
    // 客户名称
    private String customerName;
    // 整机客户ID
    private String wholeMachineId;
    // 建友.机型名称
    private String jyMachineName;
    // 建友.客户代码
    private String jyCustomerCode;
    // 建友.机型代码
    private String jyMachineCode;
    // 建友.客户名称
    private String jyCustomerName;
    // 服务等级 1.一级白金 ...以此类推
    private Integer serviceLevel;
    // 矿山等级
    private Integer mineLevel;
    // 营业等级 1.白金客户
    private Integer businessLevel;
    // 创建时间
    private Date createdTime;
    // 更新时间
    private Date modifiedTime;

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", customerOrgName='" + customerOrgName + '\'' +
                ", customerType=" + customerType +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", wholeMachineId='" + wholeMachineId + '\'' +
                ", jyMachineName='" + jyMachineName + '\'' +
                ", jyCustomerCode='" + jyCustomerCode + '\'' +
                ", jyMachineCode='" + jyMachineCode + '\'' +
                ", jyCustomerName='" + jyCustomerName + '\'' +
                ", serviceLevel=" + serviceLevel +
                ", mineLevel=" + mineLevel +
                ", businessLevel=" + businessLevel +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
