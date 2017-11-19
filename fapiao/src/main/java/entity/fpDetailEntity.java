package entity;

import java.util.List;

/**
 * Created by zhang on 2017/10/18.
 */
public class fpDetailEntity {
    //发票代码
    private String fpdm_dzfp;
    //发票号码
    private String fphm_dzfp;
    //发票所属城市和类型
    private String fpcc_dzfp;
    //查验次数
    private String cycs;
    //查验时间
    private String cysj;
    //开票日期
    private String kprq_dzfp;
    //销售方名称
    private String xfmc_dzfp;
    //销售方纳税人识别号
    private String xfsbh_dzfp;
    //销售方地址、电话
    private String xfdzdh_dzfp;
    //销售方开户行及账号
    private String xfyhzh_dzfp;
    //购方名称
    private String gfmc_dzfp;
    //购方纳税人识别号
    private String gfsbh_dzfp;
    //购方地址、电话
    private String gfdzdh_dzfp;
    //购方开户行及账号
    private String gfyhzh_dzfp;
    //金额
    private String je_dzfp;
    //税额
    private String se_dzfp;
    //价税合计 小写
    private String jshjxx_dzfp;
    //价税合计 大写
    private String jshjdx_dzfp;
    //备注
    private String bz_dzfp;
    //机器编号
    private String sbbh_dzfp;
    //校验码
    private String jym_dzfp;
    //收货员
    private String shy;
    //货物集合
    private List<String[]> hws;

    public String getJshjdx_dzfp() {
        return jshjdx_dzfp;
    }

    public void setJshjdx_dzfp(String jshjdx_dzfp) {
        this.jshjdx_dzfp = jshjdx_dzfp;
    }

    public String getFpdm_dzfp() {
        return fpdm_dzfp;
    }

    public void setFpdm_dzfp(String fpdm_dzfp) {
        this.fpdm_dzfp = fpdm_dzfp;
    }

    public String getFphm_dzfp() {
        return fphm_dzfp;
    }

    public void setFphm_dzfp(String fphm_dzfp) {
        this.fphm_dzfp = fphm_dzfp;
    }

    public String getFpcc_dzfp() {
        return fpcc_dzfp;
    }

    public void setFpcc_dzfp(String fpcc_dzfp) {
        this.fpcc_dzfp = fpcc_dzfp;
    }

    public String getCycs() {
        return cycs;
    }

    public void setCycs(String cycs) {
        this.cycs = cycs;
    }

    public String getCysj() {
        return cysj;
    }

    public void setCysj(String cysj) {
        this.cysj = cysj;
    }

    public String getKprq_dzfp() {
        return kprq_dzfp;
    }

    public void setKprq_dzfp(String kprq_dzfp) {
        this.kprq_dzfp = kprq_dzfp;
    }

    public String getXfmc_dzfp() {
        return xfmc_dzfp;
    }

    public void setXfmc_dzfp(String xfmc_dzfp) {
        this.xfmc_dzfp = xfmc_dzfp;
    }

    public String getXfsbh_dzfp() {
        return xfsbh_dzfp;
    }

    public void setXfsbh_dzfp(String xfsbh_dzfp) {
        this.xfsbh_dzfp = xfsbh_dzfp;
    }

    public String getXfdzdh_dzfp() {
        return xfdzdh_dzfp;
    }

    public void setXfdzdh_dzfp(String xfdzdh_dzfp) {
        this.xfdzdh_dzfp = xfdzdh_dzfp;
    }

    public String getXfyhzh_dzfp() {
        return xfyhzh_dzfp;
    }

    public void setXfyhzh_dzfp(String xfyhzh_dzfp) {
        this.xfyhzh_dzfp = xfyhzh_dzfp;
    }

    public String getGfmc_dzfp() {
        return gfmc_dzfp;
    }

    public void setGfmc_dzfp(String gfmc_dzfp) {
        this.gfmc_dzfp = gfmc_dzfp;
    }

