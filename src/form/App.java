package form;



import dboperation.UserData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import util.DataTransform;

import java.util.ArrayList;

public class App {

    Display display;

    Shell shell;

    Menu menuBar, fileMenu, dictMenu, nagrzkaMenu, helpMenu;

    MenuItem fileMenuHeader, dictMenuHeader, nagrzkaMenuHeader, helpMenuHeader;

    MenuItem fileExitItem, fileSaveItem, dictSprotsmensItem, dictSboriItem, dictUprgItem,
            dictTWLTCSTATUSItem,
            dictTTCPERSONALItem,


    nagrzkaF9Item,
            helpGetHelpItem;


    Label label;

    public App() {

        display = new Display();
        shell = new Shell(display);
        shell.setText("Menu Racshet ?????? ????????");
        shell.setSize(600, 300);

        label = new Label(shell, SWT.CENTER);
        label.setBounds(shell.getClientArea());

        menuBar = new Menu(shell, SWT.BAR);
        fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        fileMenuHeader.setText("&File");

        fileMenu = new Menu(shell, SWT.DROP_DOWN);
        fileMenuHeader.setMenu(fileMenu);

        fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
        fileSaveItem.setText("&Change PWD ??????? ??????");

        fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
        fileExitItem.setText("E&xit");


        //dict
        dictMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        dictMenuHeader.setText("&Dictinary");

        dictMenu = new Menu(shell, SWT.DROP_DOWN);
        dictMenuHeader.setMenu(dictMenu);

        dictSboriItem = new MenuItem(dictMenu, SWT.PUSH);
        dictSboriItem.setText("&Sbori");


        dictTWLTCSTATUSItem = new MenuItem(dictMenu, SWT.PUSH);
        dictTWLTCSTATUSItem.setText("&TWLTCSTATUS");

        dictTTCPERSONALItem = new MenuItem(dictMenu, SWT.PUSH);
        dictTTCPERSONALItem.setText("&TTCPERSONAL");

        //dict


        //nagryz
        nagrzkaMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        nagrzkaMenuHeader.setText("&Nagryzka");

        nagrzkaMenu = new Menu(shell, SWT.DROP_DOWN);
        nagrzkaMenuHeader.setMenu(nagrzkaMenu);

        nagrzkaF9Item = new MenuItem(nagrzkaMenu, SWT.PUSH);
        nagrzkaF9Item.setText("&nagrzk F9");
        //nagr

        helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        helpMenuHeader.setText("&Help");

        helpMenu = new Menu(shell, SWT.DROP_DOWN);
        helpMenuHeader.setMenu(helpMenu);

        helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
        helpGetHelpItem.setText("&Get Help");

        fileExitItem.addSelectionListener(new fileExitItemListener());
        fileSaveItem.addSelectionListener(new fileSaveItemListener());
        helpGetHelpItem.addSelectionListener(new helpGetHelpItemListener());
        dictSboriItem.addSelectionListener(new dictSboriItemiListener());

        dictTWLTCSTATUSItem.addSelectionListener(new dictTWLTCSTATUSItemListener());

        dictTTCPERSONALItem.addSelectionListener(new dictTTCPERSONALItemListener());

        nagrzkaF9Item.addSelectionListener(new nagrzkaF9ItemListener());

        shell.setMenuBar(menuBar);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    class fileExitItemListener implements SelectionListener {
        public void widgetSelected(SelectionEvent event) {
            shell.close();
            display.dispose();
        }

        public void widgetDefaultSelected(SelectionEvent event) {
            shell.close();
            display.dispose();
        }
    }

    class fileSaveItemListener implements SelectionListener {
        public void widgetSelected(SelectionEvent event) {
            label.setText("Saved");
        }

        public void widgetDefaultSelected(SelectionEvent event) {
            label.setText("Saved");
        }
    }





    class dictSboriItemiListener implements SelectionListener {
        public void widgetSelected(SelectionEvent event) {

            System.out.println(event.text);
            System.out.println(event.detail);
            //label.setText(event.text);

            ArrayList<String[]> arrayListData= DataTransform.getSbor(
                    UserData.getSborFromSQLite());

      /*
      arrayList.add("Sbor1;18.02.2015");
      arrayList.add("Sbor2;08.08.1015");
      arrayList.add("Sbor3;11.11.3015");
      */
            TableOnForm tableOnForm=new TableOnForm();
            tableOnForm.setDisplay2(display);
            tableOnForm.load(arrayListData, "Sbori", "id", "name", "data_sbora",null,null);


        }

        public void widgetDefaultSelected(SelectionEvent event) {
            label.setText("dictSboriItemiListener2222");
        }
    }

    class dictTWLTCSTATUSItemListener implements SelectionListener {
        public void widgetSelected(SelectionEvent event) {
            label.setText("TWLTCSTATUS");

            ArrayList<String[]> arrayListData= DataTransform.getTWLTCSTATUS(
                    UserData.getTWLTCSTATUSFromSQLite());
      /*
      arrayList.add("Beg 100;Beg");
      arrayList.add("Beg 800;Beg");
      arrayList.add("Skakalka;CPY");
      arrayList.add("Razminka;OFP");
      arrayList.add("Razminka;CPY");
      arrayList.add("Rabota v parax;CTTM");
      */
            TableOnForm tableOnForm=new TableOnForm();
            tableOnForm.setDisplay2(display);
            tableOnForm.load(arrayListData, "TWLTCSTATUS","id", "vTCSTATUS ", "vTCSTATUS_RU ",null,null);
        }

        public void widgetDefaultSelected(SelectionEvent event) {
            label.setText("Saved");
        }
    }

    class dictTTCPERSONALItemListener implements SelectionListener {
        public void widgetSelected(SelectionEvent event) {
            label.setText("TTCPERSONAL");

            ArrayList<String[]> arrayListData= DataTransform.getTTCPERSONAL(
                    UserData.getTTCPERSONALFromSQLite());
      /*
      arrayList.add("Beg 100;Beg");
      arrayList.add("Beg 800;Beg");
      arrayList.add("Skakalka;CPY");
      arrayList.add("Razminka;OFP");
      arrayList.add("Razminka;CPY");
      arrayList.add("Rabota v parax;CTTM");
      */
            TableOnForm tableOnForm=new TableOnForm();
            tableOnForm.setDisplay2(display);
            tableOnForm.load(arrayListData, "TTCPERSONAL","id",
                    "vSURNAME ", "vFIRSTNAME ","vMIDDLENAME",null);
        }

        public void widgetDefaultSelected(SelectionEvent event) {
            label.setText("Saved");
        }
    }


    class nagrzkaF9ItemListener implements SelectionListener {
        public void widgetSelected(SelectionEvent event) {
            label.setText("nagrzkaF9ItemListener");


      /*
      arrayList.add("Beg 100;Beg");
      arrayList.add("Beg 800;Beg");
      arrayList.add("Skakalka;CPY");
      arrayList.add("Razminka;OFP");
      arrayList.add("Razminka;CPY");
      arrayList.add("Rabota v parax;CTTM");
      */

            ComboListOnForm comboListOnForm=new ComboListOnForm();
            comboListOnForm.setDisplay2(display);
            comboListOnForm.load( "TWLMVALUES", "id",
                    "vTCAMPID", "","vTCSID", "vTCTID", "vTRAININGDATE","vTTSEQUENCE","vTRAININGID"
                    ,"vTRAININGDUR_V","vMPULSEP10S_b");


        }

        public void widgetDefaultSelected(SelectionEvent event) {
            label.setText("Saved2");
        }
    }







    class helpGetHelpItemListener implements SelectionListener {
        public void widgetSelected(SelectionEvent event) {
            label.setText("No worries!");
        }

        public void widgetDefaultSelected(SelectionEvent event) {
            label.setText("No worries!");
        }
    }

    public static void main(String[] args) {
        App menuExample = new App();
    }
}
