package view;

import entity.Buku;
import repository.BukuRepositoryImpl;
import service.BukuServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

import static validation.BukuValidation.isDataBukuDuplicate;
import static validation.BukuValidation.isDataBukuNotValid;

public class BukuView extends JFrame {
    private JTextField txtJudul, txtPenulis, txtPenerbit, txtTahunTerbit, txtCari;
    private DefaultTableModel tableModel;
    private JTable tabelBuku;
    private JButton btnAdd, btnBersihkan, btnUpdate, btnDelete, btnEdit;
    private ArrayList<Buku> dataBuku = new ArrayList<>();

    public BukuView() {
        // Ganti icon
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo-ivan.jpg")));
        setIconImage(icon.getImage());

        // Konfigurasi Jendela Full Screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Manajemen Buku Perpustakaan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        // Gunakan BorderLayout dengan padding
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(new Color(240, 248, 255));

        // Font Calibri dengan ukuran yang lebih besar
        Font calibriBesar = new Font("Calibri", Font.BOLD, 36);
        Font calibriNormal = new Font("Calibri", Font.PLAIN, 18);

        // Judul Utama
        JPanel judulPanel = new JPanel(new BorderLayout());
        JLabel judulUtama = new JLabel("MANAJEMEN BUKU PERPUSTAKAAN", SwingConstants.CENTER);
        judulUtama.setFont(calibriBesar);
        judulUtama.setForeground(new Color(25, 25, 112));
        judulPanel.add(judulUtama, BorderLayout.CENTER);
        judulPanel.setBackground(new Color(240, 248, 255));

        // Panel kiri untuk gambar dan input
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(240, 248, 255));

