package com.sudichina.shipperclient.bean;

/**
 * Created by mccccccmike on 2016/8/26.
 * {id=37.0, status=null, gmtCreate=1.472107874E12, gmtModified=1.472109147E12,
 * accountName=13716556242, accountPassword=657E2C446F48D3AA, idCard=null,
 * realname=null, accountType=0, clientIp=0, agentAccountId=null,
 * logStringimes=0.0, source=0, accountDegree=0.0, nickName=0, note3=null,
 * tradePassword=null, contactTel=null, crtDate=1.472107874E12,
 * isTempAccount=false, wechatOpenId=null, invalid=false, auth=false,
 * online=false, lock=false} code = 1 success true]
 */
public class LoginDataBean {
    private long id;
    private String status;
    private long gmtCreate;
    private long gmtModified;
    private String accountName;
    private String accountPassword;
    private String idCard;
    private String realname;
    private String accountType;
    private String clientIp;
    private String agentAccountId;
    private long loginTimes;
    private String source;
    private String accountDegree;
    private String nickName;
    private String note3;
    private String tradePassword;
    private String contactTel;
    private long crtDate;
    private boolean isTempAccount;
    private String wechatOpenId;
    private boolean invalid;
    private boolean auth;
    private boolean lock;
    private boolean online;

    public LoginDataBean(long id, String status, long gmtCreate, long gmtModified, String accountName, String accountPassword, String idCard, String realname, String accountType, String clientIp, String agentAccountId, long loginTimes, String source, String accountDegree, String nickName, String note3, String tradePassword, String contactTel, long crtDate, boolean isTempAccount, String wechatOpenId, boolean invalid, boolean auth, boolean lock, boolean online) {
        this.id = id;
        this.status = status;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.idCard = idCard;
        this.realname = realname;
        this.accountType = accountType;
        this.clientIp = clientIp;
        this.agentAccountId = agentAccountId;
        this.loginTimes = loginTimes;
        this.source = source;
        this.accountDegree = accountDegree;
        this.nickName = nickName;
        this.note3 = note3;
        this.tradePassword = tradePassword;
        this.contactTel = contactTel;
        this.crtDate = crtDate;
        this.isTempAccount = isTempAccount;
        this.wechatOpenId = wechatOpenId;
        this.invalid = invalid;
        this.auth = auth;
        this.lock = lock;
        this.online = online;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getAgentAccountId() {
        return agentAccountId;
    }

    public void setAgentAccountId(String agentAccountId) {
        this.agentAccountId = agentAccountId;
    }

    public long getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(long loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAccountDegree() {
        return accountDegree;
    }

    public void setAccountDegree(String accountDegree) {
        this.accountDegree = accountDegree;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNote3() {
        return note3;
    }

    public void setNote3(String note3) {
        this.note3 = note3;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public long getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(long crtDate) {
        this.crtDate = crtDate;
    }

    public boolean isTempAccount() {
        return isTempAccount;
    }

    public void setTempAccount(boolean tempAccount) {
        isTempAccount = tempAccount;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
