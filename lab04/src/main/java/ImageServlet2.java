

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Servlet implementation class ImageServlet2
 */
public class ImageServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String filePath = getServletContext().getRealPath("/images/img2.jpg");
		File downloadFile = new File(filePath);
		FileInputStream fin = new FileInputStream(downloadFile);
		
		ServletContext context = getServletContext();
		// gets MIME type of file
		String mimeType = context.getMimeType(filePath);
		if(mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		
		// modifies response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		
		// forces download
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		
		// obtains response's output stream
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		
		while((bytesRead = fin.read(buffer)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		
		fin.close();
		os.close();
	}

}
