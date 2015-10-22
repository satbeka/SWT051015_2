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
  nagrzkaF9Item,
  helpGetHelpItem;

  Label label;

  public App() {

    display = new Display();
    shell = new Shell(display);
    shell.setText("Menu Racshet ������ ��������");
    shell.setSize(600, 300);

    label = new Label(shell, SWT.CENTER);
    label.setBounds(shell.getClientArea());

    menuBar = new Menu(shell, SWT.BAR);
    fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
    fileMenuHeader.setText("&File");

    fileMenu = new Menu(shell, SWT.DROP_DOWN);
    fileMenuHeader.setMenu(fileMenu);

    fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
    fileSaveItem.setText("&Change PWD ������� ������");

    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
    fileExitItem.setText("E&xit");

    
    //dict
    dictMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
    dictMenuHeader.setText("&Dictinary");

    dictMenu = new Menu(shell, SWT.DROP_DOWN);
    dictMenuHeader.setMenu(dictMenu);

    dictSprotsmensItem = new MenuItem(dictMenu, SWT.PUSH);
    dictSprotsmensItem.setText("&Sprotsmens");
    
    dictSboriItem = new MenuItem(dictMenu, SWT.PUSH);
    dictSboriItem.setText("&Sbori");

    dictUprgItem = new MenuItem(dictMenu, SWT.PUSH);
    dictUprgItem.setText("&Uprg");
    
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
    dictSprotsmensItem.addSelectionListener(new dictSprotsmensItemListener());
    dictUprgItem.addSelectionListener(new dictUprgItemListener());
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

      ArrayList arrayList=new ArrayList();
      arrayList= DataTransform.getSborCsv(
              UserData.getSborFromSQLite());

      /*
      arrayList.add("Sbor1;18.02.2015");
      arrayList.add("Sbor2;08.08.1015");
      arrayList.add("Sbor3;11.11.3015");
      */
      TableOnForm tableOnForm=new TableOnForm();
      tableOnForm.setDisplay2(display);
      tableOnForm.load(arrayList,"Sbori","name","data_sbora");


    }

    public void widgetDefaultSelected(SelectionEvent event) {
      label.setText("dictSboriItemiListener2222");
    }
  }
  class dictSprotsmensItemListener implements SelectionListener {
    public void widgetSelected(SelectionEvent event) {
      label.setText("dictSprotsmensItemListener");
      ArrayList arrayList=new ArrayList();
      /*
      arrayList.add("Drogba;18.02.2010");
      arrayList.add("Snalone;08.08.1055");
      arrayList.add("Anarbai Champion Bee Best;11.11.1001");
      arrayList.add("Anarbai Champion Bee Best;11.11.1002");
      arrayList.add("Anarbai Champion Bee Best;11.11.1003");
      arrayList.add("Anarbai Champion Bee Best;11.11.1004");
      */
      arrayList= DataTransform.getSprotsmensCsv(
              UserData.getSprotsmensFromSQLite());
      TableOnForm tableOnForm=new TableOnForm();
      tableOnForm.setDisplay2(display);
      tableOnForm.load(arrayList, "Sprotsmens", "fio", "data_birth");

    }

    public void widgetDefaultSelected(SelectionEvent event) {
      label.setText("Saved");
    }
  }
  class dictUprgItemListener implements SelectionListener {
    public void widgetSelected(SelectionEvent event) {
      label.setText("Uprg");

      ArrayList arrayList=new ArrayList();
      arrayList=UserData.getUpragnFromSQLite();
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
      tableOnForm.load(arrayList, "Uprgs", "upragnenei ", "group_upragn ");
    }

    public void widgetDefaultSelected(SelectionEvent event) {
      label.setText("Saved");
    }
  }

  class nagrzkaF9ItemListener implements SelectionListener {
    public void widgetSelected(SelectionEvent event) {
      label.setText("Saved");
    }

    public void widgetDefaultSelected(SelectionEvent event) {
      label.setText("Saved");
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




