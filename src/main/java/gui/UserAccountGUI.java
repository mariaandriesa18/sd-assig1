package gui;

import controller.DocumentController;
import controller.RequestController;
import controller.ResidenceController;
import controller.UserAccountController;
import dto.RequestDto;
import entity.Residence;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;
import controller.*;
import dto.RequestDto;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;
import java.util.Objects;


public class UserAccountGUI extends JFrame {
    private JPanel mainPanel;
    private JLabel citizenNameLB;
    private JButton delReqBTN;
    private JButton upReqBtn;
    private JButton addReqBTN;
    private JButton backBtn;

    private JButton addResidenceBtn;
    private JLabel reqLB;
    private JLabel residenceLB;
    private JTextField residenceTF;
    private JTextField  addressTF;

    private JTable reqTable;
    private JTable residenceTable;

    private JPanel docBoxPanel;
    private JPanel residenceBoxPanel;

    private JLabel houseLB;
    private JLabel docLB;
    private JScrollPane docScrollPane;
    private JScrollPane residenceScroll;

    private JComboBox<String> residenceBox;
    private JComboBox<String> docBox;
    private String uid;
    private String userName;
    private  String selectedReqToDelete;

    private ResidenceController residenceController = new ResidenceController();
    private RequestController requestController = new RequestController();
    private UserAccountController userAccountController = new UserAccountController();
    private String selectedDoc;
    private String selectedResidence;


    public UserAccountGUI(String userName){

        User user = userAccountController.getUserByUserName(userName);
        uid = user.getUser_id();
        this.userName = userName;

        // 1st row
        JPanel row1 = new JPanel();
      //  row1.setPreferredSize(new Dimension(800, 50));
        row1.setLayout(new GridLayout(1,3));
        citizenNameLB = new JLabel();
        citizenNameLB.setFont(new Font("Verdana", Font.PLAIN, 16));
        citizenNameLB.setText("Welcome, "+ userName + " !");
        backBtn = new JButton("Back");
        row1.add(citizenNameLB);
        row1.add(backBtn);
        // 2ND ROW
        JPanel row2 = new JPanel();
        row2.setLayout(new GridLayout(1, 2));

        // P1
        JPanel p11 = new JPanel();
        p11.setLayout(new FlowLayout());
        reqLB = new JLabel("REQUESTS");
        reqLB.setFont(new Font("Verdana", Font.BOLD, 18));
        p11.add(reqLB);

        JPanel p12  = new JPanel();
        p12.setLayout(new GridLayout(1, 2, 5, 15));
        docLB = new JLabel("Document:");
        houseLB = new JLabel("Residence: ");
        p12.add(docLB);
        p12.add(houseLB);

        // jcombo boxes
        JPanel p13 = new JPanel();
        p13.setLayout(new GridLayout(1,2, 5, 15));
        String[] residenceNames = residenceController.fetchResidenceNamesForComboBox(uid);
        residenceBox = new JComboBox<>(residenceNames);

        DocumentController documentController = new DocumentController();
        String[] docNames = documentController.getDocNames();
        docBox = new JComboBox<>(docNames);
        p13.add(docBox);
        p13.add(residenceBox);

        JPanel p14 = new JPanel();
        addReqBTN = new JButton("Add new request");
        p14.add(addReqBTN);
        JPanel p15 = new JPanel();
        upReqBtn = new JButton("Update Request");
        p15.add(upReqBtn);
        JPanel p16 = new JPanel();
        delReqBTN = new JButton("Delete Request");
        p16.add(delReqBTN);


        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(6, 1, 1,5));
        p1.add(p11);
        p1.add(p12);
        p1.add(p13);
        p1.add(p14);
        p1.add(p15);
        p1.add(p16);


         //P2 = request table
         reqTable = new JTable(){ public boolean isCellEditable(int data, int columns)  {     return false;  } };
        residenceTable = new JTable(){ public boolean isCellEditable(int data, int columns)  {     return false;  } };
        createRequestsTable();
        createResidenceTable();

        reqTable.setPreferredScrollableViewportSize(new Dimension(700, 400));
        reqTable.setFillsViewportHeight(true);
        docScrollPane = new JScrollPane(reqTable,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.setPreferredSize(new Dimension(700, 400));
        p2.add(docScrollPane);

        row2.add(p1);
        row2.add(p2);

        // 3RD ROW
        JPanel row3 = new JPanel();
        row3.setLayout(new GridLayout(1, 2, 5, 10));

        JPanel p31 = new JPanel();
        residenceLB = new JLabel("RESIDENCES");
        p31.setLayout(new FlowLayout());
        residenceLB.setFont(new Font("Verdana", Font.BOLD, 18));
        p31.add(residenceLB);

        JPanel p32 = new JPanel();
        p32.setLayout(new GridLayout(1,2));

        JPanel p321 = new JPanel();
        p321.setLayout(new FlowLayout());
        JLabel resNameLB = new JLabel("Residence Name");
        p321.add(resNameLB);
        JPanel p322 = new JPanel();
        p322.setLayout(new FlowLayout());
        JLabel resAddressLB = new JLabel("Residence Address");
        p322.add(resAddressLB);

        p32.add(p321);
        p32.add(p322);

        JPanel p33 = new JPanel();
        p33.setLayout(new GridLayout(1, 2));
        JPanel p331 = new JPanel();
        JPanel p332 = new JPanel();
        p331.setLayout(new FlowLayout());
        p332.setLayout(new FlowLayout());

        residenceTF  = new JTextField();
        residenceTF.setPreferredSize(new Dimension(200, 15));
        addressTF = new JTextField();
        addressTF.setPreferredSize(new Dimension(200, 15));
        p331.add(residenceTF);
        p332.add(addressTF);

        p33.add(p331);
        p33.add(p332);

        JPanel p34 = new JPanel();
        p34.setLayout(new FlowLayout());
        addResidenceBtn = new JButton("Add Residence");
        p34.add(addResidenceBtn);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(4, 1, 10, 15));
        p3.add(p31);
        p3.add(p32);
        p3.add(p33);
        p3.add(p34);

