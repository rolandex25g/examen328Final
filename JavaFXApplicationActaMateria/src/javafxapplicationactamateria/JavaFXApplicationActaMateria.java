/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationactamateria;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author HP 2020
 */
public class JavaFXApplicationActaMateria extends Application {
    String idMaterias[] = new String[30];
    int nummat=0;
    
    String registros[][] = new String[100][5];
    int numreg=0;
    
    ComboBox combobox = new ComboBox();
    TableView tableview= new TableView();
    
    public class Registro {
        public String nombre;
        public String paterno;
        public String materno;
        public String ci;
        public String nota;

        public Registro(String nombre, String paterno,String materno,String ci, String nota) {
            this.nombre = nombre;
            this.paterno = paterno;
            this.materno = materno;
            this.ci = ci;
            this.nota = nota;
        }
        public String getNombre() {
            return nombre;
        }

        public String getPaterno() {
            return paterno;
        }
        public String getMaterno() {
            return materno;
        }
        public String getCi() {
            return ci;
        }
        public String getNota() {
            return nota;
        }
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        Button btn2 = new Button();
        
        Label label1= new Label("Periodo");
        Label label2= new Label("Gestion");
        Label label3= new Label(".");
        TextField textfield1= new TextField();
        TextField textfield2= new TextField();
        
        
        
        TableColumn<Registro, String> nombre=new TableColumn("Nombre");
        nombre.setCellValueFactory(new PropertyValueFactory<Registro, String>("nombre"));
        TableColumn<Registro, String> paterno=new TableColumn("Paterno");
        paterno.setCellValueFactory(new PropertyValueFactory<Registro, String>("paterno"));
        TableColumn<Registro, String> materno=new TableColumn("Materno");
        materno.setCellValueFactory(new PropertyValueFactory<Registro, String>("materno"));
        TableColumn<Registro, String> ci=new TableColumn("CI");
        ci.setCellValueFactory(new PropertyValueFactory<Registro, String>("ci"));
        TableColumn<Registro, String> nota=new TableColumn("Nota");
        nota.setCellValueFactory(new PropertyValueFactory<Registro, String>("nota"));
        
