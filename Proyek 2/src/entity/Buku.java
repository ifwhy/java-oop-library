package entity;

public class Buku {
    private int id;
    private String judul;
    private String penulis;
    private String penerbit;
    private int tahunTerbit;

    public Buku( String judul, String penulis, String penerbit, int tahunTerbit) {
        assert tahunTerbit > 0 && tahunTerbit <= 9999 : "Tahun Terbit Tidak Valid";

        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.tahunTerbit = tahunTerbit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Buku(int id, String judul, String penulis, String penerbit, int tahunTerbit) {
        assert tahunTerbit > 0 && tahunTerbit <= 9999 : "Tahun Terbit Tidak Valid";

        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.tahunTerbit = tahunTerbit;
    }

    public Buku()  {
    }

    public String getJudul() {
        return this.judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return this.penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return this.penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getTahunTerbit() {
        return this.tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }
}
