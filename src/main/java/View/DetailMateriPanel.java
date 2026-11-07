/*
 * Panel daftar materi pembelajaran.
 * Komponen GUI Builder dipertahankan; bagian ini hanya menghubungkan aksi.
 */
package View;

import dao.MateriDao;
import model.Materi;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

public class DetailMateriPanel extends javax.swing.JPanel {

    private final MateriDao materiDao = new MateriDao();
    private String topikAktif = "Semua";
    private Runnable aksiKembali;

    public DetailMateriPanel() {
        initComponents();
        panelDaftarkelas.setLayout(new BoxLayout(panelDaftarkelas, BoxLayout.Y_AXIS));
        hubungkanTombol();
        if (!java.beans.Beans.isDesignTime()) {
            muatDaftarMateri();
        }
    }

    public void setAksiKembali(Runnable aksi) {
        this.aksiKembali = aksi;
    }

    public void setTopik(String topik) {
        topikAktif = (topik == null || topik.trim().isEmpty()) ? "Semua" : topik;
        lblPaket.setText("MATERI " + topikAktif.toUpperCase());
        muatDaftarMateri();
    }

    public void refreshData() {
        muatDaftarMateri();
    }

    private void hubungkanTombol() {
        btnSemuakelas.addActionListener(e -> setTopik("Semua"));
        btnMtk.addActionListener(e -> setTopik("Matematika"));
        btnIndo.addActionListener(e -> setTopik("Bahasa Indonesia"));
        btnInggris.addActionListener(e -> setTopik("Bahasa Inggris"));
    }

    private void muatDaftarMateri() {
        panelDaftarkelas.removeAll();
        List<Materi> semua = materiDao.getAll();
        int jumlah = 0;

        for (Materi materi : semua) {
            if (!cocokTopik(materi)) continue;

            KartuMateri kartu = new KartuMateri();
            kartu.setData(materi, this::tampilkanIsiMateri);
            kartu.setAlignmentX(Component.LEFT_ALIGNMENT);
            kartu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 190));
            panelDaftarkelas.add(kartu);
            panelDaftarkelas.add(Box.createVerticalStrut(12));
            jumlah++;
        }

        lblTotal.setText(jumlah + " materi tersedia untuk kategori " + topikAktif + ".");
        if (jumlah == 0) {
            javax.swing.JLabel kosong = new javax.swing.JLabel(
                "Belum ada materi untuk kategori " + topikAktif + "."
            );
            kosong.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelDaftarkelas.add(Box.createVerticalStrut(24));
            panelDaftarkelas.add(kosong);
        }

        panelDaftarkelas.revalidate();
        panelDaftarkelas.repaint();
    }

    private boolean cocokTopik(Materi materi) {
        if ("Semua".equalsIgnoreCase(topikAktif)) return true;
        String topik = materi.getTopik() == null ? "" : materi.getTopik().trim();
        return topik.equalsIgnoreCase(topikAktif)
            || topik.toLowerCase(java.util.Locale.ROOT)
                    .contains(topikAktif.toLowerCase(java.util.Locale.ROOT));
    }

    private void tampilkanIsiMateri(Materi materi) {
        String deskripsi = materi.getDeskripsiMateri();
        if (deskripsi == null || deskripsi.trim().isEmpty()) {
            deskripsi = "Belum ada deskripsi materi.";
        }
        String pesan = "<html><div style='width:420px'>"
            + "<h2>" + amanHtml(materi.getNamaMateri()) + "</h2>"
            + "<b>Topik:</b> " + amanHtml(materi.getTopik()) + "<br>"
            + "<b>Jenis:</b> " + amanHtml(materi.getTipeMateri()) + "<br><br>"
            + amanHtml(deskripsi).replace("\\n", "<br>")
            + "</div></html>";
        JOptionPane.showMessageDialog(this, pesan, "Detail Materi",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private String amanHtml(String teks) {
        if (teks == null) return "-";
        return teks.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblPaket = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        panelFilter = new javax.swing.JPanel();
        btnKembali = new javax.swing.JButton();
        btnSemuakelas = new javax.swing.JButton();
        btnMtk = new javax.swing.JButton();
        btnIndo = new javax.swing.JButton();
        btnInggris = new javax.swing.JButton();
        panelDaftarkelas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel5.setPreferredSize(new java.awt.Dimension(1152, 648));

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));

        lblPaket.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblPaket.setForeground(new java.awt.Color(102, 0, 102));
        lblPaket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaket.setText("PILIH MATERI PEMBELAJARAN");
        lblPaket.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblTotal.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(153, 0, 153));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal.setText("Pilih materi pembelajaran sesuai dengan topik yang kamu inginkan.");
        lblTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotal)
                    .addComponent(lblPaket))
                .addContainerGap(513, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblPaket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panelFilter.setBackground(new java.awt.Color(255, 255, 255));
        panelFilter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        panelFilter.setForeground(new java.awt.Color(255, 255, 255));
        panelFilter.setOpaque(false);

        btnKembali.setBackground(new java.awt.Color(102, 0, 102));
        btnKembali.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(255, 255, 255));
        btnKembali.setText("Kembali");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        btnSemuakelas.setBackground(new java.awt.Color(102, 0, 102));
        btnSemuakelas.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnSemuakelas.setForeground(new java.awt.Color(255, 255, 255));
        btnSemuakelas.setText("Semua Kelas");

        btnMtk.setBackground(new java.awt.Color(102, 0, 102));
        btnMtk.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnMtk.setForeground(new java.awt.Color(255, 255, 255));
        btnMtk.setText("Matematika");

        btnIndo.setBackground(new java.awt.Color(102, 0, 102));
        btnIndo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnIndo.setForeground(new java.awt.Color(255, 255, 255));
        btnIndo.setText("Bahasa Indonesia");

        btnInggris.setBackground(new java.awt.Color(102, 0, 102));
        btnInggris.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnInggris.setForeground(new java.awt.Color(255, 255, 255));
        btnInggris.setText("Bahasa Inggris");

        panelDaftarkelas.setBackground(new java.awt.Color(255, 255, 255));
        panelDaftarkelas.setOpaque(false);
        panelDaftarkelas.setLayout(new javax.swing.BoxLayout(panelDaftarkelas, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout panelFilterLayout = new javax.swing.GroupLayout(panelFilter);
        panelFilter.setLayout(panelFilterLayout);
        panelFilterLayout.setHorizontalGroup(
            panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFilterLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelDaftarkelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFilterLayout.createSequentialGroup()
                        .addComponent(btnSemuakelas, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnMtk, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnIndo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnInggris, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                        .addComponent(btnKembali)))
                .addGap(18, 18, 18))
        );
        panelFilterLayout.setVerticalGroup(
            panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSemuakelas)
                    .addComponent(btnMtk)
                    .addComponent(btnIndo)
                    .addGroup(panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInggris)
                        .addComponent(btnKembali)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelDaftarkelas, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP VICTUS\\Downloads\\a.jpeg")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {
        if (aksiKembali != null) aksiKembali.run();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIndo;
    private javax.swing.JButton btnInggris;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnMtk;
    private javax.swing.JButton btnSemuakelas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPaket;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel panelDaftarkelas;
    private javax.swing.JPanel panelFilter;
    // End of variables declaration//GEN-END:variables
}
