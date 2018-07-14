package nyxdev.hackatren.taralrt1.integration.dao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AccountEntity {
    @Id private Long id;
    private String accountID;
    private String rfid;
    private String nfc;
    private String email;
    @Transient private String password;
    @Transient private String name;

    @Generated(hash = 1909721239)
    public AccountEntity(Long id, String accountID, String rfid, String nfc,
            String email) {
        this.id = id;
        this.accountID = accountID;
        this.rfid = rfid;
        this.nfc = nfc;
        this.email = email;
    }

    @Generated(hash = 40307897)
    public AccountEntity() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountID() {
        return this.accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getRfid() {
        return this.rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getNfc() {
        return this.nfc;
    }

    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
