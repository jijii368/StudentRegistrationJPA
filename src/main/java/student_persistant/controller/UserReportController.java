package student_persistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.ArrayList;
import java.io.IOException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import student_persistant.model.UserModel;

@Controller
public class UserReportController {

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/generateReport")
    @ResponseBody
    public void generateReport(HttpServletRequest request,HttpServletResponse response,
                               @RequestParam("export") String export) throws IOException {
        String path = servletContext.getRealPath("/WEB-INF/Jasper/userReporting.jrxml");
        JRBeanCollectionDataSource source = null;
        JasperReport jasperReport;
        JasperPrint print;
        ArrayList<UserModel> list = new ArrayList<>();;
        Object listAttribute = servletContext.getAttribute("list"); // Get the attribute from ServletContext
        if (listAttribute instanceof Vector) { // Check if the attribute is an instance of Vector
            Vector<UserModel> vectorList = (Vector<UserModel>) listAttribute; // Cast the attribute to Vector<User>
            list = new ArrayList<>(vectorList); // Convert Vector to ArrayList
        } else if (listAttribute instanceof ArrayList) { // Check if the attribute is an ArrayList
            list = (ArrayList<UserModel>) listAttribute; // Cast the attribute to ArrayList<User>
        } else {
            // Handle the case where the attribute is not set or is not of the expected type
            System.out.println("Attribute 'list' is null or not an instance of Vector or ArrayList");
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "User List");

        try {
            source = new JRBeanCollectionDataSource(list);
            jasperReport = JasperCompileManager.compileReport(path);
            print = JasperFillManager.fillReport(jasperReport, parameters, source);

            if ("excel".equals(export)) {
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename=userList.xls");

                JRXlsExporter exporterXLS = new JRXlsExporter();
                exporterXLS.setExporterInput(new SimpleExporterInput(print));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                exporterXLS.setConfiguration(configuration);
                exporterXLS.exportReport();
            } else {
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=userList.pdf");

                JRPdfExporter exporterPdf = new JRPdfExporter();
                exporterPdf.setExporterInput(new SimpleExporterInput(print));
                exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                exporterPdf.exportReport();
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}

