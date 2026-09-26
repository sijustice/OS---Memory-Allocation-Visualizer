package oop;
import javax.swing.*;
import java.util.*;
import java.util.List;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OSfinalsGUI extends JFrame {

    // UI Components
    private JTextField txtBlockSize, txtProcessName, txtProcessSize;
    private JComboBox<String> comboStrategy;
    private JTable blockTable;
    private DefaultTableModel blockModel;
    private JPanel memoryVisualPanel;
    private JTextArea logArea;
    private List<Block> memory = new ArrayList<>();
    
    public OSfinalsGUI() {
        setTitle("Memory Allocation System");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Colors Initialization
        Color bgDark = new Color(30, 32, 40);
        Color panelBg = new Color(42, 45, 58);
        Color accentBlue = new Color(0, 150, 255);

        getContentPane().setBackground(bgDark);
        getContentPane().setLayout(new BorderLayout(10, 10));
        //====================================================
        // Header Panel
        //====================================================
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(panelBg);
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel(" Memory Allocation Dashboard");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(accentBlue);

        comboStrategy = new JComboBox<>(new String[] { "First Fit", "Best Fit" });
        comboStrategy.setFont(new Font("SansSerif", Font.PLAIN, 14));

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(comboStrategy, BorderLayout.EAST);
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Center Panel: Controls, visual blocks, and process list
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        centerPanel.setOpaque(false);

        // Left Sub-Panel: Inputs & Controls
        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.setOpaque(false);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        inputPanel.setBackground(panelBg);
        inputPanel.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(accentBlue),
                " Memory & Allocation Controls ",
                0,
                0,
                new Font("SansSerif", Font.BOLD, 12),
                accentBlue
            )
        );

        txtBlockSize = new JTextField();
        txtProcessName = new JTextField();
        txtProcessSize = new JTextField();

        JButton btnAddBlock = createStyledButton("Add Partition", accentBlue);
        JButton btnAllocate = createStyledButton("Allocate Job", new Color(46, 204, 113));
        JButton btnDeallocate = createStyledButton("Deallocate Selected", new Color(231, 76, 60));
        JButton btnCompact = createStyledButton("Compact Memory", new Color(155, 89, 182));

        JLabel label = new JLabel("Partition Size (KB):", SwingConstants.RIGHT);
        label.setForeground(new Color(255, 255, 255));
        inputPanel.add(label);
        inputPanel.add(txtBlockSize);
        inputPanel.add(btnAddBlock);
        JLabel label_1 = new JLabel("Job Name & Size:", SwingConstants.RIGHT);
        label_1.setForeground(new Color(255, 255, 255));
        inputPanel.add(label_1);

        JPanel procInput = new JPanel(new GridLayout(1, 2, 5, 5));
        procInput.setOpaque(false);
        procInput.add(txtProcessName);
        procInput.add(txtProcessSize);
        inputPanel.add(procInput);

        inputPanel.add(btnAllocate);
        inputPanel.add(btnDeallocate);
        inputPanel.add(btnCompact);

        leftPanel.add(inputPanel, BorderLayout.NORTH);

        // Table for Memory Blocks
        blockModel = new DefaultTableModel(
            new String[] { "Block ID", "Total Size", "Used Size", "Status" },
            0
        );
        blockTable = new JTable(blockModel);
        leftPanel.add(new JScrollPane(blockTable), BorderLayout.CENTER);

        centerPanel.add(leftPanel);

        // Right Sub-Panel: Dynamic Visualizer
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.setOpaque(false);

        memoryVisualPanel = new JPanel();
        memoryVisualPanel.setLayout(new BoxLayout(memoryVisualPanel, BoxLayout.Y_AXIS));
        memoryVisualPanel.setBackground(panelBg);

        JScrollPane visualScroll = new JScrollPane(memoryVisualPanel);
        visualScroll.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(accentBlue),
                " Visual Memory Layout ",
                0,
                0,
                new Font("SansSerif", Font.BOLD, 12),
                accentBlue
            )
        );

        rightPanel.add(visualScroll, BorderLayout.CENTER);
        centerPanel.add(rightPanel);

        getContentPane().add(centerPanel, BorderLayout.CENTER);

        // Bottom Console Log
        logArea = new JTextArea(4, 50);
        logArea.setEditable(false);
        logArea.setBackground(new Color(20, 22, 28));
        logArea.setForeground(Color.GREEN);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        getContentPane().add(new JScrollPane(logArea), BorderLayout.SOUTH);

        // Action Listeners
        btnAddBlock.addActionListener((ActionEvent e) -> onAddBlockClicked());
        btnAllocate.addActionListener((ActionEvent e) -> onAllocateClicked());
        btnDeallocate.addActionListener((ActionEvent e) -> onDeallocateClicked());
        btnCompact.addActionListener((ActionEvent e) -> onCompactClicked());
    }

    // =========================================================================
    // ACTION EVENT HANDLERS (DITO NIYO NALANG ADD)
    // =========================================================================
    
    
    //pa parse nalang ng rawSize into integer

    private void onAddBlockClicked() {
        String rawSize = txtBlockSize.getText().trim();
        System.out.println("DEBUG --- the raw size is["+ rawSize +"]");
        try{
        	int size = Integer.parseInt(rawSize);
        	
        	if(size <= 0){
        		log("Block size must be greater than 0.");
        		return;
        	}
	        int start = 0;
	        if(!memory.isEmpty()){
	        	Block last = memory.get(memory.size() - 1);
	        	start = last.getStart() + last.getSize();
	        }
	        	
	    	memory.add(new Block(start, size, "free", null));
	    	refreshDisplay();
	        log("Added block of size " + size + " KB.");
						                    
        } catch (NumberFormatException e) {
        	System.out.println("DEBUG - caught exception, message: " + e.getMessage());
            log("Please enter a valid number for block size.");
            }
         txtBlockSize.setText("");
    }
    

    private void onAllocateClicked() {
        String jobName = txtProcessName.getText().trim();
        String rawSize = txtProcessSize.getText().trim();
        String selectedStrategy = (String) comboStrategy.getSelectedItem(); // for first-fit or best-fit
        
        if (jobName.isEmpty()) {
            log("Please enter a job name.");
            return;
        }
        
        Boolean success = null;
        
        try {
        	
        	int size = Integer.parseInt(rawSize);
        	
        	if("First Fit".equals(selectedStrategy)) {
        		FirstFit firstFit = new FirstFit();
        		success = firstFit.allocate(memory, jobName, size);
        	} else {
        		// here mo lagay code marl - JK
        		// pwede mo naman gayahin format ng sakin ( if same us ng methods)
        	}
        	
        	if (success) {
                refreshDisplay();
                log("Allocated " + jobName + " (" + size + " KB) using " + selectedStrategy);
            } else {
                log("No block found for " + jobName + " (" + size + " KB).");
            }
        	
        } catch (NumberFormatException e) {
        	System.out.println("DEBUG - caught exception, message: " + e.getMessage());
            log("Please enter a valid number for block size.");
            }
        
        //here nalang for on allocation

        log("Action triggered: Allocate " + jobName + " (" + rawSize + " KB) using " + selectedStrategy);
    }

    private void onDeallocateClicked() {
        int selectedRow = blockTable.getSelectedRow();

        if (selectedRow == -1) {
            log("Please select a block from the table to deallocate.");
            return;
        }

        // Here nalang for Deallocation

        log("Action triggered: Deallocate selected row " + selectedRow);
    }

    private void onCompactClicked() {
        // for compaction

        log("Action triggered: Compact Memory");
    }

    // =========================================================================
    // UI HELPER & RENDERING METHODS
    // =========================================================================

    /**
     * Call this to clear and repopulate the table with updated block data.
     */
    private void refreshDisplay() {
        clearTable();
        clearVisualPanel();
 
        for (int i = 0; i < memory.size(); i++) {
            Block b = memory.get(i);
            boolean occupied = !b.isFree();
            String jobLabel = occupied ? b.getProcessId() : "Free";
            int usedSize = occupied ? b.getSize() : 0;
 
            addTableRow(i + 1, b.getSize(), usedSize, b.getStatus());
            addVisualBlock(i + 1, b.getSize(), jobLabel, occupied);
        }
    }
    
    
    public void addTableRow(int blockId, int totalSize, int usedSize, String status) {
        blockModel.addRow(new Object[] {
            "Block " + blockId,
            totalSize + " KB",
            usedSize + " KB",
            status
        });
    }

    /**
     * Call this to clear all existing rows in the table before re-rendering.
     */
    public void clearTable() {
        blockModel.setRowCount(0);
    }

    /**
     * Call this to render a visual memory block panel on the right side of the UI.
     */
    public void addVisualBlock(int blockId, int size, String allocatedJob, boolean isOccupied) {
        JPanel p = new JPanel(new BorderLayout());
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        p.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel label = new JLabel(
            "  Block " + blockId + " (" + size + " KB) — " + allocatedJob,
            SwingConstants.LEFT
        );
        label.setFont(new Font("SansSerif", Font.BOLD, 12));

        if (isOccupied) {
            p.setBackground(new Color(52, 152, 219)); // Blue pag occupied
            label.setForeground(Color.WHITE);
        } else {
            p.setBackground(new Color(189, 195, 199)); // Gray for free
        }

        p.add(label, BorderLayout.CENTER);
        memoryVisualPanel.add(p);
        memoryVisualPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        memoryVisualPanel.revalidate();
        memoryVisualPanel.repaint();
    }

    /**
     * Call this to clear all blocks from the right visual panel before re-rendering.
     */
    public void clearVisualPanel() {
        memoryVisualPanel.removeAll();
        memoryVisualPanel.revalidate();
        memoryVisualPanel.repaint();
    }

    /**
     * Call this method to print logs into the dark console at the bottom.
     */
    public void log(String message) {
        logArea.append("> " + message + "\n");
    }

    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 11));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OSfinalsGUI().setVisible(true));
    }
}