        residenceTable.setPreferredScrollableViewportSize(new Dimension(700, 400));
        residenceTable.setFillsViewportHeight(true);
        residenceScroll = new JScrollPane(residenceTable,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        p4.setPreferredSize(new Dimension(700, 400));
        p4.add(residenceScroll);

        row3.add(p3);
        row3.add(p4);

        //MAIN PANEL
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1000, 600));
        mainPanel.setLayout(new GridLayout(3 ,1 , 5,1));
        mainPanel.add(row1);
        mainPanel.add(row2);
        mainPanel.add(row3);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2 - 300, dim.height / 2 - this.getSize().height / 2 - 400);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.getContentPane().setSize(1000, 800);
        this.setVisible(true);
        this.pack();

        addReqBTN.addActionListener( e -> {
            selectedDoc = Objects.requireNonNull(docBox.getSelectedItem()).toString();
            selectedResidence =  Objects.requireNonNull(residenceBox.getSelectedItem()).toString();

            Integer response = requestController.createRequest(uid, selectedDoc, selectedResidence);
            if(response == 0){
                JOptionPane.showMessageDialog(new JFrame(), "You cannot make any more requests for this type of document","Register Error", JOptionPane.ERROR_MESSAGE);
            }

            this.refreshRequestsTable();
        });

        reqTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = reqTable.getSelectedRow();
                if(i >= 0) {
                    selectedReqToDelete = (String) reqTable.getModel().getValueAt(i, 0);
                }
            }
        });

        delReqBTN.addActionListener(e -> {
            requestController.deleteRequest(Integer.parseInt(selectedReqToDelete));
            refreshRequestsTable();
        });

        upReqBtn.addActionListener(e -> {
            selectedDoc = Objects.requireNonNull(docBox.getSelectedItem()).toString();
            selectedResidence =  Objects.requireNonNull(residenceBox.getSelectedItem()).toString();
            requestController.updateRequest(Integer.parseInt(selectedReqToDelete), selectedDoc, selectedResidence);
            refreshRequestsTable();
        });

        addResidenceBtn.addActionListener(e -> {
            String resName = residenceTF.getText();
            String resAddres = addressTF.getText();

            residenceController.createResidence(user, resName, resAddres);
            refreshResidenceTable();
            clearAllTF();
        });

        backBtn.addActionListener(e -> {
            this.close();
            MainGUI mainGUI = new MainGUI();
        });
    }


    public void createRequestsTable(){
        java.util.List<String[]> tableData = requestController.fetchTableConfiguredRequestsFromUserByUserId(uid);
        java.util.List<RequestDto> requestDtoList = requestController.fetchRequestsByUserId(uid);

        int row=0;
        String[][] data = new String[tableData.size()][6];
        for(RequestDto req: requestDtoList)
        {
            data[row][0]= req.getRequest_id();
            data[row][1]= req.getDocumentName();
            data[row][2]= req.getResidenceName();
            data[row][3]= req.getDate();
            data[row][4]= req.getMax_nb();
            data[row][5]= req.getStatus();
            row++;
        }

        String[] columnNames = {"Request ID", "Document Name", "Residence",
                "Date", "Amount Left", "Status"};
        reqTable.setModel(new DefaultTableModel(data, columnNames));
    }

    public void createResidenceTable(){

        java.util.List<String[]> tableData = residenceController.fetchTableConfiguredResidences(uid);
        List<Residence> residenceList = residenceController.fetchAllResidenceFromUser(uid);

        int row=0;
        String[][] data = new String[tableData.size()][3];
        for(Residence res : residenceList)
        {
            data[row][0]= String.valueOf(res.getResidence_id());
            data[row][1]= res.getResidence_name();
            data[row][2]= res.getAddress();
            row++;
        }

        String[] columnNames = {"Residence ID", "Residence Name", "Residence Address"};
        residenceTable.setModel(new DefaultTableModel(data, columnNames));
    }


    public void refreshRequestsTable(){
        DefaultTableModel dm = (DefaultTableModel) reqTable.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
        createRequestsTable();
    }
    public void refreshResidenceTable(){
        DefaultTableModel dm = (DefaultTableModel) residenceTable.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
        createResidenceTable();
    }

    public void clearAllTF(){
        residenceTF.setText("");
        addressTF.setText("");
    }

    public void close(){ this.setVisible(false);}
}