    public String getGfsbh_dzfp() {
        return gfsbh_dzfp;
    }

    public void setGfsbh_dzfp(String gfsbh_dzfp) {
        this.gfsbh_dzfp = gfsbh_dzfp;
    }

    public String getGfdzdh_dzfp() {
        return gfdzdh_dzfp;
    }

    public void setGfdzdh_dzfp(String gfdzdh_dzfp) {
        this.gfdzdh_dzfp = gfdzdh_dzfp;
    }

    public String getGfyhzh_dzfp() {
        return gfyhzh_dzfp;
    }

    public void setGfyhzh_dzfp(String gfyhzh_dzfp) {
        this.gfyhzh_dzfp = gfyhzh_dzfp;
    }

    public String getJe_dzfp() {
        return je_dzfp;
    }

    public void setJe_dzfp(String je_dzfp) {
        this.je_dzfp = je_dzfp;
    }

    public String getSe_dzfp() {
        return se_dzfp;
    }

    public void setSe_dzfp(String se_dzfp) {
        this.se_dzfp = se_dzfp;
    }

    public String getJshjxx_dzfp() {
        return jshjxx_dzfp;
    }

    public void setJshjxx_dzfp(String jshjxx_dzfp) {
        this.jshjxx_dzfp = jshjxx_dzfp;
    }

    public String getBz_dzfp() {
        return bz_dzfp;
    }

    public void setBz_dzfp(String bz_dzfp) {
        this.bz_dzfp = bz_dzfp;
    }

    public String getSbbh_dzfp() {
        return sbbh_dzfp;
    }

    public void setSbbh_dzfp(String sbbh_dzfp) {
        this.sbbh_dzfp = sbbh_dzfp;
    }

    public String getJym_dzfp() {
        return jym_dzfp;
    }

    public void setJym_dzfp(String jym_dzfp) {
        this.jym_dzfp = jym_dzfp;
    }

    public List<String[]> getHws() {
        return hws;
    }

    public void setHws(List<String[]> hws) {
        this.hws = hws;
    }

    public String getShy() {
        return shy;
    }

    public void setShy(String shy) {
        this.shy = shy;
    }

    @Override
    public String toString() {
        return "fpDetailEntity{" +
                "fpdm_dzfp='" + fpdm_dzfp + '\'' +
                ", fphm_dzfp='" + fphm_dzfp + '\'' +
                ", fpcc_dzfp='" + fpcc_dzfp + '\'' +
                ", cycs='" + cycs + '\'' +
                ", cysj='" + cysj + '\'' +
                ", kprq_dzfp='" + kprq_dzfp + '\'' +
                ", xfmc_dzfp='" + xfmc_dzfp + '\'' +
                ", xfsbh_dzfp='" + xfsbh_dzfp + '\'' +
                ", xfdzdh_dzfp='" + xfdzdh_dzfp + '\'' +
                ", xfyhzh_dzfp='" + xfyhzh_dzfp + '\'' +
                ", gfmc_dzfp='" + gfmc_dzfp + '\'' +
                ", gfsbh_dzfp='" + gfsbh_dzfp + '\'' +
                ", gfdzdh_dzfp='" + gfdzdh_dzfp + '\'' +
                ", gfyhzh_dzfp='" + gfyhzh_dzfp + '\'' +
                ", je_dzfp='" + je_dzfp + '\'' +
                ", se_dzfp='" + se_dzfp + '\'' +
                ", jshjxx_dzfp='" + jshjxx_dzfp + '\'' +
                ", jshjdx_dzfp='" + jshjdx_dzfp + '\'' +
                ", bz_dzfp='" + bz_dzfp + '\'' +
                ", sbbh_dzfp='" + sbbh_dzfp + '\'' +
                ", jym_dzfp='" + jym_dzfp + '\'' +
                ", shy='" + shy + '\'' +
                ", hws=" + hws +
                '}';
    }
}
