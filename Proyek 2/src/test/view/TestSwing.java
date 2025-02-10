package test.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class TestSwing extends JFrame {
    private JTextField txtJudul, txtPenulis, txtPenerbit, txtTahunTerbit, txtSearch;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch;
    private JTable tableBuku;
    private DefaultTableModel tableModel;

    public TestSwing() {
        setTitle("Perpustakaan CRUD");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel untuk input form
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(5, 2, 10, 10));

        panelForm.add(new JLabel("Judul:"));
        txtJudul = new JTextField();
        panelForm.add(txtJudul);

        panelForm.add(new JLabel("Penulis:"));
        txtPenulis = new JTextField();
        panelForm.add(txtPenulis);

        panelForm.add(new JLabel("Penerbit:"));
        txtPenerbit = new JTextField();
        panelForm.add(txtPenerbit);

        panelForm.add(new JLabel("Tahun Terbit:"));
        txtTahunTerbit = new JTextField();
        panelForm.add(txtTahunTerbit);

        // Panel untuk tabel dan buttons
        JPanel panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());

        // Tabel untuk menampilkan data buku
        tableModel = new DefaultTableModel(new String[]{"ID", "Judul", "Penulis", "Penerbit", "Tahun Terbit"}, 0);
        tableBuku = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableBuku);
        panelTable.add(scrollPane, BorderLayout.CENTER);

        // Panel untuk fitur search dan tombol
        JPanel panelSearch = new JPanel();
        panelSearch.setLayout(new FlowLayout());

        panelSearch.add(new JLabel("Cari Buku:"));
        txtSearch = new JTextField(20);
        panelSearch.add(txtSearch);

        btnSearch = new JButton("Cari");
        panelSearch.add(btnSearch);

        btnAdd = new JButton("Tambah Buku");
        btnUpdate = new JButton("Update Buku");
        btnDelete = new JButton("Hapus Buku");

        panelSearch.add(btnAdd);
        panelSearch.add(btnUpdate);
        panelSearch.add(btnDelete);

        // Add panel to JFrame
        add(panelForm, BorderLayout.NORTH);
        add(panelTable, BorderLayout.CENTER);
        add(panelSearch, BorderLayout.SOUTH);

        // Event listener untuk tombol
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBuku();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBuku();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBuku();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBuku();
            }
        });
    }

    private void addBuku() {
        String judul = txtJudul.getText();
        String penulis = txtPenulis.getText();
        String penerbit = txtPenerbit.getText();
        String tahunTerbit = txtTahunTerbit.getText();

        // Pastikan tahun terbit adalah angka
        try {
            int tahun = Integer.parseInt(tahunTerbit);

            // Memanggil metode untuk menambahkan buku
            // Logic untuk menambah buku ke database akan ditambahkan di controller
            // Contoh, misalnya ke BukuController:
            // BukuController.addBuku(new Buku(judul, penulis, penerbit, tahun));

            // Setelah berhasil menambah, tampilkan data di tabel
            tableModel.addRow(new Object[]{null, judul, penulis, penerbit, tahun});

            // Clear input fields setelah menambah buku
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tahun Terbit harus berupa angka!");
        }
    }

    private void updateBuku() {
        int row = tableBuku.getSelectedRow();
        if (row != -1) {
            String judul = txtJudul.getText();
            String penulis = txtPenulis.getText();
            String penerbit = txtPenerbit.getText();
            String tahunTerbit = txtTahunTerbit.getText();

            // Pastikan tahun terbit adalah angka
            try {
                int tahun = Integer.parseInt(tahunTerbit);
                // Update row di tabel
                tableModel.setValueAt(judul, row, 1);
                tableModel.setValueAt(penulis, row, 2);
                tableModel.setValueAt(penerbit, row, 3);
                tableModel.setValueAt(tahun, row, 4);

                // Logic untuk update buku ke database bisa ditambahkan di controller
                // BukuController.updateBuku(new Buku(id, judul, penulis, penerbit, tahun));

                clearFields();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tahun Terbit harus berupa angka!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih buku yang akan diupdate!");
        }
    }

    private void deleteBuku() {
        int row = tableBuku.getSelectedRow();
        if (row != -1) {
            // Hapus data dari tabel
            tableModel.removeRow(row);

            // Logic untuk menghapus buku dari database bisa ditambahkan di controller
            // BukuController.deleteBuku(id);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih buku yang akan dihapus!");
        }
    }

    private void searchBuku() {
        String searchTerm = txtSearch.getText().toLowerCase();

        // Logic untuk mencari buku di database berdasarkan judul/penulis/penerbit bisa ditambahkan di controller
        // Misalnya, memfilter buku di tabel berdasarkan pencarian
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String judul = tableModel.getValueAt(i, 1).toString().toLowerCase();
            String penulis = tableModel.getValueAt(i, 2).toString().toLowerCase();
            String penerbit = tableModel.getValueAt(i, 3).toString().toLowerCase();

            if (judul.contains(searchTerm) || penulis.contains(searchTerm) || penerbit.contains(searchTerm)) {
                tableBuku.setRowSelectionInterval(i, i);
            }
        }
    }

    private void clearFields() {
        txtJudul.setText("");
        txtPenulis.setText("");
        txtPenerbit.setText("");
        txtTahunTerbit.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TestSwing().setVisible(true);
        });
    }
}
