package nimaprinting;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Printer implements Printable 
{
	private String printData;


	public Printer(String printDataIn)
	{
		this.printData = printDataIn;
	}
	int[] pageBreaks;  // array of page break line positions.

	/* Synthesise some sample lines of text */
	String[] textLines;
	private void initTextLines() {
		textLines = printData.split("\\n");
	}
	@Override
	public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException
	{
		Font font = new Font("Dialog", Font.PLAIN, 10);
		FontMetrics metrics = g.getFontMetrics(font);
		int lineHeight = metrics.getHeight();

		if (pageBreaks == null) {
			initTextLines();
			int linesPerPage = (int)(pf.getImageableHeight()/lineHeight);
			int numBreaks = (textLines.length-1)/linesPerPage;
			pageBreaks = new int[numBreaks];
			for (int b=0; b<numBreaks; b++) {
				pageBreaks[b] = (b+1)*linesPerPage; 
			}
		}

		if (pageIndex > pageBreaks.length) {
			return NO_SUCH_PAGE;
		}

		/* User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 * Since we are drawing text we
		 */
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(font);
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		/* Draw each line that is on this page.
		 * Increment 'y' position by lineHeight for each line.
		 */
		int y = 0; 
		int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
		int end   = (pageIndex == pageBreaks.length)
				? textLines.length : pageBreaks[pageIndex];
		for (int line=start; line<end; line++) {
			y += lineHeight;
			g.drawString(textLines[line], 0, y);
		}

		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;

	}
}