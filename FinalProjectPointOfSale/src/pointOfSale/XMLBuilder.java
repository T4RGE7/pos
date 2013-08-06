package pointOfSale;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLBuilder {

	private String merchantID, tranType, tranCode, invoiceNo, refNo, memo, frequency, recordNo, partialAuth, encryptedFormat, accountSource, encryptedBlock, encryptedKey, purchase;
	private final String tag1 = "MerchantID", tag2 = "TranType", tag3 = "TranCode", tag4 = "InvoiceNo", tag5 = "RefNo", tag6 = "Memo", tag7 = "Frequency", tag8 = "RecordNo", tag9 = "PartialAuth", tag10 = "EncryptedFormat", tag11 = "AccountSource", tag12 = "EncryptedBlock", tag13 = "EncryptedKey", tag14 = "Purchase";
	private final String level1 = "TStream", level2 = "Transaction", level3 = "Account", level4 = "Amount";
	
	
	public XMLBuilder(String merchantID, String tranType, String tranCode, String invoiceNo,
			String refNo, String memo, String frequency, String recordNo,
			String partialAuth, String encryptedFormat, String accountSource,
			String encryptedBlock, String encryptedKey, String purchase) {
		this.merchantID = merchantID;
		this.tranType = tranType;
		this.tranCode = tranCode;
		this.invoiceNo = invoiceNo;
		this.refNo = refNo;
		this.memo = memo;
		this.frequency = frequency;
		this.recordNo = recordNo;
		this.partialAuth = partialAuth;
		this.encryptedFormat = encryptedFormat;
		this.accountSource = accountSource;
		this.encryptedBlock = encryptedBlock;
		this.encryptedKey = encryptedKey;
		this.purchase = purchase;
	}
	
	public void build() {
		DocumentBuilderFactory docFactory = null;
		DocumentBuilder docBuilder = null;
		Document doc = null;
		TransformerFactory transformerFactory = null;
		Transformer transformer = null;
		DOMSource source = null;
		StreamResult result = null;
		
		
		try
		{
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			
			doc = docBuilder.newDocument();
			
			Element rootElement1 = doc.createElement(this.level1);
			doc.appendChild(rootElement1);
			
			Element rootElement2 = doc.createElement(this.level2);
			rootElement1.appendChild(rootElement2);
			
			Element transaction1 = doc.createElement(this.tag1);
			transaction1.appendChild(doc.createTextNode(this.merchantID));
			rootElement2.appendChild(transaction1);
			
			Element transaction2 = doc.createElement(this.tag2);
			transaction2.appendChild(doc.createTextNode(this.tranType));
			rootElement2.appendChild(transaction2);
			
			Element transaction3 = doc.createElement(this.tag3);
			transaction3.appendChild(doc.createTextNode(this.tranCode));
			rootElement2.appendChild(transaction3);
			
			Element transaction4 = doc.createElement(this.tag4);
			transaction4.appendChild(doc.createTextNode(this.invoiceNo));
			rootElement2.appendChild(transaction4);
			
			Element transaction5 = doc.createElement(this.tag5);
			transaction5.appendChild(doc.createTextNode(this.refNo));
			rootElement2.appendChild(transaction5);
			
			Element transaction6 = doc.createElement(this.tag6);
			transaction6.appendChild(doc.createTextNode(this.memo));
			rootElement2.appendChild(transaction6);
			
			Element transaction7 = doc.createElement(this.tag7);
			transaction7.appendChild(doc.createTextNode(this.frequency));
			rootElement2.appendChild(transaction7);
			
			Element transaction8 = doc.createElement(this.tag8);
			transaction8.appendChild(doc.createTextNode(this.recordNo));
			rootElement2.appendChild(transaction8);
			
			Element transaction9 = doc.createElement(this.tag9);
			transaction9.appendChild(doc.createTextNode(this.partialAuth));
			rootElement2.appendChild(transaction9);
			
			Element rootElement3 = doc.createElement(this.level3);
			rootElement2.appendChild(rootElement3);
			
			Element account1 = doc.createElement(this.tag10);
			account1.appendChild(doc.createTextNode(this.encryptedFormat));
			rootElement3.appendChild(account1);
			
			Element account2 = doc.createElement(this.tag11);
			account2.appendChild(doc.createTextNode(this.accountSource));
			rootElement3.appendChild(account2);
			
			Element account3 = doc.createElement(this.tag12);
			account3.appendChild(doc.createTextNode(this.encryptedBlock));
			rootElement3.appendChild(account3);
			
			Element account4 = doc.createElement(this.tag13);
			account4.appendChild(doc.createTextNode(this.encryptedKey));
			rootElement3.appendChild(account4);
			
			Element rootElement4 = doc.createElement(this.level4);
			rootElement2.appendChild(rootElement4);
			
			Element amount1 = doc.createElement(this.tag14);
			amount1.appendChild(doc.createTextNode(this.purchase));
			rootElement4.appendChild(amount1);
			
			
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			source = new DOMSource(doc);
			result = new StreamResult(new File("output.xml"));
			
			transformer.transform(source, result);
			System.out.println("Success");
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
}
