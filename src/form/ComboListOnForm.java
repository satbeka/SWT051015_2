package form;
import dboperation.UserData;

import model.TTCPERSONAL;
import model.TTCTEAMS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.*;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import util.DataTransform;
import util.Export2Excel;

import java.util.ArrayList;
import java.util.HashMap;

public class ComboListOnForm {
    public Display getDisplay2() {
        return display2;
    }

    public void setDisplay2(Display display2) {
        this.display2 = display2;
    }

    private Display display2;
    private Shell shell2;

    private Table table;
    private TableColumn stringColumn1,stringColumn2,stringColumn3,stringColumn4,stringColumn5,
            stringColumn6,stringColumn7,stringColumn8,stringColumn9
            ,dateColumn,doubleColumn,hourColumn;
    private String tabTitle;
    private Combo comboDropDown;// = new Combo(shell2, SWT.DROP_DOWN | SWT.BORDER);

    Menu contextMenu;
    ArrayList<String[]> arrayListData=new ArrayList<>();

    public void load(String tabTitle,String itTitleId,
                     String itTitle1,String itTitle2,String itTitle3,String itTitle4,String itTitle5,
                     String itTitle6,String itTitle7,String itTitle8,String itTitle9
    ) {

        shell2 = new Shell(display2);

        shell2.setSize(500, 500);
        shell2.setText(tabTitle);
        //shell2.setLayout(new FillLayout());

        GridLayout gridLayout = new GridLayout(1, false);
        gridLayout.verticalSpacing = 3;


        Label label = new Label(shell2, SWT.NULL);
        label.setText(" Personal ");
        //final Combo c = new Combo(shell2, SWT.READ_ONLY);
        final Combo c1 = new Combo(shell2, SWT.READ_ONLY|SWT.NULL);
        //c1.setBounds(50, 50, 150, 65);

        label = new Label(shell2, SWT.NULL);
        label.setText(" Teams ");
        final Combo c2 = new Combo(shell2, SWT.READ_ONLY|SWT.NULL);
        //c2.setBounds(50, 85, 150, 65);
        //s.setSize(250, 250);
        //c.setBounds(50, 50, 150, 65);
        ArrayList<TTCPERSONAL> data= UserData.getTTCPERSONALFromSQLite();
        String items1[]=new String[data.size()];;
        String str;
        int l=0;
        for (TTCPERSONAL ttcpersonal: data) {
            str=ttcpersonal.getvFIRSTNAME()+" "+ttcpersonal.getvMIDDLENAME()+" "+ttcpersonal.getvSURNAME();
            items1[l]=str;
            l++;
            System.out.println(str);
        }


                //{ "Item One", "Item Two", "Item Three", "Item Four", "Item Five" };



        c1.setItems(items1);

        ArrayList<TTCTEAMS> data2= UserData.getTTCTEAMSFromSQLite();
        String items2[]=new String[data2.size()];
        l=0;
        for (TTCTEAMS ttcteams: data2) {
            str=ttcteams.getvNAME();
            items2[l]=str;
            l++;
            System.out.println(str);
        }

        c2.setItems(items2);



        Button buttonDiagrm=new Button(shell2,SWT.BUTTON2);
        buttonDiagrm.setText("Diagamma");

        buttonDiagrm.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                switch (e.type) {
                    case SWT.Selection:
                        System.out.println("Button pressed");


                        // create a chart
                        ChartOnForm chartOnForm = new ChartOnForm();
                        chartOnForm.setDisplay3(display2);
                        chartOnForm.create();

                        break;
                }
            }
        });


        Button buttonExl=new Button(shell2,SWT.BUTTON2);
        buttonExl.setText("Export Excel");
        buttonExl.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                switch (e.type) {
                    case SWT.Selection:
                        System.out.println("Button pressed");


                        // create a excel

                        int size = table.getColumnCount();
                        String[] coloumNames = new String[size];
                        for (int i = 0; i < size; ) {

                            System.out.println("table.getColumn(i).getText()=" + table.getColumn(i).getText());
                            coloumNames[i] = table.getColumn(i).getText();
                            i++;

                        }
                        arrayListData = DataTransform.getTWLMVALUES(
                                UserData.getTWLMVALUESFromSQLite(c1.getText(), c2.getText()));

                        String sheetName = "sheet1111";

                        Export2Excel export2Excel = new Export2Excel(shell2, coloumNames, arrayListData, sheetName);
                        //Export2Excel(Shell shell, String[] coloumNames, ResultSet rSet, String sheetName)

                        break;
                }
            }
        });




        shell2.setLayout(gridLayout);

        table = new Table(shell2, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
        //TWLMVALUES
        //"vTCAMPID", "vTCSID", "vTCTID", "vTRAININGDATE","vTTSEQUENCE","vTRAININGID"
        //      ,"vTRAININGDUR_V","vMPULSEP10S_b"

        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        stringColumn1 = new TableColumn(table, SWT.CENTER);
        stringColumn1.setText(itTitleId);
        stringColumn1.setWidth(120);

        stringColumn2 = new TableColumn(table, SWT.CENTER);
        stringColumn2.setText(itTitle2);
        stringColumn2.setWidth(120);

        stringColumn3 = new TableColumn(table, SWT.CENTER);
        stringColumn3.setText(itTitle3);
        stringColumn3.setWidth(120);

        stringColumn4 = new TableColumn(table, SWT.CENTER);
        stringColumn4.setText(itTitle4);
        stringColumn4.setWidth(120);

        stringColumn5 = new TableColumn(table, SWT.CENTER);
        stringColumn5.setText(itTitle5);
        stringColumn5.setWidth(120);

        stringColumn6 = new TableColumn(table, SWT.CENTER);
        stringColumn6.setText(itTitle6);
        stringColumn6.setWidth(120);

        stringColumn7 = new TableColumn(table, SWT.CENTER);
        stringColumn7.setText(itTitle7);
        stringColumn7.setWidth(120);

        stringColumn8 = new TableColumn(table, SWT.CENTER);
        stringColumn8.setText(itTitle8);
        stringColumn8.setWidth(120);

        stringColumn9 = new TableColumn(table, SWT.CENTER);
        stringColumn9.setText(itTitle9);
        stringColumn9.setWidth(120);

        int size=table.getColumnCount();

                //DataTransform.getTWLMVALUES(UserData.getTWLMVALUESFromSQLite());

        int sizeData= arrayListData.size();
        String[] arrV=new String[size];
        for (int i = 0; i < sizeData; i++) {

            // ok now store it in the table
            TableItem item = new TableItem(table, SWT.NONE);
            arrV= arrayListData.get(i);
            item.setText(arrV);

        }



        c1.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (!c1.getText().isEmpty()) {
                    System.out.println("c1 c1.getText()="+c1.getText());
                    if (!c2.getText().isEmpty()){
                        System.out.println("c1 c2.getText()="+c2.getText());

                        shell2.open();
                        int size=table.getColumnCount();
                        int kk=table.getItemCount();
                        System.out.println("kk="+kk);
                        arrayListData =DataTransform.getTWLMVALUES(
                                UserData.getTWLMVALUESFromSQLite(c1.getText(),c2.getText()));
                        int sizeData= arrayListData.size();
                        System.out.println("sizeData="+sizeData);
                        String[] arrV=new String[size];
                        for (int i = 0; i < sizeData; i++) {

                            // ok now store it in the table
                            TableItem item = new TableItem(table, SWT.NONE);
                            arrV= arrayListData.get(i);
                            item.setText(arrV);

                        }
                        System.out.println(" size==0 ");
                        if (sizeData==0){
                            /*
                            int k=table.getSelectionIndices();
                            //table.setRedraw(true);
                            table.setVisible(false);
                            table.remove(0, k);
                            table.setVisible(true);
                            */
                            //table.remove (0,kk);
                            table.removeAll();
                            System.out.println("tab upd");
                            //TableItem item = new TableItem(table, SWT.NONE);
                            //item.setText("");
                        }


                        //shell2.dispose();
                        shell2.pack();


                    }



                } else if (c1.getText().equals("Item Two")) {
                    String newItems[] = { "Item Two A", "Item Two B",
                            "Item Two C" };
                    c2.setItems(newItems);
                    c2.setEnabled(true);
                }

            }
        });

        c2.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (!c1.getText().isEmpty()) {
                    System.out.println("c2 c1.getText()=" + c1.getText());
                    if (c2.getText()!=null){
                        System.out.println("c2 c2.getText()="+c2.getText());

                        shell2.open();
                        int size=table.getColumnCount();
                        arrayListData =DataTransform.getTWLMVALUES(
                                UserData.getTWLMVALUESFromSQLite(c1.getText(),c2.getText()));
                        int sizeData= arrayListData.size();
                        String[] arrV=new String[size];
                        for (int i = 0; i < sizeData; i++) {

                            // ok now store it in the table
                            TableItem item = new TableItem(table, SWT.NONE);
                            arrV= arrayListData.get(i);
                            item.setText(arrV);

                        }
                        System.out.println(" size==0 ");
                        if (sizeData==0){

                            table.removeAll();
                            System.out.println("tab upd");

                        }

                        shell2.pack();


                    }



                } else if (c1.getText().equals("Item Two")) {
                    String newItems[] = { "Item Two A", "Item Two B",
                            "Item Two C" };
                    c2.setItems(newItems);
                    c2.setEnabled(true);
                }

            }
        });



        // Create the listener
        //MouseEventExample myMouseEventExample = new MouseEventExample(shell2);
        shell2.pack();

        this.tabTitle=tabTitle; //name of tbl in Db
        System.out.println("tabTitle="+tabTitle);

        contextMenu = new Menu(table);
        table.setMenu(contextMenu);
        MenuItem mItem1 = new MenuItem(contextMenu, SWT.None);
        MenuItem mItem2 = new MenuItem(contextMenu, SWT.None);
        MenuItem mItem3 = new MenuItem(contextMenu, SWT.None);
        mItem1.setText(" Add Test.");
        mItem2.setText(" Edit Test.");
        mItem3.setText(" Delete Test.");

        //System.out.println("table.getSelection()="+table.getSelection());
        //System.out.println("table.getSelectionIndex()="+table.getSelectionIndex());
        final TableCursor cursor = new TableCursor(table, SWT.NONE);
        cursor.addSelectionListener(new SelectionAdapter() {
                                        // This is called as the user navigates around the table
                                        public void widgetSelected(SelectionEvent event) {
                                            // Select the row in the table where the TableCursor is
                                            table.setSelection(new TableItem[]{cursor.getRow()});
                                            System.out.println("cursor.getRow().getText()=" + cursor.getRow().getText());

                                        }
                                    }
        );

        mItem1.setData("Id", cursor.getRow());


        //Insert
        mItem1.addListener(SWT.Selection, new

                        Listener() {
                            public void handleEvent(Event e) {
                                System.out.println("Add Test.");


                                final Shell shellEdit = new Shell(display2, SWT.NO);
                                RowLayout layout = new RowLayout(SWT.VERTICAL);
                                layout.marginLeft = 20;
                                layout.marginTop = 20;
                                //layout.justify = true;

                                //shellEdit = new Shell(display2, SWT.NO);
                                shellEdit.setText("Add");
                                shellEdit.setSize(300, 300);
                                shellEdit.setLayout(layout);



                                System.out.println("id=" + cursor.getRow());



                                HashMap<Integer, String> map = new HashMap<Integer, String>();
                                for (int i = 1; i < size; i++) {

                                    Label label = new Label(shellEdit, SWT.SINGLE);
                                    label.setText(table.getColumn(i).getText());

                                    Text text = new Text(shellEdit, SWT.PUSH | SWT.BORDER);
                                    text.setFocus();
                                    // Copy the text from the cell to the Text control

                                    //table.getItem(itInd).getText()

                                    //text.setText(cursor.getRow().getText(i));
                                    text.setText("");
                                    //map.put(i,cursor.getRow().getText(i));
                                    map.put(i, "");
                                    final Integer k = i;
                                    //text.setData("columnId",i);
                                    text.setEditable(true);
                                    text.setFocus();

                                    text.addModifyListener(new ModifyListener() {
                                        public void modifyText(ModifyEvent me) {
                                            //Text textM=(Text)me.widget;
                                            //System.out.println("textM.getText()="+textM.getText());
                                            //text.setData("");
                                            map.remove(k);
                                            map.put(k, text.getText());
                                            System.out.println("k=" + k);
                                            System.out.println("map.get(k)=" + map.get(k));

                                        }
                                    });
                                    //Text text = new Text(shell2, SWT.SINGLE | SWT.BORDER);

                                    System.out.println("i=" + i);
                                    //text.setText("888");

                                }


                                Button button = new Button(shellEdit, SWT.BUTTON2);
                                button.setText("Save");
                                button.addListener(SWT.Selection, new Listener() {
                                    public void handleEvent(Event e) {
                                        switch (e.type) {
                                            case SWT.Selection:
                                                System.out.println("Button pressed");

                                                TableItem tableItemEdit;
                                                tableItemEdit = new TableItem(table, SWT.NONE);


                                                String strV;
                                                String[] arrV = new String[size];
                                                System.out.println("size=" + size);
                                                for (int i = 1; i < size; i++) {

                                                    System.out.println("i=" + i);
                                                    arrV[i] = map.get(i);
                                                    //text.setText("888");

                                                }
                                                System.out.println("tabTitle="+tabTitle);

                                                if (tabTitle=="TTCPERSONAL"){

                                                    arrV[0] = UserData.insTTCPERSONAL(arrV);

                                                }
                                                if (tabTitle=="TWLTCSTATUS"){

                                                    arrV[0] = UserData.insTWLTCSTATUS(arrV);

                                                }
                                                if (tabTitle=="TTCTEAMS"){

                                                    //arrV[0] = UserData.insTTCTEAMS(arrV);

                                                }



                                                tableItemEdit.setText(arrV);


                                                break;
                                        }
                                    }
                                });


                                shellEdit.open();


                            }
                        }

        );

        //Edit
        mItem2.addListener(SWT.Selection, new

                        Listener() {
                            public void handleEvent(Event e) {
                                System.out.println("Edit Test.");


                                final Shell shellEdit = new Shell(display2, SWT.NO);
                                RowLayout layout = new RowLayout(SWT.VERTICAL);
                                layout.marginLeft = 20;
                                layout.marginTop = 20;
                                //layout.justify = true;

                                //shellEdit = new Shell(display2, SWT.NO);
                                shellEdit.setText("Edit");
                                shellEdit.setSize(300, 300);
                                shellEdit.setLayout(layout);



                                System.out.println("id=" + cursor.getRow());
                                //TableItem item=(TableItem)e.widget;
                                //System.out.println("table.indexOf(item)="+table.indexOf(item));


                                HashMap<Integer, String> map = new HashMap<Integer, String>();
                                for (int i = 0; i < size; i++) {

                                    Label label=new Label(shellEdit, SWT.SINGLE );
                                    label.setText(table.getColumn(i).getText());

                                    Text text = new Text(shellEdit, SWT.PUSH| SWT.BORDER);
                                    text.setFocus();
                                    text.setText(cursor.getRow().getText(i));
                                    map.put(i,cursor.getRow().getText(i));
                                    //map.put(i,"");
                                    final Integer k=i;
                                    //text.setData("columnId",i);
                                    if (i!=0)
                                    {text.setEditable(true);}
                                    text.setFocus();

                                    text.addModifyListener(new ModifyListener() {
                                        public void modifyText(ModifyEvent me) {
                                            map.remove(k);
                                            map.put(k, text.getText());
                                            System.out.println("k="+k);
                                            System.out.println("map.get(k)="+map.get(k));

                                        }
                                    });
                                    //Text text = new Text(shell2, SWT.SINGLE | SWT.BORDER);

                                    System.out.println("i=" + i);
                                    //text.setText("888");

                                }


                                Button button=new Button(shellEdit,SWT.BUTTON2);
                                button.setText("Save");
                                button.addListener(SWT.Selection, new Listener() {
                                    public void handleEvent(Event e) {
                                        switch (e.type) {
                                            case SWT.Selection:
                                                System.out.println("Button pressed");


                                                System.out.println("id3333=" + cursor.getRow());
                                                //TableItem item=(TableItem)e.widget;
                                                //System.out.println("table.indexOf(item)="+table.indexOf(item));
                                                int index = table.getSelectionIndex();
                                                System.out.println("index table.getSelectionIndex()=" + index);
                                                TableItem tableItemEdit = table.getItem(index);


                                                //tableItemEdit = new TableItem(table, SWT.NONE);
                                                String strV;
                                                String[] arrV = new String[size];
                                                System.out.println("size=" + size);
                                                for (int i = 0; i < size; i++) {

                                                    System.out.println("i=" + i);
                                                    arrV[i] = map.get(i);
                                                    //text.setText("888");

                                                }
                                                //arrV[0]= UserData.updTWLTCSTATUS(arrV);


                                                if (tabTitle=="TTCPERSONAL"){

                                                    arrV[0] = UserData.updTTCPERSONAL(arrV);

                                                }
                                                if (tabTitle=="TWLTCSTATUS"){

                                                    arrV[0] = UserData.updTWLTCSTATUS(arrV);

                                                }
                                                if (tabTitle=="TTCTEAMS"){

                                                    //arrV[0] = UserData.insTTCTEAMS(arrV);

                                                }

                                                tableItemEdit.setText(arrV);

                                                shellEdit.close();
                                                System.out.println("shellEdit.close();====");
                                                break;
                                        }
                                    }
                                });


                                System.out.println("shellEdit.open();====");
                                shellEdit.open();







                            }
                        }

        );

        //Delete
        mItem3.addListener(SWT.Selection, new

                        Listener() {
                            public void handleEvent(Event e) {
                                System.out.println("Delete Test.");


                                final Shell shellEdit = new Shell(display2, SWT.NO);
                                RowLayout layout = new RowLayout(SWT.VERTICAL);
                                layout.marginLeft = 20;
                                layout.marginTop = 20;
                                //layout.justify = true;

                                //shellEdit = new Shell(display2, SWT.NO);
                                shellEdit.setText("Delete");
                                shellEdit.setSize(300, 300);
                                shellEdit.setLayout(layout);



                                System.out.println("id=" + cursor.getRow());
                                //TableItem item=(TableItem)e.widget;
                                //System.out.println("table.indexOf(item)="+table.indexOf(item));


                                HashMap<Integer, String> map = new HashMap<Integer, String>();
                                for (int i = 0; i < size; i++) {

                                    Label label=new Label(shellEdit, SWT.SINGLE );
                                    label.setText(table.getColumn(i).getText());

                                    Text text = new Text(shellEdit, SWT.PUSH| SWT.BORDER);
                                    text.setFocus();
                                    text.setText(cursor.getRow().getText(i));
                                    map.put(i,cursor.getRow().getText(i));
                                    //map.put(i,"");
                                    final Integer k=i;
                                    //text.setData("columnId",i);
                                    if (i!=0)
                                    {text.setEditable(true);}
                                    text.setFocus();

                                    text.addModifyListener(new ModifyListener() {
                                        public void modifyText(ModifyEvent me) {
                                            map.remove(k);
                                            map.put(k, text.getText());
                                            System.out.println("k="+k);
                                            System.out.println("map.get(k)="+map.get(k));

                                        }
                                    });
                                    //Text text = new Text(shell2, SWT.SINGLE | SWT.BORDER);

                                    System.out.println("i=" + i);
                                    //text.setText("888");

                                }


                                Button button=new Button(shellEdit,SWT.BUTTON2);
                                button.setText("Delete");
                                button.addListener(SWT.Selection, new Listener() {
                                    public void handleEvent(Event e) {
                                        switch (e.type) {
                                            case SWT.Selection:
                                                System.out.println("Button pressed");


                                                System.out.println("id3333=" + cursor.getRow());
                                                //TableItem item=(TableItem)e.widget;
                                                //System.out.println("table.indexOf(item)="+table.indexOf(item));
                                                int index = table.getSelectionIndex();
                                                System.out.println("index table.getSelectionIndex()=" + index);
                                                TableItem tableItemEdit = table.getItem(index);


                                                //tableItemEdit = new TableItem(table, SWT.NONE);
                                                String strV;
                                                String[] arrV = new String[size];
                                                System.out.println("size=" + size);
                                                for (int i = 0; i < size; i++) {

                                                    System.out.println("i=" + i);
                                                    arrV[i] = map.get(i);
                                                    //text.setText("888");

                                                }


                                                if (tabTitle=="TTCPERSONAL"){

                                                    arrV[0] = UserData.delTTCPERSONAL(arrV);

                                                }
                                                if (tabTitle=="TWLTCSTATUS"){

                                                    arrV[0] = UserData.delTWLTCSTATUS(arrV);

                                                }
                                                if (tabTitle=="TTCTEAMS"){

                                                    //arrV[0] = UserData.insTTCTEAMS(arrV);

                                                }


                                                tableItemEdit.setText(arrV);

                                                shellEdit.close();
                                                System.out.println("shellEdit.close();====");
                                                shell2.close();
                                                break;
                                        }
                                    }
                                });


                                System.out.println("shellEdit.open();====");
                                shellEdit.open();







                            }
                        }

        );

        table.addMouseListener(new

                                       MouseListener() {
                                           public void mouseDown(MouseEvent e) {
                                               System.out.println("Mouse Down." + e.button);
                                               if (e.button == 3) {

                                                   contextMenu.setVisible(true);
                                                   System.out.println("tabTitle=" + tabTitle);


                                               }
                                           }

                                           public void mouseUp(MouseEvent e) {
                                               System.out.println("Mouse Up.");
                                           }

                                           public void mouseDoubleClick(MouseEvent e) {
                                               System.out.println("Mouse Double click.");
                                           }

                                       });


        shell2.open();


    }
}
