
package Reportes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Modelo.Conexion;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
// Método para generar un reporte de productos en formato Excel
public class Excel {
    public static void reporte() {
 
        // Crear un nuevo libro de trabajo y una hoja para el reporte
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Productos");
 
        try {
            // Cargar una imagen (logo) desde el sistema de archivos
            InputStream is = new FileInputStream("src/img/logoV.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
 
            // Crear un helper para la creación de elementos en el libro de trabajo
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            // Definir el anclaje para colocar la imagen en la hoja
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);// Columna donde empieza la imagen
            anchor.setRow1(1);// Fila donde empieza la imagen
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3);// Redimensionar la imagen
 
            // Crear un estilo para el título del reporte
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);
 
            // Crear una fila para el título y establecer su estilo
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Productos");
 
            // Combinar celdas para centrar el título
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
 
            // Definir los encabezados de las columnas del reporte
            String[] cabecera = new String[]{"Código", "Nombre", "Precio", "Existencia"};
 
            // Crear un estilo para los encabezados
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            // Establecer la fuente para los encabezados
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            // Crear una fila para los encabezados y aplicar el estilo
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            // Crear una fila para los encabezados y aplicar el estilo
            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConnection();
 
            int numFilaDatos = 5;// Fila donde comienzan los datos
 
            // Crear un estilo para los datos de las celdas
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
 
            // Ejecutar la consulta SQL para obtener los datos de los productos
            ps = conn.prepareStatement("SELECT codigo, nombre, precio, stock FROM productos");
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();// Número de columnas en el resultado
 
            // Llenar la hoja con los datos de los productos
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));// Obtener los valores de cada columna
                }
 
 
                numFilaDatos++;
            }
            
            // Ajustar el tamaño de las columnas al contenido
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            
            sheet.setZoom(150);// Ajustar el zoom de la hoja
            
            // Definir la ubicación y nombre del archivo Excel a generar
            String fileName = "productos";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);// Escribir los datos en el archivo
            fileOut.close();
            Desktop.getDesktop().open(file);// Abrir el archivo Excel generado
            JOptionPane.showMessageDialog(null, "Reporte Generado");// Mostrar un mensaje de éxito
 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);// Manejo de la excepción FileNotFoundException
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);// Manejo de las excepciones IOException y SQLException
        }
 
    }
}