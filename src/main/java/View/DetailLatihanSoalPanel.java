/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;
import model.PaketSoal;
import dao.LatihanSoalDao;
import model.LatihanSoal;
import model.PaketSoal;
import model.Pengguna;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import dao.JawabanDao;
import model.Jawaban;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HP VICTUS
 */
public class DetailLatihanSoalPanel extends javax.swing.JPanel {

    /**
     * Creates new form DetailLatihanSoalPanel
     */
    public DetailLatihanSoalPanel() {
        initComponents();
    }

private LatihanSoalDao latihanDao = new LatihanSoalDao();
private JawabanDao jawabanDao = new JawabanDao();
private Map<Integer, String> jawabanDipilih =
        new HashMap<>();
private Map<Integer, String> kunciJawaban =
        new HashMap<>();
private List<LatihanSoal> daftarSoal = new ArrayList<>();
private PaketSoal paketAktif;
private int nomorAktif = 0;
private Runnable aksiKembali;

public void setAksiKembali(Runnable aksi) {
    this.aksiKembali = aksi;
}


public void setPaketSoal(PaketSoal paket) {

    this.paketAktif = paket;

    jawabanDipilih.clear();
    kunciJawaban.clear();

    lblMateri.setText(paket.getNamaPaketSoal());

    lblTingkat.setText(
        "Tingkat Kesulitan: " +
        paket.getTingkatKesulitan()
    );

    daftarSoal = latihanDao.getByPaketId(
        paket.getIdPaketSoal()
    );

    nomorAktif = 0;

    if (daftarSoal == null || daftarSoal.isEmpty()) {

        txtPertanyaan.setText(
            "Belum ada soal dalam paket ini."
        );

        lblNomorsoal.setText("Soal 0 dari 0");

        return;
    }

    if (daftarSoal.get(0).getMateri() != null) {

        lblNamamateri.setText(
            daftarSoal.get(0).getMateri().getNamaMateri()
        );

        lblTopik.setText(
            "Topik materi: " +
            daftarSoal.get(0).getMateri().getTopik()
        );
    }

    tampilkanSoal();
}


private void tampilkanSoal() {
    if (daftarSoal.isEmpty()) {
        return;
    }

    LatihanSoal soal = daftarSoal.get(nomorAktif);

    int idSoal = soal.getIdLatihanSoal();

    txtPertanyaan.setText(soal.getPertanyaan());

    lblNomorsoal.setText(
        "Soal " + (nomorAktif + 1) +
        " dari " + daftarSoal.size()
    );

    grupJawaban.clearSelection();

    // Ambil pilihan jawaban dari database
    List<Jawaban> opsi =
        jawabanDao.getByLatihanId(idSoal);

    javax.swing.JRadioButton[] tombol = {
        btnA, btnB, btnC, btnD, btnE
    };

    String[] label = {"A. ", "B. ", "C. ", "D. ", "E. "};

    for (int i = 0; i < tombol.length; i++) {

        if (i < opsi.size()) {

            String pilihan =
                opsi.get(i).getPilihanJawaban();

            tombol[i].setText(label[i] + pilihan);
            tombol[i].setVisible(true);

            if ("Benar".equalsIgnoreCase(
                    opsi.get(i).getKunciJawaban())) {

                kunciJawaban.put(idSoal, pilihan);
            }

        } else {

            tombol[i].setText("");
            tombol[i].setVisible(false);
        }
    }

    // Kembalikan pilihan pelajar jika sebelumnya sudah dipilih
    String pilihanSebelumnya =
        jawabanDipilih.get(idSoal);

    if (pilihanSebelumnya != null) {

        for (int i = 0; i < opsi.size(); i++) {

            if (opsi.get(i).getPilihanJawaban()
                    .equals(pilihanSebelumnya)) {

                tombol[i].setSelected(true);
                break;
            }
        }
    }

    btnSebelumnya.setEnabled(nomorAktif > 0);

    btnBerikutnya.setEnabled(
        nomorAktif < daftarSoal.size() - 1
    );

    JButton[] tombolNomor = {
        btnSoal1, btnSoal2, btnSoal3,
        btnSoal4, btnSoal5, btnSoal6,
        btnSoal7, btnSoal8, btnSoal9,
        btnSoal10
    };

    for (int i = 0; i < tombolNomor.length; i++) {

        tombolNomor[i].setVisible(
            i < daftarSoal.size()
        );

        tombolNomor[i].setEnabled(
            i < daftarSoal.size()
        );
    }
}

private void simpanJawabanSementara() {

    if (daftarSoal.isEmpty()) {
        return;
    }

    LatihanSoal soal = daftarSoal.get(nomorAktif);

    javax.swing.JRadioButton[] tombol = {
        btnA, btnB, btnC, btnD, btnE
    };

    for (javax.swing.JRadioButton rb : tombol) {

        if (rb.isSelected()) {

            String pilihan = rb.getText();

            // Hilangkan label A., B., C., D., atau E.
            jawabanDipilih.put(
                soal.getIdLatihanSoal(),
                pilihan.substring(3)
            );

            return;
        }
    }

    jawabanDipilih.remove(soal.getIdLatihanSoal());
}


private void pindahSoal(int nomor) {

    if (nomor >= 0 && nomor < daftarSoal.size()) {

        simpanJawabanSementara();

        nomorAktif = nomor;

        tampilkanSoal();
    }
}
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupJawaban = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblMateri = new javax.swing.JLabel();
        lblTopik = new javax.swing.JLabel();
        lblTingkat = new javax.swing.JLabel();
        lblNamamateri = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnKembali = new javax.swing.JButton();
        lblNomorsoal = new javax.swing.JLabel();
        btnA = new javax.swing.JRadioButton();
        btnB = new javax.swing.JRadioButton();
        btnC = new javax.swing.JRadioButton();
        btnD = new javax.swing.JRadioButton();
        btnE = new javax.swing.JRadioButton();
        btnSebelumnya = new javax.swing.JButton();
        btnBerikutnya = new javax.swing.JButton();
        btnSoal1 = new javax.swing.JButton();
        btnSoal2 = new javax.swing.JButton();
        btnSoal3 = new javax.swing.JButton();
        btnSoal4 = new javax.swing.JButton();
        btnSoal5 = new javax.swing.JButton();
        btnSoal6 = new javax.swing.JButton();
        btnSoal7 = new javax.swing.JButton();
        btnSoal8 = new javax.swing.JButton();
        btnSoal9 = new javax.swing.JButton();
        btnSoal10 = new javax.swing.JButton();
        btnKirim = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPertanyaan = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        jPanel2.setOpaque(false);

        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(1152, 648));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));

        lblMateri.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblMateri.setForeground(new java.awt.Color(102, 0, 102));
        lblMateri.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMateri.setText("NAMA PAKET SOAL");
        lblMateri.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblTopik.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTopik.setForeground(new java.awt.Color(153, 0, 153));
        lblTopik.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTopik.setText("Topik materi : Matematika");
        lblTopik.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblTingkat.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTingkat.setForeground(new java.awt.Color(153, 0, 153));
        lblTingkat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTingkat.setText("Tingkat Kesulitan: Easy");
        lblTingkat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblNamamateri.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblNamamateri.setForeground(new java.awt.Color(153, 0, 153));
        lblNamamateri.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamamateri.setText("Matematika");
        lblNamamateri.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblNamamateri)
                        .addGap(24, 24, 24)
                        .addComponent(lblTopik)
                        .addGap(18, 18, 18)
                        .addComponent(lblTingkat))
                    .addComponent(lblMateri))
                .addContainerGap(513, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblMateri)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTopik)
                    .addComponent(lblTingkat)
                    .addComponent(lblNamamateri))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setOpaque(false);

        btnKembali.setBackground(new java.awt.Color(102, 0, 102));
        btnKembali.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(255, 255, 255));
        btnKembali.setText("Kembali");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        lblNomorsoal.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblNomorsoal.setForeground(new java.awt.Color(153, 0, 153));
        lblNomorsoal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNomorsoal.setText("Soal 1 dari 10");
        lblNomorsoal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnA.setBackground(new java.awt.Color(255, 255, 255));
        grupJawaban.add(btnA);
        btnA.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        btnA.addActionListener(this::btnAActionPerformed);

        btnB.setBackground(new java.awt.Color(255, 255, 255));
        grupJawaban.add(btnB);
        btnB.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        btnB.addActionListener(this::btnBActionPerformed);

        btnC.setBackground(new java.awt.Color(255, 255, 255));
        grupJawaban.add(btnC);
        btnC.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        btnC.addActionListener(this::btnCActionPerformed);

        btnD.setBackground(new java.awt.Color(255, 255, 255));
        grupJawaban.add(btnD);
        btnD.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        btnD.addActionListener(this::btnDActionPerformed);

        btnE.setBackground(new java.awt.Color(255, 255, 255));
        grupJawaban.add(btnE);
        btnE.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        btnE.addActionListener(this::btnEActionPerformed);

        btnSebelumnya.setBackground(new java.awt.Color(102, 0, 102));
        btnSebelumnya.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSebelumnya.setForeground(new java.awt.Color(255, 255, 255));
        btnSebelumnya.setText("Sebelumnya");
        btnSebelumnya.addActionListener(this::btnSebelumnyaActionPerformed);

        btnBerikutnya.setBackground(new java.awt.Color(102, 0, 102));
        btnBerikutnya.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnBerikutnya.setForeground(new java.awt.Color(255, 255, 255));
        btnBerikutnya.setText("Berikutnya");
        btnBerikutnya.addActionListener(this::btnBerikutnyaActionPerformed);

        btnSoal1.setBackground(new java.awt.Color(102, 0, 102));
        btnSoal1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSoal1.setForeground(new java.awt.Color(255, 255, 255));
        btnSoal1.setText("1");
        btnSoal1.addActionListener(this::btnSoal1ActionPerformed);

        btnSoal2.setBackground(new java.awt.Color(102, 0, 102));
        btnSoal2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSoal2.setForeground(new java.awt.Color(255, 255, 255));
        btnSoal2.setText("2");
        btnSoal2.addActionListener(this::btnSoal2ActionPerformed);

        btnSoal3.setBackground(new java.awt.Color(102, 0, 102));
        btnSoal3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSoal3.setForeground(new java.awt.Color(255, 255, 255));
        btnSoal3.setText("3");
        btnSoal3.addActionListener(this::btnSoal3ActionPerformed);

        btnSoal4.setBackground(new java.awt.Color(102, 0, 102));
        btnSoal4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSoal4.setForeground(new java.awt.Color(255, 255, 255));
        btnSoal4.setText("4");
        btnSoal4.addActionListener(this::btnSoal4ActionPerformed);

        btnSoal5.setBackground(new java.awt.Color(102, 0, 102));
        btnSoal5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSoal5.setForeground(new java.awt.Color(255, 255, 255));
        btnSoal5.setText("5");
        btnSoal5.addActionListener(this::btnSoal5ActionPerformed);

        btnSoal6.setBackground(new java.awt.Color(102, 0, 102));
        btnSoal6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSoal6.setForeground(new java.awt.Color(255, 255, 255));
        btnSoal6.setText("6");
        btnSoal6.addActionListener(this::btnSoal6ActionPerformed);

        btnSoal7.setBackground(new java.awt.Color(102, 0, 102));
        btnSoal7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSoal7.setForeground(new java.awt.Color(255, 255, 255));
        btnSoal7.setText("7");
        btnSoal7.addActionListener(this::btnSoal7ActionPerformed);

        btnSoal8.setBackground(new java.awt.Color(102, 0, 102));
        btnSoal8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSoal8.setForeground(new java.awt.Color(255, 255, 255));
        btnSoal8.setText("8");
        btnSoal8.addActionListener(this::btnSoal8ActionPerformed);

        btnSoal9.setBackground(new java.awt.Color(102, 0, 102));
        btnSoal9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSoal9.setForeground(new java.awt.Color(255, 255, 255));
        btnSoal9.setText("9");
        btnSoal9.addActionListener(this::btnSoal9ActionPerformed);

        btnSoal10.setBackground(new java.awt.Color(102, 0, 102));
        btnSoal10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSoal10.setForeground(new java.awt.Color(255, 255, 255));
        btnSoal10.setText("10");
        btnSoal10.addActionListener(this::btnSoal10ActionPerformed);

        btnKirim.setBackground(new java.awt.Color(102, 0, 102));
        btnKirim.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnKirim.setForeground(new java.awt.Color(255, 255, 255));
        btnKirim.setText("Kirim Jawaban");
        btnKirim.addActionListener(this::btnKirimActionPerformed);

        txtPertanyaan.setEditable(false);
        txtPertanyaan.setBackground(new java.awt.Color(255, 255, 255));
        txtPertanyaan.setColumns(20);
        txtPertanyaan.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtPertanyaan.setLineWrap(true);
        txtPertanyaan.setRows(5);
        txtPertanyaan.setWrapStyleWord(true);
        txtPertanyaan.setOpaque(false);
        jScrollPane1.setViewportView(txtPertanyaan);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(789, Short.MAX_VALUE)
                        .addComponent(btnKembali))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSebelumnya)
                                    .addComponent(btnE, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBerikutnya))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnSoal1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btnSoal2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSoal3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSoal4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSoal5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSoal6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSoal7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSoal8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSoal9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSoal10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                                .addComponent(btnKirim))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblNomorsoal)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(32, 32, 32))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomorsoal)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSoal1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSoal2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSoal3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSoal4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSoal5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSoal6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSoal7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSoal8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSoal9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSoal10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKirim))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(btnA)
                .addGap(18, 18, 18)
                .addComponent(btnB)
                .addGap(18, 18, 18)
                .addComponent(btnC)
                .addGap(18, 18, 18)
                .addComponent(btnD)
                .addGap(18, 18, 18)
                .addComponent(btnE)
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSebelumnya)
                    .addComponent(btnBerikutnya))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP VICTUS\\Downloads\\a.jpeg")); // NOI18N

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
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 678, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
         if (aksiKembali != null) {
        aksiKembali.run();
    }
    // TODO add your handling code here:
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAActionPerformed

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCActionPerformed

    private void btnDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDActionPerformed

    private void btnEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEActionPerformed

    private void btnSebelumnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSebelumnyaActionPerformed
    pindahSoal(nomorAktif - 1);
     // TODO add your handling code here:
    }//GEN-LAST:event_btnSebelumnyaActionPerformed

    private void btnBerikutnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBerikutnyaActionPerformed
        pindahSoal(nomorAktif + 1);
   // TODO add your handling code here:
    }//GEN-LAST:event_btnBerikutnyaActionPerformed

    private void btnSoal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoal1ActionPerformed
    pindahSoal(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSoal1ActionPerformed

    private void btnSoal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoal2ActionPerformed
    pindahSoal(1);    // TODO add your handling code here:
    }//GEN-LAST:event_btnSoal2ActionPerformed

    private void btnSoal3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoal3ActionPerformed
    pindahSoal(2);    // TODO add your handling code here:
    }//GEN-LAST:event_btnSoal3ActionPerformed

    private void btnSoal4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoal4ActionPerformed
    pindahSoal(3);    // TODO add your handling code here:
    }//GEN-LAST:event_btnSoal4ActionPerformed

    private void btnSoal5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoal5ActionPerformed
    pindahSoal(4);    // TODO add your handling code here:
    }//GEN-LAST:event_btnSoal5ActionPerformed

    private void btnSoal6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoal6ActionPerformed
    pindahSoal(5);    // TODO add your handling code here:
    }//GEN-LAST:event_btnSoal6ActionPerformed

    private void btnSoal7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoal7ActionPerformed
    pindahSoal(6);    // TODO add your handling code here:
    }//GEN-LAST:event_btnSoal7ActionPerformed

    private void btnSoal8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoal8ActionPerformed
    pindahSoal(7);    // TODO add your handling code here:
    }//GEN-LAST:event_btnSoal8ActionPerformed

    private void btnSoal9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoal9ActionPerformed
    pindahSoal(8);    // TODO add your handling code here:
    }//GEN-LAST:event_btnSoal9ActionPerformed

    private void btnSoal10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoal10ActionPerformed
    pindahSoal(9);    // TODO add your handling code here:
    }//GEN-LAST:event_btnSoal10ActionPerformed

    private void btnKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimActionPerformed
        kirimJawaban();
    }//GEN-LAST:event_btnKirimActionPerformed

    private void kirimJawaban() {
        if (daftarSoal == null || daftarSoal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Paket soal belum memiliki pertanyaan.");
            return;
        }

        simpanJawabanSementara();

        int belumDijawab = daftarSoal.size() - jawabanDipilih.size();
        if (belumDijawab > 0) {
            int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Masih ada " + belumDijawab + " soal yang belum dijawab. Tetap kirim?",
                "Konfirmasi Jawaban",
                JOptionPane.YES_NO_OPTION
            );
            if (konfirmasi != JOptionPane.YES_OPTION) return;
        }

        int benar = 0;
