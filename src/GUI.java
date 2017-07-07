import java.awt.*;
import java.awt.event.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map; 

public class GUI extends Frame implements ActionListener{ 
	
	public TextField a;
	public TextField b;
	
	public String PdfFilePath;
	
	TextArea pdfSourcePath = new TextArea("",3,1,TextArea.SCROLLBARS_BOTH);

	TextArea programInfo = new TextArea("",3,1,TextArea.SCROLLBARS_BOTH);
	int s = 0;
	int space = 30;
	int y = 0;
	
	Map<String, TextField> inputFields = new HashMap<String, TextField>();
	
	GUI(){ 
		
		Label l = new Label();
		l.setText("Angaben zur eigenen Firma:");
		l.setBounds(30, 50+s, 220, 20);
		add(l);
		s+=space;

		addLabelTextfieldPair("Name", "OwnOrganisationName");
		addLabelTextfieldPair("Ort", "OwnLocation");
		addLabelTextfieldPair("Straﬂe", "OwnStreet");
		addLabelTextfieldPair("PLZ", "OwnZIP");
		addLabelTextfieldPair("Land", "OwnCountry");
		
		addLabelTextfieldPair("Steuernummer", "OwnTaxID" );
		addLabelTextfieldPair("UmsatzsteuerID", "OwnVATID");
		addLabelTextfieldPair("BIC", "OwnBIC");
		addLabelTextfieldPair("IBAN", "OwnIBAN");
		addLabelTextfieldPair("Name der Bank", "OwnBankName");
		
		addLabelTextfieldPair("Volltext Info der Firma", "OwnOrganisationFullPlaintextInfo");

				
		
		s+=10;
		Button choosePdfButton=new Button("PDF-Datei ausw‰hlen"); 
		choosePdfButton.addActionListener(this);
		choosePdfButton.setBounds(30,50+s,280,30);// setting button position  
		add(choosePdfButton);//adding button into frame
		s+=space;
		
		pdfSourcePath.setBounds(30, 50+s, 500, 40);
		add(pdfSourcePath);
		s+=space+10;
		
		Button b=new Button("ZUGFeRD-Dokument erstellen"); 
		b.addActionListener(this);
		b.setBounds(30,50+s,280,30);// setting button position  
		add(b);//adding button into frame
		s+=space;
		
		
		programInfo.setBounds(30, 50+s, 500, 100);
		add(programInfo);
		s+=space;
		
		
		s=0;
		y += 300;
		Label l2 = new Label();
		l2.setText("Angaben  zur Rechnung: (dd.MM.yyyy)");
		l2.setBounds(30+y, 50+s, 260, 20);
		add(l2);
		s+=space;
		addLabelTextfieldPair("Rechnungsnummer", "Number" );
		addLabelTextfieldPair("Rechnungsdatum", "DeliveryDate" );
		addLabelTextfieldPair("F‰lligkeitsdatum", "DueDate" );
		addLabelTextfieldPair("Leistungsdatum", "IssueDate" );


		
		s = 0;
		y = 600;
		Label l5 = new Label();
		l5.setText("Angaben zum Rechnungsempf‰nger:");
		l5.setBounds(30+y, 50+s, 220, 20);
		add(l5);
		s+=space;

		
		addLabelTextfieldPair("Name", "Name");
		addLabelTextfieldPair("Straﬂe", "Street");
		addLabelTextfieldPair("Ort", "Location");
		addLabelTextfieldPair("PLZ", "ZIP");
		addLabelTextfieldPair("Land", "Country");
		addLabelTextfieldPair("UmsatzsteuerID", "VATID");

		s = 0;
		y += 300;
		Label l3 = new Label();
		l3.setText("Rechnungsposition 1:");
		l3.setBounds(30+y, 50+s, 200, 20);
		add(l3);
		s+=space;
		
		addLabelTextfieldPair("Beschreibung1", "desc1");
		addLabelTextfieldPair("Name1", "name1");
		addLabelTextfieldPair("Einheit1", "unit1");
		addLabelTextfieldPair("MwSt-Satz1", "vATPercent1");
		addLabelTextfieldPair("Preis pro Einheit 1", "price1");
		addLabelTextfieldPair("Anzahl Einheiten 1", "quantity1");

		Label l4 = new Label();
		l4.setText("Rechnungsposition 2:");
		l4.setBounds(30+y, 50+s, 200, 20);
		add(l4);
		s+=space;
		
		addLabelTextfieldPair("Beschreibung2", "desc2");
		addLabelTextfieldPair("Name2", "name2");
		addLabelTextfieldPair("Einheit2", "unit2");
		addLabelTextfieldPair("MwSt-Satz2", "vATPercent2");
		addLabelTextfieldPair("Preis pro Einheit 2", "price2");
		addLabelTextfieldPair("Anzahl Einheiten 2", "quantity2");

		
		setName("ZUGFeRD-Dokument erstellen");
		setSize(1300,900);//frame size 300 width and 300 height  
		setLayout(null);//no layout manager  
		setVisible(true);//now frame will be visible, by default not visible  
		}  
	
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()) {
			case "PDF-Datei ausw‰hlen":
				PdfFilePath = choosePdfFile();
				pdfSourcePath.setText(PdfFilePath);
				break;
			case "ZUGFeRD-Dokument erstellen":
				MustangWriter mw = new MustangWriter();
				programInfo.setText("");
				mw.apply(PdfFilePath, programInfo, inputFields);
				break;
		}
  
		} 
	
	public String choosePdfFile() {
		FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.pdf");
		fd.setVisible(true);
		return fd.getDirectory()+fd.getFile();
	}

	public static void main(String[] args) {
		GUI f = new GUI();
		f.a.getText();
		f.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent we) {
	        	System.exit(0);
	         }
	     }
		);
		
	}
  public void addLabelTextfieldPair(String displayName, String fieldName ) {
		Label l = new Label();
		l.setText(displayName);
		l.setBounds(30+y, 50+s, 170, 20);
		add(l);
		s+=20;
		
		a=new TextField("");  
		a.setBounds(30+y, 50+s,170,20);
		add(a);
		s+=space;
		
		inputFields.put(fieldName, a);
  }

}
