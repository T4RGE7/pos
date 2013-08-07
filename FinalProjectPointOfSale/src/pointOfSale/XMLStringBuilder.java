package pointOfSale;


public class XMLStringBuilder {

	private String merchantID, tranType, tranCode, invoiceNo, refNo, memo, frequency, recordNo, partialAuth, accountNum, expDate, purchase, authorize, gratuity;
	private String result;
	
	public XMLStringBuilder(String merchantID, String tranType,
			String tranCode, String invoiceNo, String refNo, String memo,
			String frequency, String recordNo, String partialAuth,
			String accountNum, String expDate, String purchase,
			String authorize, String gratuity)
	{
		this.merchantID = merchantID;
		this.tranType = tranType;
		this.tranCode = tranCode;
		this.invoiceNo = invoiceNo;
		this.refNo = refNo;
		this.memo = memo;
		this.frequency = frequency;
		this.recordNo = recordNo;
		this.partialAuth = partialAuth;
		this.accountNum = accountNum;
		this.expDate = expDate;
		this.purchase = purchase;
		this.authorize = authorize;
		this.gratuity = gratuity;
		generate();
	}
	
	public void generate()
	{
		result = "";
		result += "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
		result += merchantID;
		result += "</MerchantID>\n\t\t<TranType>";
		result += tranType;
		result += "</TranType>\n\t\t<TranCode>";
		result += tranCode;
		result += "</TranCode>\n\t\t<InvoiceNo>";
		result += invoiceNo;
		result += "</InvoiceNo>\n\t\t<RefNo>";
		result += refNo;
		result += "</RefNo>\n\t\t<Memo>";
		result += memo;
		result += "</Memo>\n\t\t<PartialAuth>";
		result += partialAuth;
		result += "</PartialAuth>\n\t\t<Account>\n\t\t\t<AcctNo>";
		result += accountNum;
		result += "</AcctNo>\n\t\t\t<ExpDate>";
		result += expDate;
		result += "</ExpDate>\n\t\t</Account>\n\t\t<Amount>\n\t\t\t<Purchase>";
		result += purchase;
		result += "</Purchase>\n\t\t\t<Authorize>";
		result += authorize;
		result += "</Authorize>\n";
		if(gratuity != null)
		{
			result += "\t\t\t<Gratuity>";
			result += gratuity;
			result += "</Gratuity>\n";
		}
		result += "\t\t</Amount>\n\t</Transaction>\n</TStream>";
		System.out.println(result);
	}
	
	public String getResult()
	{
		return result;
	}
	
	
}
