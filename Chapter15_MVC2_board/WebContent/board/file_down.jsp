<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.*" %>
<%@page import="java.net.URLEncoder" %>

<%
String fileName = request.getParameter("file_name");

String savePath="boardUpload";

ServletContext context = request.getServletContext();
String sDownloadPath = context.getRealPath(savePath);

String sFilePath = sDownloadPath + File.separator + fileName;

File file = new File(sFilePath);
out.print(file);

byte[] b = new byte[10*1024*1024];//10m

FileInputStream in = new FileInputStream(file);

String sMimeType = request.getServletContext().getMimeType(sFilePath);

if(sMimeType == null) {
	sMimeType="application/octet-stream";
}

response.setContentType(sMimeType);

String agent = request.getHeader("user-Agent");
String fileNameEncoding;

if(agent.indexOf("MSIE") > -1 || agent.indexOf("Trident") > -1) {
	fileNameEncoding = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
} else {
	fileNameEncoding = new String(fileName.getBytes("UTF-8"),"iso-8859-1");//"8859_1"
}

response.setHeader("Content-Disposition", "attachment; filename="+fileNameEncoding);

ServletOutputStream out2 = response.getOutputStream();

int numRead;
while((numRead = in.read(b,0,b.length)) != -1) {
	out2.write(b,0,numRead);
}
out2.flush();
out2.close();

in.close(); 
%>