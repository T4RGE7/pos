package pointOfSale;

import com.mercurypay.ws.sdk.MercuryWebRequest;

public class Response {

	private final String webURL = "https://w1.mercurydev.net/ws/ws.asmx";
	private int type;
	private String merchantID = "395347306=TOKEN", tranType = "Credit", tranCode, invoiceNo, refNo, memo, frequency = "OneTime", recordNo, partialAuth = "Allow", accountNum, expDate, purchase, authorize, gratuity, authCode, acqRefData, processData;
	private String result = "", response = "";
	
	public Response(int type__, String[] data)
	{
		this.type = type__;
		switch(type)
		{
		case 1: tranCode = "PreAuth";
			invoiceNo = data[0];
			refNo = data[1];
			memo = data[2];
			recordNo = "RecordNumberRequested";
			accountNum = data[3];
			expDate = data[4];
			purchase = data[5];
			authorize = data[6];
			gratuity = "0.0";
			result = getResult1();
			break;
		case 2: tranCode = "PreAuthCaptureByRecordNo";
			invoiceNo = data[0];
			refNo = data[1];
			recordNo = data[2];
			purchase = data[3];
			authorize = data[4];
			gratuity = data[5];
			authCode = data[6];
			acqRefData = data[7];
			result = getResult2();
			break;
		case 3: tranCode = "VoidSaleByRecordNo";
			invoiceNo = data[0];
			refNo = data[1];
			memo = data[2];
			recordNo = data[3];
			purchase = data[4];
			acqRefData = data[5];
			processData = data[6];
			authCode = data[7];
			result = getResult3();
			break;
		}
		
		//System.out.println(result);
		send();
		
	}
	
	private String getResult1()
	{
		String temp = "";
		temp += "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
		temp += merchantID;
		temp += "</MerchantID>\n\t\t<TranType>";
		temp += tranType;
		temp += "</TranType>\n\t\t<TranCode>";
		temp += tranCode;
		temp += "</TranCode>\n\t\t<InvoiceNo>";
		temp += invoiceNo;
		temp += "</InvoiceNo>\n\t\t<RefNo>";
		temp += refNo;
		temp += "</RefNo>\n\t\t<Memo>";
		temp += memo;
		temp += "</Memo>\n\t\t<Frequency>";
		temp += frequency;
		temp += "</Frequency>\n\t\t<RecordNo>";
		temp += recordNo;
		temp += "</RecordNo>\n\t\t<PartialAuth>";
		temp += partialAuth;
		temp += "</PartialAuth>\n\t\t<Account>\n\t\t\t<AcctNo>";
		temp += accountNum;
		temp += "</AcctNo>\n\t\t\t<ExpDate>";
		temp += expDate;
		temp += "</ExpDate>\n\t\t</Account>\n\t\t<Amount>\n\t\t\t<Purchase>";
		temp += purchase;
		temp += "</Purchase>\n\t\t\t<Authorize>";
		temp += authorize;
		temp += "</Authorize>\n\t\t</Amount>\n\t</Transaction>\n</TStream>";
		
		return temp;
	}
	
	private String getResult2()
	{
		String temp = "";
		temp += "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
		temp += merchantID;
		temp += "</MerchantID>\n\t\t<TranType>";
		temp += tranType;
		temp += "</TranType>\n\t\t<TranCode>";
		temp += tranCode;
		temp += "</TranCode>\n\t\t<PartialAuth>";
		temp += partialAuth;
		temp += "</PartialAuth>\n\t\t<InvoiceNo>";
		temp += invoiceNo;
		temp += "</InvoiceNo>\n\t\t<RefNo>";
		temp += refNo;
		temp += "</RefNo>\n\t\t<RecordNo>";
		temp += recordNo;
		temp += "</RecordNo>\n\t\t<Frequency>";
		temp += frequency;
		temp += "</Frequency>\n\t\t<Amount>\n\t\t\t<Purchase>";
		temp += purchase;
		temp += "</Purchase>\n\t\t\t<Authorize>";
		temp += authorize;
		temp += "</Authorize>\n\t\t\t<Gratuity>";
		temp += gratuity;
		temp += "</Gratuity>\n\t\t</Amount>\n\t\t<TranInfo>\n\t\t\t<AuthCode>";
		temp += authCode;
		temp += "</AuthCode>\n\t\t\t<AcqRefData>";
		temp += acqRefData;
		temp += "</AcqRefData>\n\t\t</TranInfo>\n\t</Transaction>\n</TStream>";
		
		return temp;
	}
	
	private String getResult3()
	{
		String temp = "";
		temp += "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
		temp += merchantID;
		temp += "</MerchantID>\n\t\t<TranType>";
		temp += tranType;
		temp += "</TranType>\n\t\t<TranCode>";
		temp += tranCode;
		temp += "</TranCode>\n\t\t<InvoiceNo>";
		temp += invoiceNo;
		temp += "</InvoiceNo>\n\t\t<RefNo>";
		temp += refNo;
		temp += "</RefNo>\n\t\t<Memo>";
		temp += memo;
		temp += "</Memo>\n\t\t<RecordNo>";
		temp += recordNo;
		temp += "</RecordNo>\n\t\t<Frequency>";
		temp += frequency;
		temp += "</Frequency>\n\t\t<Amount>\n\t\t\t<Purchase>";
		temp += purchase;
		temp += "</Purchase>\n\t\t</Amount>\n\t\t<TranInfo>\n\t\t\t<AcqRefData>";
		temp += acqRefData;
		temp += "</AcqRefData>\n\t\t\t<ProcessData>";
		temp += processData;
		temp += "</ProcessData>\n\t\t\t<AuthCode>";
		temp += authCode;
		temp += "</AuthCode>\n\t\t</TranInfo>\n\t</Transaction>\n</TStream>";
		
		return temp;
	}
	
	public void send()
	{
		try {
			MercuryWebRequest test = new MercuryWebRequest(webURL);
			test.addParameter("tran", result);
			test.addParameter("pw", "123TOKEN");
			test.setWebMethodName("CreditTransaction");
			test.setTimeout(10);
			String response = test.sendRequest();
			this.response = response.replaceAll(">\t", ">\n\t");
			//System.out.println(response.replaceAll(">\t", ">\n\t"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getResponse() {
		return this.response;
	}
	
	public String getXML() {
		return this.result;
	}
}
