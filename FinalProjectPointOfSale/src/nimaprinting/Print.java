package nimaprinting;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Print {
	
	
	
	public Print(String toPrint) {
		this.printToPrinter(toPrint);
	}
	
	private void printToPrinter(String toPrint)
	{
	    String printData = toPrint;
	    PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintable(new Printer(printData));
	    boolean doPrint = job.printDialog();
	    if (doPrint)
	    { 
	        try 
	        {
	            job.print();
	        }
	        catch (PrinterException e)
	        {
	            e.printStackTrace();
	        }
	    }
	}
}
