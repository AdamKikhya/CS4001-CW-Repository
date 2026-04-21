import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * GadgetShop class provides a Swing GUI for managing a collection of gadgets.
 * Users can add Mobile phones and MP3 players, display all gadgets,
 * make calls, download music, and delete gadgets.
 * All output is shown in the built-in output panel and in the terminal.
 *
 * CS4001 Coursework - Gadget Shop
 */
public class GadgetShop extends JFrame implements ActionListener {

    private ArrayList<Gadget> gadgets = new ArrayList<Gadget>();

    // Text fields
    private JTextField txtModel;
    private JTextField txtPrice;
    private JTextField txtWeight;
    private JTextField txtSize;
    private JTextField txtCredit;
    private JTextField txtMemory;
    private JTextField txtPhoneNo;
    private JTextField txtDuration;
    private JTextField txtDownload;
    private JTextField txtDisplayNumber;

    // Buttons
    private JButton btnAddMobile;
    private JButton btnAddMP3;
    private JButton btnClear;
    private JButton btnDisplayAll;
    private JButton btnMakeCall;
    private JButton btnDownloadMusic;
    private JButton btnDelete;

    // Output display area
    private JTextArea outputArea;

    /**
     * Constructor: builds and displays the Gadget Shop GUI.
     */
    public GadgetShop() {
        super("Gadget Shop");

        // Colour palette
        Color darkBg     = new Color(30, 30, 40);
        Color panelBg    = new Color(45, 45, 60);
        Color accent     = new Color(0, 180, 255);
        Color btnGreen   = new Color(40, 180, 80);
        Color btnRed     = new Color(220, 60, 60);
        Color btnOrange  = new Color(230, 140, 0);
        Color btnPurple  = new Color(140, 80, 220);
        Color textColour = Color.WHITE;
        Font  labelFont  = new Font("Segoe UI", Font.BOLD, 12);
        Font  fieldFont  = new Font("Segoe UI", Font.PLAIN, 12);
        Font  btnFont    = new Font("Segoe UI", Font.BOLD, 12);

        // Initialise and style text fields
        txtModel         = makeField(10, fieldFont, panelBg, textColour);
        txtPrice         = makeField(6,  fieldFont, panelBg, textColour);
        txtWeight        = makeField(6,  fieldFont, panelBg, textColour);
        txtSize          = makeField(10, fieldFont, panelBg, textColour);
        txtCredit        = makeField(6,  fieldFont, panelBg, textColour);
        txtMemory        = makeField(6,  fieldFont, panelBg, textColour);
        txtPhoneNo       = makeField(10, fieldFont, panelBg, textColour);
        txtDuration      = makeField(6,  fieldFont, panelBg, textColour);
        txtDownload      = makeField(6,  fieldFont, panelBg, textColour);
        txtDisplayNumber = makeField(4,  fieldFont, panelBg, textColour);

        // Initialise and style buttons
        btnAddMobile     = makeButton("Add Mobile",     btnGreen,  btnFont);
        btnAddMP3        = makeButton("Add MP3",        btnGreen,  btnFont);
        btnClear         = makeButton("Clear",          btnRed,    btnFont);
        btnDisplayAll    = makeButton("Display All",    accent,    btnFont);
        btnMakeCall      = makeButton("Make A Call",    btnOrange, btnFont);
        btnDownloadMusic = makeButton("Download Music", btnOrange, btnFont);
        btnDelete        = makeButton("Delete Gadget",  btnPurple, btnFont);

        // Register action listeners
        btnAddMobile.addActionListener(this);
        btnAddMP3.addActionListener(this);
        btnClear.addActionListener(this);
        btnDisplayAll.addActionListener(this);
        btnMakeCall.addActionListener(this);
        btnDownloadMusic.addActionListener(this);
        btnDelete.addActionListener(this);

        // Output area - mirrors all System.out output inside the GUI window
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        outputArea.setBackground(new Color(20, 20, 28));
        outputArea.setForeground(new Color(180, 220, 255));
        outputArea.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(accent, 1),
            "Output",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            labelFont, accent));
        outputScroll.setBackground(darkBg);

        // Redirect System.out to the output area while keeping terminal output
        redirectSystemOut(outputArea);

        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));
        headerPanel.setBackground(new Color(20, 20, 30));
        JLabel titleLabel = new JLabel("GADGET SHOP");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(accent);
        JLabel subLabel = new JLabel("CS4001 Coursework");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        subLabel.setForeground(new Color(160, 160, 180));
        headerPanel.add(titleLabel);
        headerPanel.add(subLabel);

        // Build input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(darkBg);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(8, 8, 4, 8),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(accent, 1),
                "Gadget Details",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                labelFont, accent)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        // Row 0: Model | Price | Weight | Size
        addLabelField(inputPanel, gbc, "Model:",          txtModel,         0,  0, labelFont, darkBg, accent);
        addLabelField(inputPanel, gbc, "Price (\u00A3):",      txtPrice,         2,  0, labelFont, darkBg, accent);
        addLabelField(inputPanel, gbc, "Weight (g):",     txtWeight,        4,  0, labelFont, darkBg, accent);
        addLabelField(inputPanel, gbc, "Size:",           txtSize,          6,  0, labelFont, darkBg, accent);

        JLabel sizeHint = new JLabel("e.g. 71mm x 137mm x 9mm");
        sizeHint.setFont(new Font("Segoe UI", Font.ITALIC, 10));
        sizeHint.setForeground(Color.GRAY);
        gbc.gridx = 8;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(sizeHint, gbc);

        // Row 1: Credit | Memory | Phone No | Duration | Download | Item No
        addLabelField(inputPanel, gbc, "Credit (min):",   txtCredit,        0,  1, labelFont, darkBg, accent);
        addLabelField(inputPanel, gbc, "Memory (MB):",    txtMemory,        2,  1, labelFont, darkBg, accent);
        addLabelField(inputPanel, gbc, "Phone No:",       txtPhoneNo,       4,  1, labelFont, darkBg, accent);
        addLabelField(inputPanel, gbc, "Duration (min):", txtDuration,      6,  1, labelFont, darkBg, accent);
        addLabelField(inputPanel, gbc, "Download (MB):",  txtDownload,      8,  1, labelFont, darkBg, accent);
        addLabelField(inputPanel, gbc, "Item No:",        txtDisplayNumber, 10, 1, labelFont, darkBg, accent);

        // Build button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        buttonPanel.setBackground(new Color(20, 20, 30));
        buttonPanel.add(btnAddMobile);
        buttonPanel.add(btnAddMP3);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnDisplayAll);
        buttonPanel.add(btnMakeCall);
        buttonPanel.add(btnDownloadMusic);
        buttonPanel.add(btnDelete);

        // Centre panel: input fields on top, output area below
        JPanel centrePanel = new JPanel(new BorderLayout(0, 4));
        centrePanel.setBackground(darkBg);
        centrePanel.add(inputPanel,   BorderLayout.NORTH);
        centrePanel.add(outputScroll, BorderLayout.CENTER);

        // Assemble frame
        getContentPane().setBackground(darkBg);
        setLayout(new BorderLayout());
        add(headerPanel,  BorderLayout.NORTH);
        add(centrePanel,  BorderLayout.CENTER);
        add(buttonPanel,  BorderLayout.SOUTH);

        setSize(920, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Redirects System.out so output appears in the GUI panel and the terminal.
     */
    private void redirectSystemOut(JTextArea area) {
        PrintStream original = System.out;
        OutputStream stream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                original.write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                String text = new String(b, off, len, java.nio.charset.StandardCharsets.UTF_8);
                SwingUtilities.invokeLater(() -> {
                    area.append(text);
                    area.setCaretPosition(area.getDocument().getLength());
                });
                original.write(b, off, len);
            }
        };
        System.setOut(new PrintStream(stream, true));
    }

    /**
     * Helper: creates a styled JTextField.
     */
    private JTextField makeField(int cols, Font font, Color bg, Color fg) {
        JTextField field = new JTextField(cols);
        field.setFont(font);
        field.setBackground(bg);
        field.setForeground(fg);
        field.setCaretColor(fg);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 120), 1),
            BorderFactory.createEmptyBorder(2, 4, 2, 4)));
        return field;
    }

    /**
     * Helper: creates a styled JButton.
     */
    private JButton makeButton(String text, Color bg, Font font) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(7, 14, 7, 14));
        return btn;
    }

    /**
     * Helper: adds a styled JLabel and JTextField pair to a GridBagLayout panel.
     */
    private void addLabelField(JPanel panel, GridBagConstraints gbc,
                               String label, JTextField field, int col, int row,
                               Font labelFont, Color bg, Color fg) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(labelFont);
        lbl.setForeground(fg);
        lbl.setBackground(bg);

        gbc.gridx   = col;
        gbc.gridy   = row;
        gbc.weightx = 0;
        panel.add(lbl, gbc);

        gbc.gridx   = col + 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

    // ── Input helper methods ──────────────────────────────────────────────

    /** Returns the model text field value. */
    private String getModel() {
        return txtModel.getText();
    }

    /** Returns the size text field value. */
    private String getSizeInput() {
        return txtSize.getText();
    }

    /** Returns the phone number text field value. */
    private String getPhoneNo() {
        return txtPhoneNo.getText();
    }

    /** Parses and returns the price text field as a double. */
    private double getPrice() {
        return Double.parseDouble(txtPrice.getText());
    }

    /** Parses and returns the weight text field as an int. */
    private int getWeight() {
        return Integer.parseInt(txtWeight.getText());
    }

    /** Parses and returns the credit text field as an int. */
    private int getCredit() {
        return Integer.parseInt(txtCredit.getText());
    }

    /** Parses and returns the memory text field as an int. */
    private int getMemory() {
        return Integer.parseInt(txtMemory.getText());
    }

    /** Parses and returns the duration text field as an int. */
    private int getDuration() {
        return Integer.parseInt(txtDuration.getText());
    }

    /** Parses and returns the download size text field as an int. */
    private int getDownload() {
        return Integer.parseInt(txtDownload.getText());
    }

    /**
     * Reads and validates the item number from the display number text field.
     * Returns -1 if the value is not a valid integer or is out of range.
     */
    private int getDisplayNumber() {
        int displayNumber = -1;
        try {
            displayNumber = Integer.parseInt(txtDisplayNumber.getText());
            if (displayNumber < 0 || displayNumber >= gadgets.size()) {
                JOptionPane.showMessageDialog(this, "Number out of range");
                displayNumber = -1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer");
            displayNumber = -1;
        }
        return displayNumber;
    }

    // ── Button action methods ─────────────────────────────────────────────

    /**
     * Reads input fields and adds a new Mobile to the gadgets list.
     */
    private void addMobileAction() {
        Mobile mobile = new Mobile(getModel(), getPrice(), getWeight(), getSizeInput(), getCredit());
        gadgets.add(mobile);
        System.out.println("Mobile added. Total gadgets: " + gadgets.size());
    }

    /**
     * Reads input fields and adds a new MP3 player to the gadgets list.
     */
    private void addMP3Action() {
        MP3 mp3 = new MP3(getModel(), getPrice(), getWeight(), getSizeInput(), getMemory());
        gadgets.add(mp3);
        System.out.println("MP3 added. Total gadgets: " + gadgets.size());
    }

    /**
     * Clears all 10 text fields.
     */
    private void clearAction() {
        txtModel.setText("");
        txtPrice.setText("");
        txtWeight.setText("");
        txtSize.setText("");
        txtCredit.setText("");
        txtMemory.setText("");
        txtPhoneNo.setText("");
        txtDuration.setText("");
        txtDownload.setText("");
        txtDisplayNumber.setText("");
    }

    /**
     * Prints every gadget in the list to the output area with its index number.
     */
    private void displayAllAction() {
        System.out.println("=== All Gadgets ===");
        for (int i = 0; i < gadgets.size(); i++) {
            System.out.println("--- Item " + i + " ---");
            gadgets.get(i).display();
        }
        System.out.println("===================");
    }

    /**
     * Gets the Mobile at the specified index and calls makeCall().
     * Shows a dialog if the selected gadget is not a Mobile.
     */
    private void makeCallAction() {
        int idx = getDisplayNumber();
        if (idx != -1) {
            try {
                Mobile mobile = (Mobile) gadgets.get(idx);
                mobile.makeCall(getPhoneNo(), getDuration());
            } catch (ClassCastException e) {
                JOptionPane.showMessageDialog(this, "Selected gadget is not a Mobile phone");
            }
        }
    }

    /**
     * Gets the MP3 at the specified index and calls downloadMusic().
     * Shows a dialog if the selected gadget is not an MP3 player.
     */
    private void downloadMusicAction() {
        int idx = getDisplayNumber();
        if (idx != -1) {
            try {
                MP3 mp3 = (MP3) gadgets.get(idx);
                mp3.downloadMusic(getDownload());
            } catch (ClassCastException e) {
                JOptionPane.showMessageDialog(this, "Selected gadget is not an MP3 player");
            }
        }
    }

    /**
     * Deletes the gadget at the specified index (additional feature).
     */
    private void deleteGadgetAction() {
        int idx = getDisplayNumber();
        if (idx != -1) {
            Gadget removed = gadgets.remove(idx);
            JOptionPane.showMessageDialog(this,
                "Gadget \"" + removed.getModel() + "\" removed. Remaining: " + gadgets.size());
        }
    }

    // ── ActionListener ────────────────────────────────────────────────────

    /**
     * Routes button clicks to the appropriate action method.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddMobile) {
            addMobileAction();
        } else if (e.getSource() == btnAddMP3) {
            addMP3Action();
        } else if (e.getSource() == btnClear) {
            clearAction();
        } else if (e.getSource() == btnDisplayAll) {
            displayAllAction();
        } else if (e.getSource() == btnMakeCall) {
            makeCallAction();
        } else if (e.getSource() == btnDownloadMusic) {
            downloadMusicAction();
        } else if (e.getSource() == btnDelete) {
            deleteGadgetAction();
        }
    }

    // ── Main ──────────────────────────────────────────────────────────────

    /**
     * Entry point — creates and displays the GadgetShop window.
     */
    public static void main(String[] args) {
        new GadgetShop();
    }
}
