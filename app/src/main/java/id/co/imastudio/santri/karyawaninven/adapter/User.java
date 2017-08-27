package id.co.imastudio.santri.karyawaninven.adapter;

/**
 * Created by Server on 06/08/2017.
 */

public class User {
    private long id;
    private String nama;
    private String email;
    private String deplop;
    private String perusahan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeplop() {
        return deplop;
    }

    public void setDeplop(String deplop) {
        this.deplop = deplop;
    }

    public String getPerusahan() {
        return perusahan;
    }

    public void setPerusahan(String perusahan) {
        this.perusahan = perusahan;
    }
    @Override
    public String toString() {
        return "User" + nama+" " +email+ "" +deplop+" "+perusahan+" ";
    }
}