        tableview.getColumns().addAll(nombre,paterno,materno,ci,nota);
        
        
        
        
        btn.setText("Buscar");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                String periodo=textfield1.getText();
                String gestion=textfield2.getText();
                
                
                BuscarMaterias(periodo,gestion);
                
            }
        });
        
        btn2.setText("Reporte");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                //String periodo=textfield1.getText();
                //tring gestion=textfield2.getText();
                
                
                ReportePDF();
                
            }
        });
        
        combobox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                int x=combobox.getSelectionModel().getSelectedIndex();
                if(x>=0){
                    //label3.setText(String.valueOf(x));
                    String periodo=textfield1.getText();
                    String gestion=textfield2.getText();
                    //label3.setText(idMaterias[x]);
                    //label3.setText(idMaterias[0]+"_"+idMaterias[1]+"_"+idMaterias[2]+"_");
                    BuscarNotas(periodo,gestion,idMaterias[x]);
                }
            }
        });
        
        
        //StackPane root = new StackPane();
        //root.getChildren().add(btn);
        GridPane root = new GridPane();
        
        //Definir espacios de columnas
        ColumnConstraints col1= new ColumnConstraints();
        col1.setPercentWidth(4);
        ColumnConstraints col2= new ColumnConstraints();
        col2.setPercentWidth(23);
        ColumnConstraints col3= new ColumnConstraints();
        col3.setPercentWidth(23);
        ColumnConstraints col4= new ColumnConstraints();
        col4.setPercentWidth(23);
        ColumnConstraints col5= new ColumnConstraints();
        col5.setPercentWidth(23);
        ColumnConstraints col6= new ColumnConstraints();
        col6.setPercentWidth(4);
        root.getColumnConstraints().addAll(col1,col2,col3,col4,col5,col6);
        
        GridPane.setColumnIndex(label1, 1);
        GridPane.setRowIndex(label1, 0);
        GridPane.setColumnSpan(label1, 2);
        root.getChildren().add(label1);
        
        GridPane.setColumnIndex(textfield1, 2);
        GridPane.setRowIndex(textfield1, 0);  
        GridPane.setColumnSpan(textfield1, 2);
        root.getChildren().add(textfield1);
        
        GridPane.setColumnIndex(label2, 1);
        GridPane.setRowIndex(label2, 1);
        GridPane.setColumnSpan(label2, 2);
        root.getChildren().add(label2);
        
        GridPane.setColumnIndex(textfield2, 2);
        GridPane.setRowIndex(textfield2, 1);  
        GridPane.setColumnSpan(textfield2, 2);
        root.getChildren().add(textfield2);

        GridPane.setColumnIndex(label3, 1);
        GridPane.setRowIndex(label3, 2);
        GridPane.setColumnSpan(label3, 2);
        root.getChildren().add(label3);

        GridPane.setColumnIndex(btn, 1);
        GridPane.setRowIndex(btn, 3);
        GridPane.setColumnSpan(btn, 2);
        root.getChildren().add(btn);
        
        GridPane.setColumnIndex(combobox, 1);
        GridPane.setRowIndex(combobox, 4);
        GridPane.setColumnSpan(combobox, 4);
        root.getChildren().add(combobox);
        
        GridPane.setColumnIndex(btn2, 4);
        GridPane.setRowIndex(btn2, 5);
        GridPane.setColumnSpan(btn2, 1);
        root.getChildren().add(btn2);
        
        GridPane.setColumnIndex(tableview, 1);
        GridPane.setRowIndex(tableview, 6);
        GridPane.setColumnSpan(tableview, 4);
        root.getChildren().add(tableview);
        
        
        Scene scene = new Scene(root, 600, 550);
        
        primaryStage.setTitle("Acta Materia");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void BuscarMaterias(String periodo,String gestion){   
        try { 
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            //TODO write your implementation code here:
            Connection con = null;
            
                con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/academico2", "postgres", "root");
            
            
            Statement stmt = con.createStatement();
            String query = "select M.idMateria,M.nombre_materia" +
                " from inscripcion as I, alumno as A, materia as M" +
                " where I.idAlumno=A.idAlumno" +
                " and I.idMateria=M.idMateria" +
                " and I.periodo='" + periodo + "'"+
                " and I.gestion='" + gestion + "'";;
            ResultSet result = stmt.executeQuery(query);
            
            combobox.getItems().clear();
            nummat=0;
            while(result.next())
            {
                
                if(Arrays.asList(idMaterias).contains(result.getString("idMateria"))==false){
                    combobox.getItems().add(result.getString("nombre_materia"));
                    idMaterias[nummat]=result.getString("idMateria");
                    nummat=nummat+1;
                }
                
            }
            //return null;
        } catch (SQLException ex) {
            Logger.getLogger(JavaFXApplicationActaMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void BuscarNotas(String periodo,String gestion,String idMateria){   
        try { 
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        try {
            //TODO write your implementation code here:
            Connection con = null;
            
                con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/academico2", "postgres", "root");
            
            
            Statement stmt = con.createStatement();
            String query = "select A.nombre,A.paterno,A.materno,A.ci,I.nota" +
                " from inscripcion as I, alumno as A, materia as M" +
                " where I.idAlumno=A.idAlumno" +
                " and I.idMateria=M.idMateria" +
                " and I.idMateria='" + idMateria + "'"+
                " and I.periodo='" + periodo + "'"+
                " and I.gestion='" + gestion + "'";;
            ResultSet result = stmt.executeQuery(query);
            
            //for ( int i = 0; i<tableview.getItems().size(); i++) {
                tableview.getItems().clear();
            //}
            
            numreg=0;
            while(result.next())
            {
                Registro p1 = new Registro(
                                    result.getString("nombre"),
                                    result.getString("paterno"),
                                    result.getString("materno"),
                                    result.getString("ci"),
                                    result.getString("nota"));

                tableview.getItems().addAll(p1);
                
                registros[numreg][0] = p1.getNombre();
                registros[numreg][1] = p1.getPaterno();
                registros[numreg][2] =  p1.getMaterno();
                registros[numreg][3] = p1.getCi();
                registros[numreg][4] = p1.getNota();
                numreg=numreg+1;
            }
            //return null;
        } catch (SQLException ex) {
            Logger.getLogger(JavaFXApplicationActaMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void ReportePDF() {
        //Crea el documento
        Document documento = new Document();
        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
        FileOutputStream ficheroPdf;
        try {
            ficheroPdf = new FileOutputStream("fichero.pdf");
            // Se asocia el documento al OutputStream
            //se indica que el espaciado entre
            PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
            // Se abre el documento.
            documento.open();
            
            //int x=combobox..getValue();
            String titulo=combobox.getValue().toString();
            //if(x>=0)titulo=idMaterias[x];
                
                
            documento.add(new Paragraph("ACTA DE MATERIAS"));
            documento.add(new Paragraph(titulo,
                                    FontFactory.getFont("arial",   // fuente
                                    22,                            // tamaño
                                    Font.ITALIC,                   // estilo
                                    BaseColor.DARK_GRAY)));             // color

            try {
                Image foto = Image.getInstance("pdf/logo.jpg");
                foto.scaleToFit(100, 100);
                foto.setAlignment(Chunk.ALIGN_MIDDLE);
                documento.add(foto);
            }
            catch ( Exception e )
            {
                    e.printStackTrace();
            }
        
            PdfPTable tablep = new PdfPTable(5);
            tablep.addCell("NOMBRE");
            tablep.addCell("PATERNO");
            tablep.addCell("MATERNO");
            tablep.addCell("CI");
            tablep.addCell("NOTA");

            for ( int i = 0; i<numreg; i++) {
                tablep.addCell(registros[i][0]);
                tablep.addCell(registros[i][1]);
                tablep.addCell(registros[i][2]);
                tablep.addCell(registros[i][3]);
                tablep.addCell(registros[i][4]);
            }
            documento.add(tablep);
                
            documento.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JavaFXApplicationActaMateria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(JavaFXApplicationActaMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