        // Panel untuk gambar
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(240, 248, 255));

        // Membuat label gambar dengan ImageIcon
        ImageIcon bookIcon = createImageIcon("/book.png", "Buku Perpustakaan");
        JLabel imageLabel = new JLabel(bookIcon);
        imageLabel.setPreferredSize(new Dimension(300, 200));
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Tambahkan padding ke panel gambar
        imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Panel Input dengan ukuran yang lebih besar
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBackground(new Color(230, 240, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label dan TextField
        JLabel[] labels = {
                new JLabel("Judul Buku      :"),
                new JLabel("Penulis             :"),
                new JLabel("Penerbit           :"),
                new JLabel("Tahun Terbit    :")
        };

        for (JLabel label : labels) {
            label.setFont(calibriNormal);
        }

        // [Kode input fields sama seperti sebelumnya...]
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelInput.add(labels[0], gbc);
        gbc.gridx = 1;
        txtJudul = new JTextField(30);
        txtJudul.setFont(calibriNormal);
        panelInput.add(txtJudul, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelInput.add(labels[1], gbc);
        gbc.gridx = 1;
        txtPenulis = new JTextField(30);
        txtPenulis.setFont(calibriNormal);
        panelInput.add(txtPenulis, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelInput.add(labels[2], gbc);
        gbc.gridx = 1;
        txtPenerbit = new JTextField(30);
        txtPenerbit.setFont(calibriNormal);
        panelInput.add(txtPenerbit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelInput.add(labels[3], gbc);
        gbc.gridx = 1;
        txtTahunTerbit = new JTextField(30);
        txtTahunTerbit.setFont(calibriNormal);
        panelInput.add(txtTahunTerbit, gbc);

        // Tombol Tambah dan Bersihkan
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnAdd = new JButton("Tambah Buku");
        btnEdit = new JButton("Perbarui Buku");
        btnBersihkan = new JButton("Bersihkan");
        btnEdit.setEnabled(false);

        btnAdd.setFont(calibriNormal);
        btnEdit.setFont(calibriNormal);
        btnBersihkan.setFont(calibriNormal);

        btnAdd.setPreferredSize(new Dimension(150, 40));
        btnEdit.setPreferredSize(new Dimension(150, 40));
        btnBersihkan.setPreferredSize(new Dimension(150, 40));

        btnAdd.setBackground(new Color(100, 149, 237));
        btnEdit.setBackground(new Color(100, 237, 102));
        btnBersihkan.setBackground(new Color(255, 99, 71));

        btnAdd.setForeground(Color.WHITE);
        btnBersihkan.setForeground(Color.WHITE);

        panelTombol.add(btnAdd);
        panelTombol.add(btnEdit);
        panelTombol.add(btnBersihkan);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panelInput.add(panelTombol, gbc);

        // Panel Pencarian
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblCari = new JLabel("Cari Buku : ");
        lblCari.setFont(calibriNormal);
        txtCari = new JTextField(30);
        txtCari.setFont(calibriNormal);
        JButton btnCari = new JButton("Cari");
        btnCari.setFont(calibriNormal);
        btnCari.setPreferredSize(new Dimension(100, 40));
        btnCari.setBackground(new Color(60, 179, 113));
        btnCari.setForeground(Color.WHITE);

        panelCari.add(lblCari);
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        // Tabel Buku tanpa kolom Aksi
        String[] kolom = {"No", "Judul Buku", "Penulis", "Penerbit", "Tahun Terbit"};
        tableModel = new DefaultTableModel(kolom, 0);
        tabelBuku = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelBuku.setFont(calibriNormal);
        tabelBuku.setRowHeight(30);

        // Mengatur lebar kolom
        TableColumnModel columnModel = tabelBuku.getColumnModel();

        // Set lebar kolom "ID"
        TableColumn idColumn = columnModel.getColumn(0);
        idColumn.setPreferredWidth(30); // Lebar ideal
        idColumn.setMinWidth(10); // Lebar minimum
        idColumn.setMaxWidth(40); // Lebar maksimum

        TableColumn judulColumn = columnModel.getColumn(0);
        judulColumn.setPreferredWidth(80); // Lebar ideal
        judulColumn.setMinWidth(50); // Lebar minimum
        judulColumn.setMaxWidth(90); // Lebar maksimum

        TableColumn tahunTerbitColumn = columnModel.getColumn(4);
        tahunTerbitColumn.setPreferredWidth(80); // Lebar ideal
        tahunTerbitColumn.setMinWidth(50); // Lebar minimum
        tahunTerbitColumn.setMaxWidth(90); // Lebar maksimum

        // Panel tombol Update dan Delete di bawah tabel
        JPanel panelUpdateDelete = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnUpdate = new JButton("Update Buku");
        btnDelete = new JButton("Hapus Buku");

        btnUpdate.setFont(calibriNormal);
        btnDelete.setFont(calibriNormal);

        btnUpdate.setPreferredSize(new Dimension(150, 40));
        btnDelete.setPreferredSize(new Dimension(150, 40));

        btnUpdate.setBackground(new Color(100, 149, 237));
        btnDelete.setBackground(new Color(255, 99, 71));

        btnUpdate.setForeground(Color.WHITE);
        btnDelete.setForeground(Color.WHITE);

        leftPanel.add(imagePanel);
        leftPanel.add(panelInput);

        add(judulPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);

        // Nonaktifkan tombol di awal
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);

        panelUpdateDelete.add(btnUpdate);
        panelUpdateDelete.add(btnDelete);

        // Tambahkan listener seleksi untuk mengaktifkan/menonaktifkan tombol
        tabelBuku.getSelectionModel().addListSelectionListener(e -> {
            boolean rowSelected = tabelBuku.getSelectedRow() != -1;
            btnUpdate.setEnabled(rowSelected);
            btnDelete.setEnabled(rowSelected);
        });

        // Tambahkan event listener untuk tombol Update
        btnUpdate.addActionListener(e -> {
            int selectedRow = tabelBuku.getSelectedRow();
            if (selectedRow != -1) {
                // Ambil data buku yang dipilih
                String judul = (String) tableModel.getValueAt(selectedRow, 1);
                String penulis = (String) tableModel.getValueAt(selectedRow, 2);
                String penerbit = (String) tableModel.getValueAt(selectedRow, 3);
                Integer tahunTerbit = (Integer) tableModel.getValueAt(selectedRow, 4);

                // Isi field input
                txtJudul.setText(judul);
                txtPenulis.setText(penulis);
                txtPenerbit.setText(penerbit);
                txtTahunTerbit.setText(tahunTerbit.toString());

                // Ubah tombol untuk update
                btnBersihkan.setEnabled(false);
                btnEdit.setEnabled(true);
            }
        });

        // Tambahkan event listener untuk tombol Delete
        btnDelete.addActionListener(e -> {
            int selectedRow = tabelBuku.getSelectedRow();
            if (selectedRow != -1) {
                int konfirmasi = JOptionPane.showConfirmDialog(
                        this,
                        "Apakah Anda yakin ingin menghapus buku ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE
                );

                if (konfirmasi == JOptionPane.YES_OPTION) {
                    try {
                        // Hapus dari database
                        (new BukuRepositoryImpl()).remove(selectedRow + 1);

                        // Hapus dari tabel dan dataBuku
                        tableModel.removeRow(selectedRow);
                        dataBuku.remove(selectedRow);

                        JOptionPane.showMessageDialog(
                                this,
                                "Buku berhasil dihapus!",
                                "Sukses",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        loadDataBuku();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Gagal menghapus buku: " + ex.getMessage(),
                                "Kesalahan",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } finally {
                        loadDataBuku();
                    }
                }
            }
        });

        // Scroll Pane untuk tabel
        JScrollPane scrollPane = new JScrollPane(tabelBuku);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        // Panel untuk footer
        JPanel footerPanel = new JPanel(new BorderLayout());
        JLabel lblFooter = new JLabel("Made by Ivan Wahyu Nugroho with love", SwingConstants.CENTER);
        lblFooter.setFont(new Font("Calibri", Font.BOLD, 24));
        lblFooter.setForeground(new Color(70, 130, 180)); // Warna biru sedang
        footerPanel.add(lblFooter, BorderLayout.CENTER);
        footerPanel.setBackground(new Color(240, 248, 255));

        // Panel untuk bagian tengah (Pencarian, Tabel, dan Tombol Update/Delete)
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(panelCari, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(panelUpdateDelete, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        add(footerPanel, BorderLayout.SOUTH);

        // Tambahkan listener untuk tombol tambah/update
        btnAdd.addActionListener(e -> {
            tambahBuku();
        });

        btnBersihkan.addActionListener(e -> bersihkanInput());
        btnCari.addActionListener(e -> cariBuku());
        btnEdit.addActionListener(e -> editBuku(tabelBuku.getSelectedRow()));

        // Tambahkan padding di sekitar komponen
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Muat data buku
        loadDataBuku();
    }

    private ImageIcon createImageIcon(String path, String description) {
        // Gunakan placeholder image jika resource tidak ditemukan
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
            // Buat gambar placeholder
            int width = 300;
            int height = 200;
            java.awt.image.BufferedImage bufferedImage = new java.awt.image.BufferedImage(
                    width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // Set background
            g2d.setColor(new Color(200, 220, 240));
            g2d.fillRect(0, 0, width, height);

            // Gambar buku sederhana
            g2d.setColor(new Color(70, 130, 180));
            g2d.fillRect(100, 50, 100, 120);
            g2d.setColor(new Color(50, 100, 150));
            g2d.fillRect(110, 60, 80, 100);

            g2d.dispose();

            return new ImageIcon(bufferedImage);
        }
        return icon;
    }

    private void editBuku(int selectedRow) {
        String judul = txtJudul.getText();
        String penulis = txtPenulis.getText();
        String penerbit = txtPenerbit.getText();
        String tahunTerbitStr = txtTahunTerbit.getText();

        try {
            int tahunTerbit = Integer.parseInt(tahunTerbitStr);

            // Tambah baris ke tabel
            Vector<Object> baris = new Vector<>();
            baris.add(dataBuku.size() + 1);
            baris.add(judul);
            baris.add(penulis);
            baris.add(penerbit);
            baris.add(tahunTerbit);

            Buku buku = new Buku(judul, penulis, penerbit, tahunTerbit);
            if (isDataBukuNotValid(buku)) {
                JOptionPane.showMessageDialog(this, "Data Buku Tidak Boleh Kosong");
                return;
            } else if(isDataBukuDuplicate(buku)){
                JOptionPane.showMessageDialog(this, "Buku Terduplikat dengan Buku Nomor " + (new BukuServiceImpl()).getNomorBukuDuplicate(buku));
                return;
            }

            dataBuku.set(selectedRow - 1, buku);
            tableModel.removeRow(selectedRow);
            (new BukuRepositoryImpl()).update(selectedRow + 1, buku);
            loadDataBuku();

            JOptionPane.showMessageDialog(this, "Buku berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            // Bersihkan input setelah tambah
            bersihkanInput();
            btnAdd.setText("Tambah Buku");
        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }finally {
            btnBersihkan.setEnabled(true);
            btnEdit.setEnabled(false);
            loadDataBuku();
        }

    }

    private void tambahBuku() {
        btnAdd.setText("Tambah Buku");
        String judul = txtJudul.getText();
        String penulis = txtPenulis.getText();
        String penerbit = txtPenerbit.getText();
        String tahunTerbitStr = txtTahunTerbit.getText();

        try {
            int tahunTerbit = Integer.parseInt(tahunTerbitStr);

            // Tambah baris ke tabel
            Vector<Object> baris = new Vector<>();
            baris.add(dataBuku.size() + 1);
            baris.add(judul);
            baris.add(penulis);
            baris.add(penerbit);
            baris.add(tahunTerbit);

            Buku buku = new Buku(judul, penulis, penerbit, tahunTerbit);

            if (isDataBukuNotValid(buku)) {
                JOptionPane.showMessageDialog(this, "Data Buku Tidak Boleh Kosong");
                return;
            } if(isDataBukuDuplicate(buku)){
                JOptionPane.showMessageDialog(this, "Buku Terduplikat dengan Buku Nomor " + (new BukuServiceImpl()).getNomorBukuDuplicate(buku));
                return;
            }

            dataBuku.add(buku);
            tableModel.addRow(baris);
            (new BukuRepositoryImpl()).add(buku);

            JOptionPane.showMessageDialog(this, "Buku berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            // Bersihkan input setelah tambah
            bersihkanInput();
        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            loadDataBuku();
        }
    }

    private void bersihkanInput() {
        txtJudul.setText("");
        txtPenulis.setText("");
        txtPenerbit.setText("");
        txtTahunTerbit.setText("");
        loadDataBuku();
    }

    private void cariBuku() {
        String kataKunci = txtCari.getText().toLowerCase();

        // Reset tabel
        tableModel.setRowCount(0);

        // Filter dan tampilkan hasil pencarian
        for (Buku buku : dataBuku) {
            if (buku.getPenulis().toLowerCase().contains(kataKunci) ||
                    buku.getJudul().toLowerCase().contains(kataKunci)   ||
                    buku.getPenerbit().toLowerCase().contains(kataKunci)
            ) {

                Vector<Object> baris = new Vector<>();
                baris.add(dataBuku.indexOf(buku) + 1);
                baris.add(buku.getJudul());
                baris.add(buku.getPenulis());
                baris.add(buku.getPenerbit());
                baris.add(buku.getTahunTerbit());

                tableModel.addRow(baris);
            }
        }
    }

    private void loadDataBuku(){
        ArrayList<Buku> booksFromDb = (new BukuRepositoryImpl()).getAll();
        tableModel.setRowCount(0);
        dataBuku.clear();

        int urutanBuku = 1;
        for(var book : booksFromDb){
            Vector<Object> baris = new Vector<>();
            baris.add(urutanBuku);
            baris.add(book.getJudul());
            baris.add(book.getPenulis());
            baris.add(book.getPenerbit());
            baris.add(book.getTahunTerbit());

            dataBuku.add(new Buku(book.getJudul(), book.getPenulis(), book.getPenerbit(), book.getTahunTerbit()));
            tableModel.addRow(baris);

            urutanBuku++;
        }
    }
}
