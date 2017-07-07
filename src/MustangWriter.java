import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.mustangproject.ZUGFeRD.IZUGFeRDAllowanceCharge;
import org.mustangproject.ZUGFeRD.IZUGFeRDExportableContact;
import org.mustangproject.ZUGFeRD.IZUGFeRDExportableItem;
import org.mustangproject.ZUGFeRD.IZUGFeRDExportableProduct;
import org.mustangproject.ZUGFeRD.IZUGFeRDExportableTransaction;
import org.mustangproject.ZUGFeRD.ZUGFeRDExporter;

import java.awt.*;

public class MustangWriter implements IZUGFeRDExportableTransaction {
	
	public Map<String, TextField> inputFields;
	@Override
	public String getCurrency() {
		// TODO Auto-generated method stub
		return null;
	}
	
	SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yyyy");
	@Override
	public Date getDeliveryDate() {
		try {
			return myFormat.parse(inputFields.get("DeliveryDate").getText());
		} catch (ParseException e) {
			return null;
		}
	}

	@Override
	public Date getDueDate() {
		try {
			return myFormat.parse(inputFields.get("DueDate").getText());
		} catch (ParseException e) {
			return null;
		}}
	@Override
	public Date getIssueDate() {
		// TODO Auto-generated method stub
		try {
			return myFormat.parse(inputFields.get("IssueDate").getText());
		} catch (ParseException e) {
			return null;
		}
	}
	@Override
	public String getNumber() {
		// TODO Auto-generated method stub
		return inputFields.get("Number").getText();
	}
	@Override
	public String getOwnBIC() {
		// TODO Auto-generated method stub
		return inputFields.get("OwnBIC").getText();
	}
	@Override
	public String getOwnBankName() {
		// TODO Auto-generated method stub
		return inputFields.get("OwnBankName").getText();
	}
	@Override
	public String getOwnCountry() {
		// TODO Auto-generated method stub
		return inputFields.get("OwnCountry").getText();
	}
	@Override
	public String getOwnIBAN() {
		// TODO Auto-generated method stub
		return inputFields.get("OwnIBAN").getText();
	}
	@Override
	public String getOwnLocation() {
		// TODO Auto-generated method stub
		return inputFields.get("OwnLocation").getText();
	}
	@Override
	public String getOwnOrganisationFullPlaintextInfo() {
		// TODO Auto-generated method stub
		return inputFields.get("OwnOrganisationFullPlaintextInfo").getText();

	}
	@Override
	public String getOwnOrganisationName() {
		// TODO Auto-generated method stub
		return inputFields.get("OwnOrganisationName").getText();
	}
	@Override
	public String getOwnPaymentInfoText() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getOwnStreet() {
		// TODO Auto-generated method stub
		return inputFields.get("OwnStreet").getText();
	}
	@Override
	public String getOwnTaxID() {
		// TODO Auto-generated method stub
		return inputFields.get("OwnTaxID").getText();
	}
	@Override
	public String getOwnVATID() {
		// TODO Auto-generated method stub
		return inputFields.get("OwnVATID").getText();
	}
	@Override
	public String getOwnZIP() {
		// TODO Auto-generated method stub
		return inputFields.get("OwnZIP").getText();
	}
	@Override
	public String getPaymentTermDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IZUGFeRDExportableContact getRecipient() {
		// TODO Auto-generated method stub
		return new Contact();
	}
	@Override
	public String getReferenceNumber() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IZUGFeRDAllowanceCharge[] getZFAllowances() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IZUGFeRDAllowanceCharge[] getZFCharges() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IZUGFeRDExportableItem[] getZFItems() {
		if(inputFields.get("price2").getText().length()>0) {
			Item[] allItems=new Item[2];
			Product designProduct=new Product(inputFields.get("desc1").getText(), inputFields.get("name1").getText(), inputFields.get("unit1").getText(), new BigDecimal(inputFields.get("vATPercent1").getText()));
			Product balloonProduct=new Product(inputFields.get("desc2").getText(), inputFields.get("name2").getText(), inputFields.get("unit2").getText(), new BigDecimal(inputFields.get("vATPercent2").getText()));
			allItems[0]=new Item(new BigDecimal(inputFields.get("price1").getText()), new BigDecimal(inputFields.get("quantity1").getText()), designProduct);
			allItems[1]=new Item(new BigDecimal(inputFields.get("price2").getText()), new BigDecimal(inputFields.get("quantity2").getText()), balloonProduct);
					
			return allItems;
		}
		else {
			Item[] allItems=new Item[1];
			Product designProduct=new Product(inputFields.get("desc1").getText(), inputFields.get("name1").getText(), inputFields.get("unit1").getText(), new BigDecimal(inputFields.get("vATPercent1").getText()));
			allItems[0]=new Item(new BigDecimal(inputFields.get("price1").getText()), new BigDecimal(inputFields.get("quantity1").getText()), designProduct);
					
			return allItems;
		}

	}
	@Override
	public IZUGFeRDAllowanceCharge[] getZFLogisticsServiceCharges() {
		// TODO Auto-generated method stub
		return null;
	}
	class Contact implements IZUGFeRDExportableContact {

