package gui;

import controller.RequestController;
import controller.UserAccountController;
import dto.RequestDto;
import entity.Request;
import entity.User;
import mappers.RequestMapper;
import utils.MyUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class RequestGUI extends JFrame{
    private JPanel mainP;
    private JTable reqTable;
    private JLabel userLB;
    private JLabel userNameLB;
    private JButton approveButton;
    private JButton deleteButton;
    private JButton backButton;
    private JScrollPane requestsScrollPane;

    private String rid;
    private String userId;
    private RequestController requestController = new RequestController();
    private UserAccountController adminAccountController = new UserAccountController();

    public RequestGUI(String userId){
        this.userId = userId;
        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        row1.setLayout(new GridLayout(1,2));
        row2.setLayout(new GridLayout(1,2));

        // 1st ROW
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2,1, 50, 20));
        userLB = new JLabel("Requests for user : ");
        userLB.setFont(new Font("Verdana", Font.BOLD, 18));
        backButton = new JButton("Back");
        p1.add(userLB);
        p1.add(backButton);
        userNameLB = new JLabel();
        userNameLB.setFont(new Font("Verdana", Font.BOLD, 18));
        String userName = adminAccountController.getUserByID(userId).getUser_name();
        userNameLB.setText(userName);
        p1.add(userNameLB);
        row1.add(p1);

        // 2nd ROW


        reqTable = new JTable(){
            public boolean isCellEditable(int data, int columns)
            {
                return false;
            }
        };

        createRequestsTable();
        requestsScrollPane = new JScrollPane(reqTable,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        row2.add(requestsScrollPane);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2,1, 20, 3));
        approveButton = new JButton("Approve Request");
        deleteButton = new JButton("Delete Request");
        p2.add(approveButton);
        p2.add(deleteButton);
        row2.add(p2);

        mainP = new JPanel();
        mainP.setLayout(new GridLayout(2, 1, 15, 1));
        mainP.add(row1);
        mainP.add(row2);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2 - 600, dim.height / 2 - this.getSize().height / 2 - 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainP);
        this.pack();
        this.setVisible(true);

        reqTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = reqTable.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) reqTable.getModel();
                rid = (String) model.getValueAt(i, 0);
            }
        });

        approveButton.addActionListener( e-> {
           requestController.approveRequest(Integer.parseInt(rid));
           refreshTable();
        });

        deleteButton.addActionListener( e-> {
            requestController.deleteRequest(Integer.parseInt(rid));
            refreshTable();
        });

        backButton.addActionListener( e-> {
            this.close();
            AdminAccountGUI adminAccountGUI = new AdminAccountGUI();
        });
    }

    public void createRequestsTable(){
        List<String[]> tableData = requestController.fetchTableConfiguredRequestsFromUserByUserId(userId);
        List<RequestDto> requestDtoList = requestController.fetchRequestsByUserId(userId);
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

    public void refreshTable(){
        DefaultTableModel dm = (DefaultTableModel) reqTable.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
        createRequestsTable();
    }
    public void close(){ this.setVisible(false);}
}
