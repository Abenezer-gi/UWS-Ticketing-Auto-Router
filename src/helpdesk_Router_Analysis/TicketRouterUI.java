package helpdesk_Router_Analysis;

import helpdesk_Router_Ingestion.TicketFactory;
import helpdesk_router_model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class TicketRouterUI extends JFrame {

    // ── colours ────────────────────────────────────────────────────
    private static final Color UWS_YELLOW = new Color(245, 200, 66);
    private static final Color UWS_BLACK  = new Color(26, 26, 26);
    private static final Color BG_LIGHT   = new Color(235, 230, 222); // paper tone
    private static final Color WHITE      = Color.WHITE;
    private static final Color TEXT_MAIN  = new Color(30, 30, 30);
    private static final Color TEXT_HINT  = new Color(160, 155, 148);
    private static final Color BORDER_CLR = new Color(180, 175, 168);

    private static final Color GREEN_BG   = new Color(234, 243, 222);
    private static final Color GREEN_FG   = new Color(59, 109, 17);
    private static final Color AMBER_BG   = new Color(250, 238, 218);
    private static final Color AMBER_FG   = new Color(133, 79, 11);
    private static final Color RED_BG     = new Color(252, 235, 235);
    private static final Color RED_FG     = new Color(163, 45, 45);

    private static final Font FONT_INPUT  = new Font("SansSerif", Font.PLAIN,  15);
    private static final Font FONT_BTN    = new Font("SansSerif", Font.BOLD,   15);
    private static final Font FONT_UNIV   = new Font("SansSerif", Font.PLAIN,  12);
    private static final Font FONT_SUP    = new Font("SansSerif", Font.BOLD,   28);
    private static final Font FONT_SMALL  = new Font("SansSerif", Font.PLAIN,  11);
    private static final Font FONT_LABEL  = new Font("SansSerif", Font.PLAIN,  12);
    private static final Font FONT_BOLD   = new Font("SansSerif", Font.BOLD,   13);

    // ── widgets ────────────────────────────────────────────────────
    private final RoundTextField descInput   = new RoundTextField("Enter Description");
    private final JButton        submitBtn   = new RoundButton("SUBMIT");
    private final JPanel         resultPanel = new JPanel();
    private final JLabel         teamBadge   = new JLabel();
    private final JLabel         idVal       = new JLabel("—");
    private final JLabel         teamVal     = new JLabel("—");
    private final JLabel         summaryVal  = new JLabel("—");

    private int ticketCounter = 1000;

    // ══════════════════════════════════════════════════════════════
    public TicketRouterUI() {
        super("UWS Technology Services — Ticket Router");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // paper-textured background panel
        TexturedPanel root = new TexturedPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(40, 60, 40, 60));

        root.add(buildLogo());
        root.add(Box.createVerticalStrut(32));
        root.add(buildInputRow());
        root.add(Box.createVerticalStrut(16));
        root.add(buildSubmitRow());
        root.add(Box.createVerticalStrut(20));
        root.add(buildResultArea());

        setContentPane(root);
        pack();
        setMinimumSize(new Dimension(620, 380));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ── logo ───────────────────────────────────────────────────────
    private JPanel buildLogo() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        p.setAlignmentX(CENTER_ALIGNMENT);

        // load real UWS logo from resources folder
        JLabel shield = new JLabel();
        shield.setAlignmentX(CENTER_ALIGNMENT);
        try {
            ImageIcon raw = new ImageIcon("resources/uws_logo.webp.png");
            Image scaled = raw.getImage().getScaledInstance(280, -1, Image.SCALE_SMOOTH);
            shield.setIcon(new ImageIcon(scaled));
        } catch (Exception ex) {
            // fallback to drawn shield if image fails to load
            ShieldCanvas fallback = new ShieldCanvas();
            fallback.setAlignmentX(CENTER_ALIGNMENT);
            p.add(fallback);
        }
        p.add(shield);
        return p;
    }

    // ── input ──────────────────────────────────────────────────────
    private JPanel buildInputRow() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        p.setOpaque(false);
        descInput.setPreferredSize(new Dimension(480, 44));
        descInput.setFont(FONT_INPUT);
        p.add(descInput);
        return p;
    }

    // ── submit ─────────────────────────────────────────────────────
    private JPanel buildSubmitRow() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.setOpaque(false);
        submitBtn.setPreferredSize(new Dimension(160, 42));
        submitBtn.setFont(FONT_BTN);
        submitBtn.addActionListener(e -> routeTicket());
        p.add(submitBtn);
        return p;
    }

    // ── result ─────────────────────────────────────────────────────
    private JPanel buildResultArea() {
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setOpaque(false);
        resultPanel.setVisible(false);
        resultPanel.setAlignmentX(CENTER_ALIGNMENT);

        // badge
        teamBadge.setFont(FONT_BOLD);
        teamBadge.setOpaque(true);
        teamBadge.setBorder(new EmptyBorder(4, 14, 4, 14));
        teamBadge.setAlignmentX(CENTER_ALIGNMENT);
        resultPanel.add(teamBadge);
        resultPanel.add(Box.createVerticalStrut(12));

        // meta row
        JPanel meta = new JPanel(new GridLayout(1, 2, 10, 0));
        meta.setOpaque(false);
        meta.setMaximumSize(new Dimension(480, 60));
        meta.setAlignmentX(CENTER_ALIGNMENT);
        meta.add(metaCard("Ticket ID", idVal));
        meta.add(metaCard("Assigned Team", teamVal));
        resultPanel.add(meta);
        resultPanel.add(Box.createVerticalStrut(8));

        // summary
        JPanel sumCard = new RoundCard(WHITE);
        sumCard.setLayout(new BoxLayout(sumCard, BoxLayout.Y_AXIS));
        sumCard.setBorder(new EmptyBorder(8, 12, 8, 12));
        sumCard.setMaximumSize(new Dimension(480, 60));
        sumCard.setAlignmentX(CENTER_ALIGNMENT);
        JLabel sumLbl = new JLabel("SUMMARY");
        sumLbl.setFont(FONT_SMALL);
        sumLbl.setForeground(TEXT_HINT);
        summaryVal.setFont(FONT_LABEL);
        summaryVal.setForeground(TEXT_MAIN);
        sumCard.add(sumLbl);
        sumCard.add(summaryVal);
        resultPanel.add(sumCard);

        return resultPanel;
    }

    private JPanel metaCard(String label, JLabel val) {
        RoundCard card = new RoundCard(WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(8, 12, 8, 12));
        JLabel lbl = new JLabel(label.toUpperCase());
        lbl.setFont(FONT_SMALL);
        lbl.setForeground(TEXT_HINT);
        val.setFont(FONT_BOLD);
        val.setForeground(TEXT_MAIN);
        card.add(lbl);
        card.add(val);
        return card;
    }

    // ── routing ────────────────────────────────────────────────────
    private void routeTicket() {
        String desc = descInput.getText().trim();
        if (desc.isEmpty() || desc.equals("Enter Description")) return;

        submitBtn.setEnabled(false);
        submitBtn.setText("Routing...");

        SwingWorker<Final_Verdict, Void> worker = new SwingWorker<>() {
            @Override protected Final_Verdict doInBackground() throws Exception {
                Thread.sleep(700);
                helpdesk_router_model.Ticket ticket = TicketFactory.Ticket_Generator(desc);
                Processed_Data data = Ticket_Pre_Processor.Description_Cleaner(ticket);
                Ticket_Router_Engine engine = new Ticket_Router_Engine();
                return engine.Ticket_Grader(data);
            }
            @Override protected void done() {
                try {
                    Final_Verdict result = get();
                    ticketCounter++;
                    displayResult(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(TicketRouterUI.this, "Error: " + ex.getMessage());
                }
                submitBtn.setEnabled(true);
                submitBtn.setText("SUBMIT");
                descInput.clear();
            }
        };
        worker.execute();
    }

    private void displayResult(Final_Verdict result) {
        Team team = result.getTeam();
        String name = team.name().replace("_", " ");

        Color bg, fg;
        if (team == Team.HUMAN_REVIEW)     { bg = AMBER_BG; fg = AMBER_FG; }
        else if (team == Team.FAULTY_TRASH){ bg = RED_BG;   fg = RED_FG;   }
        else                               { bg = GREEN_BG;  fg = GREEN_FG; }

        teamBadge.setText("● " + name);
        teamBadge.setBackground(bg);
        teamBadge.setForeground(fg);

        idVal.setText("#" + ticketCounter);
        teamVal.setText(name);

        String desc = result.getFinal_Assigned_Ticket().getDescription();
        String summary = desc;
        for (int i = 0; i < desc.length(); i++) {
            char c = desc.charAt(i);
            if (c == '.' || c == '?' || c == '!') { summary = desc.substring(0, i + 1); break; }
        }
        if (summary.length() > 70) summary = summary.substring(0, 70) + "...";
        summaryVal.setText(summary);

        resultPanel.setVisible(true);
        pack();
    }

    // ══════════════════════════════════════════════════════════════
    // ── custom components ──────────────────────────────────────────

    /** Paper-textured background */
    static class TexturedPanel extends JPanel {
        private final BufferedImage texture;
        TexturedPanel() {
            texture = makeTexture();
            setOpaque(true);
        }
        private BufferedImage makeTexture() {
            int s = 128;
            BufferedImage img = new BufferedImage(s, s, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = img.createGraphics();
            g.setColor(new Color(235, 230, 222));
            g.fillRect(0, 0, s, s);
            // subtle noise
            java.util.Random rnd = new java.util.Random(42);
            for (int i = 0; i < 4000; i++) {
                int x = rnd.nextInt(s), y = rnd.nextInt(s);
                int v = 200 + rnd.nextInt(30);
                g.setColor(new Color(v, v - 5, v - 10, 40));
                g.fillRect(x, y, 1, 1);
            }
            g.dispose();
            return img;
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            int w = getWidth(), h = getHeight();
            int tw = texture.getWidth(), th = texture.getHeight();
            for (int x = 0; x < w; x += tw)
                for (int y = 0; y < h; y += th)
                    g2.drawImage(texture, x, y, null);
        }
    }

    /** Rounded text field with placeholder */
    static class RoundTextField extends JTextField {
        private final String placeholder;
        RoundTextField(String placeholder) {
            this.placeholder = placeholder;
            setOpaque(false);
            setBorder(new EmptyBorder(0, 16, 0, 16));
            setForeground(new Color(30, 30, 30));
        }
        public void clear() { setText(""); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), getHeight(), getHeight()));
            g2.setColor(new Color(180, 175, 168));
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(new RoundRectangle2D.Float(0.6f, 0.6f, getWidth() - 1.2f, getHeight() - 1.2f, getHeight(), getHeight()));
            super.paintComponent(g);
            if (getText().isEmpty() && !isFocusOwner()) {
                g2.setColor(new Color(160, 155, 148));
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(placeholder, 16, (getHeight() - fm.getHeight()) / 2 + fm.getAscent());
            }
        }
        @Override protected void paintBorder(Graphics g) {}
    }

    /** Rounded pill button */
    static class RoundButton extends JButton {
        RoundButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isPressed() ? new Color(220, 215, 207) : Color.WHITE);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), getHeight(), getHeight()));
            g2.setColor(new Color(180, 175, 168));
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(new RoundRectangle2D.Float(0.6f, 0.6f, getWidth() - 1.2f, getHeight() - 1.2f, getHeight(), getHeight()));
            FontMetrics fm = g2.getFontMetrics(getFont());
            int tx = (getWidth() - fm.stringWidth(getText())) / 2;
            int ty = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g2.setFont(getFont());
            g2.setColor(new Color(30, 30, 30));
            g2.drawString(getText(), tx, ty);
        }
    }

    /** Rounded card panel */
    static class RoundCard extends JPanel {
        private final Color bg;
        RoundCard(Color bg) { this.bg = bg; setOpaque(false); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bg);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
            super.paintComponent(g);
        }
    }

    /** UWS shield logo drawn with Graphics2D */
    static class ShieldCanvas extends JPanel {
        ShieldCanvas() {
            setPreferredSize(new Dimension(90, 88));
            setMaximumSize(new Dimension(90, 88));
            setOpaque(false);
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int cx = getWidth() / 2;

            // black outer
            g2.setColor(new Color(26, 26, 26));
            g2.fillRoundRect(cx - 36, 0, 72, 70, 16, 16);
            // black bottom chevron
            int[] bx = {cx-36, cx, cx+36, cx+26, cx, cx-26};
            int[] by = {58, 86, 58, 58, 78, 58};
            g2.fillPolygon(bx, by, 6);

            // yellow inner
            g2.setColor(new Color(245, 200, 66));
            g2.fillRoundRect(cx - 29, 5, 58, 58, 11, 11);
            int[] yx = {cx-29, cx, cx+29, cx+20, cx, cx-20};
            int[] yy = {58, 82, 58, 58, 74, 58};
            g2.fillPolygon(yx, yy, 6);

            // white S shape (3 bars carved to look like S)
            g2.setColor(Color.WHITE);
            // top bar
            g2.fillRoundRect(cx - 20, 11, 38, 11, 6, 6);
            // middle bar
            g2.fillRoundRect(cx - 20, 28, 38, 11, 6, 6);
            // bottom bar
            g2.fillRoundRect(cx - 20, 45, 38, 11, 6, 6);
            // carve yellow to form S gaps
            g2.setColor(new Color(245, 200, 66));
            g2.fillRect(cx - 20, 18, 16, 14);
            g2.fillRect(cx + 4,  35, 16, 14);

            // white inner chevron
            g2.setColor(Color.WHITE);
            int[] wx = {cx-20, cx, cx+20, cx+12, cx, cx-12};
            int[] wy = {58, 76, 58, 58, 70, 58};
            g2.fillPolygon(wx, wy, 6);
        }
    }

    // ── main ───────────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicketRouterUI::new);
    }
}