		@Override
		public String getCountry() {
			// TODO Auto-generated method stub
			return inputFields.get("Country").getText();
		}

		@Override
		public String getLocation() {
			// TODO Auto-generated method stub
			return inputFields.get("Location").getText();
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return inputFields.get("Name").getText();
		}

		@Override
		public String getStreet() {
			// TODO Auto-generated method stub
			return inputFields.get("Street").getText();
		}

		@Override
		public String getVATID() {
			// TODO Auto-generated method stub
			return inputFields.get("VATID").getText();
		}

		@Override
		public String getZIP() {
			// TODO Auto-generated method stub
			return inputFields.get("ZIP").getText();
		}}
	class Item implements IZUGFeRDExportableItem {
		@Override
		public IZUGFeRDAllowanceCharge[] getItemAllowances() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public IZUGFeRDAllowanceCharge[] getItemCharges() {
			// TODO Auto-generated method stub
			return null;
		}
		public Item(BigDecimal price, BigDecimal quantity, Product product) {
			super();
			this.price = price;
			this.quantity = quantity;
			this.product = product;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public BigDecimal getQuantity() {
			return quantity;
		}
		public void setQuantity(BigDecimal quantity) {
			this.quantity = quantity;
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		private BigDecimal price, quantity;
		private Product product;
		}
	class Product implements IZUGFeRDExportableProduct {
		public Product(String description, String name, String unit, BigDecimal vATPercent) {
			super();
			this.description = description;
			this.name = name;
			this.unit = unit;
			VATPercent = vATPercent;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public BigDecimal getVATPercent() {
			return VATPercent;
		}
		public void setVATPercent(BigDecimal vATPercent) {
			VATPercent = vATPercent;
		}
		private String description, name, unit;
		private BigDecimal VATPercent;
		}
	public void apply(String pdfSourceFile, TextArea infoLabel, Map<String, TextField> input) {
		try {
			
			inputFields =  input;
			String outputFile = pdfSourceFile.replaceFirst(".pdf", "_zugferd.pdf");
			String outputFileA1 = pdfSourceFile.replaceFirst(".pdf", "_A1.pdf");

			//System.out.println("Lese Blanko-PDF");
			infoLabel.append("Lese Blanko-PDF");
			// automatically add Zugferd to all outgoing invoices
			ZUGFeRDExporter ze = new ZUGFeRDExporter();
			
//			infoLabel.append("\r\n" + "Wandle in PDF/A1 um");
//			convertPDFtoPDFa1(pdfSourceFile, outputFileA1 );
			outputFileA1 = pdfSourceFile;
			
			infoLabel.append("\r\n" + "Wandle in PDF/A-3u um");
			ze.PDFmakeA3compliant(outputFileA1, "My Application",
					System.getProperty("user.name"), true);
			
			//System.out.println("ZUGFeRD-Daten generieren und anhängen");
			infoLabel.append('\n' +"ZUGFeRD-Daten generieren und anhängen");
			ze.PDFattachZugferdFile(this);
			
			//System.out.println("Schreibe ZUGFeRD-PDF");
			infoLabel.append('\n' +"Schreibe ZUGFeRD-PDF"+'\n'+outputFile);
			ze.export(outputFile);
			
			
			//System.out.println("Fertig.");
			infoLabel.append('\n' +"Fertig.");
			} catch (IOException e) {
				infoLabel.setText("Fehler:"+ e.getMessage());
			} catch (TransformerException e) {
				infoLabel.setText("Fehler:"+ e.getMessage());
				e.printStackTrace();
			} catch (JAXBException e) {
				infoLabel.setText("Fehler:"+ e.getMessage());
				e.printStackTrace();
			}catch (Exception e) {
				infoLabel.setText("Fehler:"+ e.getMessage());
				e.printStackTrace();
		}
	}
	
	public static void convertPDFtoPDFa1(String inputFile, String outputFile) throws IOException {
		Runtime rt = Runtime.getRuntime();
		String cmdString = "gswin32 -dPDFA -dBATCH -dNOPAUSE -sColorConversionStrategy=/RGB -sProcessColorModel=DeviceRGB -sDEVICE=pdfwrite -sPDFACompatibilityPolicy=1 -sOutputFile="+outputFile+"  "+inputFile;
		try {
			Process pr = rt.exec(cmdString);
			pr.waitFor();
		}
		catch (IOException e) {
			throw new IOException("Fehler beim Konvertieren von PDF nach PDF-A1: "+ e.getMessage());
		}
		catch (InterruptedException e) {
			throw new IOException("Fehler beim Konvertieren von PDF nach PDF-A1: "+ e.getMessage());
		}
	}
//	public static void main(String[] args) {
//			new MustangWriter().apply();
//	}

}