java.util.List<String[]> detail = new java.util.ArrayList<>();

for (LatihanSoal soal : daftarSoal) {
    List<Jawaban> opsi = jawabanDao.getByLatihanId(soal.getIdLatihanSoal());
    String kunci = null;
    for (Jawaban jawaban : opsi) {
        if ("Benar".equalsIgnoreCase(jawaban.getKunciJawaban())) {
            kunci = jawaban.getPilihanJawaban();
            break;
        }
    }
    String pilihan = jawabanDipilih.get(soal.getIdLatihanSoal());
    boolean betul = (kunci != null && kunci.equals(pilihan));
    if (betul) benar++;

    detail.add(new String[]{
        pilihan == null ? "-" : pilihan,
        betul ? "Benar" : "Salah",
        String.valueOf(soal.getIdLatihanSoal())
    });
}

double skor = (benar * 100.0) / daftarSoal.size();
simpanHasil(daftarSoal.size(), benar, (int) Math.round(skor), detail);
            JOptionPane.showMessageDialog(
            this,
            "Latihan selesai!\n\n"
            + "Paket: " + paketAktif.getNamaPaketSoal() + "\n"
            + "Jawaban benar: " + benar + " dari " + daftarSoal.size() + "\n"
            + "Skor: " + String.format(java.util.Locale.US, "%.0f", skor),
            "Hasil Latihan",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void simpanHasil(int totalSoal, int jumlahBenar, int skor,
                         java.util.List<String[]> detail) {
    model.Pengguna user = controller.SessionManagerController.getPenggunaAktif();
    if (user == null) return;

    String sqlHasil = "INSERT INTO hasil_belajar "
        + "(tanggal_pengerjaan, total_soal, jumlah_benar, skor, pelajar_id_pengguna) "
        + "VALUES (CURDATE(), ?, ?, ?, ?)";
    String sqlDetail = "INSERT INTO detail_latihan "
        + "(jawaban_pelajar, status_jawaban, latihan_soal_id_latihan_soal, hasil_belajar_id_hasil) "
        + "VALUES (?, ?, ?, ?)";

    try (java.sql.Connection conn = config.EducateDB.getConnection()) {
        conn.setAutoCommit(false);
        try {
            int idHasil;
            try (java.sql.PreparedStatement ps = conn.prepareStatement(
                    sqlHasil, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, totalSoal);
                ps.setInt(2, jumlahBenar);
                ps.setInt(3, skor);
                ps.setInt(4, user.getIdPengguna());
                ps.executeUpdate();
                try (java.sql.ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    idHasil = rs.getInt(1);
                }
            }
            try (java.sql.PreparedStatement ps = conn.prepareStatement(sqlDetail)) {
                for (String[] d : detail) {   // {jawaban, status, idSoal}
                    ps.setString(1, d[0]);
                    ps.setString(2, d[1]);
                    ps.setInt(3, Integer.parseInt(d[2]));
                    ps.setInt(4, idHasil);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            conn.commit();
        } catch (java.sql.SQLException ex) {
            conn.rollback();
            throw ex;
        }
    } catch (java.sql.SQLException ex) {
        ex.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Hasil gagal disimpan: " + ex.getMessage());
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnA;
    private javax.swing.JRadioButton btnB;
    private javax.swing.JButton btnBerikutnya;
    private javax.swing.JRadioButton btnC;
    private javax.swing.JRadioButton btnD;
    private javax.swing.JRadioButton btnE;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnKirim;
    private javax.swing.JButton btnSebelumnya;
    private javax.swing.JButton btnSoal1;
    private javax.swing.JButton btnSoal10;
    private javax.swing.JButton btnSoal2;
    private javax.swing.JButton btnSoal3;
    private javax.swing.JButton btnSoal4;
    private javax.swing.JButton btnSoal5;
    private javax.swing.JButton btnSoal6;
    private javax.swing.JButton btnSoal7;
    private javax.swing.JButton btnSoal8;
    private javax.swing.JButton btnSoal9;
    private javax.swing.ButtonGroup grupJawaban;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMateri;
    private javax.swing.JLabel lblNamamateri;
    private javax.swing.JLabel lblNomorsoal;
    private javax.swing.JLabel lblTingkat;
    private javax.swing.JLabel lblTopik;
    private javax.swing.JTextArea txtPertanyaan;
    // End of variables declaration//GEN-END:variables
}
