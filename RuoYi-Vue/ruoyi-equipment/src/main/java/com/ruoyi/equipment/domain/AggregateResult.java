package com.ruoyi.equipment.domain;

public class AggregateResult{
    /**
     * 用户/设备 名字
     */
    private String name;
    /**
     * 预约次数
     */
    private long count;
    /**
     * 设备费
     */
    private double equipmentMoney;
    /**
     * 人工费
     */
    private double artificialMoney;
    /**
     * 耗材费
     */
    private double materialMoney;
    /**
     * 预约时计算的总费用
     */
    private double totalMoney;
    /**
     * 真实总费用
     */
    private double actualTotalMoney;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public Long getCount(){
        return count;
    }
    public void setCount(long count){
        this.count=count;
    }
    public double getTotalMoney(){
        return totalMoney;
    }
    public void setTotalMoney(double totalMoney){
        this.totalMoney=totalMoney;
    }
    public double getEquipmentMoney(){
        return equipmentMoney;
    }
    public void setEquipmentMoney(double equipmentMoney){
        this.equipmentMoney=equipmentMoney;
    }
    public double getArtificialMoney(){
        return artificialMoney;
    }
    public void setArtificialMoney(double artificialMoney){
        this.artificialMoney=artificialMoney;
    }
    public double getMaterialMoney(){
        return materialMoney;
    }
    public void setMaterialMoney(double materialMoney){
        this.materialMoney=materialMoney;
    }
    public double getActualTotalMoney(){
        return actualTotalMoney;
    }
    public void setActualTotalMoney(double actualTotalMoney){
        this.actualTotalMoney=actualTotalMoney;
    }
    @Override
    public String toString(){
        return "AggregateResult{"+"name='"+name+'\''+", count="+count+", equipmentMoney="+equipmentMoney+", artificialMoney="+artificialMoney+", materialMoney="+materialMoney+", totalMoney="+totalMoney+", actualTotalMoney="+actualTotalMoney+'}';
    }
}
