import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class servlet extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // response.setContentType("text/html;carset-UTF-8");
        // PrintWriter out = response.getWriter();

        try {
            Part filePart = request.getPart("wordFile"); // Assuming you have a form field named "wordFile" for file
                                                         // upload

            // Read the uploaded Word file
            InputStream wordInputStream = filePart.getInputStream();
            XWPFDocument wordDocument = new XWPFDocument(wordInputStream);

            // Create a new PDF document
            PDDocument pdfDocument = new PDDocument();
            PDPage page = new PDPage();
            pdfDocument.addPage(page);

            // Extract text from wordDocument and write it to the PDF document
            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);
            contentStream.setFont(font, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700); // Adjust the coordinates as needed
            for (XWPFParagraph paragraph : wordDocument.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        contentStream.showText(text);
                    }
                }
            }
            contentStream.endText();
            contentStream.close();

            // Save the PDF to a file or send it as a response
            pdfDocument.save(response.getOutputStream());
            pdfDocument.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // handles exceptions
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
