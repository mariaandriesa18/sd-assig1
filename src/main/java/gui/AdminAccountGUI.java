package gui;

import controller.DocumentController;
import controller.UserAccountController;
import entity.User;
import utils.MyUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class AdminAccountGUI extends JFrame {

    private JPanel mainPanel;
    private JLabel adminNameLB;
    private JTable reqTable;
    private JComboBox docTypeBox;
    private JLabel docTypeLB;

    private JLabel userTableLB;
    private JLabel seeReqLB;
    private JScrollPane usersScrollPane;
    private JTable table1;
    private JTextField newDocTF;
    private JLabel addDocLB;
    private JButton addButton;
    private JButton delDocBtn;
    private JButton backBtn;
    private JButton seeRequestsButton;
    private JPanel tabPanel;
    private String[][] data;
    private DefaultTableModel model;
    private  String  uid  = "";
    private  String  selectedDoc;

    private UserAccountController adminController = new UserAccountController();
    private  DocumentController documentController = new DocumentController();

    public AdminAccountGUI() throws HeadlessException {
        initialize();
    }

    public void initialize(){

        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        row1.setLayout(new GridLayout(4, 1, 5, 5));
        row2.setLayout(new GridLayout(1, 2, 5, 15));
        row1.setPreferredSize(new Dimension(800, 200));
        row2.setPreferredSize(new Dimension(800, 600));

        // 1ST ROW
        JPanel r1 = new JPanel();
        r1.setLayout(new GridLayout(1,2));
        adminNameLB = new JLabel("Hello, Admin!");
        adminNameLB.setFont(new Font("Verdana", Font.BOLD, 18));
        r1.add(adminNameLB);
        backBtn = new JButton("Back");
        r1.add(backBtn);
        row1.add(r1);

        JPanel r2 = new JPanel();
        r2.setLayout(new GridLayout(1, 3));
        docTypeLB = new JLabel("Add Document:");
        newDocTF = new JTextField();
        newDocTF.setPreferredSize(new Dimension(200, 15));
        addButton = new JButton("Add Document");
        r2.add(docTypeLB);
        r2.add(newDocTF);
        r2.add(addButton);
        row1.add(r2);

        JPanel r3 = new JPanel();
        r3.setLayout(new GridLayout(1, 3, 5, 5));
        String[] docNames = documentController.getDocNames();
        docTypeBox = new JComboBox<>(docNames);
        r3.add(docTypeBox);
        delDocBtn = new JButton("Delete Document");
        r3.add(delDocBtn);
        row1.add(r3);

        JPanel r4 = new JPanel();
        r4.setLayout(new GridLayout(1,1, 5, 10));
        userTableLB = new JLabel("USERS");
        userTableLB.setFont(new Font("Verdana", Font.BOLD, 18));
        r4.add(userTableLB);
        row1.add(r4);

        // 2ND ROW
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,1,5, 10));
        table1  = new JTable(){
            public boolean isCellEditable(int data, int columns)
            {
                return false;
            }
        };
        createTable();
        table1.setPreferredScrollableViewportSize(new Dimension(700, 400));
        table1.setFillsViewportHeight(true);
        usersScrollPane = new JScrollPane(table1,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        p1.add(usersScrollPane);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2, 1, 10, 5));
        seeReqLB = new JLabel("See Requests for selected user: ");
        seeReqLB.setFont(new Font("Verdana", Font.BOLD, 14));
        p2.add(seeReqLB);
        seeRequestsButton = new JButton("See Requests");
        p2.add(seeRequestsButton);
        row2.add(p1);
        row2.add(p2);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2));
        mainPanel.add(row1);
        mainPanel.add(row2);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2 - 600, dim.height / 2 - this.getSize().height / 2 - 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);


        seeRequestsButton.addActionListener( e -> {
            this.closeAdminGui();
            if(uid.equals("")){
                JOptionPane.showMessageDialog(new JFrame(), "Please select a user whose requests to see.", "Blank", JOptionPane.ERROR_MESSAGE);
            }else{
                RequestGUI requestGUI = new RequestGUI(uid);
            }

        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table1.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                uid = (String) model.getValueAt(i, 0);
            }
        });

        addButton.addActionListener( e-> {
            String doc = newDocTF.getText();
            documentController.createDocument(doc);
            docTypeBox.addItem(new String(doc));
        });

        delDocBtn.addActionListener( e-> {
            selectedDoc = Objects.requireNonNull(docTypeBox.getSelectedItem()).toString();
            documentController.deleteDocument(selectedDoc);
        });

        backBtn.addActionListener( e-> {
            this.closeAdminGui();
            MainGUI mainGUI = new MainGUI();
        });
    }



    public void createTable(){

        List<String[]> tableData = adminController.fetchTableConfiguredUsers();
        List<User> users = adminController.fetchAllUsers();
        int row=0;
        String[][] data = new String[tableData.size()][4];
        for(User us: users)
        {
            data[row][0]=us.getUser_id();
            data[row][1]=us.getUser_name();
            data[row][2]=us.getEmail();
            data[row][3]= MyUtils.getPrettyDate(us.getCreate_time());
            row++;
        }

        String[] columnNames = {"User ID", "User Name", "User Email", "Time Registered"};
        // table1 = new JTable(userModel);
        table1.setModel(new DefaultTableModel(data, columnNames));
    }


    public void closeAdminGui() { this.setVisible(false);}
}